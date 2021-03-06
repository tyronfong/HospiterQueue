package tyron.hospiterorder.uicomponent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import tyron.hospiterorder.common.RefreshAuthCode;

public class LabelAuthCode
{
	private Label label;

	public LabelAuthCode(Shell shell)
	{
		label = new Label(shell, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		// lblNewLabel.setImage(httprequest.getImage());
		RefreshAuthCode.getInstance(label).start();
		label.setBounds(413, 170, 120, 50);
	}

	public Label getLabel()
	{
		return label;
	}

	public void setLabel(Label label)
	{
		this.label = label;
	}

}
