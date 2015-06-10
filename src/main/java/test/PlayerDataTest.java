package test;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.BeforeClass;
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
	public void testGetAllPlayerData() throws RemoteException {
		PlayerPO[] players = player.getAllActivePlayerData();
		
		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testFindPlayer() throws RemoteException {
		//未完成
		HPlayerPO p = player.findPlayer("Kobe Bryant");
//		print (p);
	}

	@Test
	public void testGetPlayerOfTeam() throws RemoteException
	{
		PlayerPO[] players = player.getPlayersOfTeam("LAL");
		print(players);
	}
	
	@Test
	public void testGetPlayerHigh() throws RemoteException {
		PlayerHighPO players = player.getPlayerHigh(2012, "Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testSortPlayerHighn() throws RemoteException {
		PlayerHighPO[] players = player.sortPlayerHighn(2003, "efficiency desc", 9, SeasonType.REGULAR);
//		print(players);
	    assertEquals(true,true);
	}

	@Test
	public void testGetPlayerNormalTotal() throws RemoteException {
		PlayerNormalPO players = player.getPlayerNormalTotal(2013, "Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testSortPlayerNormalTotal() throws RemoteException {
		PlayerNormalPO[] players = player.sortPlayerNormalTotal(2010, "rebs desc"
				+ "", 10, SeasonType.PLAYOFF);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerNormalAve() throws RemoteException {
        PlayerNormalPO normalPlayer = player.getPlayerNormalAve(2009, "Kobe Bryant", SeasonType.REGULAR);
//        print(normalPlayer);
        assertEquals(true,true);
	}

	@Test
	public void testSortPlayerNormalAven() throws RemoteException {
		PlayerNormalPO[] players = player.sortPlayerNormalAven(2012, "rebs", 67, SeasonType.PLAYOFF);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasonsTotal() throws RemoteException {
		PlayerNormalPO[] players =  player.getPlayerAllSeasonsTotal("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasonsAve() throws RemoteException {
		PlayerNormalPO[] players = player.getPlayerAllSeasonsAve("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerAllSeasons() throws RemoteException {
		PlayerHighPO[] players =  player.getPlayerAllSeasons("Kobe Bryant", SeasonType.REGULAR);
//		print(players);
		assertEquals(true,true);
	}

	@Test
	public void testGetSeasonAveData() throws RemoteException {
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
	public void testHplayer() throws RemoteException
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
	public void testMatchPlayer() throws RemoteException
	{
		MatchPlayerPO[] players = player.getSeasonMatches(2014, "Kobe Bryant", SeasonType.REGULAR);
//		print (players);
	}
	@Test 
	public void testSeasonTeamPlayer() throws RemoteException
	{
		PlayerNormalPO[] players = player.getSeasonPlayerNormalOfTeam(1999, SeasonType.PLAYOFF, "LAL");
		print (players);
	}
	@Test 
	public void testPlayerOfTeam() throws RemoteException
	{
		PlayerNormalPO[] players = player.getSeasonPlayerNormalOfTeam(1999, SeasonType.REGULAR, "LAL");
		print(players);
	}
	@Test
	public void testScreen1() throws RemoteException
	{
		PlayerPO[] players = player.screenPlayer("age desc", "W", "Northwest", "F", 10);
		print("sdfsdf");
		print(players);
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
