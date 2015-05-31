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
		return null;
	}
	
	//获得当前赛季所有的赛后季比赛数据
	public synchronized MatchesPO[] getPlayerOffMatches(int season){
		return matchservice.getPlayerOffMatches(season);
	}

	// 获得某球员最近的几场比赛数据
	public synchronized MatchesPO[] getRecentPlayerMatches(String playerName,
			int num) {
		return null;
	}

	// 获得某球队最近的几场比赛数据
	public synchronized MatchesPO[] getRecentTeamMatches(String teamName,
			int num) {
		return null;
	}

	// 获得某球员所有的比赛数据
	public synchronized MatchesPO[] getPlayerMatches(int season, String playername) {
		return null;
	}

	// 获得某球队所有的比赛数据
	public synchronized MatchesPO[] getTeamMatches(int season, String teamname) {
		return null;
	}

	// 获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(Date date) {
		return matchservice.getMatches(date);
	}

	@Override
	public synchronized void update1() {
	}

}
