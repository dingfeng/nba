package po;

import java.io.Serializable;
import java.util.ArrayList;

public class CurrentMatch implements Serializable
{
  CurrentTeam team1;
  CurrentTeam team2;
  String date;
  String time;
  String gym;
  String audience;
  String matchId;
  ArrayList<String> messages;
  public CurrentMatch(String matchId,CurrentTeam team1, CurrentTeam team2,
		  String date,String time,String gym,String audience)
		  {
	      this.matchId = matchId;
	      this.team1 = team1;
	      this.team2 = team2;
	      this.date = date;
	      this.time = time;
	      this.gym = gym;
	      this.audience = audience;
		  }
  public String toString()
  {
	  StringBuilder sb = new StringBuilder();
	  sb.append("date : "+date+"\n");
	  sb.append("time : "+time+"\n");
	  sb.append("gym : "+gym+"\n");
	  sb.append("audience : "+gym+"\n");
	  sb.append("team1 : \n"+team1.toString()+"\n");
	  sb.append("team2 : \n"+team2.toString()+"\n");
	  if (messages != null && messages.size() != 0)
	  sb.append("文字直播 ："+ messages.get(0));
	  return sb.toString();
  }
  /**
   * 获得比赛编号
   * @return  比赛编号
   */
  public int getMatchId()
  {
	  return Integer.parseInt(matchId);
  }
  /**
   * 设置文字直播内容
   * @param mess  文字直播内容
   */
  public void setMessages(ArrayList<String> mess)
  {
	  messages = mess;
  }
  /**
   * 获得文字直播
   * @return  文字直播内容
   */
  public ArrayList<String> getMessages()
  {
	  return messages;
  }
  /**
   * 获得主场球队
   * @return  主场球队
   */
public CurrentTeam getTeam1() {
	return team1;
}
/**
 * 获得客场球队
 * @return  客场球队
 */
public CurrentTeam getTeam2() {
	return team2;
}
/**
 * 获得比赛日期
 * @return  比赛日期
 */
public String getDate() {
	return date;
}
/**
 * 获得比赛时间
 * @return  比赛时间
 */
public String getTime() {
	return time;
}
/**
 * 获得体育场
 * @return   体育场
 */
public String getGym() {
	return gym;
}
/**
 * 获得观众数
 * @return 观众数
 */
public String getAudience() {
	return audience;
}
/**
 * 调整小比分数组
 */
public void adjustTeamPoints()
{
	String[] pts1 = team1.getPoints();
	String[] pts2 = team2.getPoints();
	int i = 0;
	for (; i < pts1.length; ++i)
	{
		if (pts1[i]==null && pts2[i]==null)
		{
		 break;
		}
	}
	team1.changePoints(i);
	team2.changePoints(i);
}
}
