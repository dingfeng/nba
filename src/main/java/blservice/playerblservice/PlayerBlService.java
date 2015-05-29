package blservice.playerblservice;


import java.awt.Image;
import java.util.Iterator;

import bl.matchbl.Match;
import po.MatchPlayerPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;

public interface PlayerBlService {
	//排序球员 场均数据
	public PlayerMatchVO[] sortAvePlayers(PlayerSortBy playerSortBy, SortType sortType);
	//排序球员 赛季数据
	public PlayerMatchVO[] sortTotalPlayers(PlayerSortBy playerSortBy, SortType sortType);
	//筛选球员 场均数据
	public PlayerMatchVO[] screenAvePlayers(String playerPosition, Area playerArea, PlayerSortBy sortBy);
	//筛选球员 赛季数据
	public PlayerMatchVO[] screenTotalPlayers(String playerPosition, Area playerArea, PlayerSortBy sortBy);
	//获得当天热点球员
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby);
	//获得赛季热点球员
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby);
	//获得进步最快球员 5名
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby);
	//查找模糊
	public Iterator<String> fuzzilyFind(String info);
	//查找球员 并获得球员基本信息
    public PlayerPO findPlayer(String info);
    //查找球员场均数据
    public PlayerMatchVO findPlayerMatchAve(String playername);
    //查找球员赛季数据
    public PlayerMatchVO findPlayerTotal(String playername);
    //获得所有球员姓名
    public String[] getAllPlayerNames();
    //获得以字母为开头的球员场均数据
    public PlayerMatchVO[] getAvePlayers(String start);
    //获得首字母为start的球员的所有数据
    public PlayerMatchVO[] getTotalPlayers(String start);
    //获得所有球员场均数据
    public PlayerMatchVO[] getAvePlayers();
    //获得所有球员所有数据
    public PlayerMatchVO[] getTotalPlayers();
    //根据球员的名字得到球员的头像和全身图片
    public Image[] getPlayerImage(String name);
}
