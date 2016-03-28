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

import tyron.hospiterorder.common.FileUtil;

public class ComboDept
{
	private Combo combo;
	private String hosCode;
	private String deptName;

	public ComboDept(Shell shell) throws JSONException, IOException
	{
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(378, 304, 144, 25);
		combo.setText("科室");
	}

	public void attachHosAction(final ComboHospital comboHospital)
	{
		combo.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					setPostDeptInf("hosdata/dept/" + comboHospital.getHosCode() + ".txt", combo.getSelectionIndex());
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

	private void setPostDeptInf(String deptfilepath, int index) throws JSONException, IOException
	{
		File areafile = new File(deptfilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(FileUtil.filetoString(areafile));

		deptName = areajson.getJSONObject(index).getString("deptName");
	}

	public Combo getCombo()
	{
		return combo;
	}

	public void setCombo(Combo combo)
	{
		this.combo = combo;
	}

	public String getHosCode()
	{
		return hosCode;
	}

	public void setHosCode(String hosCode)
	{
		this.hosCode = hosCode;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

}
