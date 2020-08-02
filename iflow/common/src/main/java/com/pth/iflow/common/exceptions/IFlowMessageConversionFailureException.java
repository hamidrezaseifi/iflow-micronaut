package com.pth.iflow.common.exceptions;

public class IFlowMessageConversionFailureException extends Exception {

	  private static final long serialVersionUID = 1L;

	  public IFlowMessageConversionFailureException(final String message, final Exception cause) {
	    super(message, cause);
	  }

	  public IFlowMessageConversionFailureException(final String message) {
	    super(message);
	  }

	  public IFlowMessageConversionFailureException(final Throwable cause) {
	    super(cause);
	  }

}