package net.codejava.spring.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.dao.ContactDAOImpl;*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import net.codejava.spring.dao.EmployeeDAO;
import net.codejava.spring.dao.EmployeeDAOImpl;

/**
 * This is Coniguration class Which is used instead of xml configuration
 * 
 * @author saikiran
 *
 */
@Configuration
@ComponentScan(basePackages = "net.codejava.spring")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	Logger logger = LoggerFactory.getLogger("MvcConfiguration");

	@Bean
	public ViewResolver getViewResolver() {
		logger.info("In side InternalResourceViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		logger.debug("resolverBefore Put setPrefix and setSuffix " + resolver);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		logger.debug("resolver " + resolver);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.info("In side addResourceHandlers Method() ");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		logger.debug("After addResourceLocations " + registry);
	}

	/*
	 * @Bean public DataSource getDataSource() { DriverManagerDataSource
	 * dataSource = new DriverManagerDataSource();
	 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb");
	 * dataSource.setUsername("root"); dataSource.setPassword("");
	 * 
	 * return dataSource; }
	 */

	@Bean
	public Mongo mongo() throws Exception {
		logger.info(" In side Mongo method()");
		return new MongoClient("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		logger.info(" In side MongoTemplate method()");
		return new MongoTemplate(mongo(), "db10000");
	}

	@Bean
	public EmployeeDAO getContactDAO() throws Exception {
		logger.info(" In side EmployeeDAO method()");
		return new EmployeeDAOImpl(mongoTemplate());
	}
}
