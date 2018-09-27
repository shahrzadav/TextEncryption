package cryptoplugin.handlers;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.swt.SWT;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class EncryptionDialog {
	
	protected Shell shell;
	private Text Inputtext;
	private Label lblCipherText;
	
	
	public EncryptionDialog(Shell windowShell) {
	    windowShell.setText("Encryption Window");
		  }
	
	//Display the Encryption box
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	//creation of input&output labels and the button
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Encryption");
		
		Label lblInput = new Label(shell, SWT.NONE);
		lblInput.setBounds(57, 30, 81, 25);
		lblInput.setText("input");
		
		Inputtext = new Text(shell, SWT.BORDER);
		Inputtext.setBounds(58, 59, 80, 31);
		
		final Label lblCipherText = new Label(shell, SWT.NONE);
		lblCipherText.setBounds(53, 158, 327, 60);
		
		Button btnEncrypt = new Button(shell, SWT.NONE);
		btnEncrypt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String input;
				try {
				input = Inputtext.getText();
				 String key = "key"; 
			     byte[] b = encrypt(input.getBytes(), key.getBytes());
			     lblCipherText.setText(b.toString());
				}
				catch(Exception exc){
					MessageDialog.openError(shell , "Error", "Bad Input");
					return;
				}
				    
			}
		});
		btnEncrypt.setBounds(58, 96, 105, 35);
		btnEncrypt.setText("Encrypt");
		
	

	}
	
	//Encryption using blowfish algorithm
	public byte[] encrypt(byte encrypt[], byte en_key[]) { 
	     try { 
	           SecretKeySpec key = new SecretKeySpec(en_key, "Blowfish"); 
	           Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding"); 
	           cipher.init(Cipher.ENCRYPT_MODE, key); 
	           return cipher.doFinal(encrypt); 
	     } catch (Exception e) { 
	           e.printStackTrace();
	           return null; 
	         }

	}

}
