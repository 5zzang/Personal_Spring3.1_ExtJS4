package com.sample.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.app.domain.UserInfoDomain;


@Controller
public class RootController {
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@RequestMapping(value="/")
	public String root(Model model) {
		logger.info("root");
		
		if (model.containsAttribute("userInfo") == false) {
			model.addAttribute("userInfo", new UserInfoDomain());
		}
		
		return "index";
	}
}