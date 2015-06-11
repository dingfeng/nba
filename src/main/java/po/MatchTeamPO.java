package po;

import java.io.Serializable;


public class MatchTeamPO implements Serializable{
       private MatchPlayerPO[] players;
       private int[] scores;
       private int totalScores;
       private String name;
       private int time;
       public MatchTeamPO(MatchPlayerPO[] player,int [] scores,int totalScores,String name, int time)
       {
    	   this.players = player;
    	   this.scores = scores;
    	   this.totalScores = totalScores;
    	   this.name = name;
    	   this.time = time;
    	   if (name.equals("NOH"))
    	   {
    		   this.name = "NOP";
    	   }
    	   if (player != null)
    	   {
    		   for (MatchPlayerPO p : player)
    		   {
    			   p.setTeamnameAbridge(name);
    		   }
    	   }
       }
       /**
        * 获得在场时间
        * @return  在场时间
        */
    public int getTime()
    {
    	return time;
    }
    /**
     * 获得球员<br/>
     * @return  球员
     */
	public MatchPlayerPO[] getPlayers()
	{
		return players;
	}
	/**
	 * 获得小比分
	 * @return  小比分
	 */
	public int[] getScores()
	{
		return scores;
	}
	/**
	 * 获得总分
	 * @return  总分
	 */
	public int getTotalScores() {
		return totalScores;
	}
	/**
	 * 获得名字
	 * @return  名字
	 */
	public String getName() {
		return name;
	}
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append(name + "---");
    	sb.append(totalScores+"  ");
    	for (int score : scores)
    	{
    		sb.append(score);
    		sb.append("  ");
    	}
    	sb.append("  ");
    	sb.append("\n");
    	if (players != null)
    	for (MatchPlayerPO player : players)
    	{
    		sb.append(player.toString());
    	}
        return sb.toString();
    }
    /**
     * 该球员是否在球队中<br/>
     * @param playername 球员
     * @return     是否在该球队中
     */
    public boolean ifPlayer(String playername){
    	for(MatchPlayerPO p : players){
    		if(p.getName().equals(playername)){
    			return true;
    		}
    	}
    	return false;
    }
} 
