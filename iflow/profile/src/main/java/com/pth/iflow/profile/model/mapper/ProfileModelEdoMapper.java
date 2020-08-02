package com.pth.iflow.profile.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.enums.EWorkflowMessageType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.edo.CompanyEdo;
import com.pth.iflow.common.models.edo.CompanyProfileEdo;
import com.pth.iflow.common.models.edo.CompanyWorkflowTypeControllerEdo;
import com.pth.iflow.common.models.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.iflow.common.models.edo.CompanyWorkflowtypeItemOcrSettingPresetItemEdo;
import com.pth.iflow.common.models.edo.DepartmentEdo;
import com.pth.iflow.common.models.edo.ProfileResponseEdo;
import com.pth.iflow.common.models.edo.UserAuthenticationRequestEdo;
import com.pth.iflow.common.models.edo.UserAuthenticationResponseEdo;
import com.pth.iflow.common.models.edo.UserDashboardMenuEdo;
import com.pth.iflow.common.models.edo.UserDepartmentEdo;
import com.pth.iflow.common.models.edo.UserEdo;
import com.pth.iflow.common.models.edo.UserGroupEdo;
import com.pth.iflow.common.models.edo.UserPasswordChangeRequestEdo;
import com.pth.iflow.common.models.edo.WorkflowMessageEdo;
import com.pth.iflow.profile.model.Company;
import com.pth.iflow.profile.model.CompanyProfile;
import com.pth.iflow.profile.model.CompanyWorkflowTypeController;
import com.pth.iflow.profile.model.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.iflow.profile.model.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.iflow.profile.model.Department;
import com.pth.iflow.profile.model.ProfileResponse;
import com.pth.iflow.profile.model.User;
import com.pth.iflow.profile.model.UserAuthenticationRequest;
import com.pth.iflow.profile.model.UserAuthenticationSession;
import com.pth.iflow.profile.model.UserDashboardMenu;
import com.pth.iflow.profile.model.UserDepartment;
import com.pth.iflow.profile.model.UserGroup;
import com.pth.iflow.profile.model.UserPasswordChangeRequest;
import com.pth.iflow.profile.model.WorkflowMessage;

public class ProfileModelEdoMapper {

  private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

  public static CompanyEdo toEdo(final Company model) {

    final CompanyEdo edo = new CompanyEdo();
    edo.setCompanyName(model.getCompanyName());
    edo.setIdentity(model.getIdentity());
    edo.setStatus(model.getStatus());
    edo.setVersion(model.getVersion());
    edo.setCompanyType(model.getCompanyType());
    edo.setCompanyTypeCustome(model.getCompanyTypeCustome());

    return edo;
  }

  public static Company fromEdo(final CompanyEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final Company model = new Company();
    model.setCompanyName(edo.getCompanyName());
    model.setIdentity(edo.getIdentity());
    model.setStatus(edo.getStatus());
    model.setVersion(edo.getVersion());
    model.setCompanyType(edo.getCompanyType());
    model.setCompanyTypeCustome(edo.getCompanyTypeCustome());

    return model;
  }

  public static UserPasswordChangeRequest fromEdo(final UserPasswordChangeRequestEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final UserPasswordChangeRequest model = new UserPasswordChangeRequest();
    model.setIdentity(edo.getIdentity());
    model.setPassword(edo.getPassword());
    model.setCompanyIdentity(edo.getCompanyIdentity());

    return model;
  }

  public static WorkflowMessage fromEdo(final WorkflowMessageEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final WorkflowMessage model = new WorkflowMessage();
    model.setStatus(EWorkflowMessageStatus.ofValue(edo.getStatus()));
    model.setUserIdentity(edo.getUserIdentity());
    model.setCreatedByIdentity(edo.getCreatedByIdentity());
    model.setVersion(edo.getVersion());
    model.setWorkflowIdentity(edo.getWorkflowIdentity());
    model.setMessageType(EWorkflowMessageType.ofValue(edo.getMessageType()));
    model.setExpireDays(edo.getExpireDays());
    model.setMessage(edo.getMessage());
    model.setStepIdentity(edo.getStepIdentity());
    model.setCreatedAt(edo.getCreatedAt());

    return model;
  }

  public static WorkflowMessageEdo toEdo(final WorkflowMessage model) {

    final WorkflowMessageEdo edo = new WorkflowMessageEdo();
    edo.setStatus(model.getStatus().getValue());
    edo.setUserIdentity(model.getUserIdentity());
    edo.setCreatedByIdentity(model.getCreatedByIdentity());
    edo.setVersion(model.getVersion());
    edo.setWorkflowIdentity(model.getWorkflowIdentity());
    edo.setMessageType(model.getMessageType().getValue());
    edo.setExpireDays(model.getExpireDays());
    edo.setMessage(model.getMessage());
    edo.setCreatedAt(model.getCreatedAt());
    edo.setStepIdentity(model.getStepIdentity());

    return edo;
  }

  public static DepartmentEdo toEdo(final Department model) {

    final DepartmentEdo edo = new DepartmentEdo();
    edo.setTitle(model.getTitle());
    edo.setStatus(model.getStatus());
    edo.setIdentity(model.getIdentity());
    edo.setVersion(model.getVersion());
    edo.setCompanyIdentity(model.getCompanyIdentity());

    return edo;
  }

  public static Department fromEdo(final DepartmentEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final Department model = new Department();

    model.setTitle(edo.getTitle());
    model.setStatus(edo.getStatus());
    model.setIdentity(edo.getIdentity());
    model.setVersion(edo.getVersion());
    model.setCompanyIdentity(edo.getCompanyIdentity());

    return model;
  }

  public static UserAuthenticationResponseEdo toEdo(final UserAuthenticationSession model) {

    final UserAuthenticationResponseEdo edo = new UserAuthenticationResponseEdo();
    edo.setSessionid(model.getSessionid());
    edo.setToken(model.getToken());
    edo.setUserIdentity(model.getUserIdentity());
    edo.setCreated(model.getCreatedLong());
    edo.setLastAccess(model.getLastAccessLong());
    edo.setCompanyIdentity(model.getCompanyIdentity());

    return edo;
  }

  public static UserAuthenticationRequestEdo toEdo(final UserAuthenticationRequest model) {

    final UserAuthenticationRequestEdo edo = new UserAuthenticationRequestEdo();
    edo.setCompanyIdentity(model.getCompanyIdentity());
    edo.setUserIdentity(model.getUserIdentity());
    edo.setPassword(model.getPassword());
    edo.setAppId(model.getAppId());

    return edo;
  }

  public static UserAuthenticationRequest fromEdo(final UserAuthenticationRequestEdo edo) {

    final UserAuthenticationRequest request = new UserAuthenticationRequest();

    request.setCompanyIdentity(edo.getCompanyIdentity());
    request.setUserIdentity(edo.getUserIdentity());
    request.setPassword(edo.getPassword());
    request.setAppId(edo.getAppId());

    return request;
  }

  public static UserEdo toEdo(final User model) {

    final UserEdo edo = new UserEdo();
    edo.setFirstName(model.getFirstName());
    edo.setLastName(model.getLastName());
    edo.setPermission(model.getPermission());
    edo.setStatus(model.getStatus());
    edo.setVersion(model.getVersion());
    edo.setEmail(model.getEmail());
    edo.setBirthDate(model.getBirthDate());
    edo.setCompanyIdentity(model.getCompanyIdentity());
    edo.setGroups(model.getGroups());
    edo
        .setUserDepartments(
            model
                .getUserDepartments()
                .stream()
                .map(d -> new UserDepartmentEdo(d.getDepartmentIdentity(), d.getMemberType().getValue()))
                .collect(Collectors.toList()));

    edo.setDeputies(model.getDeputies());
    edo.setRoles(model.getRoles());
    edo.setIdentity(model.getIdentity());

    return edo;
  }

  public static User fromEdo(final UserEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final User model = new User();

    model.setFirstName(edo.getFirstName());
    model.setLastName(edo.getLastName());
    model.setPermission(edo.getPermission());
    model.setStatus(edo.getStatus());
    model.setVersion(edo.getVersion());
    model.setEmail(edo.getEmail());
    model.setBirthDate(edo.getBirthDate());
    model.setCompanyIdentity(edo.getCompanyIdentity());
    model.setGroups(edo.getGroups());
    model
        .setUserDepartments(edo
            .getUserDepartments()
            .stream()
            .map(d -> new UserDepartment(d.getDepartmentIdentity(), d.getMemberType()))
            .collect(Collectors.toList()));

    model.setDeputies(edo.getDeputies());
    model.setRoles(edo.getRoles());
    model.setIdentity(edo.getIdentity());

    return model;
  }

  public static UserGroupEdo toEdo(final UserGroup model) {

    final UserGroupEdo edo = new UserGroupEdo();
    edo.setTitle(model.getTitle());
    edo.setStatus(model.getStatus());
    edo.setIdentity(model.getIdentity());
    edo.setVersion(model.getVersion());
    edo.setIdentity(model.getIdentity());
    edo.setCompanyIdentity(model.getCompanyIdentity());

    return edo;
  }

  public static UserGroup fromEdo(final UserGroupEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final UserGroup model = new UserGroup();

    model.setTitle(edo.getTitle());
    model.setStatus(edo.getStatus());
    model.setIdentity(edo.getIdentity());
    model.setVersion(edo.getVersion());
    model.setIdentity(edo.getIdentity());
    model.setCompanyIdentity(edo.getCompanyIdentity());

    return model;
  }

  public static List<UserGroupEdo> toUserGroupEdoList(final List<UserGroup> modelList) {

    final List<UserGroupEdo> edoList = new ArrayList<>();
    if (modelList != null) {

      for (final UserGroup model : modelList) {
        edoList.add(toEdo(model));
      }
    }
    return edoList;
  }

  public static List<DepartmentEdo> toDepartmentEdoList(final List<Department> modelList) {

    final List<DepartmentEdo> edoList = new ArrayList<>();
    if (modelList != null) {
      for (final Department model : modelList) {
        edoList.add(toEdo(model));
      }
    }

    return edoList;
  }

  public static List<WorkflowMessageEdo> toWorkflowMessageEdoList(final List<WorkflowMessage> modelList) {

    final List<WorkflowMessageEdo> edoList = new ArrayList<>();
    if (modelList != null) {
      for (final WorkflowMessage model : modelList) {
        edoList.add(toEdo(model));
      }
    }

    return edoList;
  }

  public static List<WorkflowMessage> fromWorkflowMessageEdoList(final List<WorkflowMessageEdo> edoList)
      throws IFlowMessageConversionFailureException {

    final List<WorkflowMessage> modelList = new ArrayList<>();
    if (modelList != null) {
      for (final WorkflowMessageEdo edo : edoList) {
        modelList.add(fromEdo(edo));
      }
    }

    return modelList;
  }

  public static List<UserEdo> toUserEdoList(final List<User> modelList) {

    final List<UserEdo> edoList = new ArrayList<>();
    if (modelList != null) {
      for (final User model : modelList) {
        edoList.add(toEdo(model));
      }
    }
    return edoList;
  }

  private static <E> void validateCustomer(final E model) throws IFlowMessageConversionFailureException {

    final Set<ConstraintViolation<E>> violations = VALIDATOR.validate(model);
    if (violations != null && violations.size() > 0) {
      final String validationErrorMessage = createValidationErrorMessage(violations);
      throw new IFlowMessageConversionFailureException(validationErrorMessage);
    }
  }

  private static <E> String createValidationErrorMessage(final Set<ConstraintViolation<E>> violations) {

    final StringBuilder builder = new StringBuilder();
    builder.append("There are errors in the received XML:");
    builder.append(System.lineSeparator());
    for (final ConstraintViolation<E> violation : violations) {
      builder.append("Error: ");
      builder.append(violation.getMessage());
      builder.append(": ");
      builder.append(violation.getInvalidValue());
      builder.append(System.lineSeparator());
    }
    return builder.toString();
  }

  public static CompanyProfileEdo toEdo(final CompanyProfile model) {

    final CompanyProfileEdo edo = new CompanyProfileEdo(toEdo(model.getCompany()), toDepartmentEdoList(model.getDepartments()),
        toUserGroupEdoList(model.getUserGroups()), toCompanyWorkflowTypeControllerEdoList(model.getWorkflowTypeControllers()),
        toCompanyWorkflowtypeItemOcrSettingPresetEdoList(model.getOcrPresets()));

    return edo;
  }

  public static ProfileResponseEdo toEdo(final ProfileResponse model) {

    return new ProfileResponseEdo(toEdo(model.getUser()), toEdo(model.getCompanyProfile()), model.getSessionid(),
        toUserDashboardMenuEdoList(model.getUserDashboardMenus()));
  }

  public static ProfileResponse fromEdo(final ProfileResponseEdo edo) throws IFlowMessageConversionFailureException {

    return new ProfileResponse(fromEdo(edo.getUser()), fromEdo(edo.getCompanyProfile()), edo.getSessionid(),
        fromUserDashboardMenuEdoList(edo.getUserDashboardMenus()));
  }

  private static UserDashboardMenuEdo toUserDashboardMenuEdo(final UserDashboardMenu model) {

    final UserDashboardMenuEdo edo = new UserDashboardMenuEdo();
    edo.setUserIdentity(model.getUserIdentity());
    edo.setColumnIndex(model.getColumnIndex());
    edo.setAppId(model.getAppId());
    edo.setMenuId(model.getMenuId());
    edo.setRowIndex(model.getRowIndex());
    edo.setStatus(model.getStatus());
    edo.setVersion(model.getVersion());

    return edo;
  }

  public static List<UserDashboardMenuEdo> toUserDashboardMenuEdoList(final List<UserDashboardMenu> modelList) {

    final List<UserDashboardMenuEdo> edoList = new ArrayList<>();
    for (final UserDashboardMenu model : modelList) {
      edoList.add(toUserDashboardMenuEdo(model));
    }

    return edoList;
  }

  private static UserDashboardMenu fromUserDashboardMenuEdo(final UserDashboardMenuEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final UserDashboardMenu model = new UserDashboardMenu();
    model.setUserIdentity(edo.getUserIdentity());
    model.setColumnIndex(edo.getColumnIndex());
    model.setAppId(edo.getAppId());
    model.setMenuId(edo.getMenuId());
    model.setRowIndex(edo.getRowIndex());
    model.setStatus(edo.getStatus());
    model.setVersion(edo.getVersion());

    return model;
  }

  public static List<UserDashboardMenu> fromUserDashboardMenuEdoList(final List<UserDashboardMenuEdo> edoList)
      throws IFlowMessageConversionFailureException {

    final List<UserDashboardMenu> modelList = new ArrayList<>();
    for (final UserDashboardMenuEdo edo : edoList) {
      modelList.add(fromUserDashboardMenuEdo(edo));
    }

    return modelList;
  }

  public static CompanyProfile fromEdo(final CompanyProfileEdo edo) throws IFlowMessageConversionFailureException {

    validateCustomer(edo);

    final CompanyProfile model = new CompanyProfile(fromEdo(edo.getCompany()), fromDepartmentEdoList(edo.getDepartments()),
        fromUserGroupEdoList(edo.getUserGroups()), fromCompanyWorkflowTypeControllerEdoList(edo.getWorkflowTypeControllers()),
        fromCompanyWorkflowtypeItemOcrSettingPresetEdoList(edo.getOcrPresets()));

    return model;
  }

  public static List<CompanyWorkflowTypeControllerEdo>
      toCompanyWorkflowTypeControllerEdoList(final List<CompanyWorkflowTypeController> modelList) {

    final List<CompanyWorkflowTypeControllerEdo> edoList = new ArrayList<>();
    for (final CompanyWorkflowTypeController model : modelList) {
      edoList.add(toEdo(model));
    }

    return edoList;
  }

  public static CompanyWorkflowtypeItemOcrSettingPresetEdo
      toCompanyWorkflowtypeItemOcrSettingPresetEdo(final CompanyWorkflowtypeItemOcrSettingPreset model) {

    final CompanyWorkflowtypeItemOcrSettingPresetEdo edo = new CompanyWorkflowtypeItemOcrSettingPresetEdo();
    edo.setPresetName(model.getPresetName());
    edo.setStatus(model.getStatus());
    edo.setVersion(model.getVersion());
    edo.setWorkflowTypeIdentity(model.getWorkflowTypeIdentity());
    edo.setCompanyIdentity(model.getCompanyIdentity());
    edo.setItems(toCompanyWorkflowtypeItemOcrSettingPresetItemEdoList(model.getItems()));
    edo.setIdentity(model.getIdentity());

    return edo;
  }

  public static CompanyWorkflowtypeItemOcrSettingPresetItemEdo
      toCompanyWorkflowtypeItemOcrSettingPresetItemEdo(final CompanyWorkflowtypeItemOcrSettingPresetItem model) {

    final CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo = new CompanyWorkflowtypeItemOcrSettingPresetItemEdo();
    edo.setPropertyName(model.getPropertyName());
    edo.setStatus(model.getStatus());
    edo.setVersion(model.getVersion());
    edo.setValue(model.getValue());
    edo.setOcrType(model.getOcrType());

    return edo;
  }

  public static CompanyWorkflowtypeItemOcrSettingPresetItem
      fromCompanyWorkflowtypeItemOcrSettingPresetItemEdo(final CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo) {

    final CompanyWorkflowtypeItemOcrSettingPresetItem model = new CompanyWorkflowtypeItemOcrSettingPresetItem();
    model.setPropertyName(edo.getPropertyName());
    model.setStatus(edo.getStatus());
    model.setVersion(edo.getVersion());
    model.setValue(edo.getValue());
    model.setOcrType(edo.getOcrType());

    return model;
  }

  public static CompanyWorkflowtypeItemOcrSettingPreset
      fromCompanyWorkflowtypeItemOcrSettingPresetEdo(final CompanyWorkflowtypeItemOcrSettingPresetEdo edo) {

    final CompanyWorkflowtypeItemOcrSettingPreset model = new CompanyWorkflowtypeItemOcrSettingPreset();
    model.setPresetName(edo.getPresetName());
    model.setStatus(edo.getStatus());
    model.setVersion(edo.getVersion());
    model.setWorkflowTypeIdentity(edo.getWorkflowTypeIdentity());
    model.setCompanyIdentity(edo.getCompanyIdentity());
    model.setItems(fromCompanyWorkflowtypeItemOcrSettingPresetItemEdoList(edo.getItems()));
    model.setIdentity(edo.getIdentity());

    return model;
  }

  public static List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo>
      toCompanyWorkflowtypeItemOcrSettingPresetItemEdoList(final List<CompanyWorkflowtypeItemOcrSettingPresetItem> modelList) {

    final List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> edoList = new ArrayList<>();

    for (final CompanyWorkflowtypeItemOcrSettingPresetItem model : modelList) {
      edoList.add(toCompanyWorkflowtypeItemOcrSettingPresetItemEdo(model));
    }

    return edoList;
  }

  public static List<CompanyWorkflowtypeItemOcrSettingPresetItem>
      fromCompanyWorkflowtypeItemOcrSettingPresetItemEdoList(final List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> edoList) {

    final List<CompanyWorkflowtypeItemOcrSettingPresetItem> modelList = new ArrayList<>();

    for (final CompanyWorkflowtypeItemOcrSettingPresetItemEdo edo : edoList) {
      modelList.add(fromCompanyWorkflowtypeItemOcrSettingPresetItemEdo(edo));
    }

    return modelList;
  }

  public static List<CompanyWorkflowtypeItemOcrSettingPresetEdo>
      toCompanyWorkflowtypeItemOcrSettingPresetEdoList(final List<CompanyWorkflowtypeItemOcrSettingPreset> modelList) {

    final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList = new ArrayList<>();

    for (final CompanyWorkflowtypeItemOcrSettingPreset model : modelList) {
      edoList.add(toCompanyWorkflowtypeItemOcrSettingPresetEdo(model));
    }

    return edoList;
  }

  public static List<CompanyWorkflowtypeItemOcrSettingPreset>
      fromCompanyWorkflowtypeItemOcrSettingPresetEdoList(final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList) {

    final List<CompanyWorkflowtypeItemOcrSettingPreset> modelList = new ArrayList<>();

    for (final CompanyWorkflowtypeItemOcrSettingPresetEdo edo : edoList) {
      modelList.add(fromCompanyWorkflowtypeItemOcrSettingPresetEdo(edo));
    }

    return modelList;
  }

  public static List<CompanyWorkflowTypeController> fromCompanyWorkflowTypeControllerEdoList(
      final List<CompanyWorkflowTypeControllerEdo> edoList) throws IFlowMessageConversionFailureException {

    final List<CompanyWorkflowTypeController> modelList = new ArrayList<>();
    for (final CompanyWorkflowTypeControllerEdo edo : edoList) {
      modelList.add(fromEdo(edo));
    }

    return modelList;
  }

  private static CompanyWorkflowTypeController fromEdo(final CompanyWorkflowTypeControllerEdo edo) {

    final CompanyWorkflowTypeController model = new CompanyWorkflowTypeController(edo.getWorkflowTypeIdentity(), edo.getUserIdentity(),
        edo.getPriority());
    return model;
  }

  private static CompanyWorkflowTypeControllerEdo toEdo(final CompanyWorkflowTypeController model) {

    final CompanyWorkflowTypeControllerEdo edo = new CompanyWorkflowTypeControllerEdo(model.getWorkflowTypeIdentity(),
        model.getUserIdentity(),
        model.getPriority());
    return edo;
  }

  public static List<User> fromUserEdoList(final List<UserEdo> edoList) throws IFlowMessageConversionFailureException {

    final List<User> modelList = new ArrayList<>();
    if (edoList != null) {
      for (final UserEdo edo : edoList) {
        modelList.add(fromEdo(edo));
      }
    }
    return modelList;
  }

  public static List<Department> fromDepartmentEdoList(final List<DepartmentEdo> edoList)
      throws IFlowMessageConversionFailureException {

    final List<Department> modelList = new ArrayList<>();
    if (edoList != null) {
      for (final DepartmentEdo edo : edoList) {
        modelList.add(fromEdo(edo));
      }
    }

    return modelList;
  }

  public static List<UserGroup> fromUserGroupEdoList(final List<UserGroupEdo> edoList) throws IFlowMessageConversionFailureException {

    final List<UserGroup> modelList = new ArrayList<>();
    if (edoList != null) {
      for (final UserGroupEdo edo : edoList) {
        modelList.add(fromEdo(edo));
      }
    }
    return modelList;
  }

}
