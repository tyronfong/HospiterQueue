package tyron.hospiterorder.temp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {
	public static HttpClient httpclient = new DefaultHttpClient();
	
	public void login() throws ClientProtocolException, IOException{
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/login");
		HttpResponse response = httpclient.execute(httpget);
		StatusLine statusline = response.getStatusLine();
		Header[] header = response.getAllHeaders();
		
		//get uri of authcode
		StringBuffer date = new StringBuffer(new Date().toString());
		date.replace(20, 23, "UTC");
		date.replace(24, 29, "0800");
		String changecode = new String("http://www.zj12580.cn/authCode.svl?type=captcha&time="+ date);

		
	}
	
	public static void main(String[] args) throws IOException, IOException, URISyntaxException{
		//define a window
		JFrame frame = new JFrame("maimaimai");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//define buttons
		JButton jbtok1 = new JButton("提交");
		jbtok1.setSize(50,20);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(0));
		panel1.add(jbtok1,BorderLayout.NORTH);
		
		frame.add(panel1);
		
//		HttpGet httpget = new HttpGet("http://www.zj12580.cn/login");
//		HttpResponse response = httpclient.execute(httpget);
//		HttpEntity entity = response.getEntity();
//		Header[] header = response.getAllHeaders();
//		StatusLine statusline = response.getStatusLine();
		
//		System.out.println(statusline.getProtocolVersion().toString()+" "+statusline.getStatusCode()+" "+statusline.getReasonPhrase()+"\n");
//		
//		for(int i = 0; i<header.length; i++){
//			System.out.println(header[i].getName()+" : "+header[i].getValue());
//		}
//		System.out.println();
//		
//		if(entity!=null){
//			InputStream instream = entity.getContent();
//			int l;
//			byte[] tmp = new byte[2048];
//			
//			while((l=instream.read(tmp))!=-1){
//				System.out.println(new String(tmp));
//			}
//			
//		}

		
//		HttpPost httppost = new HttpPost("j");
//		httpclient.execute(httppost);
	}
}
