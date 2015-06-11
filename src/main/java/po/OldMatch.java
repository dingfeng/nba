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
       /**
        * 获得日期
        * @return  日期
        */
       public String getDate()
       {
    	   return date;
       }
       /**
        * 获得图片
        * @return  图片
        */
       public Image getImg()
       {
    	   if (img!= null)
    	   return ImageTool.bytesToImage(img);
    	   else return null;
       }
       /**
        * 获得比赛编号
        * @return  比赛编号
        */
	public int getMatchId() {
		return matchId;
	}
	/**
	 * 获得主场球队
	 * @return  主场球队
	 */
	public String getHost_team() {
		return host_team;
	}
	/**
	 * 获得客场球队
	 * @return  客场球队
	 */
	public String getGuestTeam() {
		return guest_team;
	}
	/**
	 * 获得主场球队小比分
	 * @return  主场球队小比分
	 */
	public String[] getPt1() {
		return pt1;
	}
	/**
	 * 获得客场球队小比分
	 * @return  客场球队小比分
	 */
	public String[] getPt2() {
		return pt2;
	}
       
}
