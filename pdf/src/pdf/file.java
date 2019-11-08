package pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class file {
	private	String infilePath=System.getProperty("user.dir")+"/file/1.pdf";
	private	String outfilePath=System.getProperty("user.dir")+"/file/2.pdf";
	private String picPath=System.getProperty("user.dir")+"/file/stamp.png";
	
	public void do_paint() 
	{
		try 
		{
		PdfReader reader=new PdfReader(infilePath);		
		PdfStamper stamp=new PdfStamper(reader,new FileOutputStream(outfilePath));		
		Rectangle pageSize = reader.getPageSize(1);//获得第一页
	        float height = pageSize.getHeight();
	        float width  =pageSize.getWidth();
	        int nums =34;
	        
	        Image[] nImage=new Image[34];
	        ByteArrayOutputStream out=new ByteArrayOutputStream();
	        BufferedImage img=ImageIO.read(new File(picPath));
	        int h=84;
	        int w=300;
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
	        
	        
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception 
	{
		file f=new file();
		f.do_paint();
	}
}
