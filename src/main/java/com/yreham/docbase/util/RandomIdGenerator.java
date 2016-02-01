/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yreham.com dev.yreham
 *
 */
public class RandomIdGenerator implements DocumentIdGenerator {

  private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final int KEY_SIZE_MINIMUM = 6;

  private char[] range;

  /**
   * 
   */
  public RandomIdGenerator() {
    super();
    range = ALPHANUMERICS.toCharArray();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.util.DocumentIdGenerator#generateKey(int, java.lang.String)
   */
  @Override
  public String generateKey(int count) {

    try {
      return RandomStringUtils.random(Math.max(count, KEY_SIZE_MINIMUM), 0, 36, false, false, range,
          SecureRandom.getInstance("SHA1PRNG"));


    } catch (NoSuchAlgorithmException e) {
      return defaultKey(count);
    }
  }

  private String defaultKey(int count) {

    return ALPHANUMERICS.substring(KEY_SIZE_MINIMUM, KEY_SIZE_MINIMUM);
  }

}
