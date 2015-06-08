package blservice.teamblservice;

import java.awt.Image;

import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import vo.HotPlayerTeam;

public interface Teamblservice
{
	/**
	 * 获得热点球队
	 * @param season	赛季
	 * @param sortby	排序依据 score rebs assist blockno steal threeHitRate hitRate penaltyHitRate
	 * @param type		赛季类型
	 * @return 相应前5个热点队伍
	 */
	public HotPlayerTeam[] getHotTeams(int season, String sortby, SeasonType type);
	/**
	 * 获得该球队有史以来的所有球员名
	 * @param team 队伍名
	 * @return 该球队有史以来的所有球员名
	 */
	public String[] getPlayers(String team);
	/**
	 * 获得赛季所有球队赛季数据 低阶
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return	该赛季所有球队赛季数据 低阶
	 */
	public TeamNormalPO[] getAllTeamTotal(int season, SeasonType type);
	/**
	 * 获得赛季所有球队场均数据 低阶
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return	该赛季所有球队场均数据 低阶
	 */
	public TeamNormalPO[] getAllTeamAve(int season, SeasonType type);
	/**
	 * 获得赛季所有球队高阶数据
	 * @param season	赛季
	 * @param type		赛季类型
	 * @return	该赛季所有球队高阶数据
	 */
	public TeamHighPO[] getAllTeamHigh(int season, SeasonType type);
	/**
	 * 根据球队简称查找赛季数据 低阶
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return 相应球队赛季数据 低阶
	 */
	public TeamNormalPO getTotalTeam(int season, String teamname, SeasonType type);
	/**
	 * 根据球队简称查找场均数据 低阶
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return	相应球队场均数据 低阶
	 */
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type);
	/**
	 * 根据球队简称查找高阶数据
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return	相应球队高阶数据
	 */
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type);
	/**
	 * 根据球队的简称查找球队基本数据
	 * @param team		队伍名称
	 * @return	相应球队基本数据
	 */
	public TeamPO getTeamData(String team);
	/**
	 * 根据球队简称查找其下的球员的场均数据 低阶
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return	相应球队旗下的球员season赛季的场均数据 低阶
	 */
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname, SeasonType type);
	/**
	 * 根据球队简称查找其下的球员的赛季数据 低阶
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return	相应球队旗下的球员season赛季的赛季总数据 低阶
	 */
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname, SeasonType type);
	/**
	 * 根据球队简称查找其下球员的高阶赛季数据
	 * @param season	赛季
	 * @param teamname	队伍名称
	 * @param type		赛季类型
	 * @return	相应球队旗下球员season赛季的高阶数据
	 */
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname, SeasonType type);
	/**
	 * 获得球员的基本信息
	 * @param playername	球员名字
	 * @return	相应球员的基本信息，如果没有查找到则返回null
	 */
	public HPlayerPO getPlayerBase(String playername);
	/**
	 * 获得有史以来所有球队名
	 * @return 有史以来所有球队名(按时间排序
	 */
	public String[] getTeamNames();
	/**
	 * 获得某个球队的所有赛季的总数据 低阶
	 * @param teama		队伍名称
	 * @param type		赛季类型
	 * @return	得到相应赛季类型的相应队伍所有赛季的总数据 低阶
	 */
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama,SeasonType type);
	/**
	 * 获得某个球队所有赛季的场均数据
	 * @param teama		队伍名称
	 * @param type		赛季类型
	 * @return	得到相应赛季类型的相应队伍所有赛季的场均数据 低阶
	 */
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama,SeasonType type);
	/**
	 * 获得某个球队所有赛季的高阶数据
	 * @param teama		队伍名称
	 * @param type		赛季类型
	 * @return	得到所有赛季类型的相应队伍所有赛季的高阶数据
	 */
	public TeamHighPO[] getTeamSeasonHigh(String teama,SeasonType type);
	/**
	 * 得到球队的图标
	 * @param name	球队简称
	 * @return	球队图标
	 */
	public Image getTeamImage(String name);
	/**
	 * 得到球队与当前赛季所有球队平均数据的对比柱状图
	 * @param season		赛季
	 * @param teamname		球队名称
	 * @return	该球队当前赛季和所有球队平均数据对比的柱状图
	 */
	public Image getTeamBar(int season, String teamname, SeasonType type);
	/**
	 * 得到两个球队对比的柱状图
	 * @param season		赛季
	 * @param teamname1		球队1名称
	 * @param teamname2		球队2名称
	 * @param type			赛季类型
	 * @return 两个球队对比的柱状图
	 */
	public Image getTeamCompare(int season, String teamname1, String teamname2, SeasonType type);
	
}
