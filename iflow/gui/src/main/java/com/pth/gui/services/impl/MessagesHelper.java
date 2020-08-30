package com.pth.gui.services.impl;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import com.pth.common.exceptions.EIFlowErrorType;
import io.micronaut.context.MessageSource;
import io.micronaut.context.i18n.ResourceBundleMessageSource;

import com.pth.gui.services.IMessagesHelper;



@Singleton
public class MessagesHelper implements IMessagesHelper {

  private static final String   ERROR_TYPE_PREFIX = "error-type-";

  //@Autowired
  private MessageSource messageSource;

  private MessageSource.MessageContext messageContext;

  public MessagesHelper() {
    this.messageSource = new ResourceBundleMessageSource("messages.messages");
  }

  //private MessageSourceAccessor accessor;

  @PostConstruct
  private void init() {
    messageContext = MessageSource.MessageContext.of(Locale.GERMAN);

    //this.accessor = new MessageSourceAccessor(this.messageSource, Locale.GERMAN);
  }

  @Override
  public String get(final String code) {
    return this.getMessageFromSource(code);
  }

  @Override
  public String get(final String code, final Object... args) {
    return String.format(getMessageFromSource(code), args);
  }

  @Override
  public String getErrorMessage(final EIFlowErrorType errorType) {

    return this.getMessageFromSource(this.getErrorTypeMessageKey(errorType));
  }

  @Override
  public String getErrorMessage(final EIFlowErrorType errorType, final Object... args) {

    return String.format(this.getMessageFromSource(this.getErrorTypeMessageKey(errorType)), args);
  }

  private String getMessageFromSource(String key){
    return this.messageSource.getMessage(key, messageContext).get();
  }
  private String getErrorTypeMessageKey(final EIFlowErrorType errorType) {
    return ERROR_TYPE_PREFIX + errorType.toString().toLowerCase();
  }

}
