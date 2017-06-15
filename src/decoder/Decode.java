package decoder;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import javax.imageio.ImageIO;

/**
 * Created by Meghana Madineni on 11/20/2015.
 */
public class Decode {
	private static String extractMessage(String dir, String eimage) throws IOException {
		BufferedImage img=ImageIO.read(new File(dir, eimage));
		int[] array = new int[img.getHeight()*img.getWidth()*3];
		for(int i=0;i< img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				int pixel = img.getRGB(j, i);
				//int alpha = (pixel >> 24) & 0xff;
				int red = (pixel >> 16) & 0xff;
				array[(i*3*img.getWidth())+(3*j)+0]=red;
				int green = (pixel >> 8) & 0xff;
				array[(i*3*img.getWidth())+(3*j)+1]=green;
				int blue = (pixel) & 0xff;
				array[(i*3*img.getWidth())+(3*j)+2]=blue;
			}
		}
		String size="";
		String msg="";
		String imsg="";
		int r;
		String balpha;
		for(int v=0;v<64;v++){
			balpha = Integer.toBinaryString(array[v]);
			r = balpha.length();
			size=size+balpha.charAt(r-1);
		}
		int isize=Integer.parseInt(size,2);
		for(int v=64;v<(64+isize);v++){
			balpha = Integer.toBinaryString(array[v]);
			r = balpha.length();
			msg=msg+balpha.charAt(r-1);
		}
		imsg= new String(new BigInteger(msg, 2).toByteArray());
		return imsg;
	}

	public static void main(String args[]){
		try {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("output.txt")), "utf-8"))) {
				writer.write(extractMessage(args[0],args[1]));
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
