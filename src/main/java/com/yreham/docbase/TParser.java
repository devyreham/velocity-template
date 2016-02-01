/**
 * &copy; yreham.com
 */
package com.yreham.docbase;

import java.io.OutputStream;
import java.util.Map;

/**
 * simple template parser inspired from CodeIgniter parser
 * 
 * @author yreham.com dev.yreham
 *
 */
public interface TParser {

  /**
   * @param template
   * @param variableMap
   * @param output
   */
  public void parse(byte[] input, Map<String, String> variableMap, OutputStream output);

  /**
   * @param delimiter
   */
  public void setDelimiters(String leftDelimiter, String rightDelimiter);

}
