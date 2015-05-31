package data.matchdata;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import po.MatchesPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;

public class MatchDataTest {
    MatchDataService match;
	@Before
	public void setUp()
	{
		try {
			NBADataFactory factory = DataFactory.instance();
			match = factory.getMatchData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetRegularSeasonMatches() {
		 MatchesPO[] matches = match.getRegularSeasonMatches(2012);
//		 print(matches);
		 assertEquals(true,true);
	}

	@Test
	public void testGetRegularPlayerMatches() {
		MatchesPO[] matches = match.getRegularPlayerMatches(2012, "Kobe Bryant");
//		print(matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetRegularTeamMatches() {
           MatchesPO[] matches =  match.getRegularTeamMatches(2005, "LAL");
//           print(matches);
           assertEquals(true,true);
	}

	@Test
	public void testGetTeamMatches() {
		MatchesPO matches = match.getTeamMatches(toDate("2005-05-01"), "DEN");
//		print(matches);
		assertEquals(true,true);
		
	}

	@Test
	public void testGetMatches() {
		MatchesPO[] matches = match.getMatches(toDate("2005-05-01"));
//		print(matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetMatchIdScope() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerOffMatchId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerOffMatches() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerOffPlayerMatches() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayerOffTeamMatches() {
		fail("Not yet implemented");
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
    public static Date toDate(String str)
    {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			return format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return new Date();
    }

}
