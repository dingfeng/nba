package bl.playerbl;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import po.MatchPlayerPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.MatchController;
import bl.matchbl.PlayerQueue;
import blservice.matchblservice.Matchblservice;
import blservice.playerblservice.PlayerBlService;

public class PlayerController{
	Matchblservice matchservice;
    PlayerDataService playerService;
    //排序球员数据 场均
    public PlayerController(int season)
    {
    	NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			playerService = dataFactory.getPlayerData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matchservice = new MatchController();
    }
	public synchronized PlayerMatchVO[] sortAvePlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return null;
	}
	//排序球员数据  赛季数据
	public  synchronized PlayerMatchVO[] sortTotalPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return null;
	}
	//筛选球员数据  场均  
	public  synchronized PlayerMatchVO[] screenAvePlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		Iterator<PlayerMatchVO> itr = player.screenAvePlayers(playerPosition, playerArea, sortBy);
		ArrayList<PlayerMatchVO> list = new ArrayList<PlayerMatchVO>();
		while (itr.hasNext())
		{
			list.add(itr.next());
		}
		PlayerMatchVO[] players = new PlayerMatchVO[list.size()];
		list.toArray(players);
		return players;
	}
	//筛选球员数据  赛季
	public synchronized PlayerMatchVO[] screenTotalPlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		Iterator<PlayerMatchVO> itr =  player.screenTotalPlayers(playerPosition, playerArea, sortBy);
		ArrayList<PlayerMatchVO> list = new ArrayList<PlayerMatchVO>();
		while (itr.hasNext())
		{
			list.add(itr.next());
		}
		PlayerMatchVO[] players = new PlayerMatchVO[list.size()];
		list.toArray(players);
		return players;
	}
	public  synchronized PlayerMatchVO[] getDayHotPlayer(String sortBy) {
		return null;
	}
	//获得赛季热点球员
	public synchronized PlayerNormalPO[] getSeasonHotPlayer(int season, String sortby, SeasonType type) {
		return playerService.sortPlayerNormalAven(season, sortby, 5, type);
	}
	
	//获得进步最快球员
	public synchronized PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return null;
	}

	//根据球员名字查找球员
	public synchronized PlayerPO findPlayer(String info) {
		return playerService.findPlayer(info);
	}
	
	public synchronized PlayerMatchVO findPlayerMatchAve(int season, String playername) {
		Match seasonMatch = matchservice.getMatch(season);
		PlayerQueue playerQ = seasonMatch.getPlayerData(playername);
		return playerQ.getAvePlayer();
	}
	
	public synchronized PlayerMatchVO findPlayerTotal(int season, String playername) {
		Match seasonMatch = matchservice.getMatch(season);
		PlayerQueue playerQ = seasonMatch.getPlayerData(playername);
		return playerQ.getTotalPlayer();
	}
	
	public synchronized PlayerMatchVO[] getAvePlayers(String start) {
		return null;
	}
	
	public synchronized PlayerMatchVO[] getTotalPlayers(String start) {
		return null;
	}
	
	public synchronized PlayerMatchVO[] getAvePlayers(int season) {
		Match seasonMatch = matchservice.getMatch(season);
		TIntObjectMap<PlayerQueue> players = seasonMatch.getPlayer_map();
		PlayerQueue thisPlayerQueue;
		int size = players.size();
		PlayerMatchVO[] matchVOs = new PlayerMatchVO[size];
		
		TIntObjectIterator<PlayerQueue> it = players.iterator();             
		for(int i = 0; i > size; i++){                  
			it.advance();
			thisPlayerQueue = (PlayerQueue) it.value();
			matchVOs[i] = thisPlayerQueue.getAvePlayer();
		}
		return matchVOs;
	}
	
	public synchronized PlayerMatchVO[] getTotalPlayers(int season) {
		Match seasonMatch = matchservice.getMatch(season);
		TIntObjectMap<PlayerQueue> players = seasonMatch.getPlayer_map();
		PlayerQueue thisPlayerQueue;
		int size = players.size();
		PlayerMatchVO[] matchVOs = new PlayerMatchVO[size];
		
		TIntObjectIterator<PlayerQueue> it = players.iterator();             
		for(int i = 0; i > size; i++){                  
			it.advance();
			thisPlayerQueue = (PlayerQueue) it.value();
			matchVOs[i] = thisPlayerQueue.getTotalPlayer();
		}
		return matchVOs;
	}
	
	public Image getPlayerImage(String name) {
		PlayerPO playerP = playerService.findPlayer(name);
		return playerP.getPortrait();
	}
}
