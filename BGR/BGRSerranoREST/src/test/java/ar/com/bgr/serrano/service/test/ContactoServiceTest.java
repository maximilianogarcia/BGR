package ar.com.bgr.serrano.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.service.ContactoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ContactoServiceTest {

	
	@Autowired
	ContactoService service;
	
	@Test
	public void testSave() {
		//estos test hay que tirarlos y hacer mejores test
		
		Contacto contacto= new Contacto();
		contacto.setName("contacto1");
		contacto.setBlog("google.com/blog");
		contacto.setWebSite("www.gooogle.com");
		contacto.setFax("123456789");
		contacto.setRadio("12346789");
		contacto.setTelefonoMovil("123456978654");
		contacto.setDiaHoraContacto("de lunes a viernes de 8 a 13");
		
		service.save(contacto);
	}

	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testRemove(){
	}

}
