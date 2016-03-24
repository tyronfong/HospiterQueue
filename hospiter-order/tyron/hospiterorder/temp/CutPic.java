package tyron.hospiterorder.temp;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class CutPic {
	public static void main(String[] args) throws IOException{
		System.out.println("Input filename!");
		String picname;
		Scanner in = new Scanner(System.in);
		picname = in.next();
		
		new CutPic().cutPicture(picname);
		
	}
	
	public void cutPicture(String picname) throws IOException{
		FileInputStream picfileinputstream = new FileInputStream(picname);
		String formatname_ = CutPic.getExtensionName(picname);
		Iterator<ImageReader> formatname = ImageIO.getImageReadersByFormatName(formatname_);
		ImageReader reader = formatname.next();
		ImageInputStream picimageinputstream = ImageIO.createImageInputStream(picfileinputstream);
		
		reader.setInput(picimageinputstream,true);
		
		int picheight = reader.getHeight(0);
		int picwidth = reader.getWidth(0);
		int blank = (int) (picheight*0.005);
		int subheight = (picheight-2*blank)/3;
		int subwidth = (picwidth-2*blank)/3;
		
		
		Rectangle[] rect = new Rectangle[9];
		rect[0] = new Rectangle(0,0,subwidth,subheight);
		rect[1] = new Rectangle(subwidth+blank,0,subwidth,subheight);
		rect[2] = new Rectangle(2*subwidth+2*blank,0,subwidth,subheight);
		
		rect[3] = new Rectangle(0,subheight+blank,subwidth,subheight);
		rect[4] = new Rectangle(subwidth+blank,subheight+blank,subwidth,subheight);
		rect[5] = new Rectangle(2*subwidth+2*blank,subheight+blank,subwidth,subheight);
		
		rect[6] = new Rectangle(0,2*subheight+2*blank,subwidth,subheight);
		rect[7] = new Rectangle(subwidth+blank,2*subheight+2*blank,subwidth,subheight);
		rect[8] = new Rectangle(2*subwidth+2*blank,2*subheight+2*blank,subwidth,subheight);
		
		ImageReadParam param = reader.getDefaultReadParam();
		
		for(int i = 0; i < 9; i++){
			param.setSourceRegion(rect[i]);
			BufferedImage bi = reader.read(0,param);
			
			ImageIO.write(bi,formatname_,new File(picname.substring(0, picname.length()-formatname_.length()-1)+i+"."+formatname_));
		}

		picfileinputstream.close();		
		picimageinputstream.close();
	}
	
	public static String getExtensionName(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length() - 1))) {   
                return filename.substring(dot + 1);   
            }   
        }   
        return filename;   
    }
}
