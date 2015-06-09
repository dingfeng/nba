package DBtool;

import gnu.trove.map.TIntObjectMap;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeamPlayer {
	 String usr = "root";
	 String password = "root";
	 String url = "jdbc:mysql://127.0.0.1:3306/nba";
	 String driver = "com.mysql.jdbc.Driver";
	
  public void storePlayerTeamData() throws Exception
  {
    Class.forName(driver);
//    String root = "F:/seasons";
//    File rootFile = new File(root);
//    File[] files = rootFile.listFiles();
//    for (File f : files)
//    {
     File f = new File("F:/seasons/14-15");
    	System.out.println(f.getAbsolutePath());
    	new MyThread(f).start();
//    }
    
  }
  class MyThread extends Thread
  {
	  Match match;
	  int season;
	  String playerNormalsql = 
			  "insert into player_season_normal_playoff(ave,season,player_name,team,matchNo,firstServiceNo, "
			  + "rebs ,assistNo,player_time ,offendRebsNo,defenceRebsNo,stealsNo,blockNo,mistakesNo,"
			  + "foulsNo,points,player_minute,hitNo,handNo,hitRate,penaltyHandNo,penaltyHitNo,penaltyHitRate,"
			  + "threeHitNo,threeHandNo,threeHitRate,twoPair,points_uprate,rebs_uprate,help_uprate,scoring_rebound_assist)"
			  + " values(?,?,?,?,?,?,? ,?,? ,?,?,?,?,?,"
			  + "?,?,?,?,?,?,?,?,?,"
			  + "?,?,?,?,?,?,?,?)";
	  String playerHighSql = "insert into player_season_high_playoff(season ,playerName,teamName,efficiency ,GmScEfficiency ,trueHitRate ,"
	  		+ "hitEfficiency ,rebEfficiency ,offenseRebsEfficiency ,defenceRebsEfficiency ,assistEfficiency ,stealsEfficiency ,"
	  		+ "blockEfficiency ,mistakeEfficiency ,useEfficiency ) "
	  		+ "values (? ,?,?,? ,? ,? ,"
	  		+ "? ,? ,? ,? ,? ,? ,"
	  		+ "? ,? ,? )";
	  String teamNormalSql = "insert into team_season_normal_playoff(ave ,season ,teamName ,matchNo ,hitNo ,handNo ,threeHitNo "
	  		+ ", threeHandNo ,penaltyHitNo ,penaltyHandNo ,offenseRebs ,defenceRebs ,rebs ,assistNo ,stealsNo ,blockNo ,"
	  		+ "mistakesNo ,foulsNo ,points ,hitRate ,threeHitRate ,penaltyHitRate ,winRate )"
	  		+ "values(? ,? ,? ,? ,? ,? ,? "
	  		+ ", ? ,? ,? ,? ,? ,? ,? ,? ,? ,"
	  		+ "? ,? ,? ,? ,? ,? ,? )";
	  String teamHighSql = "insert into team_season_high_playoff("
	  		+ "season ,teamName ,offenseRound ,offenseEfficiency ,defenceEfficiency ,"
	  		+ "orebsEfficiency ,drebsEfficiency ,stealsEfficiency ,assistEfficiency )"
	  		+ " values(? ,? ,? ,? ,? ,"
	  		+ "? ,? ,? ,?)";
	  public MyThread(File file)
	  {
		  match = new Match(file.getAbsolutePath());
		  String filename = file.getName();
		  season = getSeason(filename);
	  }
	  public void run() 
	  {
		 try 
		 {
		 Connection conn = DriverManager.getConnection(url,"root","");
		 TIntObjectMap<TeamQueue> team_map = match.getTeam_map();
		 TIntObjectMap<PlayerQueue> player_map = match.getPlayer_map();
		 TeamQueue[] teamqs = new TeamQueue[team_map.size()];
		 PlayerQueue[] playerqs = new PlayerQueue[player_map.size()];
		 team_map.values(teamqs);
		 player_map.values(playerqs);
		 for (TeamQueue q : teamqs)
		 {
			 TeamMatchVO ave = q.getTeamvoAverage();
			 TeamMatchVO total = q.getTeamvoTotal();
			 TeamNormalPO team_ave = ave.getTeamNormal();
			 TeamNormalPO team_total = total.getTeamNormal();
			 TeamHighPO team_high = ave.getTeamHigh();
			 PreparedStatement statement = conn.prepareStatement(teamNormalSql);
			 statement.setInt(1, 1);
			 statement.setInt(2, season);
			 statement.setString(3, team_ave.getName());
			 statement.setInt(4, team_ave.getMatchNo());
			 statement.setDouble(5, team_ave.getHitNo());
			 statement.setDouble(6, team_ave.getHandNo());
			 statement.setDouble(7, team_ave.getThreeHitNo());
			 statement.setDouble(8, team_ave.getThreeHandNo());
			 statement.setDouble(9, team_ave.getPenaltyHitNo());
			 statement.setDouble(10, team_ave.getPenaltyHandNo());
			 statement.setDouble(11, team_ave.getOffenseRebs());
			 statement.setDouble(12, team_ave.getDefenceRebs());
			 statement.setDouble(13, team_ave.getRebs());
			 statement.setDouble(14, team_ave.getAssistNo());
			 statement.setDouble(15, team_ave.getStealsNo());
			 statement.setDouble(16, team_ave.getBlockNo());
			 statement.setDouble(17, team_ave.getMistakesNo());
			 statement.setDouble(18, team_ave.getFoulsNo());
			 statement.setDouble(19, team_ave.getPoints());
			 statement.setDouble(20, team_ave.getHitRate());
			 statement.setDouble(21, team_ave.getThreeHitRate());
			 statement.setDouble(22, team_ave.getPenaltyHitRate());
			 statement.setDouble(23, team_ave.getWinRate());
			 statement.execute();
			 
			 statement = conn.prepareStatement(teamNormalSql);
			 statement.setInt(1, 0);
			 statement.setInt(2, season);
			 statement.setString(3, team_total.getName());
			 statement.setInt(4, team_total.getMatchNo());
			 statement.setDouble(5, team_total.getHitNo());
			 statement.setDouble(6, team_total.getHandNo());
			 statement.setDouble(7, team_total.getThreeHitNo());
			 statement.setDouble(8, team_total.getThreeHandNo());
			 statement.setDouble(9, team_total.getPenaltyHitNo());
			 statement.setDouble(10, team_total.getPenaltyHandNo());
			 statement.setDouble(11, team_total.getOffenseRebs());
			 statement.setDouble(12, team_total.getDefenceRebs());
			 statement.setDouble(13, team_total.getRebs());
			 statement.setDouble(14, team_total.getAssistNo());
			 statement.setDouble(15, team_total.getStealsNo());
			 statement.setDouble(16, team_total.getBlockNo());
			 statement.setDouble(17, team_total.getMistakesNo());
			 statement.setDouble(18, team_total.getFoulsNo());
			 statement.setDouble(19, team_total.getPoints());
			 statement.setDouble(20, team_total.getHitRate());
			 statement.setDouble(21, team_total.getThreeHitRate());
			 statement.setDouble(22, team_total.getPenaltyHitRate());
			 statement.setDouble(23, team_total.getWinRate());
			 statement.execute();
			 
			 statement = conn.prepareStatement(teamHighSql);
			 statement.setInt(1, season);
			 statement.setString(2, team_high.getName());
			 statement.setDouble(3, team_high.getOffenseRound());
			 statement.setDouble(4, team_high.getOffenseEfficiency());
			 statement.setDouble(5,team_high.getDefenceEfficiency());
			 statement.setDouble(6, team_high.getOrebsEfficiency());
			 statement.setDouble(7, team_high.getDrebsEfficiency());
			 statement.setDouble(8, team_high.getStealsEfficiency());
			 statement.setDouble(9, team_high.getAssistEfficiency());
			 statement.execute();
		 }
		 for (PlayerQueue q : playerqs)
		 {
			 PlayerMatchVO ave = q.getAvePlayer();
			 PlayerMatchVO total = q.getTotalPlayer();
			 
			 PlayerHighPO  high = ave.getPlayerHighPO();
			 PlayerNormalPO player_ave = ave.getPlayerNormalPO();
			 PlayerNormalPO player_total = total.getPlayerNormalPO();
			 PreparedStatement statement = conn.prepareStatement(playerHighSql);
			 statement.setInt(1, season);
			 statement.setString(2, high.getPlayerName());
			 statement.setString(3, high.getTeamName());
			 statement.setDouble(4, high.getEfficiency());
			 statement.setDouble(5, high.getGmScEfficiency());
			 statement.setDouble(6, high.getTrueHitRate());
			 statement.setDouble(7, high.getHitEfficiency());
			 statement.setDouble(8, high.getRebEfficiency());
			 statement.setDouble(9, high.getOffenseRebsEfficiency());
			 statement.setDouble(10, high.getDefenceRebsEfficiency());
			 statement.setDouble(11, high.getAssistEfficiency());
			 statement.setDouble(12, high.getStealsEfficiency());
			 statement.setDouble(13, high.getBlockEfficiency());
			 statement.setDouble(14, high.getMistakeEfficiency());
			 statement.setDouble(15, high.getUseEfficiency());
			 statement.execute();
			 
			 statement = conn.prepareStatement(playerNormalsql);
			 statement.setInt(1, 1);
			 statement.setInt(2, season);
			 statement.setString(3, player_ave.getName());
			 statement.setString(4, player_ave.getTeam());
			 statement.setInt(5, player_ave.getMatchNo());
			 statement.setInt(6,(int)player_ave.getFirstServiceNo());
			 statement.setDouble(7, player_ave.getRebs());
			 statement.setDouble(8, player_ave.getAssistNo());
			 statement.setDouble(9, player_ave.getTime());
			 statement.setDouble(10, player_ave.getOffendRebsNo());
			 statement.setDouble(11, player_ave.getDefenceRebsNo());
			 statement.setDouble(12, player_ave.getStealsNo());
			 statement.setDouble(13, player_ave.getBlockNo());
			 statement.setDouble(14, player_ave.getMistakesNo());
			 statement.setDouble(15, player_ave.getFoulsNo());
			 statement.setDouble(16, player_ave.getPoints());
			 statement.setDouble(17, player_ave.getMinute());
			 statement.setDouble(18, player_ave.getHitNo());
			 statement.setDouble(19, player_ave.getHandNo());
			 statement.setDouble(20, player_ave.getHitRate());
			 statement.setDouble(21, player_ave.getPenaltyHandNo());
			 statement.setDouble(22, player_ave.getPenaltyHitNo());
			 statement.setDouble(23, player_ave.getPenaltyHitRate());
			 statement.setDouble(24, player_ave.getThreeHitNo());
			 statement.setDouble(25, player_ave.getThreeHandNo());
			 statement.setDouble(26, player_ave.getThreeHitRate());
			 statement.setDouble(27, player_ave.getTwoPair());
			 statement.setDouble(28, player_ave.getPoints_uprate());
			 statement.setDouble(29, player_ave.getRebs_uprate());
			 statement.setDouble(30, player_ave.getHelp_uprate());
			 statement.setDouble(31, player_ave.getScoring_rebound_assist());;
			 statement.execute();
			 
			 statement = conn.prepareStatement(playerNormalsql);
			 statement.setInt(1, 0);
			 statement.setInt(2, season);
			 statement.setString(3, player_total.getName());
			 statement.setString(4, player_total.getTeam());
			 statement.setInt(5, player_total.getMatchNo());
			 statement.setInt(6,(int)player_total.getFirstServiceNo());
			 statement.setDouble(7, player_total.getRebs());
			 statement.setDouble(8, player_total.getAssistNo());
			 statement.setDouble(9, player_total.getTime());
			 statement.setDouble(10, player_total.getOffendRebsNo());
			 statement.setDouble(11, player_total.getDefenceRebsNo());
			 statement.setDouble(12, player_total.getStealsNo());
			 statement.setDouble(13, player_total.getBlockNo());
			 statement.setDouble(14, player_total.getMistakesNo());
			 statement.setDouble(15, player_total.getFoulsNo());
			 statement.setDouble(16, player_total.getPoints());
			 statement.setDouble(17, player_total.getMinute());
			 statement.setDouble(18, player_total.getHitNo());
			 statement.setDouble(19, player_total.getHandNo());
			 statement.setDouble(20, player_total.getHitRate());
			 statement.setDouble(21, player_total.getPenaltyHandNo());
			 statement.setDouble(22, player_total.getPenaltyHitNo());
			 statement.setDouble(23, player_total.getPenaltyHitRate());
			 statement.setDouble(24, player_total.getThreeHitNo());
			 statement.setDouble(25, player_total.getThreeHandNo());
			 statement.setDouble(26, player_total.getThreeHitRate());
			 statement.setDouble(27, player_total.getTwoPair());
			 statement.setDouble(28, player_total.getPoints_uprate());
			 statement.setDouble(29, player_total.getRebs_uprate());
			 statement.setDouble(30, player_total.getHelp_uprate());
			 statement.setDouble(31, player_total.getScoring_rebound_assist());;
			 statement.execute();
			 
		 }
		 conn.close();
		 } 
		 catch (Exception e)
		 {
		 e.printStackTrace();
		 }
	  }
  }
  public static int getSeason(String filename)
  {
	  String temp = filename.substring(0, 2);
	  int s = Integer.parseInt(temp);
	  int result = 0;
	  if (s > 40)
	  {
		  result = 1900+s;
	  }
	  else 
	  {
		  result = 2000+s;
	  }
	  return result;
  }
  public static void main(String[] args) throws Exception
  {
	  TeamPlayer teamPlayer = new TeamPlayer();
	  teamPlayer.storePlayerTeamData();
  }
}
