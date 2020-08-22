package com.pth.common.exceptions;

public class IFlowMessageConversionFailureException extends Exception {

	  private static final long serialVersionUID = 1L;

	  private EIFlowErrorType errorType;

	  public IFlowMessageConversionFailureException(final String message, final Exception cause) {
	    super(message, cause);
		  errorType = EIFlowErrorType.UNKNOWN;
	  }

	public IFlowMessageConversionFailureException(final String message) {
		super(message);
		errorType = EIFlowErrorType.UNKNOWN;
	}

	public IFlowMessageConversionFailureException(final String message, EIFlowErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}

	  public IFlowMessageConversionFailureException(final Throwable cause) {
	    super(cause);
		  errorType = EIFlowErrorType.UNKNOWN;
	  }

}