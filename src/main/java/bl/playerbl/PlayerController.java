package bl.playerbl;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;

import java.awt.Image;
import java.util.ArrayList;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.HotPlayerTeam;
import vo.PlayerSortBy;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;
import blservice.playerblservice.PlayerBlService;

public class PlayerController implements PlayerBlService{
	Matchblservice matchservice;
    PlayerDataService playerService;
    //排序球员数据 场均
    public PlayerController()
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

	
	public  synchronized HotPlayerTeam[] getDayHotPlayer(String sortBy) {
		return null;
	}
	//获得赛季热点球员
	public synchronized HotPlayerTeam[] getSeasonHotPlayer(int season, String sortby, SeasonType type) {
		PlayerNormalPO[] players = playerService.sortPlayerNormalAven(season, sortby, 5, type);
		HotPlayerTeam[] hotPlayers = new HotPlayerTeam[5];
		double[] data = new double[5];
		if(sortby.equals("score")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getPoints();
			}
		} else if(sortby.equals("rebs")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getRebs();
			}
		} else if(sortby.equals("assist")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getAssistNo();
			}
		} else if(sortby.equals("blockno")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getBlockNo();
			}
		} else if(sortby.equals("steal")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getStealsNo();
			}
		} else if(sortby.equals("threeHitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getThreeHitRate();
			}
		} else if(sortby.equals("hitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getHitRate();
			}
		} else if(sortby.equals("penaltyHitRate")){
			for(int i = 0; i != 5; i ++){
				data[i] = players[i].getPenaltyHitRate();
			}
		}
			
		String name;
		for(int i = 0; i != 5; i ++){
			name = players[i].getName();
			hotPlayers[i] = new HotPlayerTeam(this.getPlayerImage(name), name, data[i]);
		}
		return hotPlayers;
	}
	
	//获得进步最快球员
	public synchronized HotPlayerTeam[] getPromotePlayer(int season, String sortby) {
		return null;
	}
	
	@Override
	//根据球员名字查找球员
	public synchronized HPlayerPO findPlayer(String info) {
		return playerService.findPlayer(info);
	}
	
	
	@Override
	public synchronized HPlayerPO[] getPlayersWithStart(int season, String start) {
		String[] playersFit = playerService.fuzzilySearch(start);
		ArrayList<HPlayerPO> result = new ArrayList<HPlayerPO>(playersFit.length);
		HPlayerPO playerP;
		for(String s : playersFit){
			playerP = playerService.findPlayer(s);
			if(playerP != null){
				result.add(playerP);
			}
		}
		return (HPlayerPO[])result.toArray();
	}
	
	
	
	public Image getPlayerImage(String name) {
		HPlayerPO playerP = playerService.findPlayer(name);
		return playerP.getPortrait();
	}

	@Override
	public String[] fuzzilyFind(String info) {
		String[] playersFit= playerService.fuzzilySearch(info);
		return playersFit;
	}


	@Override
	//筛选球员 场均数据 低阶
	public PlayerNormalPO[] screenNormalAvePlayers(int season,
			String playerPosition, Area playerArea) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//筛选球员 赛季数据 低阶
	public PlayerNormalPO[] screenNormalTotalPlayers(int season,
			String playerPosition, Area playerArea) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//筛选球员 数据 高阶
	public PlayerHighPO[] screenHighPlayers(int season, String playerPosition,
			Area playerArea) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//查找球员场均数据 低阶
	public PlayerNormalPO findPlayerMatchAve(int season, String playername, SeasonType type) {
		return playerService.getPlayerNormalAve(season, playername, type);
	}


	@Override
	//查找球员赛季数据 低阶
	public PlayerNormalPO findPlayerTotal(int season, String playername, SeasonType type) {
		return playerService.getPlayerNormalTotal(season, playername, type);
	}


	@Override
	//查找球员高阶数据
	public PlayerHighPO findPlayerHigh(int season, String playername, SeasonType type) {
		return playerService.getPlayerHigh(season, playername, type);
	}


	@Override
	//获得所有球员场均数据 低阶
	public PlayerNormalPO[] getAveAllPlayers(int season) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//获得所有球员所有数据 低阶
	public PlayerNormalPO[] getTotalAllPlayers(int season) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//获得所有球员高阶数据
	public PlayerHighPO[] getHighAllPlayers(int season) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	//获得球员的所有赛季总数据
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName,
			SeasonType type) {
		return playerService.getPlayerAllSeasonsTotal(playerName, type);
	}


	@Override
	//获得球员的所有赛季场均数据
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName,
			SeasonType type) {
		return playerService.getPlayerAllSeasonsAve(playerName, type);
	}


	@Override
	//获得球员的所有的赛季高阶数据
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type) {
		return playerService.getPlayerAllSeasons(playerName, type);
	}


	@Override
	public PlayerPO[] getAllActivePlayerData() {
		return playerService.getAllActivePlayerData();
	}
}
