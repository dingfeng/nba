/**
 *Project Name : nba
 *File Name: PlayerDataService.java
 *Package Name : dataservice.playerdataservice
 *Date:2015-5-31下午 13:00：01
 * Copyright(c) 2015,南京大学软件学院13级宇宙无敌工作组 All Rights Reserved
 * 
 */
package dataservice.playerdataservice;
import java.awt.Image;
import java.rmi.Remote;
import java.rmi.RemoteException;

import po.HPlayerPO;
import po.MatchPlayerPO;
import po.PlayerHighPO;
import po.PlayerImage;
import po.PlayerNormalPO;
import po.PlayerPO;

/**
 * 球员数据接口 javaBean ClassName:PlayerDataService<br/>
 * Function: findPlayer fuzzilySearch getAllActivePlayerData getHPlayerByIni getPlayerAllSeasons getPlayerAllSeasonsAve getPlayerAllSeasonsTotal getPlayerHigh getPlayerNormalAve getPlayerNormalTotal getPlayersOfTeam getSeasonPlayerHigh getSeasonPlayerNormalAve getSeasonPlayerNormalTotal screenPlayer sortPlayerHighn sortPlayerNormalAven sortPlayerNormalTotal <br/>
 * Reason: 为球员模块提供数据接口 <br/>
 *Date:2015-5-31下午 13:00：01 <br/>
 * format: sort 的格式为 数据类型+排序类型 <br/>
 *基础数据类型：player_name（球员名）,team（球队缩写）,matchNo（比赛场数）,firstServiceNo（首发场数）, rebs（篮板数）,assistNo（助攻数）,player_time（球员上场时间）,offendRebsNo（进攻篮板数）,defenceRebsNo（防守篮板数）,stealsNo（抢断数）,blockNo（盖帽数）,mistakesNo（失误数）,foulsNo（犯规数）,points（得分数）,player_minute（分钟）,hitNo（投篮命中数）,handNo（投篮出手数）,hitRate（投篮命中率）,penaltyHandNo（罚球出手数）,penaltyHitNo（罚球命中数）,penaltyHitRate（罚球命中率）,threeHitNo（三分命中数）,threeHandNo（三分出手数）,threeHitRate（三分命中率）,twoPair（两双数）,points_uprate（得分提升率）,rebs_uprate（篮板提升率）,help_uprate（助攻提升率）,scoring_rebound_assist（得分/篮板/助攻=1：1：1） <br/>
 *高阶数据类型 ：playerName（球员名）,teamName（球队缩写）,efficiency（效率）,GmScEfficiency（gmsc效率值）,trueHitRate（真实命中率）,hitEfficiency（投篮效率）,rebEfficiency（篮板率）,offenseRebsEfficiency（进攻篮板率）,defenceRebsEfficiency（防守篮板率）,assistEfficiency（助攻率）,stealsEfficiency（抢断率）,blockEfficiency（盖帽率）,mistakeEfficiency（失误率）,useEfficiency（使用率） <br/>
 * 排序类型： asc,desc  <br/>
 * 例如  "player_name asc" 按球员名升序排序，"rebs desc"按篮板数降序排序 <br/>
 * 组合排序 ： "efficiency desc,playerName asc" 当效率值一样时按照球员名升序排  <br/>
 * 形式： 数据类型+排序类型[,数据类型+排序类型[,..]]  <br/>
 * @author 丁峰
 * @version 1.0
 * @since JDK 1.8
 */
public interface PlayerDataService extends Remote{
	//得到所有球员数据
    /**
     * 获得所有现役球员<br/>
     * 
     * @return 所有现役球员
     */
	public PlayerPO[] getAllActivePlayerData()throws RemoteException;
	//获得球员某个赛季的高阶数据
	/**
	 * getPlayerHigh 获得球员某个赛季的高阶数据 <br/>
	 * 
	 * @param season     赛季
	 * @param playerName 球员名
	 * @param type       赛季类型
	 * @return           球员高阶数据
	 */
	public PlayerHighPO getPlayerHigh(int season,String playerName,SeasonType type)throws RemoteException;
	//高阶数据排序
	/**
	 * 高阶数据排序 <br/>
	 * @param season    赛季
	 * @param sort      排序依据
	 * @param n         返回值数量。不足按较小值
	 * @param type      赛季类型
	 * @return          排序后的高阶数据
	 */
	public PlayerHighPO[] sortPlayerHighn(int season, String sort,int n, SeasonType type)throws RemoteException;
	//获得某个球员赛季总基础数据
	/**
	 * 返回某个球员的赛季总数据基础数据<br/>
	 * @param season       赛季
	 * @param playerName   球员名
	 * @param type         赛季类型
	 * @return             赛季总基础数据
	 */
	public PlayerNormalPO getPlayerNormalTotal(int season, String playerName, SeasonType type)throws RemoteException;
	//排序球员赛季总数据
	/**
	 * 排序球员赛季总数据<br/>
	 * @param season	赛季
	 * @param sort		排序依据
	 * @param n			返回数目。不足按较小的值
	 * @param type		赛季类型
	 * @return			排序后的赛季总基础数据
	 */
	public PlayerNormalPO[] sortPlayerNormalTotal(int season, String sort, int n, SeasonType type)throws RemoteException;
  	//获得某个球员赛季场均数据
	/**
	 * 获得某个球员赛季场均数据<br/>
	 * @param season		赛季
	 * @param playerName	球员名
	 * @param type			赛季类型
	 * @return				赛季场均数据
	 */
	public PlayerNormalPO getPlayerNormalAve(int season, String playerName, SeasonType type)throws RemoteException;
	//排序球员的场均基础数据
	/**
	 * 排序球员的赛季场均基础数据<br/>
	 * @param season	赛季
	 * @param sort		排序依据
	 * @param n			返回数目
	 * @param type		赛季类型
	 * @return			排序后的赛季场均数据
	 */
	public PlayerNormalPO[] sortPlayerNormalAven(int season, String sort, int n, SeasonType type)throws RemoteException;
	//获得球员的所有赛季总数据<br/>
	/**
	 * 获得球员的所有赛季总数据<br/>
	 * @param playerName	球员名
	 * @param type			赛季类型
	 * @return				所有赛季总数据
	 */
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName, SeasonType type)throws RemoteException;
	//获得球员的赛季场均数据
	/**
	 * 获得球员的所有赛季场均数据 <br/>
	 * @param playerName	球员名
	 * @param type			赛季类型
	 * @return				所有赛季场均数据
	 */
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName, SeasonType type)throws RemoteException;
	//获得球员的所有的赛季高阶数据
	/**
	 * 获得球员的所有的赛季高阶数据 <br/>
	 * @param playerName	球员名
	 * @param type			赛季类型
	 * @return				该球员的所有赛季高阶数据
	 */
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type)throws RemoteException;
	//通过首字符获得
	/**
	 * 通过首字符串获得<br/>
	 * @param ini	首字符串
	 * @return		所有符合条件的球员
	 */
	public HPlayerPO[] getHPlayerByIni(String ini)throws RemoteException;
	//模糊查找
	/**
	 * 模糊查找<br/>
	 * @param info		模糊查找依据
	 * @return			符合条件所有球员名
	 */
	public String[] fuzzilySearch(String info)throws RemoteException;
	//现役球员名查找
	/**
	 * 现役球员名查找<br/>
	 * @param playerName	球员名
	 * @return				球员信息
	 */
	public HPlayerPO findPlayer(String playerName)throws RemoteException;
	//获得某个赛季的所有球员
	/**
	 * 获得某个赛季所有参赛球员的赛季场均基础数据<br/>
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return			所有球员的赛季场均基础数据
	 */
	public PlayerNormalPO[] getSeasonPlayerNormalAve(int season,SeasonType type)throws RemoteException;
	/**
	 * 获得某个赛季的所有参赛球员的赛季总基础数据<br/>
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return			所有参赛球员的赛季总基础数据
	 */
	public PlayerNormalPO[] getSeasonPlayerNormalTotal(int season, SeasonType type)throws RemoteException;
	/**
	 * 获得某个赛季的所有参赛球员的赛季高级数据<br/>
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return			所参赛球员的赛季高阶数据
	 */
	public PlayerHighPO[] getSeasonPlayerHigh(int season, SeasonType type)throws RemoteException;
	//获得球队所有球员
	/**
	 * 获得某个球队的球员<br/>
	 * @param team	球员缩写名
	 * @return		在该球员服役的球员
	 */
	public PlayerPO[] getPlayersOfTeam(String team)throws RemoteException;
	//球员筛选
	/**
	 * 根据条件筛选球员<br/>
	 * @param sort			排序依据
	 * @param match_area	联盟
	 * @param postion		位置
	 * @param n				返回数目
	 * @return				符合条件的球员信息
	 */
	public PlayerPO[] screenPlayer(String sort,String match_area,String player_area,String postion,int n)throws RemoteException;
	/**
	 * 获得某个赛季的该球员的每场得比赛的数据
	 * @param season	赛季
	 * @param name		球员姓名
	 * @return			该球员在该赛季的每场比赛的数据
	 */
	public MatchPlayerPO[] getSeasonMatches(int season, String name, SeasonType type)throws RemoteException;
	//获得某个赛季球队球员的数据
	/**
	 * 获得某个赛季球队中的球员数据<br/>
	 * @param season    赛季
	 * @param type      赛季类型
	 * @param teamName  球队名
	 * @return          球员基础数据
	 * @throws RemoteException   可能抛出网络异常
	 */
	public PlayerNormalPO[] getSeasonPlayerNormalOfTeam(int season, SeasonType type,String teamName)throws RemoteException;
	//获得球员
	/**
	 * 获得球员图片<br/>
	 * @param playerName        球员名
	 * @return                  球员图片
	 * @throws RemoteException  可能抛出网络异常
	 */
	public PlayerImage getPlayerImage(String playerName)throws RemoteException;
	//获得现役球员
	/**
	 * 模糊查找球员名
	 * @param info               查找依据
	 * @return                   符合条件的球员名
	 * @throws RemoteException   可能抛出网络异常
	 */
	public String[] fuzzilySearchActivePlayer(String info)throws RemoteException;
	/**
	 * 模糊查找球员
	 * @param info               查找依据
	 * @return                   符合条件的球员信息
	 * @throws RemoteException   可能抛出网络异常
	 */
	public PlayerPO[] fuzzilySearchAvtivePlayerPO(String info) throws RemoteException;
	/**
	 * 根据球员名查找球员
	 * @param playerName        球员名
	 * @return                  球员信息
	 * @throws RemoteException  可能抛出网络异常
	 */
    public PlayerPO findActivePlayerPO(String playerName) throws RemoteException;
//	public PlayerNormalPO[] sortPlayerNormalTotalHis(String sort, int n);
//	public PlayerNormalPO sortPlayerNormalAveHis(String sort, int n);
}

