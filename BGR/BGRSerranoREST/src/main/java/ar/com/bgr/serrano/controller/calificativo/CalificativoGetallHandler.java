package ar.com.bgr.serrano.controller.calificativo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.bgr.serrano.model.Calificativo;
import ar.com.bgr.serrano.service.CalificativoService;

@Controller
@RequestMapping("/calificativo/list")
public class CalificativoGetallHandler {
	@Autowired
	CalificativoService service;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Calificativo> execute() {
		return service.list();
	}
}
