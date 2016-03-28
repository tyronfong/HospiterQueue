package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import tyron.hospiterorder.common.RefreshAuthCode;
import tyron.hospiterorder.temp.MyHttpRequest;

public class BtnLogin
{
	private Button button;
	private String errorCode;

	public BtnLogin(Shell shell, final TextUserName textUserName, final TextHint textHint,
			final TextPassword textPassword, final TextAuthCode textAuthCode, final MyHttpRequest httpRequest,
			final LabelAuthCode labelAuthCode)
	{
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if (textUserName.getText().getText().equals(""))
					textHint.getText().setText("账号不能为空");
				else if (textPassword.getText().equals(""))
					textHint.getText().setText("密码不能为空");
				else if (textAuthCode.getText().equals(""))
					textHint.getText().setText("验证码不能为空");
				else
					try
					{
						errorCode = new String(httpRequest.login(textUserName.getText().getText(),
								textPassword.getText().getText(), textAuthCode.getText().getText()));
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}
				textHint.getText().setText(errorCode);
				RefreshAuthCode.getInstance(labelAuthCode.getLabel()).start();
				textAuthCode.getText().setText("");
			}
		});
		button.setBounds(313, 238, 80, 27);
		button.setText("登陆");
	}

	public Button getButton()
	{
		return button;
	}

	public void setButton(Button button)
	{
		this.button = button;
	}

}
