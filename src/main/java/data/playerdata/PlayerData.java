package data.playerdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;

public class PlayerData implements PlayerDataService{
    private Connection conn;
	public PlayerData(Connection conn)
	{
		this.conn = conn;
	}
	public PlayerPO[] getAllActivePlayerData() {
		String sql = "select player_id,player_name,num,position,heightfeet,heightinch,weight,birth,age,exp,school from mplayer";
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>(3500);
		PlayerPO[] players = null;
		try
		{
		  PreparedStatement statement = conn.prepareStatement(sql);
		  ResultSet results = statement.executeQuery();
		  while (results.next())
		  {
			list.add(toPlayerPO(results));
		  }
		  players = new PlayerPO[list.size()];
		  list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}

	private PlayerPO toPlayerPO(ResultSet result) throws Exception
	{
		String name = result.getString("player_name");
		int number = result.getInt("num");
		String position = result.getString("position");
		int heightfeet = result.getInt("heightfeet");
		int heightinch = result.getInt("heightinch");
		int weight = result.getInt("weight");
		String birth = result.getString("birth");
		int age = result.getInt("age");
		int exp = result.getInt("exp");
		String school = result.getString("school");
		String[] teams = getTeam(name);
		PlayerPO player = new PlayerPO(   name,  number,
				 position,  heightfeet,  heightinch,  weight,
				 birth,  age,  exp,  school, teams[0],teams[1],getImage(name));
		return player;
	}
	
	
	 public static  Image blobToImage(Blob blob)
	 {
		 if (blob == null)
			 return null;
		 byte[] bytes = null;
		 try {
			bytes = blob.getBytes(1,(int)blob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return Toolkit.getDefaultToolkit().createImage(bytes,0,bytes.length);
	 }
	@Override
	public HPlayerPO findPlayer(String playerName) {
		String sql = "select * from hplayerinfo  where player_name = ?";
		ArrayList<HPlayerPO> list = new ArrayList<HPlayerPO>(300);
		HPlayerPO player = null;
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, playerName);
			ResultSet results = statement.executeQuery();
		   String name = null;
		   String totalName = null;
		   String position = null;
		   String height = null;
		   String weight = null;
		   String birthday = null;
		   String birthCity = null;
		   String high_school = null;
		   String university = null;
		   String num = null;
		   if(results.next())
		   {
		     name = results.getString("player_name");
		     totalName = results.getString("total_name");
		     position = results.getString("position");
		     height = results.getString("height");
		     weight = results.getString("weight");
		     birthday = results.getString("birthday");
		     birthCity = results.getString("birthcity");
		     high_school = results.getString("high_school");
		     String[] teams  = getTeam(name);
		     player = new HPlayerPO( name,  totalName,  position,  height,
		    			 weight,  birthday,  birthCity,  high_school,
		    			 university,  num, teams[0],teams[1],getImage(name));
		     list.add(player);
		   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return player;
	}
	@Override
	public PlayerHighPO getPlayerHigh(int season, String playerName, SeasonType type) {
		String sql = null;
		PlayerHighPO player = null;
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_high where season = ? and playerName = ?";
			break;
		case PLAYOFF:
			sql = "select * from player_season_high_playeroff where season = ? and playerName = ?";
			break;
		}

		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, season);
			statement.setString(2, playerName);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				player = toPlayerHigh(result);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return player;
	}
	private PlayerHighPO toPlayerHigh(ResultSet result) throws Exception
	{
		String playerName = result.getString("playerName");
		String teamName = result.getString("teamName");
		double efficiency = result.getDouble("efficiency");
		double gmScEfficiency = result.getDouble("GmScEfficiency");
		double trueHitRate = result.getDouble("trueHitRate");
		double hitEfficiency = result.getDouble("hitEfficiency");
		double rebEfficiency = result.getDouble("rebEfficiency");
		double offenseRebsEfficiency = result.getDouble("offenseRebsEfficiency");
		double defenceRebsEfficiency = result.getDouble("defenceRebsEfficiency");
		double assistEfficiency = result.getDouble("assistEfficiency");
		double stealsEfficiency = result.getDouble("stealsEfficiency");
		double blockEfficiency = result.getDouble("blockEfficiency");
		double mistakeEfficiency = result.getDouble("mistakeEfficiency");
		double useEfficiency = result.getDouble("useEfficiency");
		int season = result.getInt("season");
		PlayerHighPO player = new PlayerHighPO( playerName,  teamName,  efficiency,
				 gmScEfficiency,  trueHitRate,  hitEfficiency,
				 rebEfficiency,  offenseRebsEfficiency,
				 defenceRebsEfficiency,  assistEfficiency,
				 stealsEfficiency,  blockEfficiency,
				 mistakeEfficiency,  useEfficiency, season);
		return player;
	}
	@Override
	public PlayerHighPO[] sortPlayerHighn(int season, String sort, int n,
			SeasonType type) {
		String sql = null;
		PlayerHighPO[] players = null;
		ArrayList<PlayerHighPO> list = new ArrayList<PlayerHighPO>(n);
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_high where season = ? order by "+sort +" limit "+String.valueOf(n);
			break;
		case PLAYOFF:
			sql = "select * from player_season_high_playeroff where season = ?  order by "+sort +" limit "+String.valueOf(n);
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, season);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				list.add(toPlayerHigh(results));
			}
		    players = new PlayerHighPO[list.size()];
		    list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public PlayerNormalPO getPlayerNormalTotal(int season, String playerName,
			SeasonType type) {
		String sql = null;
		PlayerNormalPO player = null;;
		switch(type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and season = ? and player_name = ? ";
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playeroff where ave = ? and season = ? and player_name = ?";
			break;
		}
		try
		{
		 	PreparedStatement statement = conn.prepareStatement(sql);
		 	statement.setInt(1, 0);
		 	statement.setInt(2, season);
		 	statement.setString(3, playerName);
		 	ResultSet result = statement.executeQuery();
		 	if(result.next())
		 	{
		 		player  = toPlayerNormal(result);
		 	}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return player;
	}
	private PlayerNormalPO toPlayerNormal(ResultSet result) throws Exception
	{
		String name = result.getString(3);
		String team = result.getString(4);
		int matchNo = result.getInt(5);
		double firstServiceNo = result.getDouble(6);
		double rebs = result.getDouble(7);
		double assistNo = result.getDouble(8);
		double time = result.getDouble(9);
		double offendRebsNo = result.getDouble(10);
		double defenceRebsNo = result.getDouble(11);
		double stealsNo = result.getDouble(12);
		double blockNo = result.getDouble(13);
		double mistakesNo = result.getDouble(14); 
		double foulsNo = result.getDouble(15);
		double points = result.getDouble(16);
		double minute = result.getDouble(17);
		double hitNo = result.getDouble(18);
		double handNo = result.getDouble(19);
		double hitRate = result.getDouble(20);
		double penaltyHandNo = result.getDouble(21);
		double penaltyHitNo = result.getDouble(22);
		double penaltyHitRate = result.getDouble(23);
		double threeHitNo = result.getDouble(24);
		double threeHandNo = result.getDouble(25);
		double threeHitRate = result.getDouble(26);
		double twoPair = result.getDouble(27);
		double points_uprate = result.getDouble(28);
		double rebs_uprate = result.getDouble(29);
		double help_uprate = result.getDouble(30);
		double scoring_rebound_assist = result.getDouble(31);
		int season = result.getInt("season");
		PlayerNormalPO player = new PlayerNormalPO( name,  team,  matchNo,
				 firstServiceNo,  rebs,  assistNo,  time,
				 offendRebsNo,  defenceRebsNo,  stealsNo,
				 blockNo,  mistakesNo,  foulsNo,  points,
				 minute,  hitNo,  handNo,  hitRate,
				 penaltyHandNo,  penaltyHitNo,  penaltyHitRate,
				 threeHitNo,  threeHandNo,  threeHitRate,
				 twoPair,  points_uprate,  rebs_uprate,
				 help_uprate,  scoring_rebound_assist,season);
		return player;
	}
	@Override
	public PlayerNormalPO[] sortPlayerNormalTotal(int season, String sort,
			int n, SeasonType type) {
		PlayerNormalPO[] players = null;
		ArrayList<PlayerNormalPO> list = new ArrayList<PlayerNormalPO>(n);
		String sql = null;
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and season = ? order by  "+sort+" limit "+String.valueOf(n); 
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playoff where ave = ? and season = ? order by  "+sort+" limit "+String.valueOf(n);
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setInt(2, season);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				list.add(toPlayerNormal(results));
			}
			players = new PlayerNormalPO[list.size()];
			list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public PlayerNormalPO getPlayerNormalAve(int season, String playerName,
			SeasonType type) {
		String sql = null;
		PlayerNormalPO player = null;;
		switch(type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and season = ? and player_name = ? ";
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playeroff where ave = ? and season = ? and player_name = ?";
			break;
		}
		try
		{
		 	PreparedStatement statement = conn.prepareStatement(sql);
		 	statement.setInt(1, 1);
		 	statement.setInt(2, season);
		 	statement.setString(3, playerName);
		 	ResultSet result = statement.executeQuery();
		 	if(result.next())
		 	{
		 		player  = toPlayerNormal(result);
		 	}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return player;
	}
	@Override
	public PlayerNormalPO[] sortPlayerNormalAven(int season, String sort, int n,
			SeasonType type) {
		PlayerNormalPO[] players = null;
		ArrayList<PlayerNormalPO> list = new ArrayList<PlayerNormalPO>(n);
		String sql = null;
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and season = ? order by  "+sort+" limit "+String.valueOf(n); 
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playoff where ave = ? and season = ? order by  "+sort+" limit "+String.valueOf(n);
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, season);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				list.add(toPlayerNormal(results));
			}
			players = new PlayerNormalPO[list.size()];
			list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName,
			SeasonType type) {
		String sql = null;
		PlayerNormalPO[] players = null;
		ArrayList<PlayerNormalPO> list = new ArrayList<PlayerNormalPO>();
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and player_name  = ?";
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playoff where ave = ? and player_name = ?";
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setString(2, playerName);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				list.add(toPlayerNormal(results));
			}
			players = new PlayerNormalPO[list.size()];
			list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName,
			SeasonType type) {
		String sql = null;
		PlayerNormalPO[] players = null;
		ArrayList<PlayerNormalPO> list = new ArrayList<PlayerNormalPO>();
		switch (type)
		{
		case REGULAR:
			sql = "select * from player_season_normal where ave = ? and player_name  = ?";
			break;
		case PLAYOFF:
			sql = "select * from player_season_normal_playoff where ave = ? and player_name = ?";
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setString(2, playerName);
			ResultSet results = statement.executeQuery();
			while(results.next())
			{
				list.add(toPlayerNormal(results));
			}
			players = new PlayerNormalPO[list.size()];
			list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
	@Override
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type) {
		String sql = null;
		PlayerHighPO[] players = null;
		ArrayList<PlayerHighPO> list = new ArrayList<PlayerHighPO>();
		switch(type)
		{
		case REGULAR:
			sql = "select * from player_season_high where playerName = '"+playerName+"'";
			break;
		case PLAYOFF:
			sql = "select * from player_season_high_playoff where playerName = '"+playerName+"'";
			break;
		}
		try
		{
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				list.add(toPlayerHigh(results));
			}
			players = new PlayerHighPO[list.size()];
			list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}
   public double getSeasonAveData(int season,String data)
   {
	   String sql  = "select sum(?)/sum(matchNo) where season = ? group by season";
	   double result = -1;
	   try
	   {
		   PreparedStatement statement = conn.prepareStatement(sql);
		   statement.setString(1, data);
		   statement.setInt(2, season);
		   ResultSet results = statement.executeQuery();
		   if (results.next())
		   {
			   result = results.getDouble(1);
		   }
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return result;
   }
@Override
public String[] fuzzilySearch(String info) {
	String sql = "select player_name from playerinfo where player_name like '"+info+"%'";
	ArrayList<String> list = new ArrayList<String>();
	String[] names = null;
	try
	{
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			list.add(results.getString(1));
		}
		names = new String[list.size()];
		list.toArray(names);
	}
	catch (Exception  e)
	{
		e.printStackTrace();
	}
	return names;
}

@Override
public HPlayerPO[] getHPlayerByIni(String ini) {
	String sql = "select * from hplayerinfo  where player_name like '"+ini+"%'";
	ArrayList<HPlayerPO> list = new ArrayList<HPlayerPO>(300);
	HPlayerPO[] players = null;
	HPlayerPO player = null;
	try
	{
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
	   String name = null;
	   String totalName = null;
	   String position = null;
	   String height = null;
	   String weight = null;
	   String birthday = null;
	   String birthCity = null;
	   String high_school = null;
	   String university = null;
	   String num = null;
	   while (results.next())
	   {
	     name = results.getString("player_name");
	     totalName = results.getString("total_name");
	     position = results.getString("position");
	     height = results.getString("height");
	     weight = results.getString("weight");
	     birthday = results.getString("birthday");
	     birthCity = results.getString("birthcity");
	     high_school = results.getString("high_school");
	     String[] teams  = getTeam(name);
	     player = new HPlayerPO( name,  totalName,  position,  height,
	    			 weight,  birthday,  birthCity,  high_school,
	    			 university,  num, teams[0],teams[1],getImage(name));
	     list.add(player);
	   }
	   players = new HPlayerPO[list.size()];
	   list.toArray(players);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return players;
}

  private String[] getTeam(String playerName)
  {
	  String[] teams = new String[2];
//	  String sql = "select a.teama from match_player a where a.player_name = ? and a.match_id = (select max(m.match_id) from "
//	  		+ " match_player m where m.player_name = ? group by m.player_name)";
//	  String s1 = "select match_area from team where name_abr = ?";
	  String sql = "select teama,match_area from player_team where player_name = ?";
	  try
	  {
		  PreparedStatement statement = conn.prepareStatement(sql);
		  statement.setString(1, playerName);
		  ResultSet result = statement.executeQuery();
		  if (result.next())
		  {
			  teams[0] = result.getString(1);
			  teams[1] = result.getString(2);
		  }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return teams;
  }
//  
//  public void deal()
//  {
//	  String sql = "insert into player_team(player_name, teama,match_area) values(?,?,?)";
//	  String s1 = "select player_name from hplayerinfo";
//	  try
//	  {
// 		  PreparedStatement statement = conn.prepareStatement(s1);
//          ArrayList<String> list = new ArrayList<String>();
//          ResultSet results = statement.executeQuery();
//          while(results.next())
//          {
//        	  list.add(results.getString(1));
//          }
//          Iterator<String> itr = list.iterator();
//          int i = 0;
//          while(itr.hasNext())
//          {
//        	  String name = itr.next();
//        	  System.out.println((++i)+ " "+name);
//        	  String[] strs = getTeam(name);
//        	  statement = conn.prepareStatement(sql);
//        	  statement.setString(1, name);
//        	  statement.setString(2, strs[0]);
//        	  statement.setString(3, strs[1]);
//        	  statement.execute();
//          }
//	  }
//	  catch(Exception e)
//	  {
//		  e.printStackTrace();
//	  }
//  }
//  
  public Image getImage(String playerName)
  {
	  
	  String sql = "select photo_portrait from mplayer where player_name = ?";
	  Image image = null;
	  try
	  {
		  PreparedStatement statement = conn.prepareStatement(sql);
		  statement.setString(1, playerName);
		  ResultSet result = statement.executeQuery();
		  if (result.next())
		  {
			  image = blobToImage(result.getBlob(1));
		  }
	  }
	  catch (Exception e)
	  {
		  e.printStackTrace();
	  }
	  return image;
  }
  
@Override
public PlayerNormalPO[] getSeasonPlayerNormalAve(int season, SeasonType type) {
	return this.sortPlayerNormalAven(season, "player_name", 1000, type);
}
@Override
public PlayerNormalPO[] getSeasonPlayerNormalTotal(int season, SeasonType type) {
	return this.sortPlayerNormalTotal(season, "player_name", 1000, type);
}
@Override
public PlayerHighPO[] getSeasonPlayerHigh(int season, SeasonType type) {
	return this.sortPlayerHighn(season, "player_name", 1000, type);
}
@Override
public PlayerPO[] getPlayersOfTeam(String team) {
	String sql = "select a.player_id,a.player_name,a.num,a.position,a.heightfeet,a.heightinch,a.weight,a.birth,a.age,a.exp,a.school from mplayer a"
			+ " where exists(select m.teama from player_team m where m.player_name = a.player_name and  m.teama = ?)";
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>(3500);
		PlayerPO[] players = null;
		
		try
		{
		  PreparedStatement statement = conn.prepareStatement(sql);
		  statement.setString(1, team);
		  ResultSet results = statement.executeQuery();
		  while (results.next())
		  {
			list.add(toPlayerPO(results));
		  }
		  players = new PlayerPO[list.size()];
		  list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
}
@Override
public PlayerPO[] screenPlayer(String sort, String match_area, String position,
		int n) {
	String sql = "select * from mplayer m where ";
	String s1 = " m.position like "+"'%"+position+"%' and ";
	String s2 = "exists(select p.match_area from player_team  p where p.match_area = '"+match_area+"' and p.player_name = m.player_name)";
	if (sort == null)
	{
		sort = " player_name desc";
	}
	String s3 = " order by"+" "+sort +" limit "+n;
	if (position!=null)
	{
		sql+=s1;
	}
	if (match_area != null)
	{
		sql += s2;
	}
	sql += s3;
	ArrayList<PlayerPO> list = new ArrayList<PlayerPO>(3500);
	PlayerPO[] players = null;
	try
	{
	  PreparedStatement statement = conn.prepareStatement(sql);
	  ResultSet results = statement.executeQuery();
	  while (results.next())
	  {
		list.add(toPlayerPO(results));
	  }
	  players = new PlayerPO[list.size()];
	  list.toArray(players);
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return players;
}
}
