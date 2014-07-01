package ar.com.bgr.serrano.integration.test;

import org.junit.Test;

import ar.com.bgr.serrano.controller.util.JsonUtils;
import ar.com.bgr.serrano.model.Eoi;

public class jsonConvertTest {

	@Test
	public void test() {
		JsonUtils.parseFile("prueba.json", Eoi.class);
	}

}
