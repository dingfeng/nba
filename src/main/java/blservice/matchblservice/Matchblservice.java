package blservice.matchblservice;


import java.util.Date;

import po.MatchesPO;

public interface Matchblservice 
{
    public MatchesPO[] getTodayMatches();
    public MatchesPO[] getPlayerOffMatches(int season);
	public MatchesPO[] getRegularPlayerMatches(int season, String playername);
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String playername);
	public MatchesPO[] getRegularTeamMatches(int season, String teamname);
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamname);
    public MatchesPO[] getTimeMatches(Date date);
    public MatchesPO[] getRegularPlayerMatchesn(int season, String name);
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName);
	public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name);
	public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName);
}
