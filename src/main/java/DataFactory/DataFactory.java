package DataFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.teamdata.TeamData;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;
import DataFactoryService.NBADataFactory;

public class DataFactory implements NBADataFactory{
    private static MatchDataService matchData;
    private static TeamDataService teamData;
    private static PlayerDataService playerData;
//	private String usr = "root";
//	private String password = "root";
	private String url = "jdbc:mysql://dingfeng:3306/nba?useUnicode=true&characterEncoding=utf8";
//	String url = "jdbc:mysql://dingfeng:3306/nba";
//	private String url = "jdbc:mysql://127.0.0.1:3306/nba";
//	String url = "jdbc:mysql://dingfeng:3306/nba";
	private String driver = "com.mysql.jdbc.Driver";
	private Connection conn;
	private static NBADataFactory factory;
	private  DataFactory() throws Exception
	{
   	 Class.forName(driver);
//   	 conn = DriverManager.getConnection(url,"root","");
   	conn = DriverManager.getConnection(url,"root","");
	} 
	public static NBADataFactory instance() throws Exception
	{
		if (factory == null)
		{
			factory = new DataFactory();
		}
		return factory;
	}
	public MatchDataService getMatchData() {
		if (matchData == null)
		{
			matchData = new MatchData(conn);
		}
		return matchData;
	}

	@Override
	public PlayerDataService getPlayerData() {
		if (playerData == null)
		{
			playerData = new PlayerData(conn);
		}
		return playerData;
	}

	@Override
	public TeamDataService getTeamData() {
		if (teamData == null)
		{
			teamData = new TeamData(conn);
		}
		return teamData;
	}
}
