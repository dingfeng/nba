package tool;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageTool {
   public static byte[]  imageToBytes_player(Image image,String format,int imageType)
   {
	   image = new ImageIcon(image).getImage();
	   BufferedImage buf = new BufferedImage(image.getWidth(null),image.getHeight(null),imageType);
	   Graphics2D g = (Graphics2D)buf.createGraphics();  
	   g.setColor(new Color(255,0,0));
 	   g.setStroke(new BasicStroke(1));
       g.drawImage(image,0,0,null);  
       g.dispose();  
       ByteArrayOutputStream stream = new ByteArrayOutputStream(); 
       try {
		ImageIO.write(buf,format,stream);
	} catch (IOException e) {
		e.printStackTrace();
	} 
       return stream.toByteArray();
   }
   public static Image bytesToImage(byte[] bytes)
   {
	   return Toolkit.getDefaultToolkit().createImage(bytes,0,bytes.length);
   }
   public static void showImage(Image image)
   {
	   JFrame frame = new JFrame();
	   JLabel label = new JLabel(new ImageIcon(image));
	   frame.setLayout(new BorderLayout());
	   frame.add(label,BorderLayout.CENTER);
	   frame.setBounds(100, 100, 200, 200);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setVisible(true);
   }
}
