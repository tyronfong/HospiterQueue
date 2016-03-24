package tyron.hospiterorder.uicomponent;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Shell;
import org.json.JSONArray;
import org.json.JSONException;

import tyron.hospiterorder.common.ComboTool;
import tyron.hospiterorder.common.FileUtil;

public class ComboAreaCode
{
	private Combo combo;
	private String areaCode;

	public ComboAreaCode(Shell shell, final Combo comboHospital) throws JSONException, IOException
	{
		combo = new Combo(shell, SWT.NONE);
		combo.setItems(ComboTool.fileToAreaItems("hosdata/area/areacode.txt"));
		combo.setBounds(66, 304, 144, 25);
		combo.setText("地区");
		combo.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					setPostAreaInf("hosdata/area/areacode.txt", combo.getSelectionIndex());
					comboHospital.setItems(ComboTool.fileToHosItems("hosdata/hos/" + areaCode + ".txt"));
				} catch (JSONException e1)
				{
					e1.printStackTrace();
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
	}

	private void setPostAreaInf(String areafilepath, int index) throws JSONException, IOException
	{
		File areafile = new File(areafilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(FileUtil.filetoString(areafile));

		areaCode = areajson.getJSONObject(index).getString("areaCode");
	}
}
