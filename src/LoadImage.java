import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class LoadImage {
	BufferedImage img;
	public LoadImage(String file){
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e){
			
		}
	}
	
	public int[][] imgToIntMatrix(){
		int h = img.getHeight();
		int w = img.getWidth();
		int[][] res = new int[h][w];
		Raster raster = img.getData();
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				res[i][j]=raster.getSample(j,i,0);
			}
		}
		return res;
	}
	
	public static void main(String[] args){
		LoadImage square = new LoadImage("src/carre.png");
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
