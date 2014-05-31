package ar.com.bgr.serrano.controller.version;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class VersionHandler {
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String getVersion() {

		return "Api version 0.1";

	}
}
