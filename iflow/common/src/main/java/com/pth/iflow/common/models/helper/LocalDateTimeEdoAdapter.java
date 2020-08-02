package com.pth.iflow.common.models.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeEdoAdapter extends XmlAdapter<String, LocalDateTime> {

  private final DateTimeFormatter dateFormat = new DateTimeFormatterBuilder().parseCaseInsensitive()
                                                                             .appendPattern(IsoFormats.DATETIME_FORMAT_ISO)
                                                                             .toFormatter();

  @Override
  public String marshal(final LocalDateTime v) throws Exception {
    return this.dateFormat.format(v);
  }

  @Override
  public LocalDateTime unmarshal(final String v) throws Exception {
    return LocalDateTime.parse(v);
  }

}
