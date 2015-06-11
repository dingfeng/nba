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
    	            this.matchId = matchId;
    	            this.team1 = team1;
    	            this.team2 = team2;
    	            this.date = date;
    		   }
       /**
        * 获得主场球队
        * @return 主场球队
        */
	public MatchTeamPO getTeam1() {
		return team1;
	}
	/**
	 * 获得比赛编号
	 * @return   比赛编号
	 */
	public int getMatchId()
	{
		return matchId;
		
	}
	/**
	 * 获得客场球队
	 * @return 客场球队
	 */ 
	public MatchTeamPO getTeam2() 
	{
		return team2;
	}
	/**
	 * 获得比赛日期
	 * @return 比赛日期
	 */
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
