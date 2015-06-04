package bl.teambl;

import java.util.ArrayList;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import dataservice.teamdataservice.TeamDataService;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
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
		String sortBy = sortby + "desc";
		TeamNormalPO[] teams = teamservice.sortTeamNormalTotaln(season, sortBy, 5, type);
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
			names[i] = allTeams[i].getNameAbridge();
		}
		return names;
	}

	@Override
	//得到该队有史以来所有球员的名字
	public String[] getPlayers(String team) {
		PlayerPO[] players = playerservice.getPlayersOfTeam(team);
		int length = players.length;
		String[] playernames = new String[players.length];
		for(int i = 0; i != length; i ++){
			playernames[i] = players[i].getName();
		}
		return playernames;
	}

	@Override
	//得到赛季所有球队的所有数据 低阶
	public TeamNormalPO[] getAllTeamTotal(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamNormalPO> result = new ArrayList<TeamNormalPO>(length);
		TeamNormalPO thisTeam;
		for(int i = 0; i != length; i ++){
			thisTeam = this.getTotalTeam(season, teamNames[i], type);
			if(thisTeam != null){
				result.add(thisTeam);
			}
		}
		TeamNormalPO[] resultR = (TeamNormalPO[]) result.toArray(new TeamNormalPO[result.size()]);
		return resultR;
	}

	@Override
	//得到赛季所有球队的场均数据 低阶
	public TeamNormalPO[] getAllTeamAve(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamNormalPO> result = new ArrayList<TeamNormalPO>(length);
		TeamNormalPO thisTeam;
		for(int i = 0; i != length; i ++){
			thisTeam = this.getAveTeam(season, teamNames[i], type);
			if(thisTeam != null){
				result.add(thisTeam);
			}
		}
		return (TeamNormalPO[])result.toArray();
	}

	@Override
	public TeamHighPO[] getAllTeamHigh(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamHighPO> result = new ArrayList<TeamHighPO>(length);
		TeamHighPO thisTeam;
		for(int i = 0; i != length; i ++){
			thisTeam = this.getHighTeam(season, teamNames[i], type);
			if(thisTeam != null){
				result.add(this.getHighTeam(season, teamNames[i], type));
			}
		}
		return (TeamHighPO[])result.toArray();
	}

	@Override
	//根据球队简称查找赛季数据 低阶
	public TeamNormalPO getTotalTeam(int season, String teamname,
			SeasonType type) {
		TeamNormalPO result = teamservice.getTeamNormalTotal(season, teamname, type);
		return result;
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
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerNormalPO> players = new ArrayList<PlayerNormalPO>(playernames.length);
		PlayerNormalPO thisPlayer;
		for(String p : playernames){
			thisPlayer = playerservice.getPlayerNormalAve(season, p, type);
			if(thisPlayer != null){
				players.add(thisPlayer);
			}
		}
		return (PlayerNormalPO[])players.toArray();
	}

	@Override
	//根据球队简称查找其下的球员的赛季数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerNormalPO> players = new ArrayList<PlayerNormalPO>(playernames.length);
		PlayerNormalPO thisPlayer;
		for(String p : playernames){
			thisPlayer = playerservice.getPlayerNormalTotal(season, p, type);
			if(thisPlayer != null){
				players.add(thisPlayer);
			}
		}
		return (PlayerNormalPO[])players.toArray();
	}

	@Override
	//根据球队简称查找其下球员的高阶赛季数据
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerHighPO> players = new ArrayList<PlayerHighPO>(playernames.length);
		PlayerHighPO thisPlayer;
		for(String p : playernames){
			thisPlayer = playerservice.getPlayerHigh(season, p, type);
			if(thisPlayer != null){
				players.add(thisPlayer);
			}
		}
		return (PlayerHighPO[])players.toArray();
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
