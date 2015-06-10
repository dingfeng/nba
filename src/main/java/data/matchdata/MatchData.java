package data.matchdata;

import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import live.CurrentMatch;
import live.CurrentPlayer;
import live.CurrentTeam;
import live.SimpleMatchLive;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.OldMatch;
import data.playerdata.PlayerData;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.SeasonType;

public class MatchData implements MatchDataService{
	   private Connection conn;
	   private String sql_seasonMatches = "select * from matches where match_id > ? and match_id < ? ";
	   private String sql_seasonMatches_r = "select * from matches where match_id > ? and match_id < ? order by match_id desc limit ?,?";
	   public MatchData(Connection conn)
	   {
		 this.conn = conn;
	   }
	   
	   public MatchesPO[] getRegularSeasonMatches(int season,int low,int high) {
		   MatchesPO[] result  = null;
		   try{
		    PreparedStatement statement = conn.prepareStatement(sql_seasonMatches_r);
		    int[] id_scope = getMatchIdScope(season);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setInt(3, low);
		    statement.setInt(4, high);
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
		    	matchpos.add(new MatchesPO(matchId,getMatchTeamPO(matchId,team1,0),getMatchTeamPO(matchId,team2,0),date));
		    }
		    result = new MatchesPO[matchpos.size()];
		    matchpos.toArray(result);
		   }catch (Exception e)
		   {
			   e.printStackTrace();
		   }
		   
			return result;
		}

		public MatchesPO[] getRegularPlayerMatches(int season, String name) 
		{
			String sql  = "select match_id from match_player where match_id > ? and match_id < ? and player_name = ?";
		    int[] id_scope = getMatchIdScope(season);
		    ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(85);
		    int matchId = -1;
		    try{
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, name);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	MatchesPO matchespo = getMatches(matchId,0);
		    	allMatches.add(matchespo);
		    }
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
//	        finally{
//	        	try {
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//	        }
		    MatchesPO[] result = new MatchesPO[allMatches.size()];
		    allMatches.toArray(result);
			return result;
		}

		public MatchesPO[] getRegularTeamMatches(int season, String teamName) {
			String sql = "select match_id from match_team where match_id > ? and match_id < ? and teama = ?";
			int[] id_scope = getMatchIdScope(season);
			int matchId = -1;
			MatchesPO[] matchpos = null;
			MatchesPO match = null;
			ArrayList<MatchesPO> matchList = new ArrayList<MatchesPO>(90);
			try
			{
//			conn = DriverManager.getConnection(url,"root","");
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setInt(1, id_scope[0]);
			statement.setInt(2, id_scope[1]);
			statement.setString(3, teamName);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
			 matchId = results.getInt(1);
		     match = getMatches(matchId,0);
		     matchList.add(match);
			}
			}catch (Exception e)
			{}
			matchpos = new MatchesPO[matchList.size()];
			matchList.toArray(matchpos);
			return  matchpos;
		}

		@Override
		public MatchesPO getTeamMatches(Date date,
				String teamName) {
			String sql = "select m.match_id from matches m where m.match_id > ? and m.match_id < ? and m.match_date = ? and  "
					+ "? in (select team1.teama from match_team as team1 where team1.match_id = m.match_id)";
			String date1 = convertDate(date);
			int[] id_scope = getMatchIdScope(season1);
			int matchId = -1;
			MatchesPO match = null;
			try
			{
//			conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, date1);
		    statement.setString(4, teamName);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	match = getMatches(matchId,0);
		    }
		    if (match == null)
		    {
		    	id_scope = getPlayerOffMatchId(season1);
			    statement = conn.prepareStatement(sql);
			    statement.setInt(1, id_scope[0]);
			    statement.setInt(2, id_scope[1]);
			    statement.setString(3, date1);
			    statement.setString(4, teamName);
			    results = statement.executeQuery();
			    while (results.next())
			    {
			    	matchId = results.getInt(1);
			    	match = getMatches(matchId,0);
			    }
		    }
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return match;
		}
		
		public MatchesPO[] getMatches(Date date) {
			String sql = "select match_id from matches where match_id > ? and match_id < ? and match_date = ?";
			int[] id_scope = getMatchIdScope(season1);
			int[] playerOff_scope = getPlayerOffMatchId(season1);
			String dateStr = convertDate(date);
			int matchId = -1;
			MatchesPO match = null;
			ArrayList<MatchesPO> list = new ArrayList<MatchesPO>(40);
			MatchesPO[] result = null;
			try
			{
//				conn = DriverManager.getConnection(url,"root","");
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, id_scope[0]);
				statement.setInt(2,id_scope[1]);
				statement.setString(3, dateStr);
				ResultSet results = statement.executeQuery();
				while (results.next())
				{
					matchId = results.getInt(1);
					match = getMatches(matchId,0);
					list.add(match);
				}
				 statement = conn.prepareStatement(sql);
				statement.setInt(1, playerOff_scope[0]);
				statement.setInt(2,playerOff_scope[1]);
				statement.setString(3, dateStr);
				results = statement.executeQuery();
				while (results.next())
				{
					matchId = results.getInt(1);
					match = getMatches(matchId,0);
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
		
		private MatchesPO getMatches(int match_id,int flag) throws Exception
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
			MatchTeamPO teampo1 = getMatchTeamPO(match_id,team1,flag);
			MatchTeamPO teampo2 = getMatchTeamPO(match_id,team2,flag);
			MatchesPO matchpo = new MatchesPO(match_id,teampo1,teampo2,date);
			return matchpo;
		}
		
		public static int[] getMatchIdScope(int season)
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
		public static  int[] getPlayerOffMatchId(int season)
		{
			int[] scope = getMatchIdScope(season);
			scope[0] += 10000000;
			scope[1] += 10000000;
			return scope;
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
        private MatchTeamPO getMatchTeamPO(int matchId, String teamName,int flag) throws Exception
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
        	MatchPlayerPO[] players = null;
        	if (flag == 1)
             players = getMatchPlayerPOs(matchId,teamName);
        	MatchTeamPO teamPO  = new MatchTeamPO(players,scores,totalScore, teamName, time);
			return teamPO;
        }
        private MatchPlayerPO[] getMatchPlayerPOs(int matchId, String teamName) throws Exception
        {
        	String sql = "select player_name,courtTime,hitNo,handNo,threeHitNo,threeHandNo,penaltyHitNo,penaltyHandNo,"
        			+ "offenseRebs,defenceRebs,rebs,assist,steal,blockno,mistakeno,fouls"
        			+ " from match_player where match_id = ? and teama = ? order by first desc,score desc";
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
        int season1;
        private  String convertDate(Date date)
        {
        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        	String result = format.format(date);
        	this.season1 = Integer.parseInt(result.substring(0, 4));
        	String s = result.substring(5, 10);
        	if (s.charAt(0) == '0')
        	{
        		--season1;
        	}
        	return s;
        }

		public MatchesPO[] getPlayerOffMatches(int season){
			   MatchesPO[] result  = null;
			   try{
//			    conn = DriverManager.getConnection(url,"root","");
			    PreparedStatement statement = conn.prepareStatement(sql_seasonMatches);
			    int[] id_scope = getPlayerOffMatchId(season);
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
			    	matchpos.add(new MatchesPO(matchId,getMatchTeamPO(matchId,team1,0),getMatchTeamPO(matchId,team2,0),date));
			    }
			    result = new MatchesPO[matchpos.size()];
			    matchpos.toArray(result);
			   }catch (Exception e)
			   {
				   e.printStackTrace();
			   }
//			   finally 
//			   {
//				   try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			   }
			   
				return result;
		}

		public MatchesPO[] getPlayerOffPlayerMatches(int season, String name) {

			String sql  = "select match_id from match_player where match_id > ? and match_id < ? and player_name = ?";
		    int[] id_scope = getPlayerOffMatchId(season);
		    ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(85);
		    int matchId = -1;
		    try{
//		    conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, name);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	MatchesPO matchespo = getMatches(matchId,0);
		    	allMatches.add(matchespo);
		    }
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
//	        finally{
//	        	try {
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//	        }
		    MatchesPO[] result = new MatchesPO[allMatches.size()];
		    allMatches.toArray(result);
			return result;
		}

		public MatchesPO[] getPlayerOffTeamMatches(int season, String teamName) {
			String sql = "select match_id from match_team where match_id > ? and match_id < ? and teama = ?";
			int[] id_scope = getPlayerOffMatchId(season);
			int matchId = -1;
			MatchesPO[] matchpos = null;
			MatchesPO match = null;
			ArrayList<MatchesPO> matchList = new ArrayList<MatchesPO>(90);
			try
			{
//			conn = DriverManager.getConnection(url,"root","");
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setInt(1, id_scope[0]);
			statement.setInt(2, id_scope[1]);
			statement.setString(3, teamName);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
			 matchId = results.getInt(1);
		     match = getMatches(matchId,0);
		     matchList.add(match);
			}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			matchpos = new MatchesPO[matchList.size()];
			matchList.toArray(matchpos);
			return matchpos;
		}


		@Override
		public MatchesPO getMatchById(int matchId) {
			try {
				return getMatches(matchId,1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public MatchesPO[] getRegularPlayerMatchesn(int season, String name,
				int n) {
			String sql  = "select match_id from match_player where match_id > ? and match_id < ? and player_name = ? "
					+ " order by match_id desc limit "+n;
		    int[] id_scope = getMatchIdScope(season);
		    ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(85);
		    int matchId = -1;
		    try{
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, name);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	MatchesPO matchespo = getMatches(matchId,0);
		    	allMatches.add(matchespo);
		    }
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
//	        finally{
//	        	try {
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//	        }
		    MatchesPO[] result = new MatchesPO[allMatches.size()];
		    allMatches.toArray(result);
			return result;
		}

		@Override
		public MatchesPO[] getRegularTeamMatchesn(int season, String teamName,
				int n) {
			String sql = "select match_id from match_team where match_id > ? and match_id < ? and teama = ? "
					+ " order by match_id limit "+n;
			int[] id_scope = getMatchIdScope(season);
			int matchId = -1;
			MatchesPO[] matchpos = null;
			MatchesPO match = null;
			ArrayList<MatchesPO> matchList = new ArrayList<MatchesPO>(90);
			try
			{
//			conn = DriverManager.getConnection(url,"root","");
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setInt(1, id_scope[0]);
			statement.setInt(2, id_scope[1]);
			statement.setString(3, teamName);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
			 matchId = results.getInt(1);
		     match = getMatches(matchId,0);
		     matchList.add(match);
			}
			}catch (Exception e)
			{}
			matchpos = new MatchesPO[matchList.size()];
			matchList.toArray(matchpos);
			return  matchpos;
		}

		@Override
		public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name,
				int n) {
			String sql  = "select match_id from match_player where match_id > ? and match_id < ? and player_name = ? "
					+ " order by match_id desc limit "+n;
		    int[] id_scope = getPlayerOffMatchId(season);
		    ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(85);
		    int matchId = -1;
		    try{
//		    conn = DriverManager.getConnection(url,"root","");
		    PreparedStatement statement = conn.prepareStatement(sql);
		    statement.setInt(1, id_scope[0]);
		    statement.setInt(2, id_scope[1]);
		    statement.setString(3, name);
		    ResultSet results = statement.executeQuery();
		    while (results.next())
		    {
		    	matchId = results.getInt(1);
		    	MatchesPO matchespo = getMatches(matchId,0);
		    	allMatches.add(matchespo);
		    }
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
//	        finally{
//	        	try {
//					conn.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//	        }
		    MatchesPO[] result = new MatchesPO[allMatches.size()];
		    allMatches.toArray(result);
			return result;
		}

		@Override
		public MatchesPO[] getPlayerOffTeamMatchesn(int season,
				String teamName, int n) {
			String sql = "select match_id from match_team where match_id > ? and match_id < ? and teama = ? "
					+ " order by match_id desc limit "+n;
			int[] id_scope = getPlayerOffMatchId(season);
			int matchId = -1;
			MatchesPO[] matchpos = null;
			MatchesPO match = null;
			ArrayList<MatchesPO> matchList = new ArrayList<MatchesPO>(90);
			try
			{
//			conn = DriverManager.getConnection(url,"root","");
			PreparedStatement statement  = conn.prepareStatement(sql);
			statement.setInt(1, id_scope[0]);
			statement.setInt(2, id_scope[1]);
			statement.setString(3, teamName);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
			 matchId = results.getInt(1);
		     match = getMatches(matchId,0);
		     matchList.add(match);
			}
			}catch (Exception e)
			{
				e.printStackTrace();
			}
			matchpos = new MatchesPO[matchList.size()];
			matchList.toArray(matchpos);
			return matchpos;
		}

		public SimpleMatchLive[] getAllLiveMatches() {
			String sql = "select matchId,host_team,guest_team from livematch";
			SimpleMatchLive[] games = null;
			try
			{
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				ArrayList<SimpleMatchLive> list = new ArrayList<SimpleMatchLive>();
				int matchId = -1;
				String host_team = null;
				String guest_team = null;
				while(results.next())
				{
					matchId = results.getInt(1);
					host_team = results.getString(2);
					guest_team = results.getString(3);
					list.add(new  SimpleMatchLive(matchId,host_team,guest_team));
				}
				games = new SimpleMatchLive[list.size()];
				list.toArray(games);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return games;
		}

		@Override
		public CurrentMatch getLiveMatchesById(int matchId) {
		    String sql_match  = "select *  from livematch where matchId = ?";
		    CurrentMatch match = null;
		    try
		    {
		    	String host_team = null;
		    	String guest_team = null;
		    	String live_date = null;
		    	String live_time = null;
		    	String gym  = null;
		    	String audience = null;
		    	CurrentTeam team1 = null;
		    	CurrentTeam team2 = null;
		    	PreparedStatement statement  = conn.prepareStatement(sql_match);
		    	statement.setInt(1, matchId);
		    	ResultSet results = statement.executeQuery();
		    	if (results.next())
		    	{
		    		host_team = results.getString("host_team");
		    		guest_team = results.getString("guest_team");
		    		live_date = results.getString("live_date");
		    		live_time = results.getString("live_time");
		    		gym = results.getString("gym");
		    		audience = results.getString("audience");
		    	}
		    	team1 = getCurrentTeam(matchId,host_team);
		    	team2 = getCurrentTeam(matchId,guest_team);
		    	match = new CurrentMatch( String.valueOf(matchId), team1,  team2,
		    			   live_date, live_time, gym, audience);
		    	ArrayList<String> messages = getMessages(matchId);
		    	match.setMessages(messages);
		    	match.adjustTeamPoints();
		    }
		    catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
			return match;
		}
		
		private ArrayList<String> getMessages(int matchId)
		{
			String sql = "select time,team,event,ptop,type from match_messages where match_id = ? order by messId asc";
			ArrayList<String> list = new ArrayList<String>();
			try
			{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, matchId);
				ResultSet results = statement.executeQuery();
				while (results.next())
				{
					list.add(toOneLineMessages(results));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
		}
		private String toOneLineMessages(ResultSet results) throws SQLException
		{
			String time = results.getString("time");
			String team = results.getString("team");
			String event = results.getString("event");
			String ptop = results.getString("ptop");
			int  type = results.getInt("type");
		    StringBuilder sb = new StringBuilder();
		    if (type == 0)
		    {
		    	sb.append(time);
		    	sb.append(" ");
		    	sb.append(team);
		    	sb.append(" ");
		    	sb.append(event);
		    	sb.append(" ");
		    	sb.append(ptop);
		    }
		    else 
		    {
		    	sb.append(event);
		    }
		    return sb.toString();
		}
		
		public  CurrentTeam getCurrentTeam(int matchId, String teamName)
		{
		    String sql = "select * from live_team where matchId = ? and teamName = ?";
		    CurrentPlayer[] firsts = null;
		    CurrentPlayer[] benches = null;
		    CurrentTeam team = null;
		    try
		    {
		     	String[] primaryDatas = new String[12];
		     	String[] rates = new String[3];
		     	String totalScores = null;
		     	String disc = null;
		     	String win = null;
		     	String[] pts = new String[8];
		     	PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, matchId);
                statement.setString(2, teamName);
                ResultSet results = statement.executeQuery();
                if (results.next())
                {
                	for (int i = 0; i < primaryDatas.length; ++i)
                	{
                	 primaryDatas[i] = results.getString(3+i);	
                	}
                	for (int i = 0; i < rates.length; ++i)
                	{
                		rates[i] = results.getString(3+primaryDatas.length+i);
                	}
                	totalScores = results.getString("totalScores");
                	disc = results.getString("disc");
                	win = results.getString("win");
                	for (int i = 0; i < pts.length ; ++i)
                	{
                		pts[i] = results.getString(6+primaryDatas.length+rates.length + i);
                	}
                	CurrentPlayer[] players = getCurrentPlayers(matchId,teamName);
                	firsts = new CurrentPlayer[5];
                	benches = new CurrentPlayer[players.length-5];
                	for (int i = 0; i < 5; ++i)
                	{
                		firsts[i]  = players[i];
                	}
                    for (int i = 0 ; i < benches.length; ++i)
                    {
                    	benches[i] = players[5+i];
                    }
                    Image img = getLiveTeamImg(teamName);
                    team = new CurrentTeam( firsts,benches,primaryDatas,rates
                  		  ,totalScores,pts,teamName,img,disc,win);
                }
		    }
		    catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
			return team;
		}
		
		private CurrentPlayer[] getCurrentPlayers(int matchId, String teamName)
		{
			String sql = "select * from live_player where match_id = ? and teama = ? order by isFirst desc";
			CurrentPlayer[] players = null;
			try
			{
				ArrayList<CurrentPlayer> list  = new ArrayList<CurrentPlayer>();
				PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, matchId);
                statement.setString(2, teamName);
                ResultSet results = statement.executeQuery();
                String[] datas = null;
                while (results.next())
                {
                	datas = new String[16];
                	for (int i = 1; i < 16; ++i)
                	{
                		datas[i] = results.getString(4+i);
                	}
                	datas[0] = results.getString(1);
                   list.add(new CurrentPlayer(datas));
                }
                players = new CurrentPlayer[list.size()];
                list.toArray(players);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return players;
		}

		@Override
		public Image getLiveTeamImg(String teamName) {
			String sql = "select img from live_team_img where teamName = ?";
			Image img = null;
			try
			{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, teamName);
				Blob blob = null;
				ResultSet result = statement.executeQuery();
				if (result.next())
				{
					blob = result.getBlob("img");
					if (blob != null)
					{
						img = PlayerData.blobToImage(blob);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return img;
		}

		@Override
		public OldMatch[] getOldMatch(int season, int low, int high, SeasonType seasonType) {
			String sql = "select * from oldmatch where match_id > ? and match_id < ? order by match_id limit ?,?";
			int[] seasonScope = getMatchIdScope(season);
			seasonScope[0] -= 10000;
			seasonScope[1] -= 10000;
			OldMatch[] matches = null;
			ArrayList<OldMatch> list = new ArrayList<OldMatch>();
			if (seasonType == SeasonType.PLAYOFF)
			{
				seasonScope[0] += 1000000;
				seasonScope[1] += 1000000;
			}
			try
			{
				PreparedStatement statement  = conn.prepareStatement(sql);
				statement.setInt(1, seasonScope[0]);
				statement.setInt(2, seasonScope[1]);
				statement.setInt(3, low);
				statement.setInt(4, high);
				ResultSet results = statement.executeQuery();
				while (results.next())
				{
					list.add(toOldMatch(results));
     			}
				matches = new OldMatch[list.size()];
				list.toArray(matches);
				}catch(Exception e)
			{
				e.printStackTrace();
			}
			return matches;
		}
        
		private OldMatch toOldMatch(ResultSet result) throws Exception
		{
			String guest_team = result.getString("guestTeam");
			String host_team = result.getString("hostTeam");
			String date = result.getString("date");
			int matchId = result.getInt("match_id");
			OldMatch oldMatch = new OldMatch(matchId,host_team,guest_team,date);
			return oldMatch;
		}
		@Override
		public OldMatch getOldMatchInfo(int matchId) {
			String sql_match = "select guestTeam,hostTeam,date from oldmatch where match_id = ?";
			String sql_team = "select teamName,totalScores,p1,p2,p3,p4,p5,p6,p7,p8 from oldmatchteam where matchId = ?";
			String sql_img = "select infoimg from oldmatchimg where matchId = ?";
			PreparedStatement statement = null;
			ResultSet results = null;
			OldMatch oldMatch = null;
			try
			{
				statement = conn.prepareStatement(sql_match);
				statement.setInt(1, matchId);
				results = statement.executeQuery();
				String guestTeam = null;
				String hostTeam = null;
				String date = null;
				if (results.next())
				{
					guestTeam = results.getString(1);
					hostTeam = results.getString(2);
					date = results.getString(3);
				}
				statement = conn.prepareStatement(sql_team);
				statement.setInt(1, matchId);
				results = statement.executeQuery();
				String team1 = null;
				String team2 = null;
				String totalScores1 = null;
				String totalScores2 = null;
				String[] pts1 = new String[8];
				String[] pts2 = new String[8];
				if (results.next())
				{
					team1 = results.getString(1);
					totalScores1 = results.getString(2);
					for (int i = 0; i < 8; ++i)
					{
						pts1[i] = results.getString(i+3);
					}
				}
				if (results.next())
				{
					team2 = results.getString(1);
					totalScores2 = results.getString(2);
					for (int i = 0; i < 8; ++i)
					{
						pts2[i] = results.getString(i+3);
					}
				}
				int len = 0;
				for (len= 0; len < pts2.length; ++len)
				{
					if (pts2[len] == null)
					{
						break;
					}
				}
				String[]  ptsr1 = new String[len+1];
				String[] ptsr2 = new String[len+1];
				for (int i = 0; i < ptsr1.length-1; ++i)
				{
					ptsr1[i] = pts1[i];
					ptsr2[i] = pts2[i];
				}
				ptsr1[ptsr1.length-1] = totalScores1;
				ptsr2[ptsr2.length-1] = totalScores2;
				Image img = getImageInfo(matchId);
				if (team1.equals(hostTeam))
				{
					oldMatch = new OldMatch( matchId,  hostTeam,  guestTeam, ptsr1,  ptsr2 , img, date);
				}
				else 
				{
					oldMatch = new OldMatch( matchId,  hostTeam,  guestTeam, ptsr2,  ptsr1 , img, date);

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return oldMatch;
		}
		private Image getImageInfo(int matchId)
		{
			String sql = "select infoimg from oldmatchimg where matchId = ?";
			Image img = null;
			try
			{
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, matchId);
				ResultSet results = statement.executeQuery();
				Blob blob = null;
				if (results.next())
				{
					blob = results.getBlob(1);
				}
				if (blob != null)
				{
					img = PlayerData.blobToImage(blob);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return img;
		}

}
