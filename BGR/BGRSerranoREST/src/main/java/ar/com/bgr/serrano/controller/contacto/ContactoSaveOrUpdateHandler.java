package ar.com.bgr.serrano.controller.contacto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.controller.util.JsonUtils;
import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.service.ContactoService;

@Controller
@RequestMapping(value = "/contacto/save")
public class ContactoSaveOrUpdateHandler {

	@Autowired
	ContactoService service;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Contacto execute(@RequestBody MultiValueMap<String, String> body) {

		String jsonData = body.getFirst("data");

		Contacto contacto = (Contacto) JsonUtils.parseString(jsonData, Contacto.class);

		return service.save(contacto);

	}
}
