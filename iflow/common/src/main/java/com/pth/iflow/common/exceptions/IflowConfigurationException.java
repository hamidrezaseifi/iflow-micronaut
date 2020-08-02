package com.pth.iflow.common.exceptions;

/**
 *
 */
public class IflowConfigurationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IflowConfigurationException(final Exception e) {
    super(e);
  }

  /**
   * Instantiates a new mdm configuration exception.
   *
   * @param messagePattern for {@link String#format(String, Object...)}
   * @param args the args
   */
  public IflowConfigurationException(final String messagePattern, final Object... args) {
    super(String.format(messagePattern, args));
  }

  /**
   * Instantiates a new mdm configuration exception.
   *
   * @param cause the cause
   * @param messagePattern for {@link String#format(String, Object...)}
   * @param args the args
   */
  public IflowConfigurationException(final Exception cause, final String messagePattern, final Object... args) {
    super(String.format(messagePattern, args), cause);
  }

}
