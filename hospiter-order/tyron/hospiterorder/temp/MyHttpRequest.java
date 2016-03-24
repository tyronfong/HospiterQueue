package tyron.hospiterorder.temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.eclipse.swt.graphics.Image;
import org.json.JSONArray;

public class MyHttpRequest {
	private static HttpContext httpcontext;
	private BasicCookieStore cookiestore;
	private DefaultHttpClient httpclient;
	private String username;
	private String password;
	private String authcode;
	
	private String areaCode;
	private String hosCode;
	private String platCode;
	private String platDocId;
	private String hosName;
	private String deptName;
	private String docName;

	public void setPostInf(String areaCode,String hosCode,String platCode,String platDocId,String hosName,String deptName,String docName){
		this.areaCode =new String(areaCode);
		this.hosCode = new String(hosCode);
		this.platCode = new String(platCode);
		this.platDocId = new String(platDocId);
		this.hosName = new String(hosName);
		this.deptName = new String(deptName);
		this.docName = new String(docName);
	}
	
	public MyHttpRequest() throws ClientProtocolException, IOException{
		cookiestore = new BasicCookieStore();
		httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
		setHttpcontext(new BasicHttpContext());
		
		httpclient.setCookieStore(cookiestore);
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/login");
		getHttpcontext().setAttribute(ClientContext.COOKIE_STORE, cookiestore);
		
		httpclient.execute(httpget,getHttpcontext());
		
		httpget.abort();
		
	}
	
	public String logout() throws ClientProtocolException, IOException{
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/logout");
		HttpResponse response = httpclient.execute(httpget,getHttpcontext());
		
		httpget.abort();
//		return response.getStatusLine().getStatusCode()+"";
//		if(response.getStatusLine().getStatusCode() == 302)
//			return "logout success!";
//		else
//			return "logout maybe success!";
//		
		return "logout success!";
	}
	
	public String getHtmlTable() throws ClientProtocolException, IOException{
		String content = new String();
//		HttpGet httpget = new HttpGet("http://www.zj12580.cn/doc/info?docId="+platDocId);
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/doc/info?docId="+"1256");
		HttpResponse response = httpclient.execute(httpget,getHttpcontext());
		content = EntityUtils.toString(response.getEntity());
		httpget.abort();
		return content;
	}
	
	public String test(String areaCode, String hosCode, String platCode, String platDocId, String hosName, String deptName, String docName) throws ClientProtocolException, IOException{
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();  
        parameters.add(new BasicNameValuePair("hisSchemeId", null));  
//        parameters.add(new BasicNameValuePair("schemeId", "4448187"));
        parameters.add(new BasicNameValuePair("schemeId", "4520222")); 

        parameters.add(new BasicNameValuePair("orderDate", "20150108")); 
        parameters.add(new BasicNameValuePair("hosId", "957102")); 
        parameters.add(new BasicNameValuePair("hosName", "浙江大学医学院附属第二医院")); 
        parameters.add(new BasicNameValuePair("deptId", "5266")); 
        parameters.add(new BasicNameValuePair("deptName", "心血管内科名医")); 
        parameters.add(new BasicNameValuePair("docId", "101")); 
        parameters.add(new BasicNameValuePair("docName", "施育平")); 
        parameters.add(new BasicNameValuePair("regFee", "150")); 
        parameters.add(new BasicNameValuePair("resTimeSign", "1 ")); 
        UrlEncodedFormEntity postentiry = new UrlEncodedFormEntity(parameters);
        
		HttpPost httppost = new HttpPost("http://www.zj12580.cn/order/num");		
		httppost.setEntity(postentiry);
		
		HttpResponse response = httpclient.execute(httppost,getHttpcontext());
		StatusLine statusline = response.getStatusLine();
		Header[] header = response.getAllHeaders();
		HttpEntity entity = response.getEntity();
		
		System.out.println(statusline.getProtocolVersion().toString()+" "+statusline.getStatusCode()+" "+statusline.getReasonPhrase()+"\n");
		
		for(int i = 0; i<header.length; i++){
			System.out.println(header[i].getName()+" : "+header[i].getValue());
		}
		System.out.println();
		
		File file = new File("new5.html");
		if(file.exists())
			file.delete();
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(EntityUtils.toString(entity));
		writer.close();
//		System.out.println(entitytoString(entity));
		
		httppost.abort();	
		//////////////////////////////////////////////////temp///////////////////////////////////////////////
		return "haha";
	}
	
//	public JSONArray getPostmsg(String html,String key){
//		
//	}
	
	public Image getImage() throws IllegalStateException, IOException{
		StringBuffer date = new StringBuffer(new Date().toString());
		date.replace(20, 23, "UTC");
		date.replace(24, 29, "0800");
		String changecode = new String("http://www.zj12580.cn/authCode.svl?type=captcha&time="+ java.net.URLEncoder.encode(date.toString()));	
		
		HttpGet httpget = new HttpGet(changecode);
		HttpResponse response = httpclient.execute(httpget,getHttpcontext());

		DataInputStream dis = new DataInputStream(response.getEntity().getContent());
		Image image = new Image(null, dis);
		
		dis.close();	
        httpget.abort();
        
        return image;
	}
	public String login(String username, String password, String authcode) throws Exception{
		this.username = new String(username);
		this.password = new String(password);
		this.authcode = new String(authcode);
		String errorcode;
		
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();  
        parameters.add(new BasicNameValuePair("username", username));  
        parameters.add(new BasicNameValuePair("password", password)); 
        parameters.add(new BasicNameValuePair("captcha", authcode)); 
        UrlEncodedFormEntity postentiry = new UrlEncodedFormEntity(parameters);
        
		HttpPost httppost = new HttpPost("http://www.zj12580.cn/login");		
		httppost.setEntity(postentiry);

		HttpResponse response = httpclient.execute(httppost,getHttpcontext());

		//define the statusline, headers, entity
		StatusLine statusline = response.getStatusLine();
		Header[] header = response.getAllHeaders();
		HttpEntity entity = response.getEntity();
		
		if(statusline.getStatusCode()==200){
			if(entity!=null){
				errorcode = new String(getError(entity));
				httppost.abort();
				return errorcode;
			}
		}
		
		httppost.abort();

		httppost = new HttpPost(getLocation(header));
		httppost.setEntity(postentiry);
		response = httpclient.execute(httppost,getHttpcontext());
		statusline = response.getStatusLine();
		header = response.getAllHeaders();
		
		if(statusline.getStatusCode()==200){
			if(entity!=null){
				errorcode = new String(getError(entity));
				httppost.abort();
				return errorcode;
			}
		}
		
		httppost.abort();

		httppost = new HttpPost(getLocation(header));
		httppost.setEntity(postentiry);
		response = httpclient.execute(httppost,getHttpcontext());
		statusline = response.getStatusLine();
		header = response.getAllHeaders();
		
		if(statusline.getStatusCode()==200){
			if(entity!=null){
				errorcode = new String(getError(entity));
				httppost.abort();
				return errorcode;
			}
		}
		
		httppost.abort();

		httppost = new HttpPost(getLocation(header));
		httppost.setEntity(postentiry);
		response = httpclient.execute(httppost,getHttpcontext());
		statusline = response.getStatusLine();
		header = response.getAllHeaders();
		
		if(statusline.getStatusCode()==200){
			if(entity!=null){
				errorcode = new String(getError(entity));
				httppost.abort();
				return errorcode;
			}
		}
		
		httppost.abort();
		
		httppost = new HttpPost(getLocation(header));
		httppost.setEntity(postentiry);
		response = httpclient.execute(httppost,getHttpcontext());
		statusline = response.getStatusLine();
		header = response.getAllHeaders();
		entity = response.getEntity();

		System.out.println(statusline.getProtocolVersion().toString()+" "+statusline.getStatusCode()+" "+statusline.getReasonPhrase()+"\n");
		
		for(int i = 0; i<header.length; i++){
			System.out.println(header[i].getName()+" : "+header[i].getValue());
		}
		System.out.println();
		
		
		
//		if(entity!=null){
//			InputStream instream = entity.getContent();
//			int l;
//			byte[] tmp = new byte[2048];
//			
//			while((l=instream.read(tmp))!=-1){
//				System.out.println(new String(tmp));
//			}
//		}
		
//		System.out.print(entitytoString(entity));
		httppost.abort();
		return "Login success";
	}
	
	public String getLocation(Header[] header){		
		for(int i = 0; i<header.length; i++){
			if(header[i].getName().equals("Location"))
				return header[i].getValue();
		}
		
		return "NoLocation";
	}
	
	public String getError(HttpEntity entity) throws IllegalStateException, IOException{
		String str = new String(EntityUtils.toString(entity));
		String a = new String("您输入的验证码有误!");
		String b = new String("您输入的密码不正确!");
		String c = new String("该用户未注册");
		
		if(str.indexOf(a) > 0)
			return a;
		else if(str.indexOf(b)>0)
			return b;
		else if(str.indexOf(c)>0)
			return c;
		return "i don't know";
	}

	public static HttpContext getHttpcontext() {
		return httpcontext;
	}

	public static void setHttpcontext(HttpContext httpcontext) {
		MyHttpRequest.httpcontext = httpcontext;
	}
}
