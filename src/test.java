import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class test {
	

	int truc;
	test(){truc=0;}
	
	
	public static void main(String[] args){

		BufferedImage imgBuffer;
		try {
			imgBuffer = ImageIO.read(new File("carre.png"));
			byte[] pixels = (byte[])imgBuffer.getRaster().getDataElements(0, 0, imgBuffer.getWidth(), imgBuffer.getHeight(), null);
			for(int i=0; i< pixels.length;i++){
						System.out.println(pixels[i]);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
