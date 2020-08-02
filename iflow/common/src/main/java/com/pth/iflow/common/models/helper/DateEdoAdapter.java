package com.pth.iflow.common.models.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateEdoAdapter extends XmlAdapter<String, LocalDate> {

  private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(IsoFormats.DATE_FORMAT_ISO);

  @Override
  public LocalDate unmarshal(final String v) {

    return LocalDate.parse(v, this.dateFormat);

  }

  @Override
  public String marshal(final LocalDate v) {
    return this.dateFormat.format(v);
  }

}
