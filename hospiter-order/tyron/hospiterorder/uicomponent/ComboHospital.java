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

public class ComboHospital
{
	private Combo combo;
	private String areaCode;
	private String platCode;
	private String hosName;
	private String hosCode;

	public ComboHospital(Shell shell, final Combo comboDoctor, final Combo comoDept) throws JSONException, IOException
	{
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(222, 304, 144, 25);
		combo.setText("医院");
		combo.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					setPostHosInf("hosdata/hos/" + areaCode + ".txt", combo.getSelectionIndex());
					// System.out.println(hosCode+" "+platCode+" "+hosName);
					comoDept.setItems(ComboTool.fileToDeptItems("hosdata/dept/" + hosCode + ".txt"));
					comboDoctor.setItems(ComboTool.fileToDocItems("hosdata/doc/" + hosCode + ".txt"));
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

	private void setPostHosInf(String hosfilepath, int index) throws JSONException, IOException
	{
		File areafile = new File(hosfilepath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(FileUtil.filetoString(areafile));

		platCode = areajson.getJSONObject(index).getString("platCode");
		hosName = areajson.getJSONObject(index).getString("hosName");
		hosCode = areajson.getJSONObject(index).getString("hosCode");
	}

	public Combo getCombo()
	{
		return combo;
	}

	public void setCombo(Combo combo)
	{
		this.combo = combo;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getPlatCode()
	{
		return platCode;
	}

	public void setPlatCode(String platCode)
	{
		this.platCode = platCode;
	}

	public String getHosName()
	{
		return hosName;
	}

	public void setHosName(String hosName)
	{
		this.hosName = hosName;
	}

	public String getHosCode()
	{
		return hosCode;
	}

	public void setHosCode(String hosCode)
	{
		this.hosCode = hosCode;
	}

}
