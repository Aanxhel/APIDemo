package com.cdc.fis2022.configuracion;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.cdc.Support.Networking.templates.SSLClientFactory;

@Configuration
public class Fis2022Configuracion {

	private static final Logger logger = LogManager.getLogger("fis-2022");

    @Value("${jndi.name}")
    private String  jndi;
    
    
    

    @Bean
    @Qualifier("Standard")
    public DataSource dataSource() {
    	logger.info("***** Mensaje dentro de DataSource *****");
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        try {
            jndiObjectFactoryBean.setJndiName(jndi);
            jndiObjectFactoryBean.setResourceRef(true);
            jndiObjectFactoryBean.setProxyInterface(DataSource.class);
            jndiObjectFactoryBean.afterPropertiesSet();

        }catch(Exception ex){
        	logger.error("error en GeneraFis2022Configuracion -> "+ex.getMessage()+" -> cause -> "+ex.getCause());
        	ex.getStackTrace();
        	logger.error(ex);
        }
        
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean
    @Qualifier("Standard")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    @Qualifier("Standard")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
   

    @Bean
    @Qualifier("SSL")
    public RestTemplate restTemplateSSL(){
        return new RestTemplate(SSLClientFactory.getClientHttpRequestFactory());
    }
    
 /*       
    @Bean
    @Qualifier("Standard")
    public MatchService matchService() {
    	return new MatchServiceV2();
    }
  
    @Bean
    @Qualifier("Standard")
    public ScoreService scoreService() {
        return new ScoreServiceV2();
    }
*/
    

    
    
    
    
	
}
