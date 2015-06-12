package DBtool;



public interface  NBADataFactory
{
	public MatchDataService getMatchData();
	public PlayerDataService getPlayerData();
	public TeamDataService getTeamData();

}
