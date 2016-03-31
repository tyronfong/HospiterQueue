package com.tyron.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.tyron.common.HibernateDao;

@Controller
@RequestMapping("receiver")
public class TestController {
	@Autowired
	HibernateDao dao;

	@RequestMapping("check")
	public void index(String signature, String timestamp, String nonce, String echostr,HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.println(echostr);
		System.out.println(echostr);
	}

}
