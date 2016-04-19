package com.tyron.common;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.entity.ReceiveXmlEntity;

public class WechatProcess
{
	public String processWechatMag(String xml) throws ClientProtocolException, IOException
	{
		System.out.println(xml);
		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);
		String result = "请输入正确内容";
		System.out.println(AccessTokenUtil.getAccessToken());

		if (xmlEntity.getMsgType().equals("event") && xmlEntity.getEventKey().equals("bindId"))
		{
			result = "请回复邀请码";
		}
		if (xmlEntity.getMsgType().equals("event") && xmlEntity.getEventKey().equals("sendMsg"))
		{
			new TemplateMsgProcess(xmlEntity.getFromUserName(), "title", "locationContent").run();
			return "success";
		}
		if (xmlEntity.getMsgType().equals("text"))
		{
			result = "绑定成功";
		}

		result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);
		return result;
	}
}
