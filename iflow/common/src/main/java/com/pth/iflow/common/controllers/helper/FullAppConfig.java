package com.pth.iflow.common.controllers.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Profile;

/**
 * Use this to enable a bean for all modules
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Profile({ IflowSpringProfiles.SERVICE_APP, IflowSpringProfiles.GUI_APP })
public @interface FullAppConfig {

}
