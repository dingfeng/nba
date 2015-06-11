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
	 /**
	  * 获得比赛编号
	  * @return  比赛编号
	  */
	 public int getMatchId()
	 {
		 return matchId;
	 }
	 /**
	  * 获得主场球队名
	  * @return   主场球队名
	  */
	  public String getTeam1() {
			return team1;
		}
	  /**
	   * 获得客场球队名
	   * @return  客场球队名
	   */
		public String getTeam2() {
			return team2;
		}
		/**
		 * 获得主场球队得分
		 * @return  主场球队得分
		 */
		public int getScores1() {
			return scores1;
		}
		/**
		 * 获得客场球队得分
		 * @return  客场球队得分
		 */
		public int getScores2() {
			return scores2;
		}
		/**
		 * 获得日期
		 * @return  日期
		 */
		public String getDate() {
			return date;
		}
}
