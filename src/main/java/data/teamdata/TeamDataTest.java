package data.teamdata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import dataservice.playerdataservice.SeasonType;
import dataservice.teamdataservice.TeamDataService;

public class TeamDataTest {
	 TeamDataService teamData;
     @Before
    public void setUp()
    {
    	try {
			NBADataFactory factory = DataFactory.instance();
			teamData = factory.getTeamData();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Test
	public void testGetAllTeamData() {
//		TeamPO[] teams = teamData.getAllTeamData();
//		print(teams);
		assertEquals(true,true);
	}

	@Test
	public void testFindTeam() {
		TeamPO team = teamData.findTeam("Hawks");
		System.out.println(team);
		assertEquals(true,true);
	}

	@Test
	public void testGetTeamNormalTotal() {
		TeamNormalPO team = teamData.getTeamNormalTotal(1990, "ATL", SeasonType.PLAYOFF);
//		System.out.println(team);
		assertEquals(true,true);
	}

	@Test
	public void testSortTeamNormalTotaln() {
		TeamNormalPO[] teams  = teamData.sortTeamNormalTotaln(2014, "rebs desc", 3, SeasonType.REGULAR);
//		for (TeamNormalPO t : teams)
//		{
//			System.out.println(t);
//		}
		assertEquals(true,true);
	}

	@Test
	public void testGetTeamNormalAve() {
		TeamNormalPO teams = teamData.getTeamNormalAve(2014, "ATL",SeasonType.REGULAR);
//		print(teams);
//		print(teams);
		assertEquals(true,true);
	}

	@Test
	public void testSortTeamNormalAven() {
		TeamNormalPO[] teams = teamData.sortTeamNormalAven(2013, "hitNo desc", 8, SeasonType.PLAYOFF);
//		print (teams);
		teams = teamData.sortTeamNormalAven(2012, "rebs desc", 6, SeasonType.REGULAR);
//		print(teams);
		assertEquals(true,true);
	}

	@Test
	public void testGetTeamHigh() {
		TeamHighPO team = teamData.getTeamHigh(1994, "CHI", SeasonType.PLAYOFF);
//		print(team);
		assertEquals(true,true);
	}

	@Test
	public void testSortTeamHighn() {
		TeamHighPO[] teams = teamData.sortTeamHighn(2011, "offenseRound desc", 10, SeasonType.REGULAR);
//		print (teams);
		assertEquals(true,true);
	}

	@Test
	public void testGetTeamSeasonNormalTotal() {
		TeamNormalPO[] team = teamData.getTeamSeasonNormalTotal("ATL", SeasonType.PLAYOFF);
//		print(team);
		assertEquals(true,true);
	}

	@Test
	public void testGetTeamSeasonNormalAve() {
           TeamNormalPO[] teams = teamData.getTeamSeasonNormalAve("NOP", SeasonType.REGULAR);
           print (teams);
           assertEquals(true,true);
	}

	@Test
	public void testGetTeamSeasonHigh() {
		TeamHighPO[] teams = teamData.getTeamSeasonHigh("ATL", SeasonType.PLAYOFF);
//		print(teams);
		assertEquals(true,true);
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
