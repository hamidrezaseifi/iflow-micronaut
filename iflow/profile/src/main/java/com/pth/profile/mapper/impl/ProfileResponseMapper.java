package com.pth.profile.mapper.impl;

import com.pth.common.edo.CompanyProfileEdo;
import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.UserEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.mapper.*;
import com.pth.profile.models.ProfileResponse;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class ProfileResponseMapper extends ModelEdoMapperBase<ProfileResponse, ProfileResponseEdo>
        implements IProfileResponseMapper {

    private final ICompanyMapper companyMapper;
    private final IUserMapper userMapper;
    private final IUserDashboardMenuMapper userDashboardMenuMapper;
    private final ICompanyWorkflowTypeOcrSettingPresetMapper workflowTypeOcrSettingPresetMapper;
    private final ICompanyWorkflowTypeControllerMapper companyWorkflowTypeControllerMapper;
    private final IDepartmentMapper departmentMapper;
    private final IUserGroupMapper userGroupMapper;

    public ProfileResponseMapper(ICompanyMapper companyMapper,
                                 IUserMapper userMapper,
                                 IUserDashboardMenuMapper userDashboardMenuMapper,
                                 ICompanyWorkflowTypeOcrSettingPresetMapper workflowTypeOcrSettingPresetMapper,
                                 ICompanyWorkflowTypeControllerMapper companyWorkflowTypeControllerMapper,
                                 IDepartmentMapper departmentMapper,
                                 IUserGroupMapper userGroupMapper) {
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;
        this.userDashboardMenuMapper = userDashboardMenuMapper;
        this.workflowTypeOcrSettingPresetMapper = workflowTypeOcrSettingPresetMapper;
        this.companyWorkflowTypeControllerMapper = companyWorkflowTypeControllerMapper;
        this.departmentMapper = departmentMapper;
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    public ProfileResponse fromEdo(ProfileResponseEdo edo) {

        return null;
    }

    @Override
    public ProfileResponseEdo toEdo(ProfileResponse model) {
        ProfileResponseEdo edo = new ProfileResponseEdo();
        edo.setSessionid(model.getToken());
        edo.setUser(userMapper.toEdo(model.getUser()));
        edo.setUserDashboardMenus(userDashboardMenuMapper.toEdoList(model.getUserDashboardMenus()));
        edo.setCompanyProfile(new CompanyProfileEdo());
        edo.getCompanyProfile().setCompany(companyMapper.toEdo(model.getCompanyProfile().getCompany()));
        edo.getCompanyProfile().setDepartments(departmentMapper.toEdoList(model.getCompanyProfile().getDepartments()));
        edo.getCompanyProfile().setOcrPresets(
                workflowTypeOcrSettingPresetMapper.toEdoList(model.getCompanyProfile().getOcrPresetSettings()));
        edo.getCompanyProfile().setUserGroups(userGroupMapper.toEdoList(model.getCompanyProfile().getUserGroups()));
        edo.getCompanyProfile().setWorkflowTypeControllers(
                companyWorkflowTypeControllerMapper.toEdoList(model.getCompanyProfile().getWorkflowTypeControllers()));

        return edo;
    }

}
