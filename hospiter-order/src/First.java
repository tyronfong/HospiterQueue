package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.json.JSONArray;
import org.json.JSONException;
import org.eclipse.swt.browser.Browser;

//	http://www.zj12580.cn/login
public class First {
	private final static String ID_NUM = "330726199308091519";
	private final static String PASSWORD = "w84922575";
	public MyHttpRequest httprequest;
	public String errorcode;

	private static String areaCode;
	private static String hosCode;
	private static String platCode;
	private static String platDocId;
	private static String hosName;
	private static String deptName;
	private static String docName;

	private static String tableHtml;

	protected Shell shell;

	private Text text_username;
	private Text text_password;
	private Text text_2;
	private Text text_3;
	private Text text_authcode;
	private Text text_5;
	private Text text_hint;

	private Combo comboDoctorList;
	private Combo comboHospitalList;
	private Combo comboDeptList;
	private Combo comboAreaCodeList;

	private Button btnLogin;
	private Button btnrefresh;
	private Button btnNewButton;
	private Button btnLogout;
	private Button btnOk;
	private Button btnNewButton_1;

	private Browser browser;

	public static Label authCodePic;

	public First() throws ClientProtocolException, IOException {
		httprequest = new MyHttpRequest();
	}

	public void createContents() throws Exception {
		initShell();
		initTextUsername();
		initTextPassword();
		initTextAuthCode();
		initTextHint();
		initLabelAuthCode();
		initBtnLogin();
		initTextLabel();
		initBtnRefresh();
		initComboDeptList();
		initComboDoctorList();
		initComboHospitalList();
		initComboAreaCodeList();
		initBtnNewButton();
		initBtnLogout();
		initBrowser();
		initBtnOk();
		initBtnNewButton1();
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	public String getLocation(Header[] header) {
		for (int i = 0; i < header.length; i++) {
			if (header[i].getName().equals("Location"))
				return header[i].getValue();
		}

		return "NoLocation";
	}

	/**
	 * Open the window.
	 * 
	 * @throws Exception
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws JSONException
	 */
	public void open() throws Exception {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void hideFirst() {
		text_2.setVisible(false);
		text_3.setVisible(false);
		text_5.setVisible(false);
		text_authcode.setVisible(false);
		text_hint.setVisible(false);
		text_password.setVisible(false);
		text_username.setVisible(false);
		authCodePic.setVisible(false);
		btnLogin.setVisible(false);
		btnrefresh.setVisible(false);
		comboDeptList.setVisible(false);
		comboDoctorList.setVisible(false);
		comboAreaCodeList.setVisible(false);
		btnNewButton.setVisible(false);
		btnLogout.setVisible(false);
		comboHospitalList.setVisible(false);

		browser.setVisible(true);
	}

	private String filetoString(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		StringBuffer strbuffer = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			strbuffer.append(line);
		}

		reader.close();
		return strbuffer.toString();
	}

	private String[] getAreaStr(String areafilepath) throws JSONException, IOException {
		File areafile = new File(areafilepath);
		if (!areafile.exists())
			return null;

		JSONArray areajson = new JSONArray(filetoString(areafile));
		int len = areajson.length();
		String[] area = new String[len];

		for (int i = 0; i < len; i++) {
			area[i] = areajson.getJSONObject(i).getString("areaName");
		}

		return area;
	}

	private void setPostAreaInf(String areafilepath, int index) throws JSONException, IOException {
		File areafile = new File(areafilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(filetoString(areafile));

		areaCode = areajson.getJSONObject(index).getString("areaCode");
	}

	private String[] getHosStr(String hosfilepath) throws JSONException, IOException {
		File hosfile = new File(hosfilepath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(filetoString(hosfile));
		int len = hosjson.length();
		String[] hosnames = new String[len];

		for (int i = 0; i < len; i++) {
			hosnames[i] = hosjson.getJSONObject(i).getString("aliasName");
		}

		return hosnames;
	}

	private void setPostHosInf(String hosfilepath, int index) throws JSONException, IOException {
		File areafile = new File(hosfilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(filetoString(areafile));

		platCode = areajson.getJSONObject(index).getString("platCode");
		hosName = areajson.getJSONObject(index).getString("hosName");
		hosCode = areajson.getJSONObject(index).getString("hosCode");
	}

	private String[] getDeptStr(String deptfilepath) throws JSONException, IOException {
		File hosfile = new File(deptfilepath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(filetoString(hosfile));
		int len = hosjson.length();
		String[] deptnames = new String[len];

		for (int i = 0; i < len; i++) {
			deptnames[i] = hosjson.getJSONObject(i).getString("deptName");
		}

		return deptnames;
	}

	private void setPostDeptInf(String deptfilepath, int index) throws JSONException, IOException {
		File areafile = new File(deptfilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(filetoString(areafile));

		deptName = areajson.getJSONObject(index).getString("deptName");
	}

	private String[] getDocStr(String docfilepath) throws JSONException, IOException {
		File hosfile = new File(docfilepath);
		if (!hosfile.exists())
			return null;

		JSONArray hosjson = new JSONArray(filetoString(hosfile));
		int len = hosjson.length();
		String[] docnames = new String[len];

		for (int i = 0; i < len; i++) {
			docnames[i] = hosjson.getJSONObject(i).getString("docName");
		}

		return docnames;
	}

	private void setPostDocInf(String docfilepath, int index) throws JSONException, IOException {
		File areafile = new File(docfilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(filetoString(areafile));

		docName = areajson.getJSONObject(index).getString("docName");
		platDocId = areajson.getJSONObject(index).getString("platDocId");
	}

	private void initShell() {
		shell = new Shell();
		shell.setSize(775, 471);
		shell.setLocation(500, 100);
		shell.setText("SWT Application");
	}

	private void initTextUsername() {
		text_username = new Text(shell, SWT.BORDER);
		text_username.setText(ID_NUM);
		text_username.setBounds(302, 56, 214, 27);
	}

	private void initTextPassword() {
		text_password = new Text(shell, SWT.BORDER);
		text_password.setText(PASSWORD);
		text_password.setBounds(302, 114, 214, 27);
	}

	private void initTextAuthCode() {
		text_authcode = new Text(shell, SWT.BORDER);
		text_authcode.setBounds(302, 181, 91, 27);
	}

	private void initTextHint() {
		text_hint = new Text(shell, SWT.READ_ONLY);
		text_hint.setBounds(302, 10, 214, 23);
	}

	private void initLabelAuthCode() {
		authCodePic = new Label(shell, SWT.NONE);
		authCodePic.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		authCodePic.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		// lblNewLabel.setImage(httprequest.getImage());
		RefreshAuthCode.getInstance().start();
		authCodePic.setBounds(413, 170, 120, 50);
	}

	private void initBtnLogin() {
		btnLogin = new Button(shell, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text_username.getText().equals(""))
					text_hint.setText("账号不能为空");
				else if (text_password.getText().equals(""))
					text_hint.setText("密码不能为空");
				else if (text_authcode.getText().equals(""))
					text_hint.setText("验证码不能为空");
				else
					try {
						errorcode = new String(httprequest.login(text_username.getText(), text_password.getText(),
								text_authcode.getText()));

						text_hint.setText(errorcode);

						// reset the authcodeimage and clear text_authcode
						// lblNewLabel.setImage(httprequest.getImage());
						RefreshAuthCode.getInstance().start();
						text_authcode.setText("");

					} catch (ClientProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
		btnLogin.setBounds(313, 238, 80, 27);
		btnLogin.setText("登陆");
	}

	private void initTextLabel() {
		text_2 = new Text(shell, SWT.READ_ONLY);
		text_2.setTouchEnabled(true);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text_2.setEditable(false);
		text_2.setText("账    号");
		text_2.setBounds(222, 56, 80, 27);

		text_3 = new Text(shell, SWT.READ_ONLY);
		text_3.setEditable(false);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text_3.setText("密    码");
		text_3.setBounds(222, 114, 80, 27);

		text_5 = new Text(shell, SWT.READ_ONLY);
		text_5.setText("验证码");
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text_5.setEditable(false);
		text_5.setBounds(222, 179, 80, 27);
	}

	private void initBtnRefresh() {
		btnrefresh = new Button(shell, SWT.NONE);
		btnrefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// lblNewLabel.setImage(httprequest.getImage());
					RefreshAuthCode.getInstance().start();
				} catch (IllegalStateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnrefresh.setBounds(424, 238, 80, 27);
		btnrefresh.setText("看不清");
	}

	private void initComboDeptList() {
		comboDeptList = new Combo(shell, SWT.NONE);
		comboDeptList.setBounds(378, 304, 144, 25);
		comboDeptList.setText("科室");
		comboDeptList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					setPostDeptInf("hosdata/dept/" + hosCode + ".txt", comboDeptList.getSelectionIndex());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initComboDoctorList() {
		comboDoctorList = new Combo(shell, SWT.NONE);
		comboDoctorList.setBounds(533, 304, 144, 25);
		comboDoctorList.setText("医生");
		comboDoctorList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					setPostDocInf("hosdata/doc/" + hosCode + ".txt", comboDoctorList.getSelectionIndex());
					// System.out.println(areaCode);
					// System.out.println(hosCode);
					// System.out.println(platCode);
					// System.out.println(platDocId);
					// System.out.println(hosName);
					// System.out.println(deptName);
					// System.out.println(docName);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initComboHospitalList() {
		comboHospitalList = new Combo(shell, SWT.NONE);
		comboHospitalList.setBounds(222, 304, 144, 25);
		comboHospitalList.setText("医院");
		comboHospitalList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					setPostHosInf("hosdata/hos/" + areaCode + ".txt", comboHospitalList.getSelectionIndex());
					// System.out.println(hosCode+" "+platCode+" "+hosName);
					comboDeptList.setItems(getDeptStr("hosdata/dept/" + hosCode + ".txt"));
					comboDoctorList.setItems(getDocStr("hosdata/doc/" + hosCode + ".txt"));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initComboAreaCodeList() throws Exception {
		comboAreaCodeList = new Combo(shell, SWT.NONE);
		comboAreaCodeList.setItems(getAreaStr("hosdata/area/areacode.txt"));
		comboAreaCodeList.setBounds(66, 304, 144, 25);
		comboAreaCodeList.setText("地区");
		comboAreaCodeList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					setPostAreaInf("hosdata/area/areacode.txt", comboAreaCodeList.getSelectionIndex());
					comboHospitalList.setItems(getHosStr("hosdata/hos/" + areaCode + ".txt"));
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initBtnNewButton() {
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setVisible(false);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					tableHtml = httprequest.test(areaCode, hosCode, platCode, platDocId, hosName, deptName, docName);
					// private static String areaCode;
					// private static String hosCode;
					// private static String platCode;
					// private static String platDocId;
					// private static String hosName;
					// private static String deptName;
					// private static String docName;
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(31, 181, 80, 27);
		btnNewButton.setText("test");
	}

	private void initBtnLogout() {
		btnLogout = new Button(shell, SWT.NONE);
		btnLogout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					text_hint.setText(httprequest.logout());

					// reset the authcodeimage and clear text_authcode
					// lblNewLabel.setImage(httprequest.getImage());
					RefreshAuthCode.getInstance().start();
					text_authcode.setText("");
				} catch (ClientProtocolException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(559, 125, 80, 27);
		btnLogout.setText("退出登录");
	}

	private void initBrowser() {
		browser = new Browser(shell, SWT.NONE);
		browser.setBounds(0, 0, 759, 380);
		browser.setVisible(false);
	}

	private void initBtnOk() {
		btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				hideFirst();

				try {
					browser.setText(httprequest.getHtmlTable());

				} catch (ClientProtocolException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// try {
				// browser.setText(httprequest.getHtmlTable());
				// } catch (ClientProtocolException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
			}
		});
		btnOk.setBounds(313, 355, 131, 56);
		btnOk.setText("确定");
	}

	private void initBtnNewButton1() {
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(browser.getCookie("file_1", "/"));// //////////////////////////////////////////////////
				System.out.println(browser.evaluate("return document.cookie;"));
			}
		});
		btnNewButton_1.setBounds(490, 386, 80, 27);
		btnNewButton_1.setText("New Button");
		// browser.setText();
	}

}
