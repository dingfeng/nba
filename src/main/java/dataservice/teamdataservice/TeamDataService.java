/**
 *Project Name : nba
 *File Name: TeamDataService.java
 *Package Name : dataservice.teamdataservice
 *Date:2015-6-11下午 13:00：01
 * Copyright(c) 2015,南京大学软件学院13级宇宙无敌工作组 All Rights Reserved
 * 
 */
package dataservice.teamdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dataservice.playerdataservice.SeasonType;
import po.HotPlayerTeam;
import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;

/**
 * 球员数据接口 javaBean ClassName:TeanDataService<br/>
 * Function:<br/>
 * findTeam:<br/>
 * getAllTeam <br/>
 * getTeamHigh <br/>
 * getTeamNormalAve <br/>
 * getTeamNormalTotal<br/>
 * getSeasonHigh <br/>
 * getTeamSeasonNormalAve <br/>
 * getTeamSeasonNormalTotal <br/>
 * sortTeamHighn <br/>
 * sortTeamNormalAven <br/>
 * sortTeamNormalTotaln<br/>
 * Reason: 为球队模块提供数据接口 <br/>
 * 数据格式：sort 的格式为 数据类型+排序类型 <br/>
 * 数据类型：  season,teamName ,offenseRound ,offenseEfficiency ,defenceEfficiency ,
 * orebsEfficiency ,drebsEfficiency,stealsEfficiency,assistEfficiency,ave ,season ,teamName ,
 * matchNo,hitNo,handNo,threeHitNo, threeHandNo,penaltyHitNo,penaltyHandNo,offenseRebs,defenceRebs
 * ,rebs,assistNo,stealsNo,blockNo,mistakesNo,foulsNo,points,hitRate,threeHitRate,penaltyHitRate,winRate
 * 排序类型：asc,desc 
 * 例如：1.hitNo asc  2. teamName desc 
 * 组合排序： offenseRound desc,offenseEfficiency desc
 * 
 * @author 丁峰
 * @version 1.0
 * @since JDK 1.8
 */

public interface TeamDataService extends Remote{
	//得到所有球队数据
	/**
	 * 获得所有球队数据<br/>
	 * @return                 返回所有球队的数据
	 * @throws RemoteException 有可能抛出网络异常
	 * @excample sdf
	 */
	public TeamPO[] getAllTeamData()throws RemoteException;
	//球队缩写查找球队
	/**
	 * 球队缩写查找球队<br/>
	 * ex:<br/>
	 * getAllTeamData()<br/>
	 * @param teamName
	 * @return
	 * @throws RemoteException
	 */
	public TeamPO findTeam(String teamName)throws RemoteException;
	//获得球队的赛季赛季总数据
	/**
	 * 获得球队的赛季赛季总数据<br/>
	 * ex:<br/>
	 * findTeam("LAL")<br/>
	 * @param season            赛季
	 * @param teama             球队
	 * @param type              赛季类型
	 * @return                  球队基础数据
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public TeamNormalPO getTeamNormalTotal(int season, String teama,SeasonType type)throws RemoteException;
	//获得球队排序赛季总数据
	/**
	 * 获得球队排序赛季总数据<br/>
	 * ex:<br/>
	 * getTeamNormalTotal(2014,"LAL",SeasonType.REGULAR)<br/>
	 * @param season            赛季
	 * @param sort              排序依据
	 * @param n                 数量
	 * @param type              赛季类型
	 * @return                  一定数量的球队基础数据
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public TeamNormalPO[] sortTeamNormalTotaln(int season,String sort, int n,SeasonType type)throws RemoteException;
	//获得球队赛季场均数据
	/**
	 * 获得球队赛季场均数据<br/>
	 * ex:<br/>
	 * sortTeamNormalTotaln(2014,"rebs asc",5,SeasonType.REGULAR)<br/>
	 * @param season             赛季
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   球队基础数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamNormalPO getTeamNormalAve(int season, String teama,SeasonType type)throws RemoteException;
	//获得球员排序场均数据
	/**
	 * 获得球员排序场均数据<br/>
	 * ex:<br/>
	 * getTeamNormalAve(2014,"LAL",SeasonType.REGULAR)<br/>
	 * @param season             赛季
	 * @param sort               排序依据
	 * @param n                  数量
	 * @param type               赛季类型
	 * @return                   一定数量的球队基础数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamNormalPO[] sortTeamNormalAven(int season, String sort, int n,SeasonType type)throws RemoteException;
	//获得球员某个赛季的高阶数据
	/**
	 * 获得球员某个赛季的高阶数据<br/>
	 * ex:<br/>
	 * sortTeamNormalAven(2014,"rebs asc",5,SeasonType.REGULAR)<br/>
	 * @param season             赛季
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   球队高阶数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamHighPO getTeamHigh(int season, String teama,SeasonType type)throws RemoteException;
	//获得球员排序赛季高阶数据
	/**
	 * 获得球员排序赛季高阶数据<br/>
	 * ex:<br/>
	 * getTeamHigh(2014,"LAL",SeasonType.REGULAR)<br/>
	 * @param season               赛季
	 * @param sort                 排序依据
	 * @param n                    数量
	 * @param type                 赛季类型
	 * @return                     一定数量的球队高阶数据
	 * @throws RemoteException     抛出网络异常
	 */
	public TeamHighPO[] sortTeamHighn(int season, String sort, int n,SeasonType type)throws RemoteException;
//	public TeamHighPO[] sortTeamHighHis(String sort, int n);
	//获得某个球队的所有赛季的总数据
	/**
	 * 获得某个球队的所有赛季的总数据<br/>
	 * ex:<br/>
	 * sortTeamHighn(2014,"rebs asc",5,SeasonType.REGULAR)<br/>
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   所有该赛季该球队的基础数据总数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama,SeasonType type)throws RemoteException;
	//获得某个球员所有赛季的场均数据
	/**
	 * 获得某个球员所有赛季的场均数据<br/>
	 * ex:<br/>
	 * getTeamSeasonNormalTotal("LAL",SeasonType.REGULAR)<br/>
	 * @param teama             球队缩写名
	 * @param type              赛季类型
	 * @return                  所有该赛季球队的基础数据场均数据
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama,SeasonType type)throws RemoteException;
	//获得某个球员所有赛季的高阶数据
	/**
	 * 获得某个球员所有赛季的高阶数据<br/>
	 * ex:<br/>
	 * getTeamSeasonNormalAve("LAL",SeasonType.REGULAR)<br/>
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   该球队在该类赛季下所有球队高阶数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamHighPO[] getTeamSeasonHigh(String teama,SeasonType type)throws RemoteException;
	/**
	 * 获得赛季热点球队<br/>
	 * @param hot                 热点类型       
	 * @param seasonType          赛季类型
	 * @return                    前五名的热点球队
	 * @throws RemoteException    有可能抛出网络异常
	 */
	public HotPlayerTeam[] getHotTeam(int season,String hot,SeasonType seasonType)throws RemoteException;
}
