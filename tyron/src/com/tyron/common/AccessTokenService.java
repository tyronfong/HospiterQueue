package com.tyron.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class AccessTokenService
{
	static String appID;
	static String appsecret;
	static String preUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	static String tempUrl;

	static
	{
		String temp = null;
		try
		{
			Properties pps = ClassLoaderUtil.getProperties("/config/wechat.properties");
			appID=pps.getProperty("wechat.base.appid");
			appsecret=pps.getProperty("wechat.base.appsecret");
			tempUrl = String.format(preUrl, appID, appsecret);
			
			URL url = new URL(tempUrl);
			URLConnection conn = url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			temp = br.readLine();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		JSONObject j = JSONObject.fromObject(temp);
		AccessTokenUtil.setAccessToken(j.getString("access_token"));
	}

	@Scheduled(cron = "0 0 * * * ?")
	private void refreshAccessToken()
	{
		String temp = null;
		try
		{
			URL url = new URL(tempUrl);
			URLConnection conn = url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			temp = br.readLine();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		JSONObject j = JSONObject.fromObject(temp);
		AccessTokenUtil.setAccessToken(j.getString("access_token"));
		System.out.println("----------------------------------------------------------------------------------");
	}
}
