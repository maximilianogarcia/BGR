package ar.com.bgr.serrano.integration.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EOIDao;
import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.model.Eoi;
import ar.com.bgr.serrano.model.Sucursal;
import ar.com.bgr.serrano.service.ContactoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class ContactoServiceTest {

	
	@Autowired
	ContactoService service;
	
//	@Test
	public void testSave() {
		//estos test hay que tirarlos y hacer mejores test
		
		Eoi eoi = new Eoi();
		eoi.setCondicionImpositiva("sasas");
		eoi.setCuit("3216165");
		eoi.setIngresosBrutos("asdasd");
		eoi.setName("asasadasdasd");
		eoi.setObservaciones("asdd asda asd asd  asdasd asda sd asd a");
		eoi.setRazonSocial("asdasdadsa asd a");
		eoi.setType(EOIDao.PROVEEDOR);
		eoi.setId(1);
		
		
		
		Sucursal sucursal= new Sucursal();
		sucursal.setCalle("a");
		sucursal.setCodigoPostal("132");
		sucursal.setId(1);
		sucursal.setDescripcion("una sucursal");
		sucursal.setLocalidad("La plata");
		sucursal.setName("sucursala algo");
		sucursal.setNumero("132");
		sucursal.setObservacion("observacion de una sucursal");
		sucursal.setObservacionDireccion("observacion direccion");
		sucursal.setPais("Argentina");
		sucursal.setPiso("sasa");
		sucursal.setProvincia("Buenos Aires");
		sucursal.setZona("zona");
		
		
		
		
		Contacto contacto= new Contacto();
		contacto.setName("contacto00");
		contacto.setBlog("google.com/blog");
		contacto.setWebSite("www.gooogle.com");
		contacto.setFax("123456789");
		contacto.setRadio("12346789");
		contacto.setTelefonoMovil("123456978654");
		contacto.setTelefonoFijo("48992516");
		contacto.setDiaHoraContacto("de lunes a viernes de 8 a 13");
		contacto.setEoi(eoi);
//		contacto.setSucursal(sucursal);
		
		Contacto result = service.save(contacto);
		assertNotNull(result);
		assertNotNull(service.getById(result.getId()));
	}

//	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testRemoveFromSucursal(){
		service.removeFromSucursal(2,2); 
	}

}
