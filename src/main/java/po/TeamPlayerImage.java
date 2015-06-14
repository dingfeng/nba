package po;

import java.awt.Image;
import java.io.Serializable;

public class TeamPlayerImage implements Serializable{
       PlayerImage playerImage;
       String name;
       public TeamPlayerImage(Image image,String name)
       {
    	   this.playerImage = new PlayerImage(image);
    	   this.name = name;
       }
       public Image getImage()
       {
    	   return playerImage.getImage();
    	  
       }
       public String getName()
       {
    	   return name;
       }
       public String toString()
       {
    	   return name;
       }
}
