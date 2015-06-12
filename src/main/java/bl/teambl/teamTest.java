package bl.teambl;

import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

import po.HotPlayerTeam;
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
	
	//@Test
	public void testHot(){
		HotPlayerTeam[] hot = teamservice.getHotTeams(2014, "points", SeasonType.REGULAR);
		HotPlayerTeam[] hot2 = teamservice.getHotTeams(2014, "assistNo", SeasonType.REGULAR);
		HotPlayerTeam[] hot3 = teamservice.getHotTeams(2014, "blockNo", SeasonType.REGULAR);
		HotPlayerTeam[] hot4 = teamservice.getHotTeams(2014, "stealsNo", SeasonType.REGULAR);
		HotPlayerTeam[] hot5 = teamservice.getHotTeams(2014, "threeHitRate", SeasonType.REGULAR);
		HotPlayerTeam[] hot6 = teamservice.getHotTeams(2014, "hitRate", SeasonType.REGULAR);
		HotPlayerTeam[] hot7 = teamservice.getHotTeams(2014, "penaltyHitRate", SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	//@Test
	public void testTeamCompare(){
		String[] teamnames = teamservice.getTeamNames();
		Image image = teamservice.getTeamCompare(2014, teamnames[3], teamnames[6], SeasonType.PLAYOFF);
	
		assertNotNull(image);
	}
	
	//@Test
	public void testTeamBar(){
		String[] teamnames = teamservice.getTeamNames();
		Image image = teamservice.getTeamBar(2014, teamnames[7], SeasonType.PLAYOFF);
		assertNotNull(image);
	}
	
	//@Test
	public void TeamCompareGenerater(){
		String[] teamnames = teamservice.getTeamNames();
		for(int i = 29; i != teamnames.length; i ++){
			for(String t2 : teamnames){
				teamservice.getTeamCompare(2014, teamnames[i], t2, SeasonType.PLAYOFF);
			}
		}
	}
	
	//@Test
	public void TeamBarGenerater(){
		String[] teamnames = teamservice.getTeamNames();
		for(String t : teamnames){
			teamservice.getTeamBar(2014, t, SeasonType.PLAYOFF);
		}
	}
	
	//@Test
	public void testAllTeamHigh(){
		TeamHighPO[] result = teamservice.getAllTeamHigh(2014, SeasonType.PLAYOFF);
		assertEquals(true, true);
	}
	
	//@Test
	public void testGetTeamData(){
		String[] teamnames = teamservice.getTeamNames();
		TeamPO team = teamservice.getTeamData(teamnames[0]);
		assertNotNull(team);
	}
	
	//@Test
	public void testAllTeamTotal() {
		TeamNormalPO[] result = teamservice.getAllTeamTotal(2014, SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	//@Test
	public void testAllTeamAve(){
		TeamNormalPO[] result2 = teamservice.getAllTeamAve(2014, SeasonType.REGULAR);
		assertEquals(true, true);
	}
	
	
}
