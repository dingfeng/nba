package blservice.playerblservice;


import java.awt.Image;
import java.util.Iterator;

import dataservice.playerdataservice.SeasonType;
import bl.matchbl.Match;
import po.MatchPlayerPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;

public interface PlayerBlService {
	//筛选球员 场均数据
	public PlayerMatchVO[] screenAvePlayers(int season, String playerPosition, Area playerArea, PlayerSortBy sortBy);
	//筛选球员 赛季数据
	public PlayerMatchVO[] screenTotalPlayers(int season, String playerPosition, Area playerArea, PlayerSortBy sortBy);
	//获得当天热点球员
	public PlayerNormalPO[] getDayHotPlayer(String sortby);
	//获得赛季热点球员
	public PlayerNormalPO[] getSeasonHotPlayer(int season, String sortby, SeasonType type);
	//获得进步最快球员 5名
	public PlayerNormalPO[] getPromotePlayer(int season, String sortby);
	//查找模糊
	public String[] fuzzilyFind(String info);
	//查找球员 并获得球员基本信息
    public PlayerPO findPlayer(String info);
    //查找球员场均数据
    public PlayerMatchVO findPlayerMatchAve(int season, String playername);
    //查找球员赛季数据
    public PlayerMatchVO findPlayerTotal(int season, String playername);
    //获得以字母为开头的球员场均数据
    public PlayerMatchVO[] getAvePlayers(int season, String start);
    //获得首字母为start的球员的所有数据
    public PlayerMatchVO[] getTotalPlayers(int season, String start);
    //获得所有球员场均数据
    public PlayerMatchVO[] getAvePlayers(int season);
    //获得所有球员所有数据
    public PlayerMatchVO[] getTotalPlayers(int season);
    //根据球员的名字得到球员的头像和全身图片
    public Image getPlayerImage(String name);
}
