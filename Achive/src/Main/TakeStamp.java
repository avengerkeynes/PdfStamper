package Main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class TakeStamp {
	private static String outpath=System.getProperty("user.dir")+"/out/";
	private static String picPath=System.getProperty("user.dir")+"/kernel/stamp.png";
	
	
	public void paintPageStamp(String file) 
	{
		try 
		{
			File ffile=new File(file);
			String pfile=ffile.getName();
			String ofile=outpath+pfile;
//			System.out.println(file);
			PdfReader reader=new PdfReader(file);
			PdfStamper stamp=new PdfStamper(reader,new FileOutputStream(ofile));
			Rectangle pageSize = reader.getPageSize(1);
			float height=pageSize.getHeight();
			float width=pageSize.getWidth();
			int nums=reader.getNumberOfPages();
			Image[] nImage=new Image[nums];
			ByteArrayOutputStream out=new ByteArrayOutputStream();
			BufferedImage img=ImageIO.read(new File(picPath));
			int h=img.getHeight();
			int w=img.getWidth();
			int sw=w/nums;
			for(int i=0;i<nums;i++) 
	        {
	        	BufferedImage subImg;
	        	if(i==nums-1) 
	        	{
	        		subImg=img.getSubimage(i*sw,0,w-i*sw,h);
	        	}
	        	else 
	        	{
	        		subImg=img.getSubimage(i*sw,0,sw,h);
	        	}
	        	  ImageIO.write(subImg,picPath.substring(picPath.lastIndexOf(".")+1),out);
	        	  nImage[i]=Image.getInstance(out.toByteArray());
	        	  out.flush();
	        	  out.reset();
	        }
			
			for(int n=1;n<=nums;n++) 
		      {
		    	  PdfContentByte over = stamp.getOverContent(n);//设置在第几页打印印章
		            Image img1 = nImage[n-1];//选择图片
		            img1.setAbsolutePosition(width-img1.getWidth(),height/2-img1.getHeight()/2);
		            over.addImage(img1);
		      }
			stamp.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
}
