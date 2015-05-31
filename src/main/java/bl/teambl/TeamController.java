package bl.teambl;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;
import po.PlayerPO;
import po.TeamNormalPO;
import po.TeamPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.MatchController;
import bl.matchbl.TeamQueue;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import vo.PlayerMatchVO;
import vo.TeamMatchVO;

public class TeamController implements Teamblservice{
	TeamDataService teamservice;
	PlayerDataService playerservice;
	Matchblservice matchservice;
	
	public TeamController(){
		NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			playerservice = dataFactory.getPlayerData();
			teamservice = dataFactory.getTeamData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchservice = new MatchController();
	}
	
	@Override
	public TeamNormalPO[] getHotTeams(int season, String sortby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayers(int season, String team) {
		Match match = matchservice.getMatch(season);
		TeamQueue teamQ = match.getTeamData(team);
		return teamQ.getAllPlayers();
	}

	@Override
	public TeamMatchVO getTotalTeam(int season, String teamname) {
		Match match = matchservice.getMatch(season);
		TeamQueue teamQ = match.getTeamData(teamname);
		return teamQ.getTeamvoTotal();
	}

	@Override
	public TeamMatchVO getAveTeam(int season, String teamname) {
		Match match = matchservice.getMatch(season);
		TeamQueue teamQ = match.getTeamData(teamname);
		return teamQ.getTeamvoAverage();
	}

	@Override
	public TeamPO getTeamData(String team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] getAllPlayerMatchAve(int season, String teamname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] getAllPlayerMatchTotal(int season, String teamname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerPO getPlayerBase(String playername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getTeamNames() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
