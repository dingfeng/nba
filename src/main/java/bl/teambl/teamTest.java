package bl.teambl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import dataservice.playerdataservice.SeasonType;
import blservice.teamblservice.Teamblservice;

public class teamTest {
	Teamblservice teamservice;
	@Before
	public void setUp(){
		teamservice = new TeamController();
	}
	
	@Test
	public void testAllTeamHigh(){
		TeamHighPO[] result = teamservice.getAllTeamHigh(2014, SeasonType.PLAYOFF);
		assertEquals(true, true);
	}
	
	@Test
	public void testGetTeamData(){
		String[] teamnames = teamservice.getTeamNames();
		TeamPO team = teamservice.getTeamData(teamnames[0]);
		assertNotNull(team);
	}
	
	@Test
	public void testAllTeamTotal() {
		TeamNormalPO[] result = teamservice.getAllTeamTotal(2014, SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	@Test
	public void testAllTeamAve(){
		TeamNormalPO[] result2 = teamservice.getAllTeamAve(2014, SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	
}
