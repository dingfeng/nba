package blservice.matchblservice;

import gnu.trove.map.TIntObjectMap;

import java.util.Date;

import po.MatchesPO;
import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;
import bl.matchbl.TeamQueue;

public interface Matchblservice 
{
	public void update();
	public boolean changed();
	public Match getMatch(int season);
    public MatchesPO[] getTodayMatches();
	public MatchesPO[] getAllMatches(int season);
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num);
	public MatchesPO[] getRecentTeamMatches(String teamName, int num);
	public MatchesPO[] getPlayerMatches(int season, String playername);
	public MatchesPO[] getTeamMatches(int season, String teamname);
    public MatchesPO[] getTimeMatches(int season, Date date1, Date date2);
    public MatchesPO[] getTime_TeamMatches(int season, Date date1, Date date2, String teamname, String playername);
    public void update1();
}
