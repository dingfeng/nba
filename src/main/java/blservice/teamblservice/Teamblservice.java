package blservice.teamblservice;

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
	//获得热点球队
	public HotPlayerTeam[] getHotTeams(int season, String sortby, SeasonType type);
	//获得该球队有史以来的所有球员名
	public String[] getPlayers(String team);
	//获得赛季所有球队赛季数据 低阶
	public TeamNormalPO[] getAllTeamTotal(int season, SeasonType type);
	//获得赛季所有球队场均数据 低阶
	public TeamNormalPO[] getAllTeamAve(int season, SeasonType type);
	//获得赛季所有球队高阶数据
	public TeamHighPO[] getAllTeamHigh(int season, SeasonType type);
	//根据球队简称查找赛季数据 低阶
	public TeamNormalPO getTotalTeam(int season, String teamname, SeasonType type);
	//根据球队简称查找场均数据 低阶
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type);
	//根据球队简称查找高阶数据
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type);
	//根据球队的简称查找球队基本数据
	public TeamPO getTeamData(String team);
	//根据球队简称查找其下的球员的场均数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname, SeasonType type);
	//根据球队简称查找其下的球员的赛季数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname, SeasonType type);
	//根据球队简称查找其下球员的高阶赛季数据
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname, SeasonType type);
	//获得球员的基本信息
	public HPlayerPO getPlayerBase(String playername);
	//获得所有球队名
	public String[] getTeamNames();
	//获得某个球队的所有赛季的总数据
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama,SeasonType type);
	//获得某个球员所有赛季的场均数据
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama,SeasonType type);
	//获得某个球员所有赛季的高阶数据
	public TeamHighPO[] getTeamSeasonHigh(String teama,SeasonType type);
}
