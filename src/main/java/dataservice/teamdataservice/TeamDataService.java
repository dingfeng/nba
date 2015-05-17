package dataservice.teamdataservice;

import java.util.ArrayList;
import java.util.Iterator;

import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;

public interface TeamDataService {
	//得到所有球队数据
	public TeamPO[] getAllTeamData();
	//球队缩写查找球队
	public TeamPO findTeam(String teamName);
	//获得球队的赛季赛季总数据
	public TeamNormalPO getTeamNormalTotal(int season, String teama);
	//获得球队排序赛季总数据
	public TeamNormalPO[] sortTeamNormalTotaln(int season,String sort, int n);
//	public TeamNormalPO[] sortTeamNormalTotalHis(String sort, int n);
	//获得球队赛季场均数据
	public TeamNormalPO getTeamNormalAve(int season, String teama);
	//获得球员排序场均数据
	public TeamNormalPO[] sortTeamNormalAven(int season, String sort, int n);
//	public TeamNormalPO[] sortTeamNormalAveHis(String sort, int n);
	//获得球员某个赛季的高阶数据
	public TeamHighPO getTeamHigh(int season, String teama);
	//获得球员排序赛季高阶数据
	public TeamHighPO[] sortTeamHighn(String sort, int n);
//	public TeamHighPO[] sortTeamHighHis(String sort, int n);
	//获得某个球队的所有赛季的总数据
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama);
	//获得某个球员所有赛季的场均数据
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama);
	//获得某个球员所有赛季的高阶数据
	public TeamHighPO[] getTeamSeasonHigh(String teama);
}
