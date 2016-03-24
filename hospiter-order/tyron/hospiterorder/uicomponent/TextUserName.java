package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextUserName {
	private Text text;

	public TextUserName(Shell shell) {
		text = new Text(shell, SWT.BORDER);
		text.setText("330726199308091519");
		text.setBounds(302, 56, 214, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text_username) {
		this.text = text_username;
	}

}
