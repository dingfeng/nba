package po;

import java.io.Serializable;

public class SimpleMatchLive implements Serializable{
       private int matchId;
       private String hostTeam;
       private String guestTeam;
       public SimpleMatchLive(int matchId, String hostTeam, String guestTeam)
       {
    	   this.matchId = matchId;
    	   this.hostTeam = hostTeam;
    	   this.guestTeam = guestTeam;
       }
       public int getMatchId()
       {
    	   return matchId;
       }
       public String getHostTeam()
       {
    	   return hostTeam;
       }
       public String getGuestTeam()
       {
    	   return guestTeam;
       }
       public String toString()
       {
    	   StringBuilder sb = new StringBuilder();
    	   sb.append(matchId+" ");
    	   sb.append(hostTeam +" ");
    	   sb.append(guestTeam+" ");
    	   sb.append("\n");
    	   return sb.toString();
       }
}
