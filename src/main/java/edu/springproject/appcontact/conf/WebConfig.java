package edu.springproject.appcontact.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
@Configuration
@EnableWebMvc
@ComponentScan("edu.springproject.appcontact")
public class WebConfig extends WebMvcConfigurerAdapter {

}
