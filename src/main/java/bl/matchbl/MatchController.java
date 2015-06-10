package bl.matchbl;

import java.util.Date;

import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.SeasonType;
import po.MatchesPO;
import po.OldMatch;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice {
	MatchDataService matchservice;
	
	public MatchController(){
		NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			matchservice = dataFactory.getMatchData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获得今日的比赛数据
	public synchronized MatchesPO[] getTodayMatches() {
		return null;
	}
	
	//获得当前赛季所有的赛后季比赛数据
	public synchronized MatchesPO[] getPlayerOffMatches(int season){
		return matchservice.getPlayerOffMatches(season);
	}

	// 获得某球员所有的比赛数据
	public synchronized MatchesPO[] getRegularPlayerMatches(int season, String playername) {
		return matchservice.getRegularPlayerMatchesn(season, playername, 100);
	}

	// 获得某球队所有的比赛数据
	public synchronized MatchesPO[] getRegularTeamMatches(int season, String teamname) {
		return matchservice.getRegularTeamMatchesn(season, teamname, 100);
	}
	
	// 获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(Date date) {
		return matchservice.getMatches(date);
	}
	
	@Override
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name) {
		return matchservice.getRegularPlayerMatchesn(season, name, 5);
	}

	@Override
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName) {
		return matchservice.getRegularTeamMatchesn(season, teamName, 5);
	}

	@Override
	public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name) {
		return matchservice.getPlayerOffPlayerMatchesn(season, name, 5);
	}

	@Override
	public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName) {
		return matchservice.getPlayerOffTeamMatchesn(season, teamName, 5);
	}

	@Override
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String playername) {
		return matchservice.getPlayerOffPlayerMatchesn(season, playername, 100);
	}

	@Override
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamname) {
		return matchservice.getPlayerOffTeamMatchesn(season, teamname, 100);
	}

	@Override
	public OldMatch[] getOldMatch(int season, int low, int high,
			SeasonType seasonType) {
		return matchservice.getOldMatch(season, low, high, seasonType);
	}

	@Override
	public OldMatch getOldMatchInfo(int matchId) {
		return matchservice.getOldMatchInfo(matchId);
	}

}
