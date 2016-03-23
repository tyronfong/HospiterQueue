/**
 * 
 */
package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Administrator
 *113.32461,23.098776
 *113.480412,23.004867
 */
public class testsessions {
	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException, JSONException{
		////////////////////////////////////////////////////////////////////////test login.php
		
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		HttpPost post;
		HttpResponse res;
		UrlEncodedFormEntity postentiry;
		DefaultHttpClient hc = new DefaultHttpClient();
//		
//		for(int i = 1095; i < 1096; i++){
//			DefaultHttpClient hc = new DefaultHttpClient();
//			Random rand = new Random();
//			float x = (float) (rand.nextFloat()/6.45+113.32461);
//			float y = (float) (rand.nextFloat()/10.638+23.004867);
//			
//			parameters.clear();
//			parameters.add(new BasicNameValuePair("phonenum", "1356032"+i));
//			parameters.add(new BasicNameValuePair("password", "123456"));
//			parameters.add(new BasicNameValuePair("nickname", "fong"));
//			postentiry = new UrlEncodedFormEntity(parameters);
//			post = new HttpPost("http://1.eesos.sinaapp.com/regist.php");
//			post.setEntity(postentiry);
//			res = hc.execute(post);
//			System.out.println(i+EntityUtils.toString(res.getEntity()));
//			post.abort();
//		
//			
//			parameters.clear();
//			parameters.add(new BasicNameValuePair("phonenum", "1356032"+i));
//			parameters.add(new BasicNameValuePair("password", "123456"));
//			parameters.add(new BasicNameValuePair("needinfo", "0"));
//			postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);	
//			post = new HttpPost("http://1.eesos.sinaapp.com/login.php");
//			post.setEntity(postentiry);		
//			res = hc.execute(post);
//			System.out.println(i+EntityUtils.toString(res.getEntity()));
//			post.abort();
//			
//			parameters.clear();
//			parameters.add(new BasicNameValuePair("posx", y+""));
//			parameters.add(new BasicNameValuePair("posy", x+""));
//			postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
//			post = new HttpPost("http://1.eesos.sinaapp.com/setcurrentpos.php");
//			post.setEntity(postentiry);		
//			res = hc.execute(post);
//			System.out.println(EntityUtils.toString(res.getEntity()));
//			post.abort();
//			
//			hc = null;
//			System.gc();
//		}
			
		
		parameters.clear();
		parameters.add(new BasicNameValuePair("phonenum", "13560321001"));
		parameters.add(new BasicNameValuePair("password", "123456"));
		parameters.add(new BasicNameValuePair("needinfo", "0"));
		postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);	
		post = new HttpPost("http://1.eesos.sinaapp.com/login.php");
		post.setEntity(postentiry);		
		res = hc.execute(post);
		System.out.println(EntityUtils.toString(res.getEntity()));
		post.abort();
		
		parameters.clear();
		parameters.add(new BasicNameValuePair("text", "somebody"));
		parameters.add(new BasicNameValuePair("locx", "23.057356"));
		parameters.add(new BasicNameValuePair("locy", "113.391447"));
		parameters.add(new BasicNameValuePair("locinfo", "Guangdong"));
		postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
		post = new HttpPost("http://1.eesos.sinaapp.com/sos.php");
		post.setEntity(postentiry);		
		res = hc.execute(post);
		System.out.println(EntityUtils.toString(res.getEntity()));
		post.abort();

		parameters.clear();
		parameters.add(new BasicNameValuePair("sosid", "2"));
		postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
		post = new HttpPost("http://1.eesos.sinaapp.com/getsosinfo.php");
		post.setEntity(postentiry);		
		res = hc.execute(post);
//		System.out.println(res.getStatusLine());
		System.out.println(EntityUtils.toString(res.getEntity()));
		post.abort();

//		parameters.clear();
//		parameters.add(new BasicNameValuePair("text", "有人要绑架我"));
//		postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
//		post = new HttpPost("http://1.eesos.sinaapp.com/sos.php");
//		
//		post.setEntity(postentiry);		
//		res = hc.execute(post);
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		post.abort();
//		
//		Scanner in = new Scanner(System.in);
//		int i = in.nextInt();
//		if(i == 0){
//			parameters.clear();
//			parameters.add(new BasicNameValuePair("sosid", "1"));
//			postentiry = new UrlEncodedFormEntity(parameters,HTTP.UTF_8);
//			post = new HttpPost("http://1.eesos.sinaapp.com/getsostext.php");
//			post.setEntity(postentiry);		
//			res = hc.execute(post);
//			System.out.println(EntityUtils.toString(res.getEntity()));
//			post.abort();
//		}
		
//		parameters.clear();
//		parameters.add(new BasicNameValuePair("age", "22"));
//		parameters.add(new BasicNameValuePair("gender", "1"));
//		parameters.add(new BasicNameValuePair("profession", "worker"));
//		postentiry = new UrlEncodedFormEntity(parameters);		
//		post = new HttpPost("http://1.eesos.sinaapp.com/setpersoninfo.php");
//		post.setEntity(postentiry);
//		res = hc.execute(post);		
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		post.abort();
		
//		parameters.clear();
//		parameters.add(new BasicNameValuePair("x1", "23.074076"));
//		parameters.add(new BasicNameValuePair("x2", "23.048577"));
//		parameters.add(new BasicNameValuePair("y1", "113.383655"));
//		parameters.add(new BasicNameValuePair("y2", "113.399877"));
//		postentiry = new UrlEncodedFormEntity(parameters);		
//		post = new HttpPost("http://1.eesos.sinaapp.com/getsurround.php");
//		post.setEntity(postentiry);
//		res = hc.execute(post);	
//		System.out.println(EntityUtils.toString(res.getEntity()));
		
//		JSONObject a = new JSONObject(EntityUtils.toString(res.getEntity()));
//		System.out.println(a.get("State"));
//		if(a.length()>1){
//			for(int i = 0;i<a.length()-1;i++){
//				System.out.println(((JSONObject) a.get(""+i)).get("Userid"));
//				System.out.print(((JSONObject) a.get(""+i)).get("x")+"   ");
//				System.out.println(((JSONObject) a.get(""+i)).get("y"));
//			}
//		}
//		post.abort();
//		
//		HttpGet httpget = new HttpGet("http://1.eesos.sinaapp.com/getallinfo.php");
//		res = hc.execute(httpget);
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		
//		Scanner in = new Scanner(System.in);
//		int i = in.nextInt();
//		if(i == 0){
//			httpget = new HttpGet("http://1.eesos.sinaapp.com/logout.php");
//			res = hc.execute(httpget);
//			System.out.println(EntityUtils.toString(res.getEntity()));
//		}
//		
//		parameters.clear();
//		parameters.add(new BasicNameValuePair("age", "22"));
//		parameters.add(new BasicNameValuePair("gender", "1"));
//		parameters.add(new BasicNameValuePair("profession", "worker"));
//		postentiry = new UrlEncodedFormEntity(parameters);		
//		post = new HttpPost("http://1.eesos.sinaapp.com/setpersoninfo.php");
//		post.setEntity(postentiry);
//		res = hc.execute(post);		
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		post.abort();
		
//		post.abort();
////////////////////////////////////////////////////////////////////////test regist.php
//		DefaultHttpClient hc = new DefaultHttpClient();
//		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
//		parameters.add(new BasicNameValuePair("phonenum", "13560323371"));
//		parameters.add(new BasicNameValuePair("password", "123456789"));
//		parameters.add(new BasicNameValuePair("nickname", "hehe"));
//		UrlEncodedFormEntity postentiry = new UrlEncodedFormEntity(parameters);
//		
////		HttpPost post = new HttpPost("http://1.esos.sinaapp.com/test.php");
//		HttpPost post = new HttpPost("http://1.eesos.sinaapp.com/regist.php");
//		post.setEntity(postentiry);
//		
//		HttpResponse res = hc.execute(post);
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		
//		post.abort();
//////////////////////////////////////////////////////////////////////////test session
//		DefaultHttpClient hc = new DefaultHttpClient();
//		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
//		parameters.add(new BasicNameValuePair("phonenum", "13560323371"));
//		parameters.add(new BasicNameValuePair("password", "123456789"));
//		parameters.add(new BasicNameValuePair("nickname", "hehe"));
//		UrlEncodedFormEntity postentiry = new UrlEncodedFormEntity(parameters);
//		
////		HttpPost post = new HttpPost("http://1.esos.sinaapp.com/test.php");
//		HttpPost post = new HttpPost("http://1.eesos.sinaapp.com/regist.php");
//		post.setEntity(postentiry);
//		
//		HttpResponse res = hc.execute(post);
//		System.out.println(EntityUtils.toString(res.getEntity()));
//		
//		post.abort();
		
	}
}
