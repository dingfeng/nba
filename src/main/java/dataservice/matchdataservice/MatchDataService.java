package dataservice.matchdataservice;

import java.util.Date;

import po.MatchesPO;

public interface MatchDataService {
	public MatchesPO[] getRegularSeasonMatches(int season);
	public MatchesPO[] getRegularPlayerMatches(int season, String name);
	public MatchesPO[] getRegularTeamMatches(int season, String teamName);
	public MatchesPO[] getPlayerOffMatches(int season);
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String name);
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamName);
	public MatchesPO getTeamMatches(int season,Date date,String teamName);
	public MatchesPO[] getMatches(int season, Date date);
}
