package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextHint {
	private Text text;

	public TextHint(Shell shell) {
		text = new Text(shell, SWT.BORDER);
		text.setBounds(302, 10, 214, 23);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
}
