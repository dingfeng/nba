package bl.matchbl;

import gnu.trove.map.TIntObjectMap;

import java.util.ArrayList;
import java.util.Date;

import po.MatchTeamPO;
import po.MatchesPO;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice {
	MatchContainer matchContainer;
	
	public MatchController(){
		matchContainer = MatchContainer.instance();
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
	public synchronized MatchesPO[] getTimeMatches(int season, Date date1, Date date2) {
		ArrayList<MatchesPO> matchlist = new ArrayList<MatchesPO>();
		Match match1 = matchContainer.getSeasonMatch(season);
		MatchesPO[] seasonAllMatches = match1.getMatches();
		for(MatchesPO m : seasonAllMatches){
			String matchDate = m.getDate();
			//时间判定缺少
		}
		return null;
	}

	public synchronized MatchesPO[] getTime_TeamMatches(int season, Date date1, Date date2,
			String teamname, String playername) {
		ArrayList<MatchesPO> matchlist = new ArrayList<MatchesPO>();
		Match matches = matchContainer.getSeasonMatch(season);
		TIntObjectMap<TeamQueue> team_map = matches.getTeam_map();
		TeamQueue teamQ = team_map.get(teamname.hashCode());
		MatchesPO[] teamMatches = teamQ.getAllMatches();
		if(teamMatches.length != 0){
			for(MatchesPO m : teamMatches){
				//时间判断缺少
				MatchTeamPO team = m.getTeam1().getName().equals(teamname)? m.getTeam1(): m.getTeam2();
				if(team.ifPlayer(playername)){
					matchlist.add(m);
				}
			}
			return (MatchesPO[])matchlist.toArray();
		}
		return null;
	}

	@Override
	public synchronized void update1() {
	}

}
