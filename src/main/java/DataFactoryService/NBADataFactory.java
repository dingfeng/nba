/**
 *Project Name : nba
 *File Name: NBADataFactory.java
 *Package Name : DataFactoryService
 *Date:2015-6-11下午 13:00：01
 * Copyright(c) 2015,南京大学软件学院13级宇宙无敌工作组 All Rights Reserved
 * 
 */
package DataFactoryService;


import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;

/**
 * 数据工厂接口 javaBean ClassName:NBADataFactory<br/>
 * Function: getMatchData getPlayerData getTeamData<br/>
 * Reason: 为球员模块提供数据接口 <br/>
 *Date:2015-6-11下午 13:00：01 <br/>
 * @author 丁峰
 * @version 1.0
 * @since JDK 1.8
 */

public interface  NBADataFactory
{
	/**
	 * 获得球员数据控制对象<br/>
	 * @return  球员数据控制对象
	 */
	public PlayerDataService getPlayerData();
	/**
	 * 获得球队数据控制对象<br/>
	 * @return  球队数据控制对象
	 */
	public TeamDataService getTeamData();
	/**
	 * 获得比赛数据控制对象
	 * @return  比赛数据控制对象
	 */
	public MatchDataService getMatchData();
}
