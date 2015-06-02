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
		TeamPO[] allTeams = teamservice.getAllTeamData();
		String[] names = new String[allTeams.length];
		for(int i = 0; i != allTeams.length; i ++){
			names[i] = allTeams[i].getName();
		}
		return names;
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
	//根据球队简称查找赛季数据 低阶
	public TeamNormalPO getTotalTeam(int season, String teamname,
			SeasonType type) {
		return teamservice.getTeamNormalTotal(season, teamname, type);
	}

	@Override
	//根据球队简称查找场均数据 低阶
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type) {
		return teamservice.getTeamNormalAve(season, teamname, type);
	}

	@Override
	//根据球队简称查找高阶数据
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type) {
		return teamservice.getTeamHigh(season, teamname, type);
	}

	@Override
	//根据球队简称查找其下的球员的场均数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname,
			SeasonType type) {
		return null;
	}

	@Override
	//根据球队简称查找其下的球员的赛季数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//根据球队简称查找其下球员的高阶赛季数据
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname,
			SeasonType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//获得某个球队的所有赛季的总数据
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama, SeasonType type) {
		return teamservice.getTeamSeasonNormalTotal(teama, type);
	}

	@Override
	//获得某个球员所有赛季的场均数据
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama, SeasonType type) {
		return teamservice.getTeamSeasonNormalAve(teama, type);
	}

	@Override
	//获得某个球员所有赛季的高阶数据
	public TeamHighPO[] getTeamSeasonHigh(String teama, SeasonType type) {
		return teamservice.getTeamSeasonHigh(teama, type);
	}
    
}
