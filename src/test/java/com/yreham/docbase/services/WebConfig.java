package com.yreham.docbase.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.yreham.docbase.services"})
@EnableWebMvc
public class WebConfig {

}
