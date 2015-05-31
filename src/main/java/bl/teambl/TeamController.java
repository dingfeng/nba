package bl.teambl;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import dataservice.teamdataservice.TeamDataService;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import vo.HotPlayerTeam;

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
	public HotPlayerTeam[] getHotTeams(int season, String sortby, SeasonType type) {
		TeamNormalPO[] teams = teamservice.sortTeamNormalTotaln(season, sortby, 5, type);
		HotPlayerTeam[] hotTeams = new HotPlayerTeam[5];
		double[] data = new double[5];
		
		if(sortby.equals("score")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getPoints();
			}
		} else if(sortby.equals("rebs")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getRebs();
			}
		} else if(sortby.equals("assist")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getAssistNo();
			}
		} else if(sortby.equals("blockno")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getBlockNo();
			}
		} else if(sortby.equals("steal")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getStealsNo();
			}
		} else if(sortby.equals("threeHitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getThreeHitRate();
			}
		} else if(sortby.equals("hitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getHitRate();
			}
		} else if(sortby.equals("penaltyHitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = teams[i].getPenaltyHitRate();
			}
		}
		
		String name;
		TeamPO thisTeam;
		for(int i = 0; i != 5; i ++){
			name = teams[i].getName();
			thisTeam = teamservice.findTeam(name);
			hotTeams[i] = new HotPlayerTeam(thisTeam.getImage(), name, data[i]);
		}
		return hotTeams;
	}


	@Override
	public TeamPO getTeamData(String team) {
		return teamservice.findTeam(team);
	}

	@Override
	public HPlayerPO getPlayerBase(String playername) {
		return playerservice.findPlayer(playername);
	}

	@Override
	public String[] getTeamNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getPlayers(int season, String team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO[] getAllTeamTotal(int season, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO[] getAllTeamAve(int season, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamHighPO[] getAllTeamHigh(int season, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO getTotalTeam(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamHighPO[] getTeamSeasonHigh(String teama, SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
