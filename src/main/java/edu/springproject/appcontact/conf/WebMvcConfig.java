package edu.springproject.appcontact.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 
@Configuration
@EnableWebMvc
@PropertySource("classpath:/conf/env.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Value("${mvc.css.loc}")
	private String mvcCssLocation;
	
	@Value("${mvc.img.loc}")
	private String mvcImgLocation;
	
	@Value("${mvc.js.loc}")
	private String mvcJsLocation;
	
	@Value("${mvc.css.hand}")
	private String mvcCssHandler;
	
	@Value("${mvc.img.hand}")
	private String mvcImgHandler;
	
	@Value("${mvc.js.hand}")
	private String mvcJsHandler;
	
	@Value("${mcv.cache.period}")
	private Integer mcvCachePeriod;
	
    // Static Resource Config 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(mvcCssHandler)
		    	.addResourceLocations(mvcCssLocation)
		    	.setCachePeriod(mcvCachePeriod);
		registry.addResourceHandler(mvcImgHandler)
		    	.addResourceLocations(mvcImgLocation)
		    	.setCachePeriod(mcvCachePeriod);
		registry.addResourceHandler(mvcJsHandler)
		    	.addResourceLocations(mvcJsLocation)
		    	.setCachePeriod(mcvCachePeriod);
    }
 
     
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
  
}
