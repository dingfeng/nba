package data.matchdata;

import java.sql.Connection;

import po.MatchesPO;
import dataservice.matchdataservice.MatchDataService;

public class MatchData implements MatchDataService{
	   private int season;
	   private Connection conn;
	   public MatchData(Connection conn)
	   {
		   this.conn = conn;
	   }
	  @Override
		public MatchesPO[] getSeasonMatches(int season) {
			return null;
		}

		@Override
		public MatchesPO[] getPlayerMatches(int season, String name) {
			return null;
		}

		@Override
		public MatchesPO[] getTeamMatches(int season, String teamName) {
			return null;
		}

		@Override
		public MatchesPO[] getTeamMatches(int season, String Date,
				String teamName) {
			return null;
		}

		@Override
		public MatchesPO[] getMatches(int season, String Date) {
			return null;
		}
		public int getSeason()
		{
			return season;
		}
        
}
