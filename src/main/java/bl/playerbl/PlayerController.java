package bl.playerbl;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;

import java.awt.Image;
import java.util.ArrayList;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
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

	//根据球员名字查找球员
	public synchronized HPlayerPO findPlayer(String info) {
		return playerService.findPlayer(info);
	}
	
	
	
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
}
