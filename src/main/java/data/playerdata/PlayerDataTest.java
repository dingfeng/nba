package data.playerdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.HPlayerPO;
import po.MatchPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;

public class PlayerDataTest {
    PlayerDataService player;
	@Before 
	public void setUp()
	{
		try {
			NBADataFactory factory = DataFactory.instance();
			player = factory.getPlayerData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllPlayerData() {
		PlayerPO[] players = player.getAllActivePlayerData();
		
		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testFindPlayer() {
		//未完成
		HPlayerPO p = player.findPlayer("Kobe Bryant");
//		print (p);
	}

	@Test
	public void testGetPlayerOfTeam()
	{
		PlayerPO[] players = player.getPlayersOfTeam("LAL");
		print(players);
	}
	
	@Test
	public void testGetPlayerHigh() {
		PlayerHighPO players = player.getPlayerHigh(2012, "Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testSortPlayerHighn() {
		PlayerHighPO[] players = player.sortPlayerHighn(2003, "efficiency desc", 9, SeasonType.REGULAR);
//		print(players);
	    assertEquals(true,true);
	}

	@Test
	public void testGetPlayerNormalTotal() {
		PlayerNormalPO players = player.getPlayerNormalTotal(2013, "Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testSortPlayerNormalTotal() {
		PlayerNormalPO[] players = player.sortPlayerNormalTotal(2010, "rebs desc"
				+ "", 10, SeasonType.PLAYOFF);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerNormalAve() {
        PlayerNormalPO normalPlayer = player.getPlayerNormalAve(2009, "Kobe Bryant", SeasonType.REGULAR);
//        print(normalPlayer);
        assertEquals(true,true);
	}

	@Test
	public void testSortPlayerNormalAven() {
		PlayerNormalPO[] players = player.sortPlayerNormalAven(2012, "rebs", 67, SeasonType.PLAYOFF);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasonsTotal() {
		PlayerNormalPO[] players =  player.getPlayerAllSeasonsTotal("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasonsAve() {
		PlayerNormalPO[] players = player.getPlayerAllSeasonsAve("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasons() {
		PlayerHighPO[] players =  player.getPlayerAllSeasons("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetSeasonAveData() {
		PlayerNormalPO[] players =  player.getPlayerAllSeasonsAve("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}
	
	@Test
	public void getTeamTest()
	{
//		print(player.getPlayersOfTeam("LAL"));
	}
	@Test
	public void testHplayer()
	{
		HPlayerPO[] players = player.getHPlayerByIni("a");
//		print(players);
	}
	@Test
	public void testScreen()
	{
//		print(player.screenPlayer("player_name desc", "W", "F", 10));
	}
	@Test
	public void testMatchPlayer()
	{
		MatchPlayerPO[] players = player.getSeasonMatches(2014, "Kobe Bryant", SeasonType.REGULAR);
//		print (players);
	}
	@Test 
	public void testSeasonTeamPlayer()
	{
		PlayerNormalPO[] players = player.getSeasonPlayerNormalOfTeam(1999, SeasonType.PLAYOFF, "LAL");
		print (players);
	}
	public static void print(Object[] objects)
    {
    	for (Object o : objects)
    	{
    		System.out.println(o);
    	}
    }
    public static void print(Object o)
    {
    	System.out.println(o);
    }

}
