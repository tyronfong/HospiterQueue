package tyron.hospiterorder.uicomponent;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import tyron.hospiterorder.temp.MyHttpRequest;

public class BtnOk
{
	private Button button;

	public BtnOk(Shell shell, final MyHttpRequest httprequest)
	{
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					Document doc = Jsoup.parse(httprequest.getHtmlTable());
					Elements form = doc.select("[name=orderInfo]");
					System.out.println(form.get(0));

				} catch (ClientProtocolException e2)
				{
					e2.printStackTrace();
				} catch (IOException e2)
				{
					e2.printStackTrace();
				}
			}
		});
		button.setBounds(313, 355, 131, 56);
		button.setText("确定");
	}
}
