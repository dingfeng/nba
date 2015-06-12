package DBtool;

public class DataFactoryImp implements NBADataFactory
{
	private static  String dataSource = "K:/NBAData";
	private static MatchDataService match_data;
	private static PlayerDataService player_data;
	private static TeamDataService team_data;
	private static  NBADataFactory factory;
	public static void  setDataSource(String dataSource0)
	{
		dataSource = dataSource0;
	}
	private DataFactoryImp(){}
	public static NBADataFactory  instance()
	{
		if (factory == null)
		{
			factory = new DataFactoryImp();
		}
		return factory;
	}
	public  MatchDataService getMatchData(){
		if (match_data == null)
		{
			match_data = new MatchData(dataSource + "/matches");
		}
		return match_data;
	}

	public PlayerDataService getPlayerData() {
		   if (player_data == null)
			 player_data = new PlayerData(dataSource + "/players");
		return player_data;
	}

	public TeamDataService getTeamData() {
		if (team_data == null)
		{
			team_data = new TeamData(dataSource + "/teams");
		}
		return team_data;
	}

}
