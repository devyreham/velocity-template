/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author yreham.com dev.yreham
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.yreham.docbase.services", "com.yreham.docbase.controllers"})
@EnableTransactionManagement
public class DocbaseWebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }


  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
   * #configureMessageConverters(java.util.List)
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    converters.add(docConverter());
    super.configureMessageConverters(converters);
  }

  @Bean(name = "docConverter")
  public MappingJackson2HttpMessageConverter docConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setObjectMapper(
        new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false));

    return converter;
  }

}
