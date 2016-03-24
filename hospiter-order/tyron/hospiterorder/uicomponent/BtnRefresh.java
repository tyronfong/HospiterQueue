package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import tyron.hospiterorder.temp.RefreshAuthCode;

public class BtnRefresh {
	private Button button;

	public BtnRefresh(Shell shell) {
		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// lblNewLabel.setImage(httprequest.getImage());
					RefreshAuthCode.getInstance().start();
				} catch (IllegalStateException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(424, 238, 80, 27);
		button.setText("看不清");
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
