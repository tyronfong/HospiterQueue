package com.tyron.receiver.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tyron.common.HibernateDao;
import com.tyron.common.MessageUtils;
import com.tyron.common.WechatProcess;

@Controller
@RequestMapping("receiver")
public class ReceiverController
{
	@Autowired
	HibernateDao dao;

	@RequestMapping("check")
	public void index(String signature, String timestamp, String nonce, String echostr, HttpServletResponse response,
			HttpServletRequest request) throws IOException
	{
		String result = "";
		if (echostr != null && echostr.length() > 1)
		{
			result = echostr;
		} else
		{
			result = new WechatProcess().processWechatMag(MessageUtils.readFromRequest(request));
			if (result.equals(""))
			{
				return;
			}
		}
		try
		{
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8"));
			os.flush();
			os.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
