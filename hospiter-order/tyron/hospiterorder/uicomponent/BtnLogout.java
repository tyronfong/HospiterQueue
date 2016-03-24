package tyron.hospiterorder.uicomponent;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import tyron.hospiterorder.temp.MyHttpRequest;
import tyron.hospiterorder.temp.RefreshAuthCode;

public class BtnLogout
{
	private Button button;

	public BtnLogout(Shell shell, final TextHint textHint, final TextAuthCode textAuthCode,
			final MyHttpRequest httprequest)
	{

		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					textHint.getText().setText(httprequest.logout());

					// reset the authcodeimage and clear text_authcode
					// lblNewLabel.setImage(httprequest.getImage());
					RefreshAuthCode.getInstance().start();
					textAuthCode.getText().setText("");
				} catch (ClientProtocolException e1)
				{
					e1.printStackTrace();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(559, 125, 80, 27);
		button.setText("退出登录");

	}
}
