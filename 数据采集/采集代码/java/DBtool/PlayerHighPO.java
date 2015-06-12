package DBtool;

public class PlayerHighPO {
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
	public PlayerHighPO(String playerName, String teamName, double efficiency,
			double gmScEfficiency, double trueHitRate, double hitEfficiency,
			double rebEfficiency, double offenseRebsEfficiency,
			double defenceRebsEfficiency, double assistEfficiency,
			double stealsEfficiency, double blockEfficiency,
			double mistakeEfficiency, double useEfficiency) {
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
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("\tplayerName\t");
		sb.append(playerName);
		sb.append("\tteamName\t");
		sb.append(teamName);
		sb.append("\tefficiency\t");
		sb.append(efficiency);
		sb.append("\tgmscEfficiency\t");
		sb.append(GmScEfficiency);
		sb.append("\ttrueHitRate\t");
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
		sb.append("\tstealsEfficienct\t");
		sb.append(stealsEfficiency);
		sb.append("\tblockEfficiency\t");
		sb.append(blockEfficiency);
		sb.append("\tmistakeEfficiency\t");
		sb.append( mistakeEfficiency);
		sb.append("\tuseEfficiency\t");
		sb.append(useEfficiency);
		return sb.toString();
	}
	public String getPlayerName()
	{
		return playerName;
	}
	public String getTeamName()
	{
		return teamName;
	}
	public double getEfficiency() {
		return efficiency;
	}
	public double getGmScEfficiency() {
		return GmScEfficiency;
	}
	public double getTrueHitRate() {
		return trueHitRate;
	}
	public double getHitEfficiency() {
		return hitEfficiency;
	}
	public double getRebEfficiency() {
		return rebEfficiency;
	}
	public double getOffenseRebsEfficiency() {
		return offenseRebsEfficiency;
	}
	public double getDefenceRebsEfficiency() {
		return defenceRebsEfficiency;
	}
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	public double getStealsEfficiency() {
		return stealsEfficiency;
	}
	public double getBlockEfficiency() {
		return blockEfficiency;
	}
	public double getMistakeEfficiency() {
		return mistakeEfficiency;
	}
	public double getUseEfficiency() {
		return useEfficiency;
	}
}
