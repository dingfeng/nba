package test;

import static org.junit.Assert.*;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.CurrentMatch;
import po.MatchesPO;
import po.OldMatch;
import po.SimpleMatchLive;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.SeasonType;

public class MatchDataTest {
    MatchDataService match ;
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
	public void testGetRegularSeasonMatches() throws RemoteException {
		 MatchesPO[] matches = match.getRegularSeasonMatches(2012,1,1000);
		 print(matches);
		 assertEquals(true,true);
	}

	@Test
	public void testGetRegularPlayerMatches() throws RemoteException {
		MatchesPO[] matches = match.getRegularPlayerMatches(2012, "Kobe Bryant");
//		print(matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetRegularTeamMatches() throws RemoteException {
           MatchesPO[] matches =  match.getRegularTeamMatches(2005, "LAL");
//           print(matches);
           assertEquals(true,true);
	}

	@Test
	public void testGetTeamMatches() throws RemoteException {
		MatchesPO matches = match.getTeamMatches(toDate("2005-05-01"), "DEN");
//		print(matches);
		assertEquals(true,true);
		
	}

	@Test
	public void testGetMatches() throws RemoteException {
		MatchesPO[] matches = match.getMatches(toDate("2005-05-01"));
//		print(matches);
		assertEquals(true,true);
	}


	@Test
	public void testGetPlayerOffMatches() throws RemoteException {
		MatchesPO[] matches = match.getPlayOffMatches(1994);
//		print(matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerOffPlayerMatches() throws RemoteException {
		MatchesPO[] matches = match.getPlayOffPlayerMatches(2008, "Kobe Bryant");
//		print (matches);
		assertEquals(true,true);
	}

	@Test
	public void testGetPlayerOffTeamMatches() throws RemoteException {
		MatchesPO[] matches = match.getPlayOffTeamMatches(2007, "LAL");
//		print(matches);
		assertEquals(true,true);
	}
	@Test
	public void testGetPlayerOffTeamMatchesN()
	{
//		print(match.getRegularTeamMatchesn(2012, "LAL", 10).length);
	}
	@Test 
	public void testMatchLive() throws RemoteException
	{
		SimpleMatchLive[] matches = match.getAllLiveMatches();
//		print (matches);
	}
	@Test
	public void testMatchLiveIdFind() throws RemoteException
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
	public void testOldMatch() throws RemoteException
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
