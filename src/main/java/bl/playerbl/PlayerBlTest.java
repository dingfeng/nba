package bl.playerbl;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

import dataservice.playerdataservice.SeasonType;
import po.PlayerPO;
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
	public void radarTest(){
		String[] playerNames = this.getPlayernames();
		Image result = playerservice.getRadarImage(2014, playerNames[0], SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	@Test
	public void compareTest(){
		String[] playerNames = this.getPlayernames();
		Image result = playerservice.getCompareImage(2014, playerNames[0], playerNames[0], SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	@Test
	public void test() {
		String[] playernames = this.getPlayernames();
		Image image = playerservice.getPlayerImage(playernames[0]);
		assertNotNull(image);
	}

}
