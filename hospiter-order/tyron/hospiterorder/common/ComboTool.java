package tyron.hospiterorder.common;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

public class ComboTool
{
	public static String[] fileToAreaItems(String areaFilePath) throws JSONException, IOException
	{
		File areafile = new File(areaFilePath);
		if (!areafile.exists())
		{
			return null;
		}

		JSONArray areajson = new JSONArray(FileUtil.filetoString(areafile));
		int len = areajson.length();
		String[] area = new String[len];

		for (int i = 0; i < len; i++)
		{
			area[i] = areajson.getJSONObject(i).getString("areaName");
		}

		return area;
	}

	public static String[] fileToHosItems(String hosfilepath) throws JSONException, IOException
	{
		File hosfile = new File(hosfilepath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(FileUtil.filetoString(hosfile));
		int len = hosjson.length();
		String[] hosnames = new String[len];

		for (int i = 0; i < len; i++)
		{
			hosnames[i] = hosjson.getJSONObject(i).getString("aliasName");
		}

		return hosnames;
	}

	public static String[] fileToDeptItems(String deptFilePath) throws JSONException, IOException
	{
		File hosfile = new File(deptFilePath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(FileUtil.filetoString(hosfile));
		int len = hosjson.length();
		String[] deptnames = new String[len];

		for (int i = 0; i < len; i++)
		{
			deptnames[i] = hosjson.getJSONObject(i).getString("deptName");
		}

		return deptnames;
	}

	public static String[] fileToDocItems(String docFilePath) throws JSONException, IOException
	{

		File hosfile = new File(docFilePath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(FileUtil.filetoString(hosfile));
		int len = hosjson.length();
		String[] docnames = new String[len];

		for (int i = 0; i < len; i++)
		{
			docnames[i] = hosjson.getJSONObject(i).getString("docName");
		}

		return docnames;

	}
}
