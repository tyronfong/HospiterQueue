package tyron.hospiterorder.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil
{
	public static String filetoString(File file) throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		StringBuffer strbuffer = new StringBuffer();
		try
		{
			String line = null;

			while ((line = reader.readLine()) != null)
			{
				strbuffer.append(line);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			fileInputStream.close();
			inputStreamReader.close();
			reader.close();
		}
		return strbuffer.toString();
	}
}
