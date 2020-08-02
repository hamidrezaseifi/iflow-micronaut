/**
 *
 */
package com.pth.iflow.common.rest.xml;

/**
 * @author tmenzel
 * @since 14.07.2018
 */
public class XmlValidationException extends Exception {

  private static final long serialVersionUID = 1L;
  private Boolean           isOnIncoming;

  /**
   * @param e
   */
  public XmlValidationException(final Exception e) {
    super(e);
  }

  /**
   * @param string
   */
  public XmlValidationException(final String string) {
    super(string);
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#getMessage()
   */
  @Override
  public String getMessage() {
    if (getIsOnIncoming() == null) {
      return super.getMessage();
    }

    final String prefix = getIsOnIncoming() ? "On incoming request: " : "On outgoing request: ";
    return prefix + super.getMessage();
  }

  public Boolean getIsOnIncoming() {
    return isOnIncoming;
  }

  public XmlValidationException setIsOnIncoming(final Boolean isOnIncoming) {
    this.isOnIncoming = isOnIncoming;
    return this;
  }
}
