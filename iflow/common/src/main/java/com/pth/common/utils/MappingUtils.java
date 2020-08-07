package com.pth.common.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

//ToDo: Use IoC (Singleton) instead of static-class
//ToDo: Add missing Unit-Tests

public final class MappingUtils {

    private MappingUtils() {
    }

    public static <T> T copyProperties(final Object source,
                                       final T destination) {

        try {
            PropertyUtils.copyProperties(destination, source);
        } catch (IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException exception) {
            throw new RuntimeException(exception);
        }

        return destination;
    }
}
