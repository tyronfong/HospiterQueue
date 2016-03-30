package tyron.hospiterorder.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tyron.hospiterorder.common.RefreshAuthCodeRunable;

public class DrapPics extends Shell
{
	private Text text;
	private RefreshAuthCodeRunable codeRefresher;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{
		try
		{
			Display display = Display.getDefault();
			final DrapPics shell = new DrapPics(display);

			shell.addShellListener(new ShellAdapter()
			{
				public void shellClosed(ShellEvent e)
				{
					FileOutputStream fos = null;
					ObjectOutputStream oos = null;

					File file = new File("t.tmp");
					if (file.exists())
					{
						file.delete();
					}
					try
					{
						fos = new FileOutputStream(file);
						oos = new ObjectOutputStream(fos);
						oos.writeObject(shell.getCodeRefresher().getNumCache());
					} catch (Exception e1)
					{
						e1.printStackTrace();
					} finally
					{
						try
						{
							fos.close();
							oos.close();
						} catch (IOException e1)
						{
							e1.printStackTrace();
						}
					}

				}

			});

			shell.open();
			shell.layout();
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public DrapPics(Display display) throws IOException
	{
		super(display, SWT.SHELL_TRIM);

		Label lblPic = new Label(this, SWT.NONE);
		lblPic.setBounds(119, 33, 231, 92);
		codeRefresher = RefreshAuthCodeRunable.getInstance(lblPic);
		new Thread(codeRefresher).start();
		text = new Text(this, SWT.BORDER);
		text.setBounds(119, 161, 231, 60);
		text.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.keyCode == 16777296)
				{
					e.doit = false;
					codeRefresher.setCurText(text);
					new Thread(codeRefresher).start();
				}
			}

			public void keyReleased(KeyEvent arg0)
			{
			}
		});
		createContents();

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		File file = new File("t.tmp");
		if (file.exists())
		{
			try
			{
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);
				codeRefresher.setNumCache((HashMap<String, String>) ois.readObject());
			} catch (Exception e1)
			{
				e1.printStackTrace();
			} finally
			{
				fis.close();
				ois.close();
			}
		} else
		{
			codeRefresher.setNumCache(new HashMap<String, String>());
		}

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents()
	{
		setText("SWT Application");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}

	public RefreshAuthCodeRunable getCodeRefresher()
	{
		return codeRefresher;
	}

	public void setCodeRefresher(RefreshAuthCodeRunable codeRefresher)
	{
		this.codeRefresher = codeRefresher;
	}

}
