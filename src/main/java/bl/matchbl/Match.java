package bl.matchbl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class Match {
	private MatchDataService match_data;
	private TIntObjectMap<TeamQueue> team_map;
	private TIntObjectMap<PlayerQueue> player_map;
	private final int match_num = 90;
	private MatchesPO[] matches;
	private int season;

	public Match(int season) throws Exception {
		this.season = season;
		NBADataFactory factory = DataFactory.instance();
		match_data = factory.getMatchData();
		team_map = new TIntObjectHashMap<TeamQueue>();
		player_map = new TIntObjectHashMap<PlayerQueue>();
		update();
	}

	// 处理一个比赛
	private void dealWithOneMatch(MatchesPO match) {
		MatchTeamPO team1 = match.getTeam1();
		MatchTeamPO team2 = match.getTeam2();
		String teamName1 = team1.getName();
		String teamName2 = team2.getName();
		if (!team_map.containsKey(teamName1.hashCode())) {
			team_map.put(teamName1.hashCode(), new TeamQueue(match_num,
					teamName1));
		}
		if (!team_map.containsKey(teamName2.hashCode())) {
			team_map.put(teamName2.hashCode(), new TeamQueue(match_num,
					teamName2));
		}
		AbstractQueue team1_q = team_map.get(team1.getName().hashCode());
		AbstractQueue team2_q = team_map.get(team2.getName().hashCode());
		team1_q.enQueue(match);
		team2_q.enQueue(match);

		MatchPlayerPO[] player_team1 = team1.getPlayers();
		MatchPlayerPO[] player_team2 = team2.getPlayers();
		int key = -1;
		PlayerQueue q = null;
		int rebs1 = 0, totalHit1 = 0, teamHand1 = 0, teamPenalty1 = 0, teamMistakes1 = 0, rebs11 = 0;
		int penaltyHandNo1 = 0, offenseRebs1 = 0, defenceRebs1 = 0, hitNo1 = 0, threeHand1 = 0;
		int rebs2 = 0, totalHit2 = 0, teamHand2 = 0, teamPenalty2 = 0, teamMistakes2 = 0, rebs22 = 0;
		int penaltyHandNo2 = 0, offenseRebs2 = 0, defenceRebs2 = 0, hitNo2 = 0, threeHand2 = 0;

		for (MatchPlayerPO p : player_team1) {
			threeHand1 += p.getThreeHandNo();
			rebs1 += p.getRebs();
			totalHit1 += p.getHitNo();
			teamHand1 += p.getHandNo();
			teamPenalty1 += p.getPenaltyHandNo();
			teamMistakes1 += p.getMistakesNo();
			rebs11 += p.getRebs();
			penaltyHandNo1 += p.getPenaltyHandNo();
			offenseRebs1 += p.getOffenseRebs();
			defenceRebs1 += p.getDefenceRebs();
			hitNo1 += p.getHitNo();
		}

		for (MatchPlayerPO p : player_team2) {

			threeHand2 += p.getThreeHandNo();
			rebs2 += p.getRebs();
			totalHit2 += p.getHitNo();
			teamHand2 += p.getHandNo();
			teamPenalty2 += p.getPenaltyHandNo();
			teamMistakes2 += p.getMistakesNo();
			rebs22 += p.getRebs();
			penaltyHandNo2 += p.getPenaltyHandNo();
			offenseRebs2 += p.getOffenseRebs();
			defenceRebs2 += p.getDefenceRebs();
			hitNo2 += p.getHitNo();
		}
		double attackNO1 = teamHand1
				+ 0.4
				* penaltyHandNo1
				- 1.07
				* (1.0 * offenseRebs1 / (offenseRebs1 + defenceRebs2) * (teamHand1 - hitNo1))
				+ 1.07 * teamMistakes1;

		double attackNO2 = teamHand2
				+ 0.4
				* penaltyHandNo2
				- 1.07
				* (1.0 * offenseRebs2 / (offenseRebs2 + defenceRebs1) * (teamHand2 - hitNo2))
				+ 1.07 * teamMistakes2;

		int firstServiceNO = 0;
		int count = 0;
		for (MatchPlayerPO p : player_team1) {
			++count;
			firstServiceNO = 0;
			key = p.getName().hashCode();

			if (!player_map.containsKey(key)) {

				q = new PlayerQueue(match_num, p.getName());
				player_map.put(key, q);
			} else {
				q = player_map.get(p.getName().hashCode());
			}
			if (count <= 5)
				firstServiceNO = 1;

			q.enqueue(match, p, team1.getName(), teamHand2 - threeHand2,
					team1.getTime(), rebs2, totalHit1, attackNO2, teamHand1,
					teamPenalty1, teamMistakes1, firstServiceNO, rebs1,
					team1.getName(), team2.getName(), match.getDate(),
					offenseRebs2, defenceRebs2, defenceRebs1, offenseRebs1);
		}

		count = 0;
		for (MatchPlayerPO p : player_team2) {
			++count;
			firstServiceNO = 0;
			key = p.getName().hashCode();
			if (!player_map.containsKey(key)) {
				q = new PlayerQueue(match_num, p.getName());
				player_map.put(key, q);
			} else {
				q = player_map.get(p.getName().hashCode());
			}
			if (count <= 5)
				firstServiceNO = 1;

			q.enqueue(match, p, team2.getName(), teamHand1 - threeHand1,
					team2.getTime(), rebs1, totalHit2, attackNO1, teamHand2,
					teamPenalty2, teamMistakes2, firstServiceNO, rebs2,
					team1.getName(), team2.getName(), match.getDate(),
					offenseRebs1, defenceRebs1, defenceRebs2, offenseRebs2);

		}
	}

	// MatchPlayerPO player , String teamname,double teamTotalTime
	// ,int yourRebs, int totalHit, double yourAttackNO,
	// int teamHand, int teamPenalty, int teamMistakes, int firstServiceNo, int
	// myRebs,String team1, String team2,String date

	// 更新
	public void update() {
		matches = match_data.getRegularSeasonMatches(season);
		if (matches != null) {
			for (MatchesPO m : matches) {
				dealWithOneMatch(m);
			}

		}
		// }
	}

	// 获得球队的所有比赛
	public TIntObjectMap<TeamQueue> getTeam_map() {
		return team_map;
	}
	
	//获得相应球队这个赛季所有比赛数据
	public TeamQueue getTeamData(String name){
		return team_map.get(name.hashCode());
	}

	// 获得球员的所有比赛
	public TIntObjectMap<PlayerQueue> getPlayer_map() {
		return player_map;
	}
	
	//获得相应球员这个赛季所有比赛数据
	public PlayerQueue getPlayerData(String name){
		return player_map.get(name.hashCode());
	}

	public int matchNum() {
		TeamQueue[] teams = new TeamQueue[team_map.size()];
		team_map.values(teams);
		int result = 0;
		for (int i = 0; i < teams.length; i++) {
			if (teams[i].getAllMatches() != null)
				result += teams[i].getAllMatches().length;
		}
		return result / 2;
		//考虑使用一个成员变量存储当前赛季的比赛场数
	}
	
	//得到本赛季所有比赛
	public MatchesPO[] getMatches(){
		return matches;
	}
}