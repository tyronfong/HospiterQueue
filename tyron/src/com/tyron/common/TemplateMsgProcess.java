package com.tyron.common;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

public class TemplateMsgProcess implements Runnable
{
	private static String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	private static String templateId;

	static
	{
		templateId = ClassLoaderUtil.getProperties("/config/wechat.properties")
				.getProperty("wechat.template.templateid");
	}

	private String openId;
	private String title;
	private String locationContent;

	public TemplateMsgProcess(String openId, String title, String locationContent)
	{
		this.openId = openId;
		this.title = title;
		this.locationContent = locationContent;
	}

	public void run()
	{
		try
		{
			HttpRequestService.doPost(url + AccessTokenUtil.getAccessToken(),
					getTemplateJson(openId, title, locationContent));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	private String getTemplateJson(String openId, String title, String locationContent)
			throws UnsupportedEncodingException
	{
		JSONObject object1 = new JSONObject();
		JSONObject object2 = new JSONObject();
		JSONObject object3 = new JSONObject();
		JSONObject object4 = new JSONObject();
		object3.put("value", title);
		object3.put("color", "#173177");
		object4.put("value", locationContent);
		object4.put("color", "#173177");
		object2.put("first", object3);
		object2.put("keyword1", object4);
		object1.put("touser", openId);
		object1.put("template_id", templateId);
		object1.put("url", "http://weixin.qq.com/download");
		object1.put("data", object2);
		System.out.println(object1.toString()+"    "+AccessTokenUtil.getAccessToken());
		return object1.toString();
	}
}
