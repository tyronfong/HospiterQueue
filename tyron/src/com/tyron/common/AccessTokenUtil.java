package com.tyron.common;

public class AccessTokenUtil
{
	private static String accessToken;

	public static String getAccessToken()
	{
		return accessToken;
	}

	public static void setAccessToken(String accessToken)
	{
		AccessTokenUtil.accessToken = accessToken;
	}

}
