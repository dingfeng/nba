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
  public int getMatchId()
  {
	  return Integer.parseInt(matchId);
  }
  public void setMessages(ArrayList<String> mess)
  {
	  messages = mess;
  }
  public ArrayList<String> getMessages()
  {
	  return messages;
  }
public CurrentTeam getTeam1() {
	return team1;
}
public CurrentTeam getTeam2() {
	return team2;
}
public String getDate() {
	return date;
}
public String getTime() {
	return time;
}
public String getGym() {
	return gym;
}
public String getAudience() {
	return audience;
}
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
