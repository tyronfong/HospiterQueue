package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextAuthCode {
	private Text text;

	public TextAuthCode(Shell shell) {
		text = new Text(shell, SWT.BORDER);
		text.setBounds(302, 181, 91, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
}
