package bl.matchbl;

import gnu.trove.map.TIntObjectMap;

import java.util.ArrayList;
import java.util.Date;

import dataservice.matchdataservice.MatchDataService;
import po.MatchTeamPO;
import po.MatchesPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice {
	MatchContainer matchContainer;
	MatchDataService matchservice;
	
	public MatchController(){
		matchContainer = MatchContainer.instance();
		NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			matchservice = dataFactory.getMatchData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新数据
	public synchronized void update() {
		
	}

	// 数据是否改变
	public synchronized boolean changed() {
		return false;
	}

	// 获得今日的比赛数据
	public synchronized MatchesPO[] getTodayMatches() {
		return null;
	}

	// 获得当前赛季所有的比赛数据
	public synchronized MatchesPO[] getAllMatches(int season) {
		Match match = matchContainer.getSeasonMatch(season);
		return match.getMatches();
	}

	// 获得某球员最近的几场比赛数据
	public synchronized MatchesPO[] getRecentPlayerMatches(String playerName,
			int num) {
		//获得最新赛季match对象
		Match matches = matchContainer.getSeasonMatch(2014);
		TIntObjectMap<PlayerQueue> player_map = matches.getPlayer_map();
		PlayerQueue playerQ = player_map.get(playerName.hashCode());
		if(playerQ != null){
			return playerQ.getRecentPlayerMatches(num);
		}
		//如果最新赛季没有比赛则返回null
		return null;
	}

	// 获得某球队最近的几场比赛数据
	public synchronized MatchesPO[] getRecentTeamMatches(String teamName,
			int num) {
		//获得最新赛季match对象
		Match matches = matchContainer.getSeasonMatch(2014);
		TIntObjectMap<TeamQueue> team_map = matches.getTeam_map();
		TeamQueue teamQ = team_map.get(teamName.hashCode());
		if(teamQ != null){
			return teamQ.getRecentMatches(num);
		}
		return null;
	}

	// 获得某球员所有的比赛数据
	public synchronized MatchesPO[] getPlayerMatches(int season, String playername) {
		Match matches = matchContainer.getSeasonMatch(season);
		TIntObjectMap<PlayerQueue> player_map = matches.getPlayer_map();
		PlayerQueue playerQ = player_map.get(playername.hashCode());
		if(playerQ != null){
			return playerQ.getAllMatches();
		}
		return null;
	}

	// 获得某球队所有的比赛数据
	public synchronized MatchesPO[] getTeamMatches(int season, String teamname) {
		Match matches = matchContainer.getSeasonMatch(season);
		TIntObjectMap<TeamQueue> team_map = matches.getTeam_map();
		TeamQueue teamQ = team_map.get(teamname.hashCode());
		if(teamQ != null){
			return teamQ.getAllMatches();
		}
		return null;
	}

	// 获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(int season, Date date) {
		return matchservice.getMatches(date);
	}

	@Override
	public synchronized void update1() {
	}

	@Override
	public Match getMatch(int season) {
		return matchContainer.getSeasonMatch(season);
	}

}
