package dataservice.matchdataservice;

import java.awt.Image;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import dataservice.playerdataservice.SeasonType;
import po.CurrentMatch;
import po.MatchesPO;
import po.OldMatch;
import po.SimpleMatchLive;

public interface MatchDataService extends Remote{
	public MatchesPO[] getRegularSeasonMatches(int season,int low,int high)throws RemoteException;
	public MatchesPO[] getRegularPlayerMatches(int season, String name)throws RemoteException;
	public MatchesPO[] getRegularTeamMatches(int season, String teamName)throws RemoteException;
	public MatchesPO[] getPlayerOffMatches(int season)throws RemoteException;
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String name)throws RemoteException;
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamName)throws RemoteException;
	public MatchesPO getTeamMatches(Date date,String teamName)throws RemoteException;
	public MatchesPO getMatchById(int matchId)throws RemoteException;
	public MatchesPO[] getMatches( Date date)throws RemoteException;
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name,int n)throws RemoteException;
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name,int n)throws RemoteException;
	public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	public SimpleMatchLive[] getAllLiveMatches()throws RemoteException;
	public CurrentMatch getLiveMatchesById(int matchId)throws RemoteException;
	public Image getLiveTeamImg(String teamName)throws RemoteException;
	//<=1984赛季
	public OldMatch[] getOldMatch(int season, int low, int high, SeasonType seasonType)throws RemoteException;
	public OldMatch getOldMatchInfo(int matchId)throws RemoteException;
}
