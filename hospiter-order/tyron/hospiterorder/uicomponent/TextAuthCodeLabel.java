package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class TextAuthCodeLabel {
	private Text text;

	public TextAuthCodeLabel(Shell shell) {
		text = new Text(shell, SWT.READ_ONLY);
		text.setText("验证码");
		text.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text.setEditable(false);
		text.setBounds(222, 179, 80, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}
