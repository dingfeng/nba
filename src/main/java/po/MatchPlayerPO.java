package po;

import java.io.Serializable;
public class MatchPlayerPO implements Comparable<MatchPlayerPO>, Serializable
{
	private String name;// 球员名称
	private String location;//位置
	private double time;// 在场时间
	private int hitNo; // 投篮命中数
	private int handNo; // 投篮出手次数
	private int threeHitNo; // 三分命中数
	private int threeHandNo; // 三分出手数
	private int penaltyHitNo; // 罚球命中数
	private int penaltyHandNo; // 罚球出手数
	private int offenseRebs; // 进攻篮板数
	private int defenceRebs; // 防守篮板数
	private int rebs; // 篮板数
	private int help;//总篮板数
	private int stealsNo;// 抢断数
	private int blockNo;// 盖帽数
	private int mistakesNo;// 失误数
	private int foulsNo;// 犯规数
	private int points;// 得分
	private String teamnameAbridge; //球队缩写名
	private double data;//热点球队排序的依据
	private boolean first = false;//是否为首发
	private String date; //日期
	private String vsTeam; //对手
	int matchId = -1;
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(matchId+" ");
		sb.append(name + " ");// 球员名称
		sb.append(location+" ");//位置
		sb.append(time+" ");// 在场时间
		sb.append(hitNo+" "); // 投篮命中数
		sb.append(handNo+" "); // 投篮出手次数
		sb.append(threeHitNo+" "); // 三分命中数
		sb.append(threeHandNo+" "); // 三分出手数
		sb.append(penaltyHitNo+" "); // 罚球命中数
		sb.append(penaltyHandNo+" "); // 罚球出手数
		sb.append(offenseRebs+" "); // 进攻篮板数
		sb.append(defenceRebs+" "); // 防守篮板数
		sb.append(rebs+" "); // 篮板数
		sb.append(help+" ");//总篮板数
		sb.append(stealsNo+" ");// 抢断数
		sb.append(blockNo+" ");// 盖帽数
		sb.append(mistakesNo+" ");// 失误数
		sb.append(foulsNo+" ");// 犯规数
		sb.append(points+" ");// 得分
		sb.append(date+" "); //日期
		sb.append(vsTeam+" ");//对手
		sb.append(teamnameAbridge +" \n");
		return sb.toString();
	}
	public MatchPlayerPO(String name, String location, double time, int hitNo,
			int handNo, int threeHitNo, int threeHandNo, int penaltyHitNo,
			int penaltyHandNo, int offenseRebs, int defenceRebs, int rebs,
			int help, int stealsNo, int blockNo, int mistakesNo,
			int foulsNo)
	{
		super();
		this.name = name;
		this.location = location;
		this.time = time;
		this.hitNo = hitNo;
		this.handNo = handNo;
		this.threeHitNo = threeHitNo;
		this.threeHandNo = threeHandNo;
		this.penaltyHitNo = penaltyHitNo;
		this.penaltyHandNo = penaltyHandNo;
		this.offenseRebs = offenseRebs;
		this.defenceRebs = defenceRebs;
		this.rebs = rebs;
		this.help = help;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = (hitNo-threeHitNo) * 2+threeHitNo * 3 +penaltyHitNo;
	}
	/**
	 * 获得比赛编号
	 * @return 比赛编号
	 */
	public int getMatchId()
	{
		return matchId;
	}
	/**
	 * 设置比赛编号
	 * @param matchId  比赛编号
	 */
	public void setMatchId(int matchId)
	{
		this.matchId = matchId;
	}
	/**
	 * 获得日期
	 * @return  日期
	 */ 
	public String getDate()
	{
		return date;
	}
	/**
	 * 获得对手
	 * @return  对手
	 */
	public String getVSTeam()
	{
		return vsTeam;
	}
	/**
	 * 获得日期
	 * @param date  日期
	 */
	public void setDate(String date)
	{
		this.date = date;
	}
	/**
	 * 获得对手
	 * @param vsTeam 对手
	 */
	public void setVSTeam(String vsTeam)
	{
		this.vsTeam = vsTeam;
	}
	/**
	 * 获得是否为首发
	 * @return 是否为首发
	 */
	public boolean isFirst()
	{
		return first;
	}
	/**
	 * 设置为是否为首发<br/>
	 */
	public void setFirst()
	{
		first = true;
	}
	/**
	 * 设置得分
	 * @param point 得分
	 * @return    自身对象
	 */
   public MatchPlayerPO setPoints(int point)
   {
	   this.points = point;
	   return this;
   }
   /**
    * 获得名字
    * @return  名字
    */
	public String getName() {
		return name;
	}
	/**
	 * 获得位置
	 * @return 位置
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * 获得在场时间
	 * @return 在场时间
	 */
	public double getTime() {
		return time;
	}
	/**
	 * 获得投篮命中数
	 * @return  投篮命中数
	 */
	public int getHitNo() {
		return hitNo;
	}
	/**
	 * 获得投篮出手数
	 * @return  投篮出手数
	 */
	public int getHandNo() {
		return handNo;
	}
	/**
	 * 获得三分球命中数
	 * @return  三分球命中数
	 */
	public int getThreeHitNo() {
		return threeHitNo;
	}
	/**
	 * 获得三分球出手数
	 * @return 三分球出手数
	 */
	public int getThreeHandNo() {
		return threeHandNo;
	}
	/**
	 * 获得罚球命中数
	 * @return  罚球命中数
	 */
	public int getPenaltyHitNo() {
		return penaltyHitNo;
	}
	/**
	 * 获得罚球出手数
	 * @return 罚球出手数
	 */
	public int getPenaltyHandNo() {
		return penaltyHandNo;
	}
	/**
	 * 获得进攻篮板数
	 * @return   进攻篮板数
	 */
	public int getOffenseRebs() {
		return offenseRebs;
	}
	/**
	 * 获得防守篮板数
	 * @return 防守篮板数
	 */
	public int getDefenceRebs() {
		return defenceRebs;
	}
	/**
	 * 获得篮板数
	 * @return  篮板数
	 */
	public int getRebs() {
		return rebs;
	}
	/**
	 * 获得助攻数
	 * @return  助攻数
	 */
	public int getHelp() {
		return help;
	}
	/**
	 * 获得抢断数
	 * @return  抢断数
	 */
	public int getStealsNo() {
		return stealsNo;
	}
	/**
	 * 获得盖帽数
	 * @return  盖帽数
	 */
	public int getBlockNo() {
		return blockNo;
	}
	/**
	 * 获得失误数
	 * @return  失误数
	 */
	public int getMistakesNo() {
		return mistakesNo;
	}
	/**
	 * 获得犯规数
	 * @return  犯规数
	 */
	public int getFoulsNo() {
		return foulsNo;
	}
	/**
	 * 获得得分
	 * @return  得分
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * 获得投篮命中率
	 * @return 投篮命中率
	 */
	public double getHitRate()
	{
		if (handNo == 0)
			return 0;
		return 1.0 * hitNo / handNo;
	}
	/**
	 * 获得三分命中率
	 * @return 三分球命中率
	 */
	public double getThreeHitRate()
	{
		if (threeHandNo == 0)
			return 0;
		return 1.0 * threeHitNo / threeHandNo ;
	}
	/**
	 * 获得罚球命中率
	 * @return 罚球命中率
	 */
	public double getPenaltyHitRate()
	{
		if (penaltyHandNo == 0) return 0;
		return 1.0 * penaltyHandNo / penaltyHitNo;
	}
	/**
	 * 设置球队缩写名
	 * @param teamnameAbridge 球队缩写名
	 */
	public  void setTeamnameAbridge(String teamnameAbridge)
	{
		this.teamnameAbridge   = teamnameAbridge;
	}
	/**
	 * 获得球队缩写名
	 * @return  球队缩写名
	 */
	public String getTeamnameAbridge()
	{
		return teamnameAbridge;
	}
	/**
	 * 设置热点数据
	 * @param data 热点数据
	 */
	public void setHotData(double data)
	{
		this.data = data;
	}
	/**
	 * 获得热点数据
	 * @return  热点数据
	 */
	public double getHotData()
	{
		return this.data;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(MatchPlayerPO o) {
		double yourdata  = o.getHotData();
		if (yourdata > data)
			return 1;
		else if ( yourdata < data)
		{
			return -1;
		}
		else return 0;
	}
}
