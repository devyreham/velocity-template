/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase;

/**
 * Fragments represent the DocumentEntity to reduce read/write from database
 * 
 * @author yreham.com dev.yreham
 *
 */
public interface Fragment {

  /**
   * @return
   */
  public String getDocId();

  /**
   * @return
   */
  public String getDocType();

  /**
   * @return
   */
  public String getContent();

}
