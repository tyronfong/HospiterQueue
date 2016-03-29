package tyron.hospiterorder.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tyron.hospiterorder.temp.MyHttpRequest;

public class RefreshAuthCodeRunable implements Runnable
{
	private DefaultHttpClient httpclient;
	private HttpContext httpcontext;
	private Label label;
	private static RefreshAuthCodeRunable uniqueInstance = null;
	private ImageLoader loader;
	private ImageData[] imageDatas;
	private Text text;
	private HashMap<String, String> numCache;
	private String picPath;

	private RefreshAuthCodeRunable(Label label)
	{
		this.label = label;
		loader = new ImageLoader();
		imageDatas = new ImageData[1];
		httpclient = new DefaultHttpClient();
		httpcontext = MyHttpRequest.getHttpcontext();
	}

	public static RefreshAuthCodeRunable getInstance(Label label)
	{
		if (uniqueInstance == null)
			uniqueInstance = new RefreshAuthCodeRunable(label);
		else
		{
			uniqueInstance = new RefreshAuthCodeRunable(label);
		}
		return uniqueInstance;
	}

	public void run()
	{
		try
		{
			Display.getDefault().asyncExec(new Runnable()
			{
				public void run()
				{
					try
					{
						saveCurPic();
						label.setImage(getImage());
					} catch (IllegalStateException e)
					{
						e.printStackTrace();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			});

		} catch (IllegalStateException e)
		{
			e.printStackTrace();
		}
	}

	public Image getImage() throws IllegalStateException, IOException
	{
		StringBuffer date = new StringBuffer(new Date().toString());
		date.replace(20, 23, "UTC");
		date.replace(24, 29, "0800");
		String changecode = new String(
				"http://www.zj12580.cn/authCode.svl?type=captcha&time=" + java.net.URLEncoder.encode(date.toString()));

		HttpGet httpget = new HttpGet(changecode);
		HttpResponse response = httpclient.execute(httpget, httpcontext);

		DataInputStream dis = new DataInputStream(response.getEntity().getContent());
		Image image = new Image(null, dis);

		dis.close();
		httpget.abort();

		return image;
	}

	public void saveCurPic()
	{
		if (this.label != null && this.text != null)
		{
			if (numCache.get(this.text.getText()) != null)
			{
				picPath = "allCatPic/" + this.text.getText() + new Random().nextInt() % 10 + ".png";
			} else
			{
				numCache.put(this.text.getText(), "");
				picPath = "allCatPic/" + this.text.getText() + ".png";
			}
			Image image = this.label.getImage();
			if (image != null)
			{
				imageDatas[0] = image.getImageData();
				loader.data = imageDatas;
				loader.save(picPath, SWT.IMAGE_PNG);
				this.text.setText("");
			}
		}
	}

	public String getCurText()
	{
		return text.getText();
	}

	public void setCurText(Text text)
	{
		this.text = text;
	}

	public HashMap<String, String> getNumCache()
	{
		return numCache;
	}

	public void setNumCache(HashMap<String, String> numCache)
	{
		this.numCache = numCache;
	}

}
