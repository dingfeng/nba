package po;

import java.io.Serializable;

public class TeamHighPO implements Serializable{
	private String name;
	private double offenseRound; // 进攻回合
	private double offenseEfficiency;// 进攻效率
	private double defenceEfficiency;// 防守效率
	private double orebsEfficiency;// 进攻篮板效率
	private double drebsEfficiency;// 防守篮板效率
	private double stealsEfficiency;// 抢断效率
	private double assistEfficiency;// 助攻率
	private int season;//赛季名
	public TeamHighPO(String name,double offenseRound, double offenseEfficiency,
			double defenceEfficiency, double orebsEfficiency,
			double drebsEfficiency, double stealsEfficiency,
			double assistEfficiency,int season) {
		super();
		this.name = name;
		this.offenseRound = offenseRound;
		this.offenseEfficiency = offenseEfficiency;
		this.defenceEfficiency = defenceEfficiency;
		this.orebsEfficiency = orebsEfficiency;
		this.drebsEfficiency = drebsEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.assistEfficiency = assistEfficiency;
		this.season = season;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("name\t");
		sb.append(name);
		sb.append("\toffenseRound\t");
		sb.append(offenseRound);
		sb.append("\tdefenceEfficiency\t");
		sb.append(defenceEfficiency);
		sb.append("\toffenseEfficiency\t");
		sb.append(offenseEfficiency);
		sb.append("\torebsEfficiency\t");
		sb.append(orebsEfficiency);
		sb.append("\tdrebsEfficiency\t");
		sb.append(drebsEfficiency);
		sb.append("\tstealsEfficieny\t");
		sb.append(stealsEfficiency);
		sb.append("\tassistEfficiency\t");
		sb.append(assistEfficiency);
		sb.append("\n");
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
	 * 获得球队名
	 * @return  球队名
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * 获得进攻回合数
	 * @return  进攻回合数
	 */
	public double getOffenseRound() {
		return offenseRound;
	}
	/**
	 * 获得进攻效率
	 * @return  进攻效率
	 */
	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}
	/**
	 * 获得防守效率
	 * @return  防守效率
	 */
	public double getDefenceEfficiency() {
		return defenceEfficiency;
	}
	/**
	 * 获得篮板效率
	 * @return  篮板效率
	 */
	public double getOrebsEfficiency() {
		return orebsEfficiency;
	}
	/**
	 * 获得防守篮板效率
	 * @return 防守篮板效率
	 */
	public double getDrebsEfficiency() {
		return drebsEfficiency;
	}
	/**
	 * 获得抢断率
	 * @return 抢断率
	 */
	public double getStealsEfficiency() {
		return stealsEfficiency;
	}
	/**
	 * 获得助攻率
	 * @return  助攻率
	 */
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	
}
