package com.tyron.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpRequestService
{
	private static MultiThreadedHttpConnectionManager connectManager = new MultiThreadedHttpConnectionManager();

	public static void doGet(String url)
	{
		HttpClient client = new HttpClient(connectManager);
		GetMethod getMethod = new GetMethod(url);
		try
		{
			client.executeMethod(getMethod);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			getMethod.releaseConnection();
		}
	}

	public static void doPost(String url, String stringEntityContent) throws UnsupportedEncodingException
	{
		HttpClient client = new HttpClient(connectManager);
		PostMethod postMethod = new PostMethod(url);
		StringRequestEntity requestEntity;
		requestEntity = new StringRequestEntity(stringEntityContent, "text/xml", "UTF-8");
		postMethod.setRequestEntity(requestEntity);
		try
		{
			System.out.println(client.executeMethod(postMethod));
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			postMethod.releaseConnection();
		}
	}
}
