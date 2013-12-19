import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Sampling {
	LoadImage img; // image to sample from
	Point[] points; //sampled points
	int size; // number of points sampled
	
	public Sampling(LoadImage image, int n){
		img=image;
		size=n;
		points = new Point[n];
	}
	
	public Sampling(String file, int n){
		img = new LoadImage(file);
		size=n;
		points=new Point[n];
	}
	
	public void compute(){
		int h = img.height;
		int w = img.width;
		int[][] distribution = img.imgToIntMatrix();
		int tmp=0;
		for (int i=0;i<h;i++){
			for (int j=0;j<w;j++){
				distribution[i][j] =tmp+255-distribution[i][j];
				tmp = distribution[i][j];
			}
		}
		Random rand = new Random();
		for (int k=0;k<size;k++){
			int randomInt = rand.nextInt(tmp+1);
			int i=0;
			while(i<h-1&&distribution[i][w-1]<randomInt){
				i++;
			}
			int j=0;
			while(j<w-1&&distribution[i][j]<randomInt){
				j++;
			}
			points[k]=new Point(j,i);			
		}
		
	}
	
	public void visualize(Graphics g){
		g.drawImage(img.img, 0, 0, null);
		for (int i=0;i<size;i++){
			Color c = Color.RED;
			points[i].draw(g, c);
		}
	}
	
	public static void main (String[] args){
		Sampling sample = new Sampling("src/carre.png",2000);
		sample.compute();
		int w = sample.img.width;
		int h = sample.img.height;
		BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
		WritableRaster raster = image.getRaster();
		Raster data = sample.img.img.getData();
		for(int i=0;i<h;i++){
	         for(int j=0;j<w;j++){
	        	 for (int k=0;k<3;k++)
	             raster.setSample(j,i,k,data.getSample(j, i, 0));
	         }
	     }
		for (int i=0;i<sample.size;i++){
			int x = sample.points[i].x;
			int y = sample.points[i].y;
			raster.setSample(x, y, 0, 255);
			raster.setSample(x, y, 1, 0);
			raster.setSample(x, y, 2, 0);
		}
		try {
			File outputfile = new File("output.png");
			ImageIO.write(image,"png",outputfile);
		} catch(IOException e){
			
		}
		
	}
}
