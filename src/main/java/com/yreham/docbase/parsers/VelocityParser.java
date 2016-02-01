/**
 * 
 */
package com.yreham.docbase.parsers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import com.google.common.collect.Maps;
import com.yreham.docbase.TParser;

/**
 * @author dev.yreham
 *
 */
public class VelocityParser implements TParser {

  private static final String LOGTAG = "document";

  private static final String UTF_8 = "UTF-8";

  private VelocityEngine vengine;

  /**
   * 
   */
  public VelocityParser() {
    super();
    vengine = new VelocityEngine();

    vengine.setProperty("input.encoding", "UTF-8");
    vengine.setProperty("output.encoding", "UTF-8");
    vengine.setProperty("directive.if.tostring.nullcheck", false);
    vengine.setProperty("runtime.log.logsystem.class",
        "org.apache.velocity.runtime.log.NullLogSystem ");
    vengine.init();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.TParser#parse(byte[], java.util.Map, java.io.OutputStream)
   */
  @Override
  public void parse(byte[] input, Map<String, String> variableMap, OutputStream output) {

    try {
      Context context = initContext(variableMap);
      Reader in = new StringReader(readInput(input));
      Writer out = new PrintWriter(output);
      try {
        evaluate(context, out, in);
        out.close();
        in.close();
        clearContext(context);
      } catch (org.apache.velocity.exception.ParseErrorException pee) {
        out.write(pee.getMessage());
        out.close();
        in.close();
        clearContext(context);
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.TParser#setDelimiters(java.lang.String, java.lang.String)
   */
  @Override
  public void setDelimiters(String leftDelimiter, String rightDelimiter) {
    // TODO Auto-generated method stub
  }

  /**
   * initialiaze variable map. always call first
   * 
   * @param resolver
   * @return
   */
  private Context initContext(final Map<String, String> resolver) {
    Map<String, Object> computed = Maps.newHashMap();
    
    for(String key: resolver.keySet()){
      if(resolver.get(key).equals("true") || resolver.get(key).equals("false")){
        computed.put(key, Boolean.valueOf(resolver.get(key)));
      } else {
        computed.put(key, (String) resolver.get(key));
      }
    }
    
    return new VelocityContext(computed);
  }
  
  /**
   * memory optimization
   * @param context
   */
  private void clearContext(Context context){
    Object[] keys = context.getKeys();
    for(Object obj: keys){
      context.remove(obj);
    }
    context = null;
  }

  /**
   * convert template to html
   * 
   * @param context
   * @param writer
   * @param reader
   * @return
   */
  private boolean evaluate(Context context, Writer out, Reader in) {

    boolean res = vengine.evaluate(context, out, LOGTAG, in);

    return res;
  }

  /**
   * @param input
   * @return
   * @throws UnsupportedEncodingException
   */
  private String readInput(byte[] fullbytes) throws UnsupportedEncodingException {
    return new String(fullbytes, UTF_8);

  }
}
