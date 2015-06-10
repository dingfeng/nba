package bl.matchbl;

import java.rmi.RemoteException;
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
		try {
			return matchservice.getPlayerOffMatches(season);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获得某球员所有的比赛数据
	public synchronized MatchesPO[] getRegularPlayerMatches(int season, String playername) {
		try {
			return matchservice.getRegularPlayerMatchesn(season, playername, 100);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 获得某球队所有的比赛数据
	public synchronized MatchesPO[] getRegularTeamMatches(int season, String teamname) {
		try {
			return matchservice.getRegularTeamMatchesn(season, teamname, 100);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// 获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(Date date) {
		try {
			return matchservice.getMatches(date);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name) {
		try {
			return matchservice.getRegularPlayerMatchesn(season, name, 5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName) {
		try {
			return matchservice.getRegularTeamMatchesn(season, teamName, 5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name) {
		try {
			return matchservice.getPlayerOffPlayerMatchesn(season, name, 5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName) {
		try {
			return matchservice.getPlayerOffTeamMatchesn(season, teamName, 5);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String playername) {
		try {
			return matchservice.getPlayerOffPlayerMatchesn(season, playername, 100);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamname) {
		try {
			return matchservice.getPlayerOffTeamMatchesn(season, teamname, 100);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OldMatch[] getOldMatch(int season, int low, int high,
			SeasonType seasonType) {
		try {
			return matchservice.getOldMatch(season, low, high, seasonType);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OldMatch getOldMatchInfo(int matchId) {
		try {
			return matchservice.getOldMatchInfo(matchId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
