package controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utils.InstallBean;
import utils.Installator;


@Controller
@RequestMapping(value = "/install")
public class InstallController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ServletContext context;

	@RequestMapping(method = RequestMethod.POST)
    public String installResult(@ModelAttribute(value = "config") InstallBean config, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, HttpSession sess) {
		Installator inst = new Installator(config);
		inst.install();
		System.out.println("success");
		return "install";
	}
	
	
	
	
	
}






