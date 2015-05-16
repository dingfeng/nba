package data.playerdata;

import java.sql.Connection;

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
  
}
