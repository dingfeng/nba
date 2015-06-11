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
import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;

/**
 * 球员数据接口 javaBean ClassName:TeanDataService<br/>
 * Function: findTeam getAllTeam getTeamHigh getTeamNormalAve getTeamNormalTotal<br/>
 * getSeasonHigh getTeamSeasonNormalAve getTeamSeasonNormalTotal sortTeamHighn sortTeamNormalAven sortTeamNormalTotaln<br/>
 * Reason: 为球队模块提供数据接口 <br/>
 * 数据格式：
 * 
 * 
 * @author 丁峰
 * @version 1.0
 * @since JDK 1.8
 */

public interface TeamDataService extends Remote{
	//得到所有球队数据
	/**
	 * 获得所有球队数据
	 * @return                 返回所有球队的数据
	 * @throws RemoteException 有可能抛出网络异常
	 * @excample sdf
	 */
	public TeamPO[] getAllTeamData()throws RemoteException;
	//球队缩写查找球队
	/**
	 * 球队缩写查找球队
	 * @param teamName
	 * @return
	 * @throws RemoteException
	 */
	public TeamPO findTeam(String teamName)throws RemoteException;
	//获得球队的赛季赛季总数据
	/**
	 * 获得球队的赛季赛季总数据
	 * @param season            赛季
	 * @param teama             球队
	 * @param type              赛季类型
	 * @return                  球队基础数据
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public TeamNormalPO getTeamNormalTotal(int season, String teama,SeasonType type)throws RemoteException;
	//获得球队排序赛季总数据
	/**
	 * 获得球队排序赛季总数据
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
	 * 获得球队赛季场均数据
	 * @param season             赛季
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   球队基础数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamNormalPO getTeamNormalAve(int season, String teama,SeasonType type)throws RemoteException;
	//获得球员排序场均数据
	/**
	 * 获得球员排序场均数据
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
	 * 获得球员某个赛季的高阶数据
	 * @param season             赛季
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   球队高阶数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamHighPO getTeamHigh(int season, String teama,SeasonType type)throws RemoteException;
	//获得球员排序赛季高阶数据
	/**
	 * 获得球员排序赛季高阶数据
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
	 * 获得某个球队的所有赛季的总数据
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   所有该赛季该球队的基础数据总数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama,SeasonType type)throws RemoteException;
	//获得某个球员所有赛季的场均数据
	/**
	 * 获得某个球员所有赛季的场均数据
	 * @param teama             球队缩写名
	 * @param type              赛季类型
	 * @return                  所有该赛季球队的基础数据场均数据
	 * @throws RemoteException  有可能抛出网络异常
	 */
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama,SeasonType type)throws RemoteException;
	//获得某个球员所有赛季的高阶数据
	/**
	 * 获得某个球员所有赛季的高阶数据
	 * @param teama              球队缩写名
	 * @param type               赛季类型
	 * @return                   该球队在该类赛季下所有球队高阶数据
	 * @throws RemoteException   有可能抛出网络异常
	 */
	public TeamHighPO[] getTeamSeasonHigh(String teama,SeasonType type)throws RemoteException;
}
