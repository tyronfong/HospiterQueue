package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class TextPasswordLabel {
	private Text text;

	public TextPasswordLabel(Shell shell) {
		text = new Text(shell, SWT.READ_ONLY);
		text.setEditable(false);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		text.setText("密    码");
		text.setBounds(222, 114, 80, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}
