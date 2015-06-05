package live;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;

public class StoreLive {
	  private static MatchDataService matchData;
	    private static TeamDataService teamData;
	    private static PlayerDataService playerData;
//		private String usr = "root";
//		private String password = "root";
		private String url = "jdbc:mysql://127.0.0.1:3306/nba?useUnicode=true&characterEncoding=utf8";
//		String url = "jdbc:mysql://dingfeng:3306/nba";
		private String driver = "com.mysql.jdbc.Driver";
		private Connection conn;
  public StoreLive()
  {
	  try{
	  Class.forName(driver);
	   	 conn = DriverManager.getConnection(url,"root","");
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public void storeLive()
  {
	  CurrentLive live = new CurrentLive();
	  ArrayList<CurrentMatch> list = live.getAllMatches();
	  for (int i = 0 ;i <list.size();++i)
	  {
		  storeLiveMatch(list.get(i));
	  }
  }
  
  public void storeLiveMatch(CurrentMatch match)
  {
	  String matchSql = "insert into livematch values(?,?,?,?,?,?,?)";
	  try
	  {
		  CurrentTeam team1 = match.getTeam1();
		  CurrentTeam team2 = match.getTeam2();
		  int matchId = match.getMatchId();
		  String host_team = team1.getTeamName();
		  String guest_team = team2.getTeamName();
		  String date = match.getDate();
		  String time = match.getTime();
		  String gym = match.getGym();
		  String audience = match.getAudience();
		  ArrayList<String> messages = match.getMessages();
		  PreparedStatement statement = conn.prepareStatement(matchSql);
		  statement.setInt(1, matchId);
		  statement.setString(2, host_team);
		  statement.setString(3,guest_team);
		  statement.setString(4, date);
		  statement.setString(5, time);
		  statement.setString(6, gym);
		  statement.setString(7, audience);
		  statement.execute();
		  storeTeam(team1,matchId);
		  storeTeam(team2,matchId);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  private void storeTeam(CurrentTeam team, int match_id)
  {
	 String sql  = "insert into live_team values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 String primaryDatas[] = team.getPrimaryDatas();
     String rates[] = team.getRates();
     String totalScores = team.getTotalScores();
     String points[] = team.getPoints();
     String disc =  team.getDisc();
     String win = team.getWin();
     String teamName = team.getTeamName();
     try{
     PreparedStatement statement = conn.prepareStatement(sql);
     statement.setInt(1, match_id);
     statement.setString(2, teamName);
     for (int i = 0; i < primaryDatas.length; ++i)
     {
    	 statement.setString(3+i, primaryDatas[i]);
     }
     int base = primaryDatas.length + 3;
     for (int i = 0 ; i < rates.length; ++i)
     {
    	 statement.setString(base+i, rates[i]);
     }
     base = base + rates.length;
     statement.setString(base, totalScores);
     statement.setString(base+1, disc);
     statement.setString(base+2, win);
     base = base + 3;
     for (int i = 0 ; i < points.length; ++i)
     {

    	 statement.setString(base+i, points[i]);
     }
     base = base + points.length;
     for (int i = base; i < 29; ++i)
     {
    	 statement.setString(i, "0");
     }
     statement.execute();
     CurrentPlayer[] firsts = team.getFirsts();
     CurrentPlayer[] benches = team.getBenches();
     for (int i = 0;i < firsts.length; ++i)
     {
    	 storePlayers(match_id,firsts[i],teamName,1);
     }
     for (int i = 0; i < benches.length; ++i)
     {
    	 storePlayers(match_id,benches[i],teamName,0);
     }
     }catch(Exception e)
     {
    	 e.printStackTrace();
     }
  }
  private void storePlayers(int matchId,CurrentPlayer player, String teamName,int first)
  {
	  String sql =  "insert into live_player values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	  String[] datas = player.getDatas();
	  try
	  {
		  PreparedStatement statement  = conn.prepareStatement(sql);
		  statement.setInt(1, matchId);
		  statement.setString(2, teamName);
		  statement.setInt(3, first);
		  for (int i = 0; i < datas.length; ++i)
		  {
			  statement.setString(4+i, datas[i]);
		  }
		  statement.execute();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public static void main(String[] args)
  {
	  StoreLive storeLive = new StoreLive();
	  storeLive.storeLive();
  }
}
