package DataFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;
import DataFactoryService.NBADataFactory;

public class DataFactory implements NBADataFactory{
	private MatchDataService matchData;
	private PlayerDataService playerData;
	private TeamDataService teamData;
	private static NBADataFactory factory ;
	private String ip  = "rmi://127.0.0.1/"; 
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
			try {
				matchData = (MatchDataService) Naming.lookup(ip+"MatchData");
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				e.printStackTrace();
			}
		}
		return matchData;
	}

	@Override
	public PlayerDataService getPlayerData() {
		if (playerData == null)
		{
			try {
				playerData = (PlayerDataService) Naming.lookup(ip+"PlayerData");
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				e.printStackTrace();
			}
		}
		return playerData;
	}

	@Override
	public TeamDataService getTeamData() {
		if (teamData == null)
		{
			try {
				teamData = (TeamDataService) Naming.lookup(ip+"TeamData");
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				e.printStackTrace();
			}
		}
		return teamData;
	}
}
