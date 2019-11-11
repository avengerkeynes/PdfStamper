package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileList {
	private File metaAddress=new File(System.getProperty("user.dir")+"/kernel/basedir");
	private String address=System.getProperty("user.dir")+"/kernel/basedir";
	private ArrayList pdfFiles=new ArrayList();
	public ArrayList getPdfFiles() 
	{
		return pdfFiles;
	}
	
	public void attemp() 
	{
		if(!metaAddress.exists()) 
		{
			try 
			{
				metaAddress.createNewFile();
				FileOutputStream fos=new FileOutputStream(address);
				OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
				osw.write("UNDEFIND");
				osw.flush();
				osw.close();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		try 
		{
			String text=FileUtils.readFileToString(metaAddress,"UTF-8");
			text=text.replace("\n", "");
			text=text.replace("\t","");
			text=text.replace("\r","");
			text=text.replace(" ","");
				JFrame jFrame=new JFrame();
				jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				JFileChooser filechooser=new JFileChooser();
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				filechooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				filechooser.showOpenDialog(jFrame);
				String filepath=filechooser.getSelectedFile().getAbsolutePath();
				FileOutputStream fos=new FileOutputStream(address);
				OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
				osw.write(filepath);
				osw.flush();
				osw.close();
			
//			rootDir=(List<File>)FileUtils.listFiles(new File(text),TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			try 
			{
				JOptionPane.showMessageDialog(null,"导入路径出现问题，系统将自动重置为UNDEFIND","warning",JOptionPane.ERROR_MESSAGE);
				FileOutputStream fos=new FileOutputStream(address);
				OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
				osw.write("UNDEFIND");
				osw.flush();
				osw.close();
			}
			catch(Exception ee) 
			{
				ee.printStackTrace();
			}
			System.exit(0);
		}
		
	}
	public void getFiles() 
	{
		try 
		{
			String text=FileUtils.readFileToString(metaAddress,"UTF-8");
			text=text.replace("\n", "");
			text=text.replace("\t","");
			text=text.replace("\r","");
			text=text.replace(" ","");
			List<File> files=(List<File>)FileUtils.listFiles(new File(text), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
			for(File file:files) 
			{
				String filename=file.getName();
				String filep=file.getAbsolutePath();
				String prefix=filename.substring(filename.lastIndexOf(".")+1);
				prefix=prefix.replace(" ","");
				prefix=prefix.replace("\t","");
				prefix=prefix.replace("\n","");
				prefix=prefix.replace("\r","");
				if(prefix.toUpperCase().equals("PDF")) 
				{
					pdfFiles.add(filep);
				}
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}





}
