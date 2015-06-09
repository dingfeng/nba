package dataservice.matchdataservice;

import java.awt.Image;
import java.util.Date;

import dataservice.playerdataservice.SeasonType;
import live.CurrentMatch;
import live.SimpleMatchLive;
import po.MatchesPO;
import po.OldMatch;

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
	public SimpleMatchLive[] getAllLiveMatches();
	public CurrentMatch getLiveMatchesById(int matchId);
	public Image getLiveTeamImg(String teamName);
	//<=1984赛季
	public OldMatch[] getOldMatch(int season, int low, int high, SeasonType seasonType);
	public OldMatch getOldMatchInfo(int matchId);
}
