package DBtool;

import java.awt.Image;
import java.util.Arrays;

public class OldMatch {
       int matchId;
       String host_team;
       String guestTeam;
       String date;
       String pt1[];
       String pt2[];
       Image img;
       public OldMatch(int matchId, String team1, String team2, String date,Image img,String[] pt1,String[] pt2)
       {
    	   this.date = date;
    	   this.matchId = matchId;
    	   this.host_team = team1;
    	   this.guestTeam = team2;
    	   this.img = img;
    	   this.pt1 = pt1;
    	   this.pt2 = pt2;
       }
    public String getDate()
    {
    	return date;
    }
	public int getMatchId() {
		return matchId;
	}
	public String getHost_team() {
		return host_team;
	}
	public String getGuestTeam() {
		return guestTeam;
	}
	public String[] getPt1() {
		return pt1;
	}
	public String[] getPt2() {
		return pt2;
	}
	public Image getImg() {
		return img;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(matchId+" \n");
		sb.append(date+" \n");
		sb.append(host_team+" ");
		sb.append(Arrays.toString(pt1)+" \n");
		sb.append(guestTeam+" ");
		sb.append(Arrays.toString(pt2)+" \n");
		return sb.toString();
    }
	public void finalize()
	{
		System.out.println("old matches is gabage collection..");
	}
}
