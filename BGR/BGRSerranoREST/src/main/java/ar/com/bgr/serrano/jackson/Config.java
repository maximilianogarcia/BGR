package ar.com.bgr.serrano.jackson;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public ObjectMapper jacksonObjectMapper() {
	    return new CustomObjectMapper();
	}
	 
	@Bean
	public SerializationConfig serializationConfig() {
	    return jacksonObjectMapper().getSerializationConfig();
	}
}
