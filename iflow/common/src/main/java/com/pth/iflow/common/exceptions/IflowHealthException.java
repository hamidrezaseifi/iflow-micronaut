package com.pth.iflow.common.exceptions;

/**
 * @author tmenzel
 * @since 04.09.2018
 */
public class IflowHealthException extends Exception {

  private static final long serialVersionUID = 1L;

  public IflowHealthException(final String message) {
    super(message);
  }

  /**
   */
  public IflowHealthException(final Exception e) {
    super(e);
  }

  public IflowHealthException(final String message, final Exception causedBy) {
    super(message, causedBy);
  }

  public IflowHealthException(final String message, final Object... msgArgs) {
    this(message, null, msgArgs);
  }

  public IflowHealthException(final String message, final Exception causedBy, final Object... msgArgs) {
    super(String.format(message, msgArgs), causedBy);
  }

}
