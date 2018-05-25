package edu.springproject.appcontact.conf;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("edu.springproject.appcontact")
@PropertySource("classpath:/conf/contacts.properties")
public class AppConfig { 
	
	@Value("${jdbc.driver.class}")
	private String jdbcDriverClass;
	
	@Value("${jdbc.url}")
	private String jdbcUrl;
	
	@Value("${jdbc.login}")
	private String jdbcLogin;
	
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName(jdbcDriverClass);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcLogin);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {	
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}
}
