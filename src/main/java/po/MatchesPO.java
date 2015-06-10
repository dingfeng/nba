package po;

import java.io.Serializable;

public class MatchesPO implements Serializable{
	   
       private MatchTeamPO team1;
       private MatchTeamPO team2;
       private String date;
       private int matchId;
       public MatchesPO(int matchId,MatchTeamPO team1,MatchTeamPO team2,
    		   String date)
    		   {
    	            this.team1 = team1;
    	            this.team2 = team2;
    	            this.date = date;
    		   }
	public MatchTeamPO getTeam1() {
		return team1;
	}
	public int getMatchId()
	{
		return matchId;
	}
	public MatchTeamPO getTeam2() 
	{
		return team2;
	}
	public String getDate() 
	{
		return date;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(date+"\n");
		sb.append("team1 : ");
		sb.append(team1.toString());
		sb.append("\n");
		sb.append("team2 : ");
		sb.append(team2.toString());
		sb.append("\n");
		return sb.toString();
	}
       
}
