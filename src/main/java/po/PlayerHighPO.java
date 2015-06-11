package po;

import java.io.Serializable;

public class PlayerHighPO implements Serializable{
	private String playerName;
	private String teamName;
	private double efficiency;// 效率
	private double GmScEfficiency;// GmSc效率值
	private double trueHitRate;// 真实命中率
	private double hitEfficiency;// 投篮效率
	private double rebEfficiency;// 篮板率
	private double offenseRebsEfficiency;// 进攻篮板率
	private double defenceRebsEfficiency;// 防守篮板率
	private double assistEfficiency;// 助攻率
	private double stealsEfficiency;// 抢断率
	private double blockEfficiency;// 盖帽率
	private double mistakeEfficiency;// 失误率
	private double useEfficiency;// 使用率
	private int season;//赛季名
	public PlayerHighPO(String playerName, String teamName, double efficiency,
			double gmScEfficiency, double trueHitRate, double hitEfficiency,
			double rebEfficiency, double offenseRebsEfficiency,
			double defenceRebsEfficiency, double assistEfficiency,
			double stealsEfficiency, double blockEfficiency,
			double mistakeEfficiency, double useEfficiency,int season) {
		super();
		this.playerName = playerName;
		this.teamName = teamName;
		this.efficiency = efficiency;
		GmScEfficiency = gmScEfficiency;
		this.trueHitRate = trueHitRate;
		this.hitEfficiency = hitEfficiency;
		this.rebEfficiency = rebEfficiency;
		this.offenseRebsEfficiency = offenseRebsEfficiency;
		this.defenceRebsEfficiency = defenceRebsEfficiency;
		this.assistEfficiency = assistEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.blockEfficiency = blockEfficiency;
		this.mistakeEfficiency = mistakeEfficiency;
		this.useEfficiency = useEfficiency;
		this.season = season;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\tname\t");
		sb.append(playerName);
		sb.append("\tteamName\t");
		sb.append(teamName);
		sb.append("\tefficiency\t");
		sb.append(efficiency);
		sb.append("\tGmscEfficiency\t");
		sb.append(GmScEfficiency);
		sb.append("trueHitRate");
		sb.append(trueHitRate);
		sb.append("\thitEfficiency\t");
		sb.append(hitEfficiency);
		sb.append("\trebEfficiency\t");
		sb.append(rebEfficiency);
		sb.append("\toffenseRebsEfficiency\t");
		sb.append(offenseRebsEfficiency);
		sb.append("\tdefenceRebsEfficiency\t");
		sb.append(defenceRebsEfficiency);
		sb.append("\tassistEfficiency\t");
		sb.append(assistEfficiency);
		sb.append("\tstealsEfficiency\t");
		sb.append(stealsEfficiency);
		sb.append("\tblockEfficiency\t");
		sb.append( blockEfficiency);
		sb.append("\tmistakeEfficiency\t");
		sb.append(mistakeEfficiency);
		sb.append("\tuseEfficiency\t");
		sb.append(useEfficiency);
		return sb.toString();
	}
	/**
	 * 获得赛季  
	 * @return  赛季
	 */
	public int getSeason()
	{
		return season;
	}
	/**
	 * 获得球员名
	 * @return  球员名
	 */
	public String getPlayerName()
	{
		return playerName;
	}
	/**
	 * 获得球队名
	 * @return   球队名
	 */
	public String getTeamName()
	{
		return teamName;
	}
	/**
	 * 获得效率值
	 * @return  效率值
	 */
	public double getEfficiency() {
		return efficiency;
	}
	/**
	 * 获得GMSC效率值
	 * @return  GMSC效率值
	 */
	public double getGmScEfficiency() {
		return GmScEfficiency;
	}
	/**
	 * 获得真实命中率
	 * @return  真实命中率
	 */ 
	public double getTrueHitRate() {
		return trueHitRate;
	}
	/**
	 * 获得投篮效率
	 * @return  投篮效率
	 */
	public double getHitEfficiency() {
		return hitEfficiency;
	}
	/**
	 * 获得篮板效率
	 * @return  篮板效率
	 */
	public double getRebEfficiency() {
		return rebEfficiency;
	}
	/**
	 * 获得进攻篮板效率
	 * @return 进攻篮板效率
	 */
	public double getOffenseRebsEfficiency() {
		return offenseRebsEfficiency;
	}
	/**
	 * 获得防守篮板效率
	 * @return  防守篮板效率
	 */
	public double getDefenceRebsEfficiency() {
		return defenceRebsEfficiency;
	}
	/**
	 * 获得助攻率
	 * @return  助攻率
	 */
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	/**
	 * 获得抢断率
	 * @return  抢断率
	 */
	public double getStealsEfficiency() {
		return stealsEfficiency;
	}
	/**
	 * 获得盖帽率
	 * @return  盖帽率
	 */
	public double getBlockEfficiency() {
		return blockEfficiency;
	}
	/**
	 * 获得失误率
	 * @return  失误率
	 */
	public double getMistakeEfficiency() {
		return mistakeEfficiency;
	}
	/**
	 * 获得使用率
	 * @return  使用率
	 */
	public double getUseEfficiency() {
		return useEfficiency;
	}
}
