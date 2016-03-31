package com.tyron.signon.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SignonController {

	@RequestMapping("login.op")
	public String login() {
		return "/signon/signon";
	}

	@RequestMapping("dologin.op")
	public ModelAndView doLogin(String userName, String password, HttpServletRequest request) {
		ModelAndView signonPage = new ModelAndView("/signon/signon");

		UsernamePasswordToken userToken = new UsernamePasswordToken(userName, password.toCharArray());
		try {
			SecurityUtils.getSubject().login(userToken);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return signonPage;
		}

		return new ModelAndView(new RedirectView(request.getContextPath()));
	}
}
