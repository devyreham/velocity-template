/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.util;

/**
 * @author yreham.com dev.yreham
 *
 */
public interface DocumentIdGenerator {

  /**
   * @param count number of characters to create
   * @return
   */
  public String generateKey(int count);
}
