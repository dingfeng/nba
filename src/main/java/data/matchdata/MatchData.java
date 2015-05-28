package data.matchdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import po.MatchPlayerPO;
import po.MatchTeamPO;
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
		   MatchesPO[] result  = null;
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
		    	matchpos.add(new MatchesPO(getMatchTeamPO(matchId,team1),getMatchTeamPO(matchId,team2),date));
		    }
		    result = new MatchesPO[matchpos.size()];
		    matchpos.toArray(result);
		   }catch (Exception e)
		   {
		   }
		   finally 
		   {
			   try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   }
		   
			return result;
		}

		@Override
		public MatchesPO[] getPlayerMatches(int season, String name) 
		{
			String sql  = "select match_id from match_player where match_id > ? and match_id < ? and player_name = ?";
		    int[] id_scope = getMatchIdScope(season);
		    ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(85);
		    int matchId = -1;
		    try{
		    conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, name);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	MatchesPO matchespo = getMatches(matchId);
		    	allMatches.add(matchespo);
		    }
		    }catch(Exception e)
		    {
		    }
	        finally{
	        	try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
		    MatchesPO[] result = new MatchesPO[allMatches.size()];
		    allMatches.toArray(result);
			return result;
		}

		@Override
		public MatchesPO[] getTeamMatches(int season, String teamName) {
			String sql = "select match_id from match_team where match_id > ? and match_id < ? and teama = ?";
			int[] id_scope = getMatchIdScope(season);
			int matchId = -1;
			MatchesPO[] matchpos = null;
			MatchesPO match = null;
			ArrayList<MatchesPO> matchList = new ArrayList<MatchesPO>(90);
			try
			{
			conn = DriverManager.getConnection(url,"root","");
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setInt(1, id_scope[0]);
			statement.setInt(2, id_scope[1]);
			statement.setString(3, teamName);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
			 matchId = results.getInt(1);
		     match = getMatches(matchId);
		     matchList.add(match);
			}
			}catch (Exception e)
			{}
			matchpos = new MatchesPO[matchList.size()];
			matchList.toArray(matchpos);
			return  matchpos;
		}

		@Override
		public MatchesPO getTeamMatches(int season, Date date,
				String teamName) {
			String sql = "select m.match_id from matches m where m.match_id > ? and m.match_id < ? and m.match_date = ? and "
					+ "? in (select team1.teama from match_team as team1 where team1.match_id = m.match_id)";
			String date1 = convertDate(date);
			int[] id_scope = getMatchIdScope(season);
			int matchId = -1;
			MatchesPO match = null;
			try
			{
			conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, date1);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	match = getMatches(matchId);
		    }
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return match;
		}
		
		@Override
		public MatchesPO[] getMatches(int season, Date date) {
			String sql = "select match_id from matches where match_id > ? and match_id < ? and match_date = ?";
			int[] id_scope = getMatchIdScope(season);
			String dateStr = convertDate(date);
			int matchId = -1;
			MatchesPO match = null;
			ArrayList<MatchesPO> list = new ArrayList<MatchesPO>(40);
			MatchesPO[] result = null;
			try
			{
				conn = DriverManager.getConnection(url,"root","");
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id_scope[0]);
				statement.setInt(2,id_scope[1]);
				statement.setString(3, dateStr);
				ResultSet results = statement.executeQuery();
				while (results.next())
				{
					matchId = results.getInt(1);
					match = getMatches(matchId);
					list.add(match);
				}
				result = new MatchesPO[list.size()];
				list.toArray(result);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return result;
		}
		
		private MatchesPO getMatches(int match_id) throws Exception
		{
			String sql = "select * from matches where match_id = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, match_id);
			ResultSet results = statement.executeQuery();
			String team1 = null;
			String team2 = null;
			String date = null;
			if (results.next())
			{
		    	team1 = results.getString(2);
		    	team2 = results.getString(3);
		    	date  = results.getString(4);
			}
			MatchTeamPO teampo1 = getMatchTeamPO(match_id,team1);
			MatchTeamPO teampo2 = getMatchTeamPO(match_id,team2);
			MatchesPO matchpo = new MatchesPO(teampo1,teampo2,date);
			return matchpo;
		}
		
		private int getSeason()
		{
			return season;
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
        private int[] getPointsList(int[] points)
        {
           int i = 0;
           for (i=0;i<points.length;i++)
           {
        	   if (points[i] == 0)
        	   {
        		   break;
        	   }
           }
           int[] result = new int[i];
           for (int j = 0; j < i; j++)
           {
        	   result[j] = points[j];
           }
           return result;
        }
        private MatchTeamPO getMatchTeamPO(int matchId, String teamName) throws Exception
        {
        	String sql = "select * from match_team where match_id = ? and teama = ?";
        	int [] scores= new int[14];
        	int totalScore=0;
        	int time = 0;
        	PreparedStatement statement = conn.prepareStatement(sql);
        	statement.setInt(1, matchId);
        	statement.setString(2, teamName);
        	ResultSet results = statement.executeQuery();
        	if (results.next())
        	{
        	 totalScore = results.getInt(3);
        	 for (int i = 0; i < 14; ++i)
        	 {
        		 scores[i] = results.getInt(4+i);
        	 }
        	 scores = getPointsList(scores);
        	}
           time = 2880 + ( scores.length -4) * 300;
        	MatchPlayerPO[] players = getMatchPlayerPOs(matchId,teamName);
        	MatchTeamPO teamPO  = new MatchTeamPO(players,scores,totalScore, teamName, time);
			return teamPO;
        }
        private MatchPlayerPO[] getMatchPlayerPOs(int matchId, String teamName) throws Exception
        {
        	String sql = "select player_name,courtTime,hitNo,handNo,threeHitNo,threeHandNo,penaltyHitNo,penaltyHandNo,"
        			+ "offenseRebs,defenceRebs,rebs,assist,steal,blockno,mistakeno,fouls"
        			+ "from match_player where match_id = ? and teama = ?";
        	PreparedStatement statement = conn.prepareStatement(sql);
        	statement.setInt(1, matchId);
        	statement.setString(2, teamName);
        	ResultSet results = statement.executeQuery();
        	ArrayList<MatchPlayerPO> players = new ArrayList<MatchPlayerPO>(15);
        	String name= null;
        	String location= "";
        	double time=0;
        	int hitNo=0;
			int handNo=0;
			int threeHitNo=0;
			int threeHandNo=0;
			int penaltyHitNo=0;
			int penaltyHandNo=0;
			int offenseRebs=0;
			int defenceRebs=0;
			int rebs=0;
			int help=0;
			int stealsNo=0;
			int blockNo=0;
			int mistakesNo=0;
			int foulsNo=0;;
        	while (results.next())
        	{
//        		"select player_name,courtTime,hitNo,handNo,threeHitNo,threeHandNo,penaltyHitNo,penaltyHandNo,"
//            			+ "offenseRebs,defenceRebs,rebs,assist,steal,blockno,mistakeno,fouls,score, "
//            			+ "from match_player where match_id = ? and teama = ?";
        		name = results.getString(1);
        		time = results.getInt(2);
        		hitNo = results.getInt(3);
        		handNo=results.getInt(4);
        		threeHitNo = results.getInt(5);
        		threeHandNo = results.getInt(6);
        		penaltyHitNo = results.getInt(7);
        		penaltyHandNo = results.getInt(8);
        		offenseRebs = results.getInt(9);
        		defenceRebs = results.getInt(10);
        		rebs = results.getInt(11);
        		help = results.getInt(12);
        		stealsNo = results.getInt(13);
        		blockNo = results.getInt(14);
        		mistakesNo = results.getInt(15);
        		foulsNo = results.getInt(16);
        		players.add(new MatchPlayerPO( name,  location,  time,  hitNo,
        				 handNo,  threeHitNo,  threeHandNo,  penaltyHitNo,
        				 penaltyHandNo,  offenseRebs,  defenceRebs,  rebs,
        				 help,  stealsNo,  blockNo,  mistakesNo,
        				 foulsNo));
        	}
        	MatchPlayerPO[] result = new MatchPlayerPO[players.size()];
        	players.toArray(result);
			return result;
        }
        private  String convertDate(Date date)
        {
        	SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        	String result = format.format(date);
        	return result;
        }
}
