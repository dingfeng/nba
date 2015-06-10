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
	public int getSeason()
	{
		return season;
	}
	public String getName()
	{
		return name;
	}
	public double getOffenseRound() {
		return offenseRound;
	}
	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}
	public double getDefenceEfficiency() {
		return defenceEfficiency;
	}
	public double getOrebsEfficiency() {
		return orebsEfficiency;
	}
	public double getDrebsEfficiency() {
		return drebsEfficiency;
	}
	public double getStealsEfficiency() {
		return stealsEfficiency;
	}
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	
}
