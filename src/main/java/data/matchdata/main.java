package data.matchdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import live.CurrentMatch;
import live.CurrentTeam;
import dataservice.matchdataservice.MatchDataService;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;

public class main {
   public static void main(String[] args) throws Exception
   {
	   NBADataFactory factory = DataFactory.instance();
	   MatchData match = (MatchData) factory.getMatchData();
	   CurrentMatch matches = match.getLiveMatchesById(150119);
	   System.out.println(matches);
//	   CurrentTeam team = match.getCurrentTeam(150119, "勇士");
//	   System.out.println(team);
   }
}
