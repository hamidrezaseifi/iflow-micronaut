package com.pth.workflow.services.bl;

import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;

import java.util.*;
import java.util.stream.Collectors;

public abstract class WorkflowPrepareBase<W extends IWorkflowBaseEntity> implements IWorkflowPrepare<W> {


    private final IWorkflowTypeRepository workflowTypeRepository;

    public WorkflowPrepareBase(IWorkflowTypeRepository workflowTypeRepository) {

        this.workflowTypeRepository = workflowTypeRepository;
    }

    @Override
    public Optional<W> prepareWorkflow(final W workflow){

        final Optional<WorkflowTypeEntity>
                workflowTypeOptional = this.workflowTypeRepository.getByIdentity(workflow.getWorkflowTypeIdentity());

        if(workflowTypeOptional.isPresent() == false){
            return Optional.empty();
        }

        WorkflowTypeEntity workflowType = workflowTypeOptional.get();
        workflow.setWorkflowType(workflowType);

        final Map<UUID, WorkflowTypeStepEntity> map = getIdMappedSteps(workflowType);

        workflow.setCurrentStep(map.containsKey(workflow.getCurrentStepId()) ? map.get(workflow.getCurrentStepId()) : null);
        for (final WorkflowActionEntity action : workflow.getActions()) {
            action.setCurrentStep(map.containsKey(action.getCurrentStepId()) ? map.get(action.getCurrentStepId()) : null);
        }

        for (final WorkflowTypeStepEntity typeStep : workflowType.getSteps()) {
            if (typeStep.getId() == workflow.getCurrentStepId()) {
                workflow.setCurrentStep(typeStep);
            }
        }

        return Optional.of(workflow);
    }


    @Override
    public List<W> prepareWorkflowList(final List<W> workflowList){

        final List<W> list = new ArrayList<>();
        if (workflowList != null) {
            for (final W workflow : workflowList) {
                Optional<W> testThreeTaskWorkflowEntityOptional = this.prepareWorkflow(workflow);
                if(testThreeTaskWorkflowEntityOptional.isPresent()){
                    list.add(testThreeTaskWorkflowEntityOptional.get());
                }
            }

        }

        return list;
    }

    protected Map<UUID, WorkflowTypeStepEntity> getIdMappedSteps(final WorkflowTypeEntity workflowType) {

        final Map<UUID, WorkflowTypeStepEntity> list = workflowType
                .getSteps()
                .stream()
                .collect(Collectors.toMap(s -> s.getId(), s -> s));

        return list;
    }

}
