package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TextPassword {
	private Text text;

	public TextPassword(Shell shell) {
		text = new Text(shell, SWT.BORDER);
		text.setText("w84922575");
		text.setBounds(302, 114, 214, 27);
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	
	

}
