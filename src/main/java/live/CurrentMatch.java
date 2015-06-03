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
  
 public  static ArrayList<String> messages = new ArrayList<String>();
 public  static int index =-1;
 public static int size;
  public CurrentMatch(CurrentTeam team1, CurrentTeam team2,
		  String date,String time,String gym,String audience)
		  {
	      this.team1 = team1;
	      this.team2 = team2;
	      this.date = date;
	      this.time = time;
	      this.gym = gym;
	      this.audience = audience;
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

 public static void update(String[] ne)
 {
	for (String s : ne)
	{
		messages.add(s);
	}
	size = messages.size();
 }
 public static int getIndex()
 {
	 return index;
 }
 public static String[] getNewMessages()
 {
	 String[] newMessages = new String[size - index -1];
	 for (int i =0; i < newMessages.length;++i)
	 {
		 newMessages[i] = messages.get(++index);
	 }
	 return newMessages;
 }
}
