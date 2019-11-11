package Main;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) 
	{
		FileList first=new FileList();
		first.attemp();
		first.getFiles();
		for(int i=0;i<first.getPdfFiles().size();i++) 
		{
			TakeStamp t=new TakeStamp();
			t.paintPageStamp(first.getPdfFiles().get(i).toString());
		}
		JOptionPane.showMessageDialog(null,"处理完成","成功", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}
}
