package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

import tool.ImageTool;

public class OldMatch implements Serializable{
       int matchId;
       String host_team;
       String guest_team;
       String pt1[];
       String pt2[];
       String date;
       byte[] img;
       public String toString()
       {
    	   StringBuilder sb = new StringBuilder();
    	   sb.append( matchId+" "+date+" \n");
    	   sb.append(host_team +" "+Arrays.toString(pt1)+"\n");
    	   sb.append(guest_team+" "+Arrays.toString(pt2)+"\n");
    	   return sb.toString();
       }
       public OldMatch(int matchId, String host_team, String guest_team,String pt1[], String pt2[] ,Image img,String date)
       {
    	   this.matchId = matchId;
    	   this.host_team = host_team;
    	   this.guest_team = guest_team;
    	   this.pt1 = pt1;
    	   this.pt2 = pt2;
    	   if (img != null)
    	   this.img = ImageTool.imageToBytes_player(img, "jpg", BufferedImage.TYPE_INT_RGB);
    	   this.date = date;
       }
       public OldMatch(int matchId, String host_team, String guest_team,String date)
       {
    	   this.matchId = matchId;
    	   this.host_team = host_team;
    	   this.guest_team = guest_team;
    	   this.date =date;
       } 
       public String getDate()
       {
    	   return date;
       }
       public Image getImg()
       {
    	   if (img!= null)
    	   return ImageTool.bytesToImage(img);
    	   else return null;
       }
	public int getMatchId() {
		return matchId;
	}
	public String getHost_team() {
		return host_team;
	}
	public String getGuestTeam() {
		return guest_team;
	}
	public String[] getPt1() {
		return pt1;
	}
	public String[] getPt2() {
		return pt2;
	}
       
}
