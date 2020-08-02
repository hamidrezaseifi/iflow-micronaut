package com.pth.iflow.common.models.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateEdoAdapter extends XmlAdapter<String, LocalDate> {

  private final DateTimeFormatter dateFormat = new DateTimeFormatterBuilder().parseCaseInsensitive()
      .appendPattern(IsoFormats.DATE_FORMAT_ISO).toFormatter();

  @Override
  public String marshal(final LocalDate v) throws Exception {
    return this.dateFormat.format(v);
  }

  @Override
  public LocalDate unmarshal(final String v) throws Exception {
    return LocalDate.parse(v);
  }

}
