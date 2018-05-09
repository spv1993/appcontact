package edu.springproject.appcontact.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@ComponentScan("edu.springproject.appcontact.*") 
@PropertySource("classpath:/conf/env.properties")
public class ApplicationContextConfig { 
  
	@Value("${cnt.suffix}")
	private String contextSuffix;
	
	@Value("${cnt.prefix}")
	private String contextPrefix;
 
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(contextPrefix);
        viewResolver.setSuffix(contextSuffix);
        return viewResolver;
    }
  
 
}