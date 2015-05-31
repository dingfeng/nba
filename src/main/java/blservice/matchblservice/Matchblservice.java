package blservice.matchblservice;


import java.util.Date;

import po.MatchesPO;

public interface Matchblservice 
{
	public void update();
	public boolean changed();
    public MatchesPO[] getTodayMatches();
    public MatchesPO[] getPlayerOffMatches(int season);
	public MatchesPO[] getAllMatches(int season);
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num);
	public MatchesPO[] getRecentTeamMatches(String teamName, int num);
	public MatchesPO[] getPlayerMatches(int season, String playername);
	public MatchesPO[] getTeamMatches(int season, String teamname);
    public MatchesPO[] getTimeMatches(Date date);
    public void update1();
}
