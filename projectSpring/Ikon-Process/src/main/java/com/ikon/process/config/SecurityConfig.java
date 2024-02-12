/**
 * 
 */
package com.ikon.process.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @author IsraelC
 *
 */
@ComponentScan("com.ikon.process")
@Configuration
public class SecurityConfig {
	private static final Logger LOGG = LoggerFactory.getLogger(SecurityConfig.class);
	
	
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.password}")
	private String datopass;
	
	@Value("${path.image}")
	private String pathServer;
		
	/**
	 * Genera el dataSource para el manejo de instancias de Base de 
	 * datos.
	 * @return
	 */
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url.concat("?useTimezone=true&serverTimezone=UTC"));
        dataSource.setUsername(userName);
        dataSource.setPassword(datopass);
        
        LOGG.info("[::::::::: {}{}{}{}{} ::::::::]", "Levantado DataSource"," ", dataSource.toString()," ",driverClassName);
        return dataSource;
    }
	
	
	@Bean (name = "multipartResolver")
	public MultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver();
	 //  multipartResolver.setResolveLazily(true);
	    multipartResolver.setMaxUploadSize(18557049); //18557049
	    return multipartResolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory(pathServer, registry);
	}
	     
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();

		if (dirName.startsWith("../"))
			dirName = dirName.replace("../", "");

		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
	}
	    
	    
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		LOGG.info("[::::::::: {} {} {}{} {} ::::::::]", "CrossOrigin"," /ikon/ ","GET/POST");
//		
//		return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/ikon/**")
//                            .allowedOrigins("*")
//                            .allowedMethods("GET", "POST")
//                            .maxAge(3600);
//            }
//
//    };
//		
//	}
	
	
	
	
}
