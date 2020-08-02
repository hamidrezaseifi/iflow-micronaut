package com.pth.iflow.common.utils;

/**
 * class to help with logging of sensitive data
 */
/* TODO CK2-252 data-logging | this impl is a quick shot | menzelt 24.09.2018 */
public class DataLoggingUtils {

  /** The log data allowed. */
  public static boolean LOG_DATA_ALLOWED = true;

  /**
   * Gets the or mask.
   *
   * @param value the value
   * @return the or mask
   */
  public static CharSequence getOrMask(final CharSequence value) {
    return LOG_DATA_ALLOWED ? value : "#hidden#";
  }

}
