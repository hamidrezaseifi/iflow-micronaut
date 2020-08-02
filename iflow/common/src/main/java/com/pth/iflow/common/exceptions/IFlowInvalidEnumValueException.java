package com.pth.iflow.common.exceptions;

import javax.validation.ValidationException;

public class IFlowInvalidEnumValueException extends ValidationException {

  private static final long serialVersionUID = 1L;

  public IFlowInvalidEnumValueException() {
    super();

  }

  public IFlowInvalidEnumValueException(final String message) {
    super(message);
  }

  public IFlowInvalidEnumValueException(final String message, final Exception causedby) {
    super(message, causedby);
  }

}
