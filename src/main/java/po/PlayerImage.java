package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import tool.ImageTool;

public class PlayerImage implements Serializable{
	   byte[] image;
       public PlayerImage(Image image)
       {
    	   if (image != null)
    	   {
    		   this.image = ImageTool.imageToBytes_player(image, "png", BufferedImage.TYPE_INT_ARGB);
    	   }
       }
       /**
        * 获得图片
        * @return  图片
        */
       public Image getImage()
       {
    	   Image img = null;
    	   if (image != null)
    	   {
    		   img = ImageTool.bytesToImage(image);
    	   }
    	   return img;
       }
}
