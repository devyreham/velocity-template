/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.util;

import java.util.Map;

/**
 * @author yreham.com dev.yreham
 *
 */
public interface VariableHelper {

  public void extractVariables(String content, String startToken, String endToken);

  public String replaceVariable(String content, String variable, String startToken, String endToken,
      String replacement);

  public String replaceAllVariables(String content, String startToken, String endToken,
      Map<String, String> resolver);

  public int countVariables(String content, String startToken, String endToken);

  public String insertVariable(String content, String variable, int pos, String startToken,
      String endToken);

}
