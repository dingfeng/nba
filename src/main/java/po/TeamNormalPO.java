package po;

import java.io.Serializable;

public class TeamNormalPO implements Serializable{
	private String name; // 球队名称
	private int matchNo; // 比赛场数
	private double hitNo; // 投篮命中数
	private double handNo; // 投篮出手次数
	private double threeHitNo; // 三分命中数
	private double threeHandNo; // 三分出手数
	private double penaltyHitNo; // 罚球命中数
	private double penaltyHandNo; // 罚球出手数
	private double offenseRebs; // 进攻篮板数
	private double defenceRebs; // 防守篮板数
	private double rebs; // 篮板数
	private double assistNo; // 助攻数
	private double stealsNo; // 抢断数
	private double blockNo; // 盖帽数
	private double mistakesNo; // 失误数
	private double foulsNo; // 犯规数
	private double points; // 比赛得分
	private double hitRate; // 投篮命中率
	private double threeHitRate;// 三分命中率
	private double penaltyHitRate;// 罚球命中率
	private double winRate; // 胜率
	private int season;//赛季名
	public TeamNormalPO(String name, int matchNo, double hitNo, double handNo,
			double threeHitNo, double threeHandNo, double penaltyHitNo,
			double penaltyHandNo, double offenseRebs, double defenceRebs,
			double rebs, double assistNo, double stealsNo, double blockNo,
			double mistakesNo, double foulsNo, double points, double hitRate,
			double threeHitRate, double penaltyHitRate, double winRate,int season) {
		super();
		this.name = name;
		this.matchNo = matchNo;
		this.hitNo = hitNo;
		this.handNo = handNo;
		this.threeHitNo = threeHitNo;
		this.threeHandNo = threeHandNo;
		this.penaltyHitNo = penaltyHitNo;
		this.penaltyHandNo = penaltyHandNo;
		this.offenseRebs = offenseRebs;
		this.defenceRebs = defenceRebs;
		this.rebs = rebs;
		this.assistNo = assistNo;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = points;
		this.hitRate = hitRate;
		this.threeHitRate = threeHitRate;
		this.penaltyHitRate = penaltyHitRate;
		this.winRate = winRate;
		this.season = season;
	}
	public String toString()
	{
	 StringBuilder sb = new StringBuilder();
	 sb.append("\tname\t");
     sb.append(name);
     sb.append("\tmatchNo\t");
	 sb.append(matchNo);
	 sb.append("\thitNo\t");
	 sb.append(hitNo);
	 sb.append("\thandNo\t");
	 sb.append(handNo);
	 sb.append("\tthreeHitNo\t");
	 sb.append(threeHitNo);
	 sb.append("\tthreeHandNo\t");
	 sb.append( threeHandNo);
	 sb.append("\tpenalyHitNo\t");
	 sb.append(penaltyHitNo);
	 sb.append("\tpenaltyHandNo\t");
	 sb.append( penaltyHandNo);
	 sb.append("\toffenseRebs\t");
	 sb.append(offenseRebs);
	 sb.append("\tdefenceRebs\t");
	 sb.append(defenceRebs);
	 sb.append("\trebs\t");
	 sb.append(rebs);
	 sb.append("\tassistNo\t");
	 sb.append(assistNo);
	 sb.append("\tstealsNo\t");
	 sb.append(stealsNo);
	 sb.append("\tblockNo\t");
	 sb.append(blockNo);
	 sb.append("\tmistakesNo");
	 sb.append(mistakesNo);
	 sb.append("\tfoulsNo\t");
	 sb.append(foulsNo);
	 sb.append("\tpoints\t");
	 sb.append(points);
	 sb.append("\thitRate\t");
	 sb.append(hitRate);
	 sb.append("\tthreeHitRate\t");
	 sb.append(threeHitRate);
	 sb.append("penaltyHitRate\t");
	 sb.append(penaltyHitRate);
	 sb.append("\twinRate\t");
	 sb.append(winRate);
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
	public String getName() {
		return name;
	}
	/**
	 * 获得比赛场数
	 * @return  比赛场数
	 */
	public int getMatchNo() {
		return matchNo;
	}
	/**
	 * 获得投篮命中数
	 * @return  投篮命中率
	 */
	public double getHitNo() {
		return hitNo;
	}
	/**
	 * 获得投篮出手数
	 * @return  投篮出手数
	 */
	public double getHandNo() {
		return handNo;
	}
	/**
	 * 获得三分球命中数
	 * @return 三分球命中数
	 */
	public double getThreeHitNo() {
		return threeHitNo;
	}
	/**
	 * 获得三分球出手数
	 * @return  三分球出手数
	 */
	public double getThreeHandNo() {
		return threeHandNo;
	}
	/**
	 * 获得罚球命中数
	 * @return 罚球命中数
	 */
	public double getPenaltyHitNo() {
		return penaltyHitNo;
	}
	/**
	 * 获得罚球出手数
	 * @return  罚球出手数
	 */
	public double getPenaltyHandNo() {
		return penaltyHandNo;
	}
	/**
	 * 获得进攻篮板数
	 * @return  进攻篮板数
	 */
	public double getOffenseRebs() {
		return offenseRebs;
	}
	/**
	 * 获得防守篮板数
	 * @return 防守篮板数
	 */
	public double getDefenceRebs() {
		return defenceRebs;
	}
	/**
	 * 获得篮板数
	 * @return  篮板数
	 */
	public double getRebs() {
		return rebs;
	}
	/**
	 * 获得助攻数
	 * @return  助攻数
	 */
	public double getAssistNo() {
		return assistNo;
	}
	/**
	 * 获得抢断数
	 * @return  抢断数
	 */
	public double getStealsNo() {
		return stealsNo;
	}
	/**
	 * 获得盖帽数
	 * @return  盖帽数
	 */
	public double getBlockNo() {
		return blockNo;
	}
	/**
	 * 获得失误数
	 * @return 失误数
	 */
	public double getMistakesNo() {
		return mistakesNo;
	}
	/**
	 * 获得犯规数
	 * @return  犯规数
	 */
	public double getFoulsNo() {
		return foulsNo;
	}
	/**
	 * 获得得分
	 * @return  得分
	 */
	public double getPoints() {
		return points;
	}
	/**
	 * 获得投篮命中率
	 * @return 投篮命中率
	 */
	public double getHitRate() {
		return hitRate;
	}
	/**
	 * 获得三分球命中率
	 * @return  三分球命中率
	 */
	public double getThreeHitRate() {
		return threeHitRate;
	}
	/**
	 * 获得罚球命中率
	 * @return  罚球命中率
	 */
	public double getPenaltyHitRate() {
		return penaltyHitRate;
	}
	/**
	 * 获得胜率
	 * @return 胜率
	 */
	public double getWinRate() {
		return winRate;
	}
}
