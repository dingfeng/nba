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
	 * @throws RemoteException 有可能抛出网络异常
	 */
	public MatchesPO[] getRegularSeasonMatches(int season,int low,int high)throws RemoteException;
	/**
	 * 获得常规赛某个赛季的某个球员的所有比赛<br/>
	 * @param season              赛季
	 * @param name                球员名
	 * @return                    该球员在该赛季的所有常规赛中参加的比赛
	 * @throws RemoteException    有可能抛出网络异常
	 */
	public MatchesPO[] getRegularPlayerMatches(int season, String name)throws RemoteException;
	/**
	 * 获得某个球队某个赛季的的所有比赛<br/>
	 * @param season              赛季
	 * @param teamName            球队名
	 * @return                    该球队在该赛季的所有常规赛中的比赛
	 * @throws RemoteException    有可能抛出网络异常
	 */
	public MatchesPO[] getRegularTeamMatches(int season, String teamName)throws RemoteException;
	/**
	 * 获得某个赛季的季后赛中的所有比赛<br/>
	 * @param season           赛季
	 * @return                 该赛季的所有季后赛
	 * @throws RemoteException 有可能抛出网络异常
	 */
	public MatchesPO[] getPlayOffMatches(int season)throws RemoteException;
	/**
	 * 获得某个球员在某个赛季的季后赛中参加的所有比赛
	 * @param season            赛季
	 * @param name              球员名
	 * @return                  该球员在该赛季的季后赛中参加的所有比赛
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public MatchesPO[] getPlayOffPlayerMatches(int season, String name)throws RemoteException;
	/**
	 * 获得某个球队在某个季后赛中参加的所有比赛
	 * @param season            赛季
	 * @param teamName          球队名
	 * @return                  该球队在该赛季季后赛中参加的所有比赛
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public MatchesPO[] getPlayOffTeamMatches(int season, String teamName)throws RemoteException;
	/**
	 * 获得某一天的球队参加的比赛
	 * @param date             日期
	 * @param teamName         球队名
	 * @return                 该球队在该日参加的比赛
	 * @throws RemoteException 有可能抛出网络异常
	 */
	public MatchesPO getTeamMatches(Date date,String teamName)throws RemoteException;
	/**
	 * 根据比赛编号查找比赛
	 * @param matchId               比赛编号
	 * @return                      该比赛编号代表的比赛
	 * @throws RemoteException      有可能抛出网路异常
	 */
	public MatchesPO getMatchById(int matchId)throws RemoteException;
	/**
	 * 获得某个日期中举行的所有比赛
	 * @param date                 日期
	 * @return                     该日期中举行的所有比赛
	 * @throws RemoteException     有可能抛出网络异常
	 */
	public MatchesPO[] getMatches( Date date)throws RemoteException;
	/**
	 * 获得常规赛某个赛季球员参加的一定数量的比赛
	 * @param season               赛季
	 * @param name                 球员名
	 * @param n                    数量
	 * @return                     该球员在赛季的n个比赛
	 * @throws RemoteException     有可能抛出网络异常
	 */
	public MatchesPO[] getRegularPlayerMatchesn(int season, String name,int n)throws RemoteException;
	/**
	 * 获得常规赛某个赛季的某个球队的后几场比赛
	 * @param season                 赛季
	 * @param teamName               球队名
	 * @param n                      比赛数量
	 * @return                       该球队该赛季常规赛中参加的几场比赛
	 * @throws RemoteException       有可能抛出网络异常
	 */
	public MatchesPO[] getRegularTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	/**
	 * 获得某个球员的在季后赛中的参加的后几场比赛
	 * @param season               赛季
	 * @param name                 球员名
	 * @param n                    比赛数量
	 * @return                     该球员在该季后赛参加的几场比赛
	 * @throws RemoteException     有可能抛出网络异常
	 */
	public MatchesPO[] getPlayOffPlayerMatchesn(int season, String name,int n)throws RemoteException;
	/**
	 * 获得某个球队在某个季后赛中参加的后几场比赛
	 * @param season                赛季
	 * @param teamName              球队名
	 * @param n                     比赛场数
	 * @return                      该球队在该季后赛中参加的几场比赛
	 * @throws RemoteException      有可能抛出网络异常
	 */
	public MatchesPO[] getPlayOffTeamMatchesn(int season, String teamName,int n)throws RemoteException;
	/**
	 * 获得所有正在直播的赛事
	 * @return                    所有正在直播的所有赛事
	 * @throws RemoteException    有可能抛出网络异常
	 */
	public SimpleMatchLive[] getAllLiveMatches()throws RemoteException;
	/**
	 * 获得该比赛编号的直播赛事
	 * @param matchId
	 * @return
	 * @throws RemoteException
	 */
	public CurrentMatch getLiveMatchesById(int matchId)throws RemoteException;
	/**
	 * 获得某个球队的图片
	 * @param teamName           球队名
	 * @return                   放回该球队的图片
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public Image getLiveTeamImg(String teamName)throws RemoteException;
	//<=1984赛季
	/**
	 * 获得<=1984赛季的某个赛季按时间降序排序下某一段的比赛 指明常规赛和季后赛
	 * @param season             
	 * @param low
	 * @param high
	 * @param seasonType
	 * @return
	 * @throws RemoteException
	 */
	public OldMatch[] getOldMatch(int season, int low, int high, SeasonType seasonType)throws RemoteException;
	/**
	 * 按照编号查找<=1984赛季之前的比赛
	 * @param matchId            比赛编号
	 * @return                   历史赛事
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public OldMatch getOldMatchInfo(int matchId)throws RemoteException;
}
