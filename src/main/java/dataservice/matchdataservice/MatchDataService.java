package dataservice.matchdataservice;

import java.util.Date;

import po.MatchesPO;

public interface MatchDataService {
	public MatchesPO[] getRegularSeasonMatches(int season,int low,int high);
	public MatchesPO[] getRegularPlayerMatches(int season, String name);
	public MatchesPO[] getRegularTeamMatches(int season, String teamName);
	public MatchesPO[] getPlayerOffMatches(int season);
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String name);
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamName);
	public MatchesPO getTeamMatches(Date date,String teamName);
	public MatchesPO getMatchById(int matchId);
	public MatchesPO[] getMatches( Date date);
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name,int n);
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName,int n);
	public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name,int n);
	public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName,int n);
}
