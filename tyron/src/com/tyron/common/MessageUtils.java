package com.tyron.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class MessageUtils
{
	public static String readFromRequest(HttpServletRequest request) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			sb.append(line);
		}
		return sb.toString();
	}
}
