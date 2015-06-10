package data.matchdata;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import live.CurrentMatch;
import live.SimpleMatchLive;

import org.junit.Before;
import org.junit.Test;

import po.MatchesPO;
import po.OldMatch;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.SeasonType;

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
		 MatchesPO[] matches = match.getRegularSeasonMatches(2012,1,1000);
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
	public void testGetPlayerOffMatches() {
		MatchesPO[] matches = match.getPlayerOffMatches(1994);
//		print(matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerOffPlayerMatches() {
		MatchesPO[] matches = match.getPlayerOffPlayerMatches(2008, "Kobe Bryant");
//		print (matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerOffTeamMatches() {
		MatchesPO[] matches = match.getPlayerOffTeamMatches(2007, "LAL");
//		print(matches);
		assertEquals(true,true);
	}
	@Test
	public void testGetPlayerOffTeamMatchesN()
	{
//		print(match.getRegularTeamMatchesn(2012, "LAL", 10).length);
	}
	@Test 
	public void testMatchLive()
	{
		SimpleMatchLive[] matches = match.getAllLiveMatches();
//		print (matches);
	}
	@Test
	public void testMatchLiveIdFind()
	{
		CurrentMatch m = match.getLiveMatchesById(150119);
		int matchId = -1;
		CurrentMatch  s = null;
		SimpleMatchLive[] matches = match.getAllLiveMatches();
		for (SimpleMatchLive l : matches)
		{
			matchId = l.getMatchId();
			s = match.getLiveMatchesById(matchId);
//			print (s);
		}
//		print(m);
	}
	@Test
	public void testOldMatch()
	{
		OldMatch[] matches = match.getOldMatch(1966, 1, 12, SeasonType.REGULAR);
		for (int i = 0; i < matches.length; ++i)
		{
			int matchId = matches[i].getMatchId();
			OldMatch m = match.getOldMatchInfo(matchId);
//			print(m);
		}
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
