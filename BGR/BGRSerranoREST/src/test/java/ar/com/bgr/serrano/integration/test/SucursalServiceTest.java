package ar.com.bgr.serrano.integration.test;

import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.EOIDAO;
import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.model.Eoi;
import ar.com.bgr.serrano.model.Sucursal;
import ar.com.bgr.serrano.service.ProveedorService;
import ar.com.bgr.serrano.service.SucursalService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class SucursalServiceTest {

	
	@Autowired
	SucursalService service;
	@Autowired
	ProveedorService proveedorService;
	
//	@Test
	public void testSave() {
		//estos test hay que tirarlos y hacer mejores test
		
		Sucursal sucursal= new Sucursal();
		sucursal.setCalle("a");
		sucursal.setCodigoPostal("132");
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
		
		
		
		Eoi eoi = new Eoi();
		eoi.setCondicionImpositiva("sasas");
		eoi.setCuit("3216165");
		eoi.setIngresosBrutos("asdasd");
		eoi.setName("asasadasdasd");
		eoi.setObservaciones("asdd asda asd asd  asdasd asda sd asd a");
		eoi.setRazonSocial("asdasdadsa asd a");
		eoi.setType(EOIDAO.PROVEEDOR);
		eoi = proveedorService.save(eoi);
		
		
		sucursal.setEoi(eoi);
		
		
		Sucursal result = service.save(sucursal);
		assertNotNull(result);
		assertNotNull(service.getById(result.getId()));
	}

//	@Test
	public void testList() {
		System.out.println(service.list());
	}
	
//	@Test
	public void testAddContact(){
		Contacto contacto = new Contacto();
		contacto.setBlog("agregado1234");
		contacto.setDiaHoraContacto("agregado1234");
		contacto.setFax("agregado412");
		contacto.setName("agregado1234");
		contacto.setRadio("agregado1234");
		contacto.setTelefonoFijo("agregado1243");
		contacto.setTelefonoMovil("agregado1243");
		contacto.setWebSite("agregado1234");
		
		
		//Sucursal sucursal = service.getById(1);
		//contacto.setSucursal(sucursal);
	//	sucursal.getContactos().add(contacto);
		service.addExistingContact(contacto, 1);
		
	}


}
