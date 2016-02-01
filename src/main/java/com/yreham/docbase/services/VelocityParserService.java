/**
 * 
 */
package com.yreham.docbase.services;

import java.io.OutputStream;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yreham.docbase.TParser;
import com.yreham.docbase.parsers.VelocityParser;
import com.yreham.docbase.services.ParserService;

/**
 * @author dev.yreham
 *
 */
@Component
@Order(value = 1)
public class VelocityParserService implements ParserService {

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.ParserService#create(java.lang.String, java.lang.String)
   */
  @Override
  public TParser create(String leftDelimiter, String rightDelimiter) {
    TParser tparser = new VelocityParser();

    return tparser;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.ParserService#execute(com.yreham..web.docbase.TParser,
   * byte[], java.io.OutputStream, java.util.Map)
   */
  @Override
  public void execute(TParser tParser, byte[] content, OutputStream output,
      Map<String, String> resolver) {
    tParser.parse(content, resolver, output);
  }

}
