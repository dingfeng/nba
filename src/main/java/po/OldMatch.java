package po;

import java.awt.Image;

public class OldMatch {
       int matchId;
       String host_team;
       String guestTeam;
       String pt1[];
       String pt2[];
       Image img;
       public OldMatch(int matchId, String team1, String team2, Image img)
       {
    	   this.matchId = matchId;
    	   this.host_team = team1;
    	   this.guestTeam = team2;
    	   this.img = img;
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
       
}
