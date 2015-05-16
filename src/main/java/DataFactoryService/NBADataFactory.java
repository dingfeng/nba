package DataFactoryService;

import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;


public interface  NBADataFactory
{
	public PlayerDataService getPlayerData();
	public TeamDataService getTeamData();
	MatchDataService getMatchData();
}
