package ffdfafdafa;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class solvefile {
	public static void main(String[] args) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://www.zj12580.cn/doc/list/4");
		HttpResponse response = httpclient.execute(httpget);
		String content = EntityUtils.toString(response.getEntity());
		System.out.print(content);
	}
}
