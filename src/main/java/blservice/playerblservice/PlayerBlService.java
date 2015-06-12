package blservice.playerblservice;


import java.awt.Image;

import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
import po.HotPlayerTeam;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;

public interface PlayerBlService {
	/**
	 * 筛选球员 场均数据 低阶
	 * @param season			赛季
	 * @param playerPosition	球员位置
	 * @param playerArea		球员分区
	 * @param type				赛季类型
	 * @return	符合筛选条件的前50名球员场均数据 低阶
	 */
	public PlayerNormalPO[] screenNormalAvePlayers(int season, String playerPosition, Area playerArea, SeasonType type);
	/**
	 * 筛选球员 赛季数据 低阶
	 * @param season			赛季
	 * @param playerPosition	球员位置
	 * @param playerArea		球员分区
	 * @param type				赛季类型
	 * @return	符合筛选条件的前50名球员所有赛季数据 低阶
	 */
	public PlayerNormalPO[] screenNormalTotalPlayers(int season, String playerPosition, Area playerArea, SeasonType type);
	/**
	 * 筛选球员 数据 高阶
	 * @param season			赛季
	 * @param playerPosition	球员位置
	 * @param playerArea		球员分区
	 * @param type				赛季类型
	 * @return	符合筛选条件的前50名球员高阶数据
	 */
	public PlayerHighPO[] screenHighPlayers(int season, String playerPosition, Area playerArea, SeasonType type);
	/**
	 * 获得当天热点球员
	 * @param sortby			排序依据
	 * @return	返回当天热点球员
	 */
	public HotPlayerTeam[] getDayHotPlayer(String sortby);
	/**
	 * 获得赛季热点球员
	 * @param season			赛季
	 * @param sortby			排序依据
	 * @param type				赛季类型
	 * @return	返回当季相应热点球员
	 */
	public HotPlayerTeam[] getSeasonHotPlayer(int season, String sortby, SeasonType type);
	/**
	 * 获得进步最快球员 5名
	 * @param season			赛季
	 * @param sortby			排序依据
	 * @param type				赛季类型
	 * @return	相应赛季进步最快的5位球员
	 */
	public HotPlayerTeam[] getPromotePlayer(int season, String sortby, SeasonType type);
	/**
	 * 模糊查找球员
	 * @param info				查找的开头字母
	 * @return	由info开头的球员名字列表
	 */
	public String[] fuzzilyFind(String info);
	/**
	 * 查找球员 并获得球员基本信息
	 * @param info				需要查找的球员名字
	 * @return	相应球员的基本信息
	 */
    public HPlayerPO findPlayer(String info);
    /**
     * 查找球员场均数据 低阶
     * @param season			赛季
     * @param playername		球员名字
     * @param type				赛季类型
     * @return	相应球员的场均数据 低阶
     */
    public PlayerNormalPO findPlayerMatchAve(int season, String playername,SeasonType type);
    /**
     * 查找球员赛季数据 低阶
     * @param season			赛季
     * @param playername		球员名字
     * @param type				赛季类型
     * @return	相应球员赛季总数据 低阶
     */
    public PlayerNormalPO findPlayerTotal(int season, String playername, SeasonType type);
    /**
     * 查找球员高阶数据
     * @param season			赛季
     * @param playername		球员名字
     * @param type				赛季类型
     * @return	相应球员的高阶数据
     */
    public PlayerHighPO findPlayerHigh(int season, String playername, SeasonType type);
    /**
     * 获得以字母为开头的球员基本信息
     * @param season		赛季
     * @param start			球员开头字母
     * @return	以相应字幕开头的球员的基本信息
     */
    public HPlayerPO[] getPlayersWithStart(int season, String start);
    /**
     * 获得所有球员场均数据 低阶
     * @param season		赛季
     * @param type			赛季类型
     * @return	所有球员场均数据 低阶
     */
    public PlayerNormalPO[] getAveAllPlayers(int season, SeasonType type);
    /**
     * 获得所有球员所有数据 低阶
     * @param season		赛季
     * @param type			赛季类型
     * @return	所有球员所有赛季数据 低阶
     */
    public PlayerNormalPO[] getTotalAllPlayers(int season, SeasonType type);
    /**
     * 获得所有球员高阶数据
     * @param season		赛季
     * @param type			赛季类型
     * @return	所有球员的高阶数据
     */
    public PlayerHighPO[] getHighAllPlayers(int season, SeasonType type);
    /**
     * 获得球员的所有赛季总数据
     * @param playerName	球员名称
     * @param type			赛季类型
     * @return	所有赛季总数据
     */
  	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName, SeasonType type);
  	/**
  	 * 获得球员的赛季场均数据
  	 * @param playerName	球员名称
  	 * @param type			赛季类型
  	 * @return	相应球员所有赛季的场均数据 低阶
  	 */
  	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName, SeasonType type);
  	/**
  	 * 获得球员的所有的赛季高阶数据
  	 * @param playerName	球员名称
  	 * @param type			赛季类型
  	 * @return	相应球员的所有赛季的高阶数据
  	 */
  	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type);
  	/**
  	 * 得到所有现役球员基本信息
  	 * @return 所有现役球员的基本信息
  	 */
  	public PlayerPO[] getAllActivePlayerData();
  	/**
  	 * 根据球员的名字得到球员的头像
  	 * @param name			球员名称
  	 * @return	相应球员的头像
  	 */
    public Image getPlayerImage(String name);
    /**
     * 得到球员基本信息的雷达图
     * @param season		赛季
     * @param name			球员名称
     * @param type			赛季类型
     * @return	球员低阶场均数据的雷达图（也许为null）
     */
    public Image getRadarImage(int season, String name, SeasonType type);
    /**
     * 得到两个球员对比的柱状图
     * @param season		赛季
     * @param name1			左侧球员的名称
     * @param name2			右侧球员的名称
     * @param type			赛季类型
     * @return	两个球员相应赛季场均数据的对比柱状图
     */
    public Image getCompareImage(int season, String name1, String name2, SeasonType type);
    /**
     * 得到某球员赛季近10场比赛数据 以及预估下场比赛数据
     * @param season		赛季
     * @param playername	球员名称
     * @return	相应预估折线图
     */
    public Image getLineChartImage(int season, String playername);
    /**
     * 得到球员与所有球员当前赛季平均水平比较的柱状图
     * @param season	赛季
     * @param name		球员名称
     * @param type		赛季类型
     * @return	返回相应球员与所有球员当前赛季平均水平比较的柱状图
     */
    public Image getPlayerBar(int season, String name, SeasonType type);
    /**
     * 根据球员名称得到PlayerPO
     * @param name		球员名称
     * @return	返回相应的playerPO
     */
    public PlayerPO getplayerPObyName(String name);
    /**
     * 得到一个球队的所有球员playerPO对象
     * @param teamname		球队名称
     * @return	相应的playerPO对象数组
     */
    public PlayerPO[] getPlayerOfTeam(String teamname);
    /**
     * 由球员首字母得到playerpo数组
     * @param start	球员名称首字母
     * @return	返回相应的playerpo数组
     */
    public PlayerPO[] getPlayerPOWithStart(String start);
}
