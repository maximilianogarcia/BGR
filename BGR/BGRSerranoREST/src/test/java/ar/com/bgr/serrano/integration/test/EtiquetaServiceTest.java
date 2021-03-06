/**
 * 
 */
package ar.com.bgr.serrano.integration.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.model.Etiqueta;
import ar.com.bgr.serrano.service.EtiquetaService;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class EtiquetaServiceTest {

	@Autowired
	EtiquetaService service;
	
	@Test
	public void testSave() {
		//estos test hay que tirarlos y hacer mejores test
		
		Etiqueta etiqueta= new Etiqueta();
		etiqueta.setName("Etiqueta00");
		Etiqueta result = service.save(etiqueta);
		assertNotNull(result);
		assertNotNull(service.getById(result.getId()));
	}

	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testRemove(){
	}
}
