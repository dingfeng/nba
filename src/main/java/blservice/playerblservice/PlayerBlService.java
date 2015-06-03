package blservice.playerblservice;


import java.awt.Image;

import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.HotPlayerTeam;

public interface PlayerBlService {
	//筛选球员 场均数据 低阶
	public PlayerNormalPO[] screenNormalAvePlayers(int season, String playerPosition, Area playerArea);
	//筛选球员 赛季数据 低阶
	public PlayerNormalPO[] screenNormalTotalPlayers(int season, String playerPosition, Area playerArea);
	//筛选球员 数据 高阶
	public PlayerHighPO[] screenHighPlayers(int season, String playerPosition, Area playerArea);
	//获得当天热点球员
	public HotPlayerTeam[] getDayHotPlayer(String sortby);
	//获得赛季热点球员
	public HotPlayerTeam[] getSeasonHotPlayer(int season, String sortby, SeasonType type);
	//获得进步最快球员 5名
	public HotPlayerTeam[] getPromotePlayer(int season, String sortby);
	//查找模糊
	public String[] fuzzilyFind(String info);
	//查找球员 并获得球员基本信息
    public HPlayerPO findPlayer(String info);
    //查找球员场均数据 低阶
    public PlayerNormalPO findPlayerMatchAve(int season, String playername,SeasonType type);
    //查找球员赛季数据 低阶
    public PlayerNormalPO findPlayerTotal(int season, String playername, SeasonType type);
    //查找球员高阶数据
    public PlayerHighPO findPlayerHigh(int season, String playername, SeasonType type);
    //获得以字母为开头的球员场均数据
    public HPlayerPO[] getPlayersWithStart(int season, String start);
    //获得所有球员场均数据 低阶
    public PlayerNormalPO[] getAveAllPlayers(int season, SeasonType type);
    //获得所有球员所有数据 低阶
    public PlayerNormalPO[] getTotalAllPlayers(int season, SeasonType type);
    //获得所有球员高阶数据
    public PlayerHighPO[] getHighAllPlayers(int season, SeasonType type);
    //获得球员的所有赛季总数据
  	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName, SeasonType type);
  	//获得球员的赛季场均数据
  	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName, SeasonType type);
  	//获得球员的所有的赛季高阶数据
  	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type);
    //得到所有球员数据
  	public PlayerPO[] getAllActivePlayerData();
    //根据球员的名字得到球员的头像
    public Image getPlayerImage(String name);
    //得到球员基本信息的雷达图
    public Image getRadarImage(int season, String name, SeasonType type);
    //得到两个球员对比的柱状图
    public Image getCompareImage(int season, String name1, String name2, SeasonType type);
}
