package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocInfo {
	private DefaultHttpClient httpclient = new DefaultHttpClient();
	private String address = "http://192.168.11.1/cgi-bin/luci";
	public static void main(String[] args){
		new DocInfo().run();
	}
	
	public void run(){
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/area");
		StringBuffer str = new StringBuffer();
		JSONObject jsonobj;
		
		try {
			HttpResponse response = httpclient.execute(httpget);
			
			jsonobj = new JSONObject(EntityUtils.toString(response.getEntity()));
			
			if(jsonarryrtoFile(jsonobj.getJSONObject("data").getJSONArray("areaList"),"hosdata/area/areacode.txt")){
				System.out.println("1");
				if(areafiletoHosfile("hosdata/area/areacode.txt")){
					System.out.println("2");
					getAlldeptfile("hosdata/area/areacode.txt");
					System.out.println("3");
					getAlldocsfile("hosdata/area/areacode.txt");
					System.out.println("4");
				}
			}
		
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getAlldeptfile(String areafile) throws JSONException, IOException{
		File file = new File(areafile);
		if(!file.exists())
			return false;
		
		JSONArray areajson = new JSONArray(filetoString(file));
		
		for(int i = 0; i < areajson.length(); i++){
			String areacode = new String(areajson.getJSONObject(i).getString("areaCode"));
			hosfiletoDeptfile("hosdata/hos/"+areacode+".txt");
		}
		
		return true;
	}
	
	public boolean getAlldocsfile(String areafile) throws JSONException, IOException{
		File file = new File(areafile);
		if(!file.exists())
			return false;
		
		JSONArray areajson = new JSONArray(filetoString(file));
		
		for(int i = 0; i < areajson.length(); i++){
			String areacode = new String(areajson.getJSONObject(i).getString("areaCode"));
			hosfiletoDocfile("hosdata/hos/"+areacode+".txt");
		}
		
		return true;
	}
	
	public boolean hosfiletoDocfile(String hosfile) throws JSONException, IOException{
		File file = new File(hosfile);
		if(!file.exists())
			return false;
		
		JSONArray hosjson = new JSONArray(filetoString(file));
		JSONObject jsonobj = null;
		
		for(int i = 0; i < hosjson.length(); i++){
			String hoscode = new String(hosjson.getJSONObject(i).getString("hosCode"));
			File docfile = new File("hosdata/doc/"+hoscode+".txt");
			if(docfile.exists())
				docfile.delete();
			docfile.createNewFile();
			HttpGet httpget = new HttpGet("http://www.zj12580.cn/doc/list/"+hoscode);
			HttpResponse response = httpclient.execute(httpget);
			
			jsonobj = new JSONObject(EntityUtils.toString(response.getEntity()));
			httpget.abort();
			
			jsonarryrtoFile(jsonobj.getJSONObject("data").getJSONArray("docs"),"hosdata/doc/"+hoscode+".txt");
		}
		
		return true;
		
	}
	
	public boolean hosfiletoDeptfile(String hosfile) throws JSONException, IOException{
		File file = new File(hosfile);
		if(!file.exists())
			return false;
		
		JSONArray hosjson = new JSONArray(filetoString(file));
		JSONObject jsonobj = null;
		
		for(int i = 0; i < hosjson.length(); i++){
			String hoscode = new String(hosjson.getJSONObject(i).getString("hosCode"));
			File deptfile = new File("hosdata/dept/"+hoscode+".txt");
			if(deptfile.exists())
				deptfile.delete();
			deptfile.createNewFile();
			HttpGet httpget = new HttpGet("http://www.zj12580.cn/dept/list/"+hoscode);
			HttpResponse response = httpclient.execute(httpget);
			
			jsonobj = new JSONObject(EntityUtils.toString(response.getEntity()));
			httpget.abort();
			
			jsonarryrtoFile(jsonobj.getJSONObject("data").getJSONArray("dept"),"hosdata/dept/"+hoscode+".txt");
		}
		
		return true;
		
	}
	public boolean areafiletoHosfile(String areafile) throws JSONException, IOException{
		File file = new File(areafile);
		if(!file.exists())
			return false;
		
		JSONArray areajson = new JSONArray(filetoString(file));
		JSONObject jsonobj = null;
		
		for(int i = 0; i < areajson.length(); i++){
			String areacode = new String(areajson.getJSONObject(i).getString("areaCode"));
			File hosfile = new File("hosdata/hos/"+areacode+".txt");
			if(hosfile.exists())
				hosfile.delete();
			hosfile.createNewFile();
			HttpGet httpget = new HttpGet("http://www.zj12580.cn/hos/list/"+areacode);
			HttpResponse response = httpclient.execute(httpget);
			
			jsonobj = new JSONObject(EntityUtils.toString(response.getEntity()));
			httpget.abort();
			
			jsonarryrtoFile(jsonobj.getJSONObject("data").getJSONArray("hos"),"hosdata/hos/"+areacode+".txt");
			
		}
		
		return true;
	}
	
	public boolean jsonarryrtoFile(JSONArray jsonarray, String filename) throws IOException, JSONException{
		File file = new File(filename);
		if(file.exists())
			file.delete();
		file.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		jsonarray.write(writer);
	
		writer.close();
		
		return true;
	}
	
	public String filetoString(File file) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		StringBuffer strbuffer = new StringBuffer();
		
		while ((line = reader.readLine()) != null)
		{
			strbuffer.append(line);
		}
		
		reader.close();
		return strbuffer.toString();
	}
	
//	public String entitytoString(HttpEntity entity) throws IllegalStateException, IOException{
//		StringBuffer out = new StringBuffer();
//		
//		if(entity!=null){
//			InputStream instream = entity.getContent();
//			byte[] b = new byte[1024];
//			
//			for (int n; (n = instream.read(b)) != -1;) {
//				out.append(new String(b, 0, n));
//			}
//		}
//		
//		return out.toString();
//	}
}
