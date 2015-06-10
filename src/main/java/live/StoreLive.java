package live;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

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
		private CurrentLive live;
  public StoreLive()
  {
	  try{
	  Class.forName(driver);
	  conn = DriverManager.getConnection(url,"root","");
	  live = new CurrentLive();
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public void storeLive()
  {
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
		  insertMessages(matchId,messages);
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
  
  public void insertMessages(int matchId, ArrayList<String> list)
  {
	  String sql = "insert into match_messages values(?,?,?,?,?,?,?)";
	  try
	  {
		  String[] temp = null;
		  String str_temp = null;
		  String time = null;
		  String team = null;
		  String event = null;
		  String ptop = null;
		  PreparedStatement statement = null;
		  int size = list.size();
		  for (int i = size - 1;i >= 0; --i)
		  {
			  statement = conn.prepareStatement(sql);
			  str_temp = list.get(i);
			  temp = str_temp.split(" ");
			  if (temp.length == 4)
			  {
				  statement.setInt(1,size - 1 - i);
				  statement.setInt(2, matchId);
				  statement.setString(3, temp[0]);
				  statement.setString(4, temp[1]);
				  statement.setString(5, temp[2]);
				  statement.setString(6, temp[3]);
				  statement.setInt(7, 0);
				  statement.execute();
			  }
			  else 
			  {
				  statement.setInt(1,size - 1 - i);
				  statement.setInt(2, matchId);
				  statement.setNull(3,Types.NVARCHAR);
				  statement.setNull(4, Types.NVARCHAR);
				  statement.setString(5,temp[0]);
				  statement.setNull(6, Types.NVARCHAR);
				  statement.setInt(7,1);
				  statement.execute();
			   }
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public  void updateMessages(int matchId, ArrayList<String> list)
  {
	  String sql = "insert into match_messages values(?,?,?,?,?,?,?)";
	  int messSize = getMessageSize(matchId);
	  int messId = -1;
	  int nowSize = list.size();
	  String str_temp  = null;
	  String[] temp = null;
	  try
	  {
	  PreparedStatement statement = conn.prepareStatement(sql);
	  for (int i = 0; i < nowSize - messSize; ++i)
	  {
		  str_temp = list.get(nowSize - messSize - 1 - i);
		  temp = str_temp.split(" ");
		  if (temp.length == 4)
		  {
			  statement.setInt(1,messSize+ i);
			  statement.setInt(2, matchId);
			  statement.setString(3, temp[0]);
			  statement.setString(4, temp[1]);
			  statement.setString(5, temp[2]);
			  statement.setString(6, temp[3]);
			  statement.setInt(7, 0);
			  statement.execute();
		  }
		  else 
		  {
			  statement.setInt(1,messSize+ i);
			  statement.setInt(2, matchId);
			  statement.setNull(3,Types.NVARCHAR);
			  statement.setNull(4, Types.NVARCHAR);
			  statement.setString(5,temp[0]);
			  statement.setNull(6, Types.NVARCHAR);
			  statement.setInt(7,1);
			  statement.execute();
		   }
	  }
	  
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  private int getMessageSize(int matchId)
  {
	  String sql = "select count(*) from match_messages where match_id = ?";
	  int result = -1;
	  try
	  {
		  PreparedStatement statement = conn.prepareStatement(sql);
		  statement.setInt(1, matchId);
		  ResultSet results = statement.executeQuery();
		  if (results.next())
		  {
			  result = results.getInt(1);
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return result;
  }
  
  public void updateLive()
  {
	  ArrayList<CurrentMatch> list = live.getAllMatches();
	  for (int i = 0; i < list.size(); ++i)
	  {
		  updateCurrentMatch(list.get(i));
	  }
  }
  
  public void updateCurrentMatch(CurrentMatch match)
  {
	  try
	  {
		  CurrentTeam team1 = match.getTeam1();
		  CurrentTeam team2 = match.getTeam2();
		  int matchId = match.getMatchId();
		  ArrayList<String> messages = match.getMessages();
		  updateMessages(matchId,messages);
		  updateTeam(team1,matchId);
		  updateTeam(team2,matchId);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  
  
  private  void updateTeam(CurrentTeam team, int match_id)
  {
	  String sql = 
			  "update live_team set shot=?,threeShot=?,penalty=?,offenseRebs=?,defenceRebs=?,"
			  + "rebs=?,assist=?,fouls=?,steals=?,mistakes=?,blockNo=?,scores=?,shotRate=?,threeRate=?"
			  + ",penaltyRate=?,totalScores=?,disc=?,win=?,p1=?,p2=?,p3=?,p4=?,p5=?,p6=?,p7=?,p8=? where "
			  + "matchId=? and teamName = ?";
	 String primaryDatas[] = team.getPrimaryDatas();
     String rates[] = team.getRates();
     String totalScores = team.getTotalScores();
     String points[] = team.getPoints();
     String disc =  team.getDisc();
     String win = team.getWin();
     String teamName = team.getTeamName();
	  try
	  {
		  PreparedStatement statement = conn.prepareStatement(sql);
		  for (int i = 0; i < primaryDatas.length;++i)
		  {
			  statement.setString(1+i, primaryDatas[i]);
		  }
		  int len = primaryDatas.length + 1;
		  statement.setString(len, rates[0]);
		  statement.setString(len+1, rates[1]);
		  statement.setString(len+2, rates[2]);
		  statement.setString(len+3,  totalScores);
		  statement.setString(len+4, disc);
		  statement.setString(len+5, win);
		  for (int i = 0;i < 8; ++i)
		  {
			  if ( i < points.length)
			  statement.setString(len+6+i, points[i]);
			  else
			  {
				  statement.setString(len+6+i, null);
			  }
		  }
		  statement.setInt(len+14, match_id);
		  statement.setString(len+15, teamName);
		  statement.execute();
		  CurrentPlayer[] firsts = team.getFirsts();
		  CurrentPlayer[] benches = team.getBenches();
		  for (int i = 0; i < firsts.length; ++i)
		  {
			  updatePlayer(match_id,firsts[i],teamName);
		  }
		  for (int i = 0; i < benches.length; ++i)
		  {
			  updatePlayer(match_id,benches[i],teamName);
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  private void updatePlayer(int matchId,CurrentPlayer player, String teamName)
  {
	  String sql = "update live_player set position=?,match_time=?,shot=?,threeShot=?,penalty=?,offenseRebs=?,defenseRebs=?,rebs=?,assist=?,"
	  		+ "fouls=?,steals=?,mistakes=?,blockNo=?,scores=?,efficiency=? where match_id=? and teama =? and player_name=?";
	  
	  try
	  {
		  PreparedStatement statement = conn.prepareStatement(sql);
		  String[] datas = player.getDatas();
		  for(int i = 0; i < datas.length - 1; ++i)
		  {
			  statement.setString(1+i, datas[i+1]);
		  }
		  statement.setInt(datas.length, matchId);
		  statement.setString(datas.length+1,  teamName);
		  statement.setString(datas.length+2,  datas[0]);
		  statement.execute();
	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
  }
  public void beginUpdate()
  {
	 Timer timer = new Timer();
	 TimerTask task = new TimerTask()
	 {
        int  i  = 0;
		public void run() {
			System.out.println(++i);
			updateLive();
		}
	 };
	 timer.schedule(task, 0,3000);
  }
  public static void main(String[] args)
  {
	  StoreLive storeLive = new StoreLive();
	  storeLive.storeLive();
	  storeLive.beginUpdate();
  }
}
