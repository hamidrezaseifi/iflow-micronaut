package com.pth.iflow.common.rest;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

  @Override
  public LocalDateTime unmarshal(final String v) throws Exception {
    return LocalDateTime.parse(v);
  }

  /**
   * (non-Javadoc)
   *
   * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
   */
  @Override
  public String marshal(final LocalDateTime v) throws Exception {
    return v.toString();
  }
}
