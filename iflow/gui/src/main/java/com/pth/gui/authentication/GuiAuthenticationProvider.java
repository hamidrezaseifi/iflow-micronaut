package com.pth.gui.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pth.common.clients.profile.IProfileClient;
import com.pth.common.clients.profile.IUserClient;
import com.pth.common.clients.workflow.IWorkflowTypeClient;
import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.WorkflowTypeListEdo;
import com.pth.common.edo.enums.EApplication;
import com.pth.gui.mapper.*;
import com.pth.gui.models.*;
import com.pth.gui.models.gui.UiMenuItem;
import com.pth.gui.models.gui.uisession.*;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.services.IGuiMenuService;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class GuiAuthenticationProvider implements AuthenticationProvider {

    private final IProfileClient profileClient;
    private final IUserClient userClient;
    private  final IWorkflowTypeClient workflowTypeClient;

    private final IUserMapper userMapper;
    private final ICompanyMapper companyMapper;
    private final ICompanyWorkflowTypeOcrSettingPresetMapper companyWorkflowTypeOcrSettingPresetMapper;
    private final IDepartmentMapper departmentMapper;
    private final IUserGroupMapper userGroupMapper;
    private final IUserDashboardMenuMapper dashboardMenuMapper;
    private final IWorkflowTypeMapper workflowTypeMapper;

    private final IGuiMenuService menuService;

    private final ObjectMapper objectMapper;

    public GuiAuthenticationProvider(IProfileClient profileClient,
                                     IUserClient userClient,
                                     IWorkflowTypeClient workflowTypeClient,
                                     IUserMapper userMapper,
                                     ICompanyMapper companyMapper,
                                     ICompanyWorkflowTypeOcrSettingPresetMapper companyWorkflowTypeOcrSettingPresetMapper,
                                     IDepartmentMapper departmentMapper,
                                     IUserGroupMapper userGroupMapper,
                                     IUserDashboardMenuMapper dashboardMenuMapper,
                                     IWorkflowTypeMapper workflowTypeMapper,
                                     IGuiMenuService menuService,
                                     ObjectMapper objectMapper) {

        this.profileClient = profileClient;
        this.userClient = userClient;
        this.workflowTypeClient = workflowTypeClient;

        this.userMapper = userMapper;
        this.companyMapper = companyMapper;
        this.companyWorkflowTypeOcrSettingPresetMapper = companyWorkflowTypeOcrSettingPresetMapper;
        this.departmentMapper = departmentMapper;
        this.userGroupMapper = userGroupMapper;
        this.dashboardMenuMapper = dashboardMenuMapper;
        this.workflowTypeMapper = workflowTypeMapper;

        this.menuService = menuService;

        this.objectMapper = objectMapper;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {

        String username = authenticationRequest.getIdentity().toString();
        String password = authenticationRequest.getSecret().toString();

        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username,
                                                                                                  password);

        Optional<BearerAccessRefreshToken> accessRefreshToken = this.profileClient.postLogin(usernamePasswordCredentials);
        if(accessRefreshToken.isPresent()){
            BearerAccessRefreshToken bearerAccessRefreshToken = accessRefreshToken.get();

            String refreshToken = bearerAccessRefreshToken.getRefreshToken();
            Optional<SessionData> sessionDataOptional = gerSessionData(bearerAccessRefreshToken);

            if(sessionDataOptional.isPresent()){
                SessionData sessionData = sessionDataOptional.get();

                Collection<String> roles = bearerAccessRefreshToken.getRoles();

                Map<String, Object> attributes = new HashMap<>();
                attributes.put("access_token" , bearerAccessRefreshToken.getAccessToken());
                attributes.put("refresh_token" , refreshToken);
                attributes.put("expires_in" , bearerAccessRefreshToken.getExpiresIn());
                //attributes.put("user-id" , userId);
                //attributes.put("company-id" , companyId);
                attributes.put("session-data" , sessionData);

                UserDetails userDetails = new UserDetails(bearerAccessRefreshToken.getUsername(),
                                                          roles,
                                                          attributes);

                return Flowable.just(userDetails);
            }





        }
        return Flowable.just(new AuthenticationFailed());
    }

    private Optional<SessionData> gerSessionData(BearerAccessRefreshToken bearerAccessRefreshToken){
        SessionData sessionData = new SessionData();

        Optional<ProfileResponseEdo> profileResponseEdoOptional =
                this.userClient.readUserProfileByUsername(bearerAccessRefreshToken.getRefreshToken(),
                                                    EApplication.IFLOW.getIdentity(),
                                                          bearerAccessRefreshToken.getUsername());

        if(profileResponseEdoOptional.isPresent()){
            ProfileResponseEdo profileResponseEdo = profileResponseEdoOptional.get();

            sessionData.setLogged(true);
            sessionData.setCurrentUser(this.userMapper.fromEdo(profileResponseEdo.getUser()));

            List<UserDashboardMenu> dashboardMenuList =
                    this.dashboardMenuMapper.fromEdoList(profileResponseEdo.getUserDashboardMenus());
            Company company = this.companyMapper.fromEdo(profileResponseEdo.getCompanyProfile().getCompany());
            List<Department> departments =
                    this.departmentMapper.fromEdoList(profileResponseEdo.getCompanyProfile().getDepartments());
            List<UserGroup> userGroups =
                    this.userGroupMapper.fromEdoList(profileResponseEdo.getCompanyProfile().getUserGroups());

            List<UiMenuItem> menuItemList = this.menuService.getAllMenus();
            List<List<UserDashboardMenu>> preparedDashboardMenuList =
                    DashboardSessionData.getPreparedUserDashboardMenus(dashboardMenuList, menuItemList);

            sessionData.setCompanySessionData(new CompanySessionData(company, departments, userGroups));
            sessionData.setAppSessionData(
                    new AppSessionData(menuItemList, new DashboardSessionData(preparedDashboardMenuList)));

            Optional<WorkflowTypeListEdo> workflowTypeListEdoOptional =
                    this.workflowTypeClient.readByCompanyId(bearerAccessRefreshToken.getRefreshToken(),
                                                            profileResponseEdo.getCompanyProfile().getCompany().getId());
            if(workflowTypeListEdoOptional.isPresent()){
                WorkflowTypeListEdo workflowTypeListEdo = workflowTypeListEdoOptional.get();
                List<WorkflowType> workflowTypes =
                        this.workflowTypeMapper.fromEdoList(workflowTypeListEdo.getWorkflowTypes());
                sessionData.setWorkflowSessionData(new WorkflowSessionData(workflowTypes));
            }

            return Optional.of(sessionData);
        }

        return Optional.empty();
    }
}
