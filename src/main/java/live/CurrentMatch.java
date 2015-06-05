package live;

import java.util.ArrayList;

public class CurrentMatch 
{
  CurrentTeam team1;
  CurrentTeam team2;
  String date;
  String time;
  String gym;
  String audience;
  String matchId;
 public  static ArrayList<String> messages = new ArrayList<String>();
 public  static int index =-1;
 public static int size;
  public CurrentMatch(String matchId,CurrentTeam team1, CurrentTeam team2,
		  String date,String time,String gym,String audience)
		  {
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
	  sb.append("文字直播 ："+ messages.get(0));
	  return sb.toString();
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

}
