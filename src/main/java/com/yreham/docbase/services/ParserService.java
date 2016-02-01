/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.services;

import java.io.OutputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yreham.docbase.TParser;

/**
 * @author yreham.com dev.yreham
 *
 */
@Service
@Transactional(value = "transactionManager")
public interface ParserService {

  public TParser create(String leftDelimiter, String rightDelimiter);

  public void execute(TParser tParser, byte[] content, OutputStream output,
      Map<String, String> resolver);
}
