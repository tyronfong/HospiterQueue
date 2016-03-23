package src;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class RefreshAuthCode extends Thread {
	private DefaultHttpClient httpclient;
	private HttpContext httpcontext;
	private static RefreshAuthCode uniqueInstance = null;
	
	private RefreshAuthCode(){
		httpclient = new DefaultHttpClient();
		httpcontext = MyHttpRequest.getHttpcontext();
	}
	
	public static RefreshAuthCode getInstance(){
		 if (uniqueInstance == null)
			 uniqueInstance = new RefreshAuthCode();
		 else{
			 uniqueInstance.stop();
			 uniqueInstance = new RefreshAuthCode();
		 }
		 return uniqueInstance;
	}
	
	public void run(){
		try {
	        Display.getDefault().asyncExec(new Runnable() {
	            @Override
	            public void run() {
	            	try {
						First.authCodePic.setImage(getImage());
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Image getImage() throws IllegalStateException, IOException{
		StringBuffer date = new StringBuffer(new Date().toString());
		date.replace(20, 23, "UTC");
		date.replace(24, 29, "0800");
		String changecode = new String("http://www.zj12580.cn/authCode.svl?type=captcha&time="+ java.net.URLEncoder.encode(date.toString()));	
		
		HttpGet httpget = new HttpGet(changecode);
		HttpResponse response = httpclient.execute(httpget,httpcontext);

		DataInputStream dis = new DataInputStream(response.getEntity().getContent());
		Image image = new Image(null, dis);
		
		dis.close();	
        httpget.abort();
        
        return image;
	}

}
