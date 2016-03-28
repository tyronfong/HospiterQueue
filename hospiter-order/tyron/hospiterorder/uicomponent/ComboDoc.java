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

public class ComboDoc
{
	private Combo combo;
	private String docName;
	private String platDocId;
	private String hosCode;

	public ComboDoc(Shell shell) throws JSONException, IOException
	{
		combo = new Combo(shell, SWT.NONE);
		combo.setBounds(533, 304, 144, 25);
		combo.setText("医生");
	}

	public void attachHosAction(final ComboHospital comboHospital)
	{
		combo.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent e)
			{
				try
				{
					setPostDocInf("hosdata/doc/" + comboHospital.getHosCode() + ".txt", combo.getSelectionIndex());
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

	private void setPostDocInf(String docFilePath, int index) throws JSONException, IOException
	{
		File areafile = new File(docFilePath);
		if (!areafile.exists())
			return;

		JSONArray areajson = new JSONArray(FileUtil.filetoString(areafile));

		docName = areajson.getJSONObject(index).getString("docName");
		platDocId = areajson.getJSONObject(index).getString("platDocId");
	}

	public Combo getCombo()
	{
		return combo;
	}

	public void setCombo(Combo combo)
	{
		this.combo = combo;
	}

	public String getDocName()
	{
		return docName;
	}

	public void setDocName(String docName)
	{
		this.docName = docName;
	}

	public String getPlatDocId()
	{
		return platDocId;
	}

	public void setPlatDocId(String platDocId)
	{
		this.platDocId = platDocId;
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
