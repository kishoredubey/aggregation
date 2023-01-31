package org.aggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Aggregation 
{
    public static void main( String[] args ) {
    	System.setProperty("server.servlet.context-path", "/aggregation");
        SpringApplication.run(Aggregation.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
}
