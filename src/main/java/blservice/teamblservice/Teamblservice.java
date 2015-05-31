package blservice.teamblservice;

import java.util.Iterator;

import po.PlayerPO;
import po.TeamNormalPO;
import po.TeamPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import vo.TeamVO;

public interface Teamblservice
{
	//获得热点球队
	public TeamNormalPO[] getHotTeams(int season, String sortby);
	//获得该球队的所有球员名
	public String[] getPlayers(int season, String team);
	//根据球队简称查找赛季数据
	public TeamMatchVO getTotalTeam(int season, String teamname);
	//根据球队简称查找场均数据
	public  TeamMatchVO getAveTeam(int season, String teamname);
	//根据球队的简称查找球队基本数据
	public TeamPO getTeamData(String team);
	//根据球队简称查找其下的球员的场均数据
	public PlayerMatchVO[] getAllPlayerMatchAve(int season, String teamname);
	//根据球队简称查找其下的球员的赛季数据
	public PlayerMatchVO[] getAllPlayerMatchTotal(int season, String teamname);
	//获得球员的基本信息
	public PlayerPO getPlayerBase(String playername);
	//获得所有球队名
	public String[] getTeamNames();
}
