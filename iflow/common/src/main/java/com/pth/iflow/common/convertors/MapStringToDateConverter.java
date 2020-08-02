package com.pth.iflow.common.convertors;

import com.pth.iflow.common.constants.DateTimeConstants;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@Singleton
public class MapStringToDateConverter implements TypeConverter<String, Date> {


    private static SimpleDateFormat formatter = new SimpleDateFormat(DateTimeConstants.ISO8601_DATETIME_PATTERN);

    private static SimpleDateFormat oldFormatter = new SimpleDateFormat(DateTimeConstants.OLD_DATETIME_PATTERN);

    @Override
    public Optional<Date> convert(String dateAsString,
                                  Class<Date> targetType,
                                  ConversionContext context) {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        try {
            Date date = formatter.parse(dateAsString);
            return Optional.of(date);

        } catch (ParseException e) {

        }

        try {
            Date date = oldFormatter.parse(dateAsString);
            return Optional.of(date);

        } catch (ParseException e) {

        }
        return Optional.empty();
    }

    public static String convertDateToString(Date date){
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static Date convertStringToDate(String dateAsString){
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return formatter.parse(dateAsString);
        } catch (ParseException e) {
            return null;
        }
    }
}
