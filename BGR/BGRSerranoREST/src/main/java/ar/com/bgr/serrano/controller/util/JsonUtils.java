package ar.com.bgr.serrano.controller.util;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import ar.com.bgr.serrano.controller.exception.BGRJsonParseException;

public class JsonUtils {

	public static Object parseString(String jsonData, Class<?> clasz) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(jsonData, clasz);
		} catch (IOException e) {
			throw new BGRJsonParseException(e);
		}
	}
	public static Object parseFile(String path, Class<?> clasz) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(new File(path), clasz);
		} catch (IOException e) {
			throw new BGRJsonParseException(e);
		}
	}
}
