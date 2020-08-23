package com.pth.workflow.services.bl.strategy.strategies;

import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.entities.WorkflowMessageEntity;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.User;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.repositories.IWorkflowBaseRepository;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import com.pth.workflow.services.bl.IDepartmentDataService;
import com.pth.workflow.services.bl.IGuiCachDataDataService;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategy;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyStep;

import java.util.*;
import java.util.stream.Collectors;



public abstract class AbstractWorkflowSaveStrategy<W extends IWorkflowBaseEntity> implements IWorkflowSaveStrategy<W> {

  private final IDepartmentDataService departmentDataService;
  private final IWorkflowMessageRepository workflowMessageRepository;
  private final IGuiCachDataDataService profileCachDataDataService;
  private final IWorkflowBaseRepository<W> workflowRepository;
  private final IWorkflowPrepare<W> workflowPrepare;

  protected final IWorkflowSaveRequest<W> processingWorkflowSaveRequest;
  protected final WorkflowActionEntity prevActiveAction;
  protected final Optional<W> existsingWorkflowOptional;

  protected final List<IWorkflowSaveStrategyStep> steps = new ArrayList<>();

  protected final Map<UUID, W> savedWorkflowList = new HashMap<>();

  protected Optional<W> savedSingleWorkflowOptional = Optional.empty();

  private final Set<UUID> assignedUsersId = new HashSet<>();

  protected final String authorization;

  public AbstractWorkflowSaveStrategy(final IWorkflowSaveRequest<W> workflowCreateRequest,
                                      final String authorization,
                                      final IDepartmentDataService departmentDataService,
                                      final IWorkflowMessageRepository workflowMessageRepository,
                                      final IGuiCachDataDataService profileCachDataDataService,
                                      final IWorkflowBaseRepository<W> workflowRepository,
                                      final IWorkflowPrepare<W> workflowPrepare)
      throws WorkflowCustomizedException {

    this.authorization = authorization;

    this.departmentDataService = departmentDataService;
    this.workflowMessageRepository = workflowMessageRepository;
    this.workflowRepository = workflowRepository;
    this.profileCachDataDataService = profileCachDataDataService;
    this.workflowPrepare = workflowPrepare;

    this.processingWorkflowSaveRequest = workflowCreateRequest;
    this.prevActiveAction = workflowCreateRequest.getWorkflow().getActiveAction();


    if(workflowRepository.isWorkflowNew(processingWorkflowSaveRequest.getWorkflow().getWorkflowId())){
      this.existsingWorkflowOptional = Optional.empty();
    }
    else{
      Optional<W> workflowOptional = workflowRepository.getByWorkflowId(getProcessingWorkflow().getWorkflowId());
      if(workflowOptional.isPresent()){
        this.existsingWorkflowOptional = workflowOptional;
      }
      else{
        this.existsingWorkflowOptional = Optional.empty();
      }
    }

    this.setup();
  }

  public String getAuthorization() {
    return authorization;
  }

  public IDepartmentDataService getDepartmentDataService() {

    return departmentDataService;
  }

  public IWorkflowMessageRepository getWorkflowMessageRepository() {

    return workflowMessageRepository;
  }

  public void createWorkflowMessage(final W workflow,
                                    final UUID userId,
                                    final EWorkflowMessageType messageType,
                                    final EWorkflowMessageStatus messageStatus){

    final WorkflowMessageEntity message = new WorkflowMessageEntity();
    message.setCreatedById(workflow.getCreatedById());
    message.setExpireDays(this.processingWorkflowSaveRequest.getExpireDays());
    message.setMessage("Offering Workflow Message");
    message.setMessageType(messageType.getValue());
    message.setStatus(messageStatus.getValue());
    message.setUserId(userId);
    message.setWorkflowId(workflow.getWorkflowId());
    message.setStepId(workflow.getCurrentStepId());
    message.setVersion(1);
    getWorkflowMessageRepository().save(message);

    // EWorkflowMessageType.OFFERING_WORKFLOW
    // EWorkflowMessageStatus.OFFERING
  }

  public void updateWorkflowMessageStatus(final UUID workflowID,
                                          final UUID stepId,
                                          final EWorkflowMessageStatus status) {

    workflowMessageRepository.updateWorkflowMessageStatus(workflowID, stepId, status);
  }

  public void updateUserAndWorkflowMessageStatus(final UUID workflowID,
                                                 final UUID stepId,
                                                 final UUID userId,
                                                 final EWorkflowMessageStatus status){

    workflowMessageRepository.updateWorkflowMessageStatus(workflowID, stepId, userId, status);
  }

  public void resetUserListCachData(final UUID companyId, final Set<UUID> userIdList){

    profileCachDataDataService.resetCachDataForUserList(companyId, userIdList, authorization);
  }

  public void resetWorkflowtCachData(final UUID companyId, final UUID workflowId){

    profileCachDataDataService.resetCachDataForWorkflow(companyId, workflowId, authorization);
  }

  public Optional<W> saveWorkflow(final W workflow)
      throws WorkflowCustomizedException {

    this.workflowRepository.save(workflow);
    final Optional<W> savedWorkflowOptional = this.workflowRepository.getByWorkflowId(workflow.getWorkflowId());

    if(savedWorkflowOptional.isPresent()){
      return prepareWorkflow(savedWorkflowOptional.get());
    }
    return Optional.empty();
  }

  public WorkflowTypeStepEntity findFirstStep(final WorkflowTypeEntity workflowType) {

    final List<WorkflowTypeStepEntity> steps = this.getSortedStepsList(workflowType);

    return steps.size() > 0 ? steps.get(0) : null;
  }

  private Map<Integer, WorkflowTypeStepEntity> getIndexKeySteps(final WorkflowTypeEntity workflowType) {

    final Map<Integer,
        WorkflowTypeStepEntity> map = workflowType.getSteps().stream().collect(Collectors.toMap(step -> step.getStepIndex(), step -> step));

    return map;
  }

  private List<Integer> getSortedStepIndexs(final WorkflowTypeEntity workflowType) {

    final List<Integer> list = workflowType.getSteps().stream().map(step -> step.getStepIndex()).sorted().collect(Collectors.toList());

    return list;
  }

  public WorkflowTypeStepEntity findNextStep(final WorkflowTypeEntity workflowType, final W workflow) {

    final WorkflowTypeStepEntity currectStep = workflow.getLastAction().getCurrentStep();

    final Map<Integer, WorkflowTypeStepEntity> steps = this.getIndexKeySteps(workflowType);
    final List<Integer> sortedIndexes = getSortedStepIndexs(workflowType);

    final int stepIndexInList = sortedIndexes.indexOf(currectStep.getStepIndex());
    int nextStepIndexList = stepIndexInList + 1;
    nextStepIndexList = stepIndexInList < sortedIndexes.size() - 1 ? stepIndexInList + 1 : stepIndexInList;
    final Integer nextStepIndex = sortedIndexes.get(nextStepIndexList);

    return steps.get(nextStepIndex);

  }

  public boolean isLastStep(final WorkflowTypeEntity workflowType, final WorkflowTypeStepEntity step) {

    final WorkflowTypeStepEntity lastStep = this.findLastStep(workflowType);

    return step.getStepIndex() == lastStep.getStepIndex();
  }

  private WorkflowTypeStepEntity findLastStep(final WorkflowTypeEntity workflowType) {

    final List<WorkflowTypeStepEntity> steps = this.getSortedStepsList(workflowType);

    return steps.size() > 0 ? steps.get(steps.size() - 1) : null;
  }

  private List<WorkflowTypeStepEntity> getSortedStepsList(final WorkflowTypeEntity workflowType) {

    final List<WorkflowTypeStepEntity> list = workflowType.getSteps().stream().sorted(new Comparator<WorkflowTypeStepEntity>() {

      @Override
      public int compare(final WorkflowTypeStepEntity o1, final WorkflowTypeStepEntity o2) {

        return o1.getStepIndex() > o2.getStepIndex() ? 1 : o1.getStepIndex() == o2.getStepIndex() ? 0 : -1;
      }
    }).collect(Collectors.toList());

    return list;
  }

  public WorkflowTypeStepEntity findWorkflowStepinWorkflowTypeById(final WorkflowTypeEntity workflowType, final String stepIdentity) {

    WorkflowTypeStepEntity foundStep = null;
    for (final WorkflowTypeStepEntity step : workflowType.getSteps()) {
      if (step.getIdentity() == stepIdentity) {
        foundStep = step;
        break;
      }
    }
    return foundStep;
  }

  public void validateCurrentStepExistsInWorkflowType(final W newWorkflow, final WorkflowTypeEntity workflowType) {

    final List<String> stepIdList = this.getWorkflowTypeIdList(workflowType);

    if (stepIdList.contains(newWorkflow.getCurrentStep().getIdentity()) == false) {
      throw new WorkflowCustomizedException("Invalid workflow step id:" + newWorkflow.getIdentity(), EIFlowErrorType.INVALID_WORKFLOW_STEP);
    }
  }

  private List<String> getWorkflowTypeIdList(final WorkflowTypeEntity workflowType) {

    return workflowType.getSteps().stream().map(step -> step.getIdentity()).collect(Collectors.toList());
  }

  public WorkflowActionEntity getInitialStepAction(final W workflow) {

    final WorkflowTypeStepEntity firstStep = this.findFirstStep(workflow.getWorkflowType());

    final WorkflowActionEntity action = new WorkflowActionEntity();
    action.setAssignToId(null);
    action.setComments(workflow.getComments());
    action.setCurrentStep(firstStep);
    action.setCurrentStepId(firstStep.getId());
    action.setStatus(EWorkflowActionStatus.INITIALIZE.getValue());

    return action;
  }

  public WorkflowActionEntity initialNextStep(final W workflow) {

    final WorkflowTypeStepEntity nextStep = this.findNextStep(workflow.getWorkflowType(), getProcessingWorkflow());

    if (nextStep != null) {
      final WorkflowActionEntity action = new WorkflowActionEntity();
      action.setAssignToId(null);
      action.setComments(workflow.getComments());
      action.setCurrentStep(nextStep);
      action.setCurrentStepId(nextStep.getId());
      action.setStatus(EWorkflowActionStatus.INITIALIZE.getValue());
      return action;
    }
    return null;
  }

  public boolean hasNextStep(final IWorkflowSaveStrategyStep currentStep) {

    final int index = steps.indexOf(currentStep);
    return index < steps.size() - 1;
  }

  public IWorkflowSaveStrategyStep getNextStep(final IWorkflowSaveStrategyStep currentStep) {

    final int index = currentStep != null ? steps.indexOf(currentStep) : -1;
    return steps.get(index + 1);
  }

  public IWorkflowSaveStrategyStep getFirstStep() {

    return steps.get(0);
  }

  public IWorkflowSaveRequest<W> getProcessingWorkflowSaveRequest() {

    return processingWorkflowSaveRequest;
  }

  public W getProcessingWorkflow() {

    return processingWorkflowSaveRequest.getWorkflow();
  }

  public void setProcessingWorkflow(final W workflow) {

    processingWorkflowSaveRequest.setWorkflow(workflow);
  }

  public WorkflowActionEntity getActiveAction() {

    return processingWorkflowSaveRequest.getWorkflow().getActiveAction();
  }

  public WorkflowActionEntity getPrevActiveAction() {

    return prevActiveAction;
  }

  public WorkflowTypeEntity getProcessingWorkflowType() {

    return processingWorkflowSaveRequest.getWorkflow().getWorkflowType();
  }

  public IWorkflowBaseRepository<W> getWorkflowRepository() {

    return workflowRepository;
  }

  public Optional<W> getSavedSingleWorkflowOptional() {

    return savedSingleWorkflowOptional;
  }

  public void setSavedSingleWorkflowOptional(final W savedSingleWorkflowOptional) {

    this.savedSingleWorkflowOptional = Optional.of(savedSingleWorkflowOptional);
  }

  public Map<UUID, W> getSavedWorkflowList() {

    return savedWorkflowList;
  }

  public void addSavedWorkflowToList(final UUID userId, final W savedWorkflow) {

    this.savedWorkflowList.put(userId, savedWorkflow);

  }

  public Set<UUID> getAssignedUsers() {

    return assignedUsersId;
  }

  public void setAssignedUsers(final Collection<UUID> assignedUsers) {

    this.assignedUsersId.clear();
    if (assignedUsers != null) {
      this.assignedUsersId.addAll(assignedUsers);
    }
  }

  @Override
  public void process() throws WorkflowCustomizedException {

    for (int stepProcessIndex = 0; stepProcessIndex < steps.size(); stepProcessIndex++) {
      final IWorkflowSaveStrategyStep processStep = steps.get(stepProcessIndex);

      if (processStep.shouldProcess()) {
        processStep.process();
      }
    }

  }

  @Override
  public Optional<W> getSingleProceedWorkflow() {

    return getSavedSingleWorkflowOptional();
  }

  @Override
  public List<W> getProceedWorkflowList() {

    return getSavedWorkflowList().values().stream().collect(Collectors.toList());
  }

  public boolean IsWorkflowCurrentStepChanged() {

    final W processingWorkflow = getProcessingWorkflow();

    if(existsingWorkflowOptional.isPresent() == false){
      return true;
    }
    return processingWorkflow.isCurrentStepId(existsingWorkflowOptional.get().getCurrentStepId()) == false;
  }

  public Optional<W> prepareWorkflow(final W workflow) {

    return workflowPrepare.prepareWorkflow(workflow);
  }

  public List<W> prepareWorkflowList(final List<W> workflowList) {

    return this.workflowPrepare.prepareWorkflowList(workflowList);
  }

  public List<User> getDepartmentUserList(UUID departmentId) {
    return this.departmentDataService.getUserListByDepartmentId(departmentId, this.authorization);
  }
}
