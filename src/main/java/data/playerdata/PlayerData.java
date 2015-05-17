package data.playerdata;

import java.sql.Connection;

import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;

public class PlayerData implements PlayerDataService{
    private Connection conn;
	public PlayerData(Connection conn)
	{
		this.conn = conn;
	}
	@Override
	public PlayerPO[] getAllPlayerData() {
		return null;
	}

	@Override
	public PlayerPO findPlayer(String playerName) {
		return null;
	}
	@Override
	public PlayerHighPO getPlayerHigh(int season, String playerName) {
		return null;
	}
	@Override
	public PlayerHighPO[] sortPlayerHighn(int season, String sort, int n) {
		return null;
	}
	@Override
	public PlayerNormalPO getPlayerNormalTotal(int season, String playerName) {
		return null;
	}
	@Override
	public PlayerNormalPO[] sortPlayerNormalTotal(int season, String sort, int n) {
		return null;
	}
	@Override
	public PlayerNormalPO getPlayerNormalAve(int season, String playerName) {
		return null;
	}
	@Override
	public PlayerNormalPO sortPlayerNormalAven(int season, String sort, int n) {
		return null;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName) {
		return null;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName) {
		return null;
	}
	@Override
	public PlayerHighPO[] getPlayerAllSeasons(String playerName) {
		return null;
	}
  
}
