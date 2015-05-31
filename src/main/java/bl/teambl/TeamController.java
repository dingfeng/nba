package bl.teambl;

import java.util.ArrayList;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import dataservice.teamdataservice.TeamDataService;
import po.PlayerPO;
import po.TeamNormalPO;
import po.TeamPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.MatchController;
import bl.matchbl.PlayerQueue;
import bl.matchbl.TeamQueue;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import vo.HotPlayerTeam;
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
		return teamservice.findTeam(team);
	}

	@Override
	public PlayerMatchVO[] getAllPlayerMatchAve(int season, String teamname) {
		Match matches = matchservice.getMatch(season);
		TeamQueue teamQ = matches.getTeamData(teamname);
		String[] playernames = teamQ.getAllPlayers();
		ArrayList<PlayerMatchVO> result = new ArrayList<PlayerMatchVO>(25);
		
		for(String p : playernames){
			PlayerQueue playerQ = matches.getPlayerData(p);
			if(playerQ != null){
				result.add(playerQ.getAvePlayer());
			}
		}
		return (PlayerMatchVO[])result.toArray();
	}

	@Override
	public PlayerMatchVO[] getAllPlayerMatchTotal(int season, String teamname) {
		Match matches = matchservice.getMatch(season);
		TeamQueue teamQ = matches.getTeamData(teamname);
		String[] playernames = teamQ.getAllPlayers();
		ArrayList<PlayerMatchVO> result = new ArrayList<PlayerMatchVO>(25);
		
		for(String p : playernames){
			PlayerQueue playerQ = matches.getPlayerData(p);
			if(playerQ != null){
				result.add(playerQ.getTotalPlayer());
			}
		}
		return (PlayerMatchVO[])result.toArray();
	}

	@Override
	public PlayerPO getPlayerBase(String playername) {
		return playerservice.findPlayer(playername);
	}

	@Override
	public String[] getTeamNames() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
