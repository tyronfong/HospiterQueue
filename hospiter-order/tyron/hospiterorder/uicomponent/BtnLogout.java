package tyron.hospiterorder.uicomponent;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import tyron.hospiterorder.common.RefreshAuthCode;
import tyron.hospiterorder.temp.MyHttpRequest;

public class BtnLogout
{
	private Button button;

	public BtnLogout(Shell shell, final TextHint textHint, final TextAuthCode textAuthCode,
			final MyHttpRequest httpRequest, final LabelAuthCode labelAuthCode)
	{

		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					textHint.getText().setText(httpRequest.logout());

					// reset the authcodeimage and clear text_authcode
					// lblNewLabel.setImage(httprequest.getImage());
					RefreshAuthCode.getInstance(labelAuthCode.getLabel()).start();
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
