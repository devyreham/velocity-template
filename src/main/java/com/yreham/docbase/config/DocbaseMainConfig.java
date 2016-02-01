/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.config;

import java.util.logging.Logger;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author yreham.com dev.yreham
 *
 */
/*
 * @Configuration
 * 
 * @Import({DocbaseWebConfig.class, DocbasePersistenceConfig.class })
 */
public class DocbaseMainConfig {

  private static final Logger LOG =
      Logger.getLogger(com.yreham.docbase.config.DocbaseMainConfig.class.getCanonicalName());

  // @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();

    msgSource.setUseCodeAsDefaultMessage(true);
    // msgSource.setDefaultEncoding(UTF_8);

    return msgSource;
  }

}
