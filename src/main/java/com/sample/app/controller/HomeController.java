package com.sample.app.controller;

import static com.sample.app.common.Constant.User._USER_INFO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.app.common.BaseController;
import com.sample.app.domain.UserInfoDomain;
import com.sample.app.service.LoginService;


@Controller
public class HomeController extends BaseController {	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private LoginService loginService;
	
	@RequestMapping(value="/index.do")
	public String index(Model model) throws ServletRequestBindingException {
		logger.info("index");
		
		UserInfoDomain userInfo = new UserInfoDomain();

		model.addAttribute("userInfo", userInfo);
		
		return "index";
	}
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public String login(Model model, @ModelAttribute UserInfoDomain params, BindingResult bindingResult) {
		logger.info("login");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("userInfo", params);
			
			return "redirect:/index.do";
		}
		
		UserInfoDomain userInfo = loginService.login(params);
		
		if (userInfo == null) {
			return "fail";
		} else {
			super.requestSession.getRequest().getSession().setAttribute(_USER_INFO, userInfo);
			
			return "success";
		}
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(Model model) {
		logger.info("logout");
		
		super.requestSession.getRequest().getSession().invalidate();
		
		return "redirect:/index.do";
	}
	
	@RequestMapping(value="/home.do")
	public String home(Model model) {
		logger.info("home");
		
		UserInfoDomain userInfo = (UserInfoDomain) super.requestSession.getRequest().getSession().getAttribute(_USER_INFO);
		if (userInfo == null) return "redirect:/index.do";
		
		return "home";
	}
}
