package getimages;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class LoadImage {
	private BufferedImage image;
	
	public BufferedImage loadImage(String pathway) throws IOException
	{
		image = ImageIO.read(new File(pathway));
		return image;
	}
	
	public BufferedImage resize(BufferedImage prev, int width, int height)
	{
		Image tmp = prev.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		prev = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = prev.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return prev;
	}
}
