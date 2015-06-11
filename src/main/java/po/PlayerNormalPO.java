package po;

import java.io.Serializable;

public class PlayerNormalPO implements Serializable{
	private String name;// 球员名称
	private String team;// 所属球队
	private int matchNo;// 参赛场数
	private double firstServiceNo;// 先发场数
	private double rebs;// 篮板数
	private double assistNo;// 助攻数
	private double time;// 在场时间
	private double offendRebsNo;// 进攻篮板数
	private double defenceRebsNo;// 防守篮板数
	private double stealsNo;// 抢断数
	private double blockNo;// 盖帽数
	private double mistakesNo;// 失误数
	private double foulsNo;// 犯规数
	private double points;// 得分
	private double minute;//分钟
	private int season;//赛季
	private double hitNo; // 投篮命中数
    private double handNo;//投篮出手数
	private double hitRate;// 投篮命中率
    private double penaltyHandNo;//罚球	
    private double penaltyHitNo; // 罚球命中数
    private double penaltyHitRate;// 罚球命中率
	private double threeHitNo; // 三分命中数
	private double threeHandNo; // 三分出手数
	private double threeHitRate;// 三分命中率
	
	private double twoPair;//两双
	private double points_uprate; //得分提升率
	private double rebs_uprate; //篮板提升率
	private double help_uprate; //助攻提升率
	private double scoring_rebound_assist;//得分/篮板/助攻（加权比1：1：1）
	
	public PlayerNormalPO(String name, String team, int matchNo,
			double firstServiceNo, double rebs, double assistNo, double time,
			double offendRebsNo, double defenceRebsNo, double stealsNo,
			double blockNo, double mistakesNo, double foulsNo, double points,
			double minute, double hitNo, double handNo, double hitRate,
			double penaltyHandNo, double penaltyHitNo, double penaltyHitRate,
			double threeHitNo, double threeHandNo, double threeHitRate,
			double twoPair, double points_uprate, double rebs_uprate,
			double help_uprate, double scoring_rebound_assist,int season) {
		super();
		this.name = name;
		this.team = team;
		this.matchNo = matchNo;
		this.firstServiceNo = firstServiceNo;
		this.rebs = rebs;
		this.assistNo = assistNo;
		this.time = time;
		this.offendRebsNo = offendRebsNo;
		this.defenceRebsNo = defenceRebsNo;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = points;
		this.minute = minute;
		this.hitNo = hitNo;
		this.handNo = handNo;
		this.hitRate = hitRate;
		this.penaltyHandNo = penaltyHandNo;
		this.penaltyHitNo = penaltyHitNo;
		this.penaltyHitRate = penaltyHitRate;
		this.threeHitNo = threeHitNo;
		this.threeHandNo = threeHandNo;
		this.threeHitRate = threeHitRate;
		this.twoPair = twoPair;
		this.points_uprate = points_uprate;
		this.rebs_uprate = rebs_uprate;
		this.help_uprate = help_uprate;
		this.scoring_rebound_assist = scoring_rebound_assist;
		this.season = season;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\tname\t");
		sb.append(name);
		sb.append("\tteam\t");
		sb.append(team);
		sb.append("\tmatchNo\t");
		sb.append(matchNo);
		sb.append("\tfirstServiceNo\t");
		sb.append(firstServiceNo);
		sb.append("\trebs\t");
		sb.append(rebs);
		sb.append("\tassistNo\t");
		sb.append(assistNo);
		sb.append("\ttime\t");
		sb.append(time);
		sb.append("\toffendRebsNo\t");
		sb.append(offendRebsNo);
		sb.append("\tdefenceRebsNo\t");
		sb.append(defenceRebsNo);
		sb.append("\tstealsNo\t");
		sb.append(stealsNo);
		sb.append("\tblockNo\t");
		sb.append(blockNo);
		sb.append("\tmistakesNo\t");
		sb.append(mistakesNo);
		sb.append("\tfoulsNo\t");
		sb.append(foulsNo);
		sb.append("\tpoints\t");
		sb.append(points);
		sb.append("\tminute\t");
		sb.append( minute);
		sb.append("\thitNo\t");
		sb.append(hitNo);
		sb.append("\thandNo\t");
		sb.append(handNo);
		sb.append("\thitRate\t");
		sb.append(hitRate);
		sb.append("\tpenaltyHandNo\t");
		sb.append(penaltyHandNo);
		sb.append("\tpenaltyHitNo\t");
		sb.append( penaltyHitNo);
		sb.append("\tpenaltyHitRate\t");
		sb.append( penaltyHitRate);
		sb.append("\tthreeHitNo\t");
		sb.append(threeHitNo);
		sb.append("\threeHandNo\t");
		sb.append(threeHandNo);
		sb.append("\tthreeHitRate\t");
		sb.append(threeHitRate);
		sb.append("\ttwoPair\t");
		sb.append(twoPair);
		sb.append("\tpoints_uprate\t");
		sb.append(points_uprate);
		sb.append("\trebs_uprate\t");
		sb.append(rebs_uprate);
		sb.append("\thelp_uprate\t");
		sb.append(help_uprate);
		sb.append("\tscoring_rebound_assist\t");
		sb.append(  scoring_rebound_assist);
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
	 * 获得名字
	 * @return  名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获得球队
	 * @return 球队
	 */
	public String getTeam() {
		return team;
	}
	/**
	 * 获得比赛场数
	 * @return  比赛场数
	 */
	public int getMatchNo() {
		return matchNo;
	}
	/**
	 * 获得首发次数
	 * @return  首发次数
	 */
	public double getFirstServiceNo() {
		return firstServiceNo;
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
	 * 获得在场时间
	 * @return  在场时间
	 */
	public double getTime() {
		return time;
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
	 * 获得进攻篮板数
	 * @return  进攻篮板数
	 */
	public double getOffendRebsNo() {
		return offendRebsNo;
	}
	/**
	 * 获得防守篮板数
	 * @return 防守篮板数
	 */
	public double getDefenceRebsNo() {
		return defenceRebsNo;
	}
	/**
	 * 获得抢断数
	 * @return  抢断数
	 */
	public double getStealsNo() {
		return stealsNo;
	}
	/**
	 * 获得篮板数
	 * @return  篮板数
	 */
	public double getBlockNo() {
		return blockNo;
	}
	/**
	 * 获得失误数
	 * @return  失误数
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
	 * @return 得分
	 */
	public double getPoints() {
		return points;
	}
	/**
	 * 获得两双数
	 * @return 两双数
	 */
	public double getTwoPair() {
		return twoPair;
	}
	/**
	 * 获得得分提升率
	 * @return  得分提升率
	 */
	public double getPoints_uprate() {
		return points_uprate;
	}
	/**
	 * 获得篮板提升率
	 * @return 篮板提升率
	 */
	public double getRebs_uprate() {
		return rebs_uprate;
	}
	/**
	 * 获得助攻提升率
	 * @return 助攻提升率
	 */
	public double getHelp_uprate() {
		return help_uprate;
	}
	/**
	 * 获得得分篮板助攻比（1：1：1）
	 * @return  得分篮板助攻比（1：1：1）
	 */
	public double getScoring_rebound_assist() {
		return scoring_rebound_assist;
	}
}
