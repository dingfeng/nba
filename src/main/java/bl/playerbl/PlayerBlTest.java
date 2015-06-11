package bl.playerbl;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

import dataservice.playerdataservice.SeasonType;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.HotPlayerTeam;
import blservice.playerblservice.PlayerBlService;

public class PlayerBlTest {
	PlayerBlService playerservice;
	@Before
	public void setUp(){
		playerservice = new PlayerController();
	}

	private String[] getPlayernames(){
		PlayerPO[] players = playerservice.getAllActivePlayerData();
		String[] names = new String[players.length];
		for(int i = 0; i != players.length; i ++){
			names[i] = players[i].getName();
		}
		return names;
	}
	
	@Test
	public void screenTest(){
		PlayerNormalPO[] result = playerservice.screenNormalAvePlayers(2014, "G", Area.ATLANTIC, SeasonType.REGULAR);
		assertNotNull(result);
	}
	
	//@Test
	public void writeTest(){
		PlayerController pc = new PlayerController();
		pc.write();
	}
	
	//@Test
	public void PromoteTest(){
		HotPlayerTeam[] pro1 = playerservice.getPromotePlayer(2014, "points", SeasonType.REGULAR);
		HotPlayerTeam[] pro2 = playerservice.getPromotePlayer(2014, "help", SeasonType.REGULAR);
		HotPlayerTeam[] pro3 = playerservice.getPromotePlayer(2014, "rebs", SeasonType.REGULAR);
		assertNotNull(pro1);
		assertNotNull(pro2);
		assertNotNull(pro3);
	}
	
	//@Test
	public void radarTest(){
		String[] playerNames = this.getPlayernames();
		Image result = playerservice.getRadarImage(2014, playerNames[0], SeasonType.REGULAR);
		assertNotNull(result);
	}
	
	//@Test
	public void compareTest(){
		String[] playerNames = this.getPlayernames();
		Image result = playerservice.getCompareImage(2014, playerNames[0], playerNames[1], SeasonType.REGULAR);
		assertNotNull(result);
	}
	
	//@Test
	public void test() {
		String[] playernames = this.getPlayernames();
		Image image = playerservice.getPlayerImage(playernames[0]);
		assertNotNull(image);
	}

}
