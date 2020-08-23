package com.pth.workflow.exceptions;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.common.exceptions.IFlowCustomizedException;

public class WorkflowCustomizedException extends IFlowCustomizedException {
    public WorkflowCustomizedException(String message,
                                       String detailes) {
        super(message, detailes, EModule.WORKFLOW.getModuleName());
    }

    public WorkflowCustomizedException(String message,
                                       String detailes,
                                       EIFlowErrorType errorType) {
        super(message, detailes, EModule.WORKFLOW.getModuleName(), errorType);
    }

    public WorkflowCustomizedException(String message,
                                       EIFlowErrorType errorType) {
        super(message, EModule.WORKFLOW.getModuleName(), errorType);
    }
}
