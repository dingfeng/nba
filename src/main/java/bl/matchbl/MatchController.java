package bl.matchbl;

import gnu.trove.map.TIntObjectMap;

import java.util.Date;

import po.MatchesPO;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice {
	Match match;
	public MatchController(int season) throws Exception{
		match = new Match(season);
	}

	// 更新数据
	public synchronized void update() {
		match.update();
	}

	// 数据是否改变
	public synchronized boolean changed() {
		return false;
	}

	// 获得今日的比赛数据
	public synchronized MatchesPO[] getTodayMatches() {
		return null;
	}

	// 获得所有的比赛数据
	public synchronized MatchesPO[] getAllMatches() {
		return null;
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
	public synchronized MatchesPO[] getPlayerMatches(String playername) {
		return null;
	}

	// 获得某球队所有的比赛数据
	public synchronized MatchesPO[] getTeamMatches(String teamname) {
		return null;
	}

	// 获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(Date date1, Date date2) {
		return null;
	}

	public synchronized MatchesPO[] getTime_TeamMatches(Date date1, Date date2,
			String teamname, String playername) {
		return null;
	}

	@Override
	public synchronized void update1() {
	}

}
