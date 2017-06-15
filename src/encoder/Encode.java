package encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Meghana Madineni on 11/20/2015.
 */
public class Encode {
	public Encode(String dir, String file, String Message) {
		try {
			BufferedImage image= ImageIO.read(new File(dir, file));
			String text = Message;
			String etext =textProcessing(text);
			int msglen=etext.length();
			int pcount=decidePixelsCount(msglen, image);
			modifyPixels(pcount, etext, image);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private void modifyPixels(int pcount, String etext, BufferedImage image) throws IOException {
		String balpha;
		int r;
		int size = pcount*image.getWidth()*3;
		int[] array = new int[size];
		for(int i=0;i< pcount;i++){
			for(int j=0;j<image.getWidth();j++){
				int pixel = image.getRGB(j, i);
				//int alpha = (pixel >> 24) & 0xff;
				int red = (pixel >> 16) & 0xff;
				array[(i*3*image.getWidth())+(3*j)+0]=red;
				int green = (pixel >> 8) & 0xff;
				array[(i*3*image.getWidth())+(3*j)+1]=green;
				int blue = (pixel) & 0xff;
				array[(i*3*image.getWidth())+(3*j)+2]=blue;
			}
		}
		for(int v=0;v<etext.length();v++) {
			balpha = Integer.toBinaryString(array[v]);
			r = balpha.length();
			if (etext.charAt(v) != balpha.charAt(r-1)) {
				balpha = balpha.substring(0, balpha.length() - 1) + etext.charAt(v);
				array[v] = Integer.parseInt(balpha,2);
			}

		}
		for(int i=0;i<pcount;i++){
			for(int j=0;j<image.getWidth();j++){
				Color color = new Color(array[(i*3*image.getWidth())+(3*j)+0],array[(i*3*image.getWidth())+(3*j)+1],array[(i*3*image.getWidth())+(3*j)+2]);
				int rgb = color.getRGB();
				image.setRGB(j,i,rgb);
			}
		}
		ImageIO.write(image, "BMP", new File("filename.bmp"));
	}

	private int decidePixelsCount(int msglen, BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		double i;
		int ii;
		if(msglen>(3*w)){
			i= (double)msglen/(3*w);
			ii=(int)Math.ceil(i);}
		else
			ii=1;
		return ii;
	}
	
	private String textProcessing(String text) {
		String padding = "0000000000000000000000000000000000000000000000000000000000000000";
		String binary = new BigInteger(text.getBytes()).toString(2);
		int a = binary.length();
		String b = Integer.toBinaryString(a);
		String result = padding + b;
		result = result.substring(result.length() - 64, result.length());
		binary=result+binary;
		return binary;
	}

	public static void main(String[] args) {
		new Encode(args[0],args[1],args[2]);
	}
	
}
