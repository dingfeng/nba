/**
 *Project Name : nba
 *File Name: MatchDataService.java
 *Package Name : dataservice.matchdataservice
 *Date:2015-6-11下午 13:00：01
 * Copyright(c) 2015,南京大学软件学院13级宇宙无敌工作组 All Rights Reserved
 * 
 */
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


/**
 * 比赛数据接口 javaBean ClassName: MatchDataService.java<br/>
 * Function: getAllLiveMatches getLiveMatchById getLiveTeamImg getMatchById getMatches getOldMatch getOldMatchInfo<br/>
 * getPlayOffMatches getPlayerOffPlayerMatches getPlayerOffPlayerMatchesn getPlayerOffTeamMatches getPlayerOffTeamMatchesn<br/>
 * getRegularPlayerMatches getRegularPlayerMatchesn getRegularSeasonMatches getRegularTeamMatches getRegularTeamMatchesn<br/>
 * getTeamMatches 
 * Reason: 为比赛模块提供数据接口 <br/>
 *Date:2015-6-11下午 13:00：01 <br/>
 * @author 丁峰
 * @version 1.0
 * @since JDK 1.8
 */
public interface MatchDataService extends Remote{
	/**
	 * 获得某个赛季的常规赛比赛<br/>
	 * @param season           赛季
	 * @param low              按比赛的时间顺序
	 * @param high
	 * @return
	 * @throws RemoteException
	 */
	public MatchesPO[] getRegularSeasonMatches(int season,int low,int high)throws RemoteException;
	public MatchesPO[] getRegularPlayerMatches(int season, String name)throws RemoteException;
	public MatchesPO[] getRegularTeamMatches(int season, String teamName)throws RemoteException;
	public MatchesPO[] getPlayOffMatches(int season)throws RemoteException;
	public MatchesPO[] getPlayOffPlayerMatches(int season, String name)throws RemoteException;
	public MatchesPO[] getPlayOffTeamMatches(int season, String teamName)throws RemoteException;
	public MatchesPO getTeamMatches(Date date,String teamName)throws RemoteException;
	public MatchesPO getMatchById(int matchId)throws RemoteException;
	public MatchesPO[] getMatches( Date date)throws RemoteException;
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name,int n)throws RemoteException;
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	public MatchesPO[] getPlayOffPlayerMatchesn(int season, String name,int n)throws RemoteException;
	public MatchesPO[] getPlayOffTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	public SimpleMatchLive[] getAllLiveMatches()throws RemoteException;
	public CurrentMatch getLiveMatchesById(int matchId)throws RemoteException;
	public Image getLiveTeamImg(String teamName)throws RemoteException;
	//<=1984赛季
	public OldMatch[] getOldMatch(int season, int low, int high, SeasonType seasonType)throws RemoteException;
	public OldMatch getOldMatchInfo(int matchId)throws RemoteException;
}
