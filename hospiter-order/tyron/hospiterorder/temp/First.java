package tyron.hospiterorder.temp;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import tyron.hospiterorder.uicomponent.BtnLogin;
import tyron.hospiterorder.uicomponent.BtnLogout;
import tyron.hospiterorder.uicomponent.BtnOk;
import tyron.hospiterorder.uicomponent.BtnRefresh;
import tyron.hospiterorder.uicomponent.ComboAreaCode;
import tyron.hospiterorder.uicomponent.ComboDept;
import tyron.hospiterorder.uicomponent.ComboDoc;
import tyron.hospiterorder.uicomponent.ComboHospital;
import tyron.hospiterorder.uicomponent.LabelAuthCode;
import tyron.hospiterorder.uicomponent.TextAuthCode;
import tyron.hospiterorder.uicomponent.TextAuthCodeLabel;
import tyron.hospiterorder.uicomponent.TextHint;
import tyron.hospiterorder.uicomponent.TextPassword;
import tyron.hospiterorder.uicomponent.TextPasswordLabel;
import tyron.hospiterorder.uicomponent.TextUserName;
import tyron.hospiterorder.uicomponent.TextUserNameLabel;

//	http://www.zj12580.cn/login
public class First
{
	public MyHttpRequest httpRequest;

	protected Shell shell;

	private TextUserName textUserName;
	private TextUserNameLabel textUserNameLabel;
	private TextPassword textPassword;
	private TextPasswordLabel textPasswordLabel;
	private TextAuthCode textAuthCode;
	private TextAuthCodeLabel textAuthCodeLabel;
	private TextHint textHint;

	private ComboAreaCode comboAreaCode;
	private ComboHospital comboHospital;
	private ComboDept comboDept;
	private ComboDoc comboDoc;

	private BtnLogin btnLogin;
	private BtnRefresh btnRefresh;
	private BtnLogout btnLogout;
	private BtnOk btnOk;

	private LabelAuthCode labelAuthCode;

	public Label authCodePic;

	public First() throws ClientProtocolException, IOException
	{
		httpRequest = new MyHttpRequest();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createContents() throws Exception
	{
		initShell();

		labelAuthCode = new LabelAuthCode(shell);

		textUserName = new TextUserName(shell);
		textUserNameLabel = new TextUserNameLabel(shell);
		textPassword = new TextPassword(shell);
		textPasswordLabel = new TextPasswordLabel(shell);
		textAuthCode = new TextAuthCode(shell);
		textAuthCodeLabel = new TextAuthCodeLabel(shell);
		textHint = new TextHint(shell);

		btnLogin = new BtnLogin(shell, textUserName, textHint, textPassword, textAuthCode, httpRequest, labelAuthCode);
		btnRefresh = new BtnRefresh(shell, labelAuthCode);
		btnLogout = new BtnLogout(shell, textHint, textAuthCode, httpRequest, labelAuthCode);
		btnOk = new BtnOk(shell, httpRequest);

		comboDept = new ComboDept(shell);
		comboDoc = new ComboDoc(shell);
		comboHospital = new ComboHospital(shell);
		comboAreaCode = new ComboAreaCode(shell, comboHospital);
		
		comboDept.attachHosAction(comboHospital);
		comboDoc.attachHosAction(comboHospital);
		comboHospital.attachAction(comboAreaCode, comboDept, comboDoc);
	}

	public void open() throws Exception
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	private void initShell()
	{
		shell = new Shell();
		shell.setSize(775, 471);
		shell.setLocation(500, 100);
		shell.setText("SWT Application");
	}

}
