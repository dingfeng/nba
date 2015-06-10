package po;

import java.io.Serializable;

public class SimpleMatchPO implements Serializable{
       String team1;
       String team2;
       int scores1;
       int scores2;
       String date;
       int matchId;
	 public SimpleMatchPO(String team1, String team2, int scores1, int scores2,String date)
       {
    	   this.team1 = team1;
    	   this.team2 = team2;
    	   this.scores1 = scores1;
    	   this.scores2 = scores2;
    	   this.date = date;
       }
	 public int getMatchId()
	 {
		 return matchId;
	 }
	  public String getTeam1() {
			return team1;
		}
		public String getTeam2() {
			return team2;
		}
		public int getScores1() {
			return scores1;
		}
		public int getScores2() {
			return scores2;
		}
		public String getDate() {
			return date;
		}
}
