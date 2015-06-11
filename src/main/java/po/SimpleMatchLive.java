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
       /**
        * 获得比赛编号
        * @return  比赛编号  
        */
       public int getMatchId()
       {
    	   return matchId;
       }
       /**
        * 获得主场球队
        * @return  主场球队
        */
       public String getHostTeam()
       {
    	   return hostTeam;
       }
       /**
        * 获得客场球队
        * @return  客场球队
        */
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
