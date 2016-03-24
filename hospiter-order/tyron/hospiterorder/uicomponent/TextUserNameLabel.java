package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class TextUserNameLabel {
	private Text text;

	public TextUserNameLabel(Shell shell) {
		text = new Text(shell, SWT.READ_ONLY);
		text.setTouchEnabled(true);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text.setEditable(false);
		text.setText("账    号");
		text.setBounds(222, 56, 80, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text_username) {
		this.text = text_username;
	}

}
