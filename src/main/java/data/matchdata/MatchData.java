package data.matchdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import po.MatchPlayerPO;
import po.MatchesPO;
import dataservice.matchdataservice.MatchDataService;

public class MatchData implements MatchDataService{
	   private int season;
	   private Connection conn;
	   private String sql_match = "select * from matches where match_id = ?";
	   private String sql_team = "select * from match_team where match_id = ?";
	   private String sql_player = "select * from match_player where match_id = ? and player_name = ? and teama = ?"; 
	   private String sql_seasonMatches = "select * from matches where match_id > ? and match_id < ?";
	   private String sql_playerById = "select * from match_player where match_id = ? and teama = ?";
	   private String usr = "root";
	   private String password = "root";
	   private String url = "jdbc:mysql://127.0.0.1:3306/nba";
	   private String driver = "com.mysql.jdbc.Driver";
	   public MatchData(Connection conn)
	   {
    	 try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 this.conn = conn;
	   }
	   
	   @Override
	   public MatchesPO[] getSeasonMatches(int season) {
		   try{
		    conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql_seasonMatches);
		    int[] id_scope = getMatchIdScope(season);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    ResultSet results =  statement.executeQuery();
		    ArrayList<MatchesPO> matchpos = new ArrayList<MatchesPO>(1300);
		    String date = null;
		    String team1 = null;
		    String team2 = null;
		    int matchId = -1;
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	team1 = results.getString(2);
		    	team2 = results.getString(3);
		    	date  = results.getString(4);
		    }
		    conn.close();
		   }catch (Exception e)
		   {
		   }
		   finally 
		   {
		   }
		   
			return null;
		}

		@Override
		public MatchesPO[] getPlayerMatches(int season, String name) {
			return null;
		}

		@Override
		public MatchesPO[] getTeamMatches(int season, String teamName) {
			return null;
		}

		@Override
		public MatchesPO[] getTeamMatches(int season, String Date,
				String teamName) {
			return null;
		}
		
		@Override
		public MatchesPO[] getMatches(int season, String Date) {
			return null;
		}
		
		public MatchesPO[] getMatches(int match_id)
		{
			return null;
		}
		
		public int getSeason()
		{
			return season;
		}
		
		private Iterator<MatchPlayerPO> findMatchPlayer(int matchId, String teama)
		{
			 try {
				PreparedStatement statement = conn.prepareStatement(sql_playerById);
				//"select * from match_player where match_id = ? and teama = ?";
				statement.setInt(1, matchId);
				statement.setString(2, teama);
				ResultSet results = statement.executeQuery();
				ArrayList<MatchPlayerPO> matchPlayers = new ArrayList<MatchPlayerPO>(15);
				MatchPlayerPO tempPlayer = null;
				while(results.next()) 
				{
				 String name = results.getString(2);// 球员名称
				 String location = results.getString(3);//位置
				 int  time = results.getInt(4); // 在场时间
				 int hitNo = results.getInt(5); // 投篮命中数
				 int handNo = results.getInt(6); // 投篮出手次数
				 int threeHitNo = results.getInt(7); // 三分命中数
				 int threeHandNo = results.getInt(8); // 三分出手数
				 int penaltyHitNo = results.getInt(9); // 罚球命中数
				 int penaltyHandNo = results.getInt(10); // 罚球出手数
				 int offenseRebs = results.getInt(11); // 进攻篮板数
				 int defenceRebs = results.getInt(12); // 防守篮板数
				 int rebs = results.getInt(13); // 篮板数
				 int help = results.getInt(14);//总篮板数
				 int stealsNo = results.getInt(15);// 抢断数
				 int blockNo = results.getInt(16);// 盖帽数
				 int mistakesNo = results.getInt(17);// 失误数
				 int foulsNo = results.getInt(18);// 犯规数
				 int points = results.getInt(19);// 得分
				 String teamnameAbridge = results.getString(20); //球队缩写名
//				 tempPlayer = new MatchPlayerPO();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			 
		}
		
		private int[] getMatchIdScope(int season)
		{
			int[] result = new int[2];
			int temp = 0;
			if (season >= 2000)
			{
				temp = season - 2000;
				temp += 200;
				temp *= 10000;
				result[0] = temp;
				result[1] = temp + 10000;
			}
			else 
			{
				temp = season - 1900;
				temp += 100;
				temp *= 10000;
				result[0] = temp;
				result[1] = temp + 10000;
			}
			return result;
		}
        
}
