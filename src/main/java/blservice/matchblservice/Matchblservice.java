package blservice.matchblservice;


import java.util.Date;

import po.MatchesPO;

public interface Matchblservice 
{
	/**
	 * 得到今天的比赛
	 * @return	方法为空
	 */
    public MatchesPO[] getTodayMatches();
    /**
     * 得到某赛季所有赛后季比赛
     * @param season	赛季
     * @return	相应赛季所有赛后季比赛对象
     */
    public MatchesPO[] getPlayerOffMatches(int season);
    /**
     * 得到某赛季某球员所有常规赛比赛
     * @param season		赛季
     * @param playername	球员名称
     * @return	相应赛季相应球员所有常规赛比赛数据
     */
	public MatchesPO[] getRegularPlayerMatches(int season, String playername);
	/**
	 * 得到某赛季某球员所有季后赛比赛
	 * @param season		赛季
	 * @param playername	球员名称
	 * @return	相应赛季相应球员所有季后赛比赛数据
	 */
	public MatchesPO[] getPlayerOffPlayerMatches(int season, String playername);
	/**
	 * 需要相应赛季相应球队所有常规赛比赛数据
	 * @param season	赛季
	 * @param teamname	球队名称
	 * @return	相应赛季相应球队所有常规赛比赛数据
	 */
	public MatchesPO[] getRegularTeamMatches(int season, String teamname);
	/**
	 * 得到相应赛季相应球队所有赛后季比赛数据
	 * @param season	赛季
	 * @param teamname	球队名称
	 * @return 相应赛季相应球队所有赛后季比赛数据
	 */
	public MatchesPO[] getPlayerOffTeamMatches(int season, String teamname);
    /**
     * 得到相应日期的所有比赛
     * @param date	日期
     * @return 相应日期的所有比赛
     */
	public MatchesPO[] getTimeMatches(Date date);
    /**
     * 得到球员某赛季最近5场常规赛比赛
     * @param season	赛季
     * @param name		球员名称
     * @return	球员该赛季最近5场常规赛比赛对象
     */
    public MatchesPO[] getRegularPlayerMatchesn(int season, String name);
	/**
	 * 得到球队某赛季最近5场常规赛比赛
	 * @param season	赛季
	 * @param teamName	球队名称
	 * @return 球队该赛季最近5场常规赛比赛对象
	 */
    public MatchesPO[] getRegularTeamMatchesn(int season, String teamName);
	/**
	 * 得到球员某赛季最近5场季后赛比赛
	 * @param season	赛季
	 * @param name		球员名称
	 * @return 球员该赛季最近5场季后赛比赛对象
	 */
    public MatchesPO[] getPlayerOffPlayerMatchesn(int season, String name);
	/**
	 * 得到球队某赛季最近5场季后赛比赛
	 * @param season	赛季
	 * @param teamName	球队名称
	 * @return	球队该赛季最近5场季后赛比赛对象
	 */
    public MatchesPO[] getPlayerOffTeamMatchesn(int season, String teamName);
}
