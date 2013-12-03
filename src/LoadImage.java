import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoadImage {
	BufferedImage img;
	int height;
	int width;
	
	public LoadImage(){
		img=null;
		height=width=0;
	}
	public LoadImage(String file){
		try {
			img = ImageIO.read(new File(file));
			height = img.getHeight();
			width = img.getWidth();
		} catch (IOException e){
			
		}
	}
	
	/**
	 * Conversion d'une image en tableau d'entiers. Attention aux indices!
	 * @return
	 */
	public int[][] imgToIntMatrix(){
		int[][] res = new int[height][width];
		Raster raster = img.getData();
		for (int i=0;i<height;i++){
			for (int j=0;j<width;j++){
				res[i][j]=raster.getSample(j,i,0);
			}
		}
		return res;
	}
	
	public static void main(String[] args){
		LoadImage square = new LoadImage("src/a.png");
		int w = square.img.getWidth();
		int h = square.img.getHeight();
		int [][] tmp = square.imgToIntMatrix();
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				tmp[i][j]=255-tmp[i][j];
			}
		}
		BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster;
		raster = img.getRaster();
		for(int i=0;i<h;i++)
	     {
	         for(int j=0;j<w;j++)
	         {
	             raster.setSample(j,i,0,tmp[i][j]);
	         }
	     }
		try {
			File outputfile = new File("output.png");
			ImageIO.write(img,"png",outputfile);
		} catch(IOException e){
			
		}
	}
}
