package com.tyron.common;

import java.util.Properties;

public class ClassLoaderUtil
{
	public static Properties getProperties(String path)
	{
		Properties properties = new Properties();
		try
		{
			properties.load(ClassLoaderUtil.class.getResourceAsStream(path));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return properties;
	}
	
	public static void main(String[] args)
	{
		getProperties("/config/wechat.properties");
	}
}
