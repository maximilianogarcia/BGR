package ar.com.bgr.serrano.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.bgr.serrano.dao.EtiquetaDao;
import ar.com.bgr.serrano.model.Etiqueta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class EtiquetaDaoTest {

	
	@Autowired
	EtiquetaDao dao;
	
	@Test
	public void test() {
	
	}

}
