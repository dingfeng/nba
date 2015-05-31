package dataservice.playerdataservice;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;

public interface PlayerDataService {
	//得到所有球员数据
	public PlayerPO[] getAllActivePlayerData();
	//获得球员某个赛季的高阶数据
	public PlayerHighPO getPlayerHigh(int season,String playerName,SeasonType type);
	//高阶数据排序
	public PlayerHighPO[] sortPlayerHighn(int season, String sort,int n, SeasonType type);
	//获得某个球员赛季总基础数据
	public PlayerNormalPO getPlayerNormalTotal(int season, String playerName, SeasonType type);
	//排序球员赛季总数据
	public PlayerNormalPO[] sortPlayerNormalTotal(int season, String sort, int n, SeasonType type);
  	//获得某个球员赛季场均数据
	public PlayerNormalPO getPlayerNormalAve(int season, String playerName, SeasonType type);
	//排序球员的场均基础数据
	public PlayerNormalPO[] sortPlayerNormalAven(int season, String sort, int n, SeasonType type);
	//获得球员的所有赛季总数据
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName, SeasonType type);
	//获得球员的赛季场均数据
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName, SeasonType type);
	//获得球员的所有的赛季高阶数据
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type);
	//通过首字符获得
	public HPlayerPO[] getHPlayerByIni(String ini);
	//模糊查找
	public String[] fuzzilySearch(String info);
	//现役球员名查找
	public HPlayerPO findPlayer(String playerName);
	//获得某个赛季的所有球员
	public PlayerNormalPO[] getSeasonPlayerNormalAve(int season,SeasonType type);
	public PlayerNormalPO[] getSeasonPlayerNormalTotal(int season, SeasonType type);
	public PlayerHighPO[] getSeasonPlayerHigh(int season, SeasonType type);
	//获得球队所有球员
	public PlayerPO[] getPlayersOfTeam(String team);
	//球员筛选
	public PlayerPO[] screenPlayer(String sort,String match_area,String postion,int n);
//	public PlayerNormalPO[] sortPlayerNormalTotalHis(String sort, int n);
//	public PlayerNormalPO sortPlayerNormalAveHis(String sort, int n);
}

