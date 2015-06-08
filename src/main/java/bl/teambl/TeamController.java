package bl.teambl;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import dataservice.teamdataservice.TeamDataService;
import po.HPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import po.TeamHighPO;
import po.TeamNormalPO;
import po.TeamPO;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import blservice.teamblservice.Teamblservice;
import vo.HotPlayerTeam;

public class TeamController implements Teamblservice {
	private TeamDataService teamservice;
	private PlayerDataService playerservice;
	private String filenameB;
	private String filenameC;
	private String ImageB;
	private String ImageC;

	public TeamController() {
		NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			playerservice = dataFactory.getPlayerData();
			teamservice = dataFactory.getTeamData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filenameB = "D:/teamBar";
		filenameC = "D:/teamCompare";
		ImageB = "D:/teamB.png";
		ImageC = "D:/teamC.png";
	}

	@Override
	public HotPlayerTeam[] getHotTeams(int season, String sortby,
			SeasonType type) {
		String sortBy = sortby + " desc";
		TeamNormalPO[] teams = teamservice.sortTeamNormalTotaln(season, sortBy,
				5, type);
		HotPlayerTeam[] hotTeams = new HotPlayerTeam[5];
		double[] data = new double[5];

		if (sortby.equals("points")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getPoints();
			}
		} else if (sortby.equals("rebs")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getRebs();
			}
		} else if (sortby.equals("assistNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getAssistNo();
			}
		} else if (sortby.equals("blockNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getBlockNo();
			}
		} else if (sortby.equals("stealsNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getStealsNo();
			}
		} else if (sortby.equals("threeHitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getThreeHitRate();
			}
		} else if (sortby.equals("hitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getHitRate();
			}
		} else if (sortby.equals("penaltyHitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = teams[i].getPenaltyHitRate();
			}
		}

		String name;
		TeamPO thisTeam;
		for (int i = 0; i != 5; i++) {
			name = teams[i].getName();
			thisTeam = teamservice.findTeam(name);
			hotTeams[i] = new HotPlayerTeam(thisTeam.getImage(), name, data[i]);
		}
		return hotTeams;
	}

	@Override
	public TeamPO getTeamData(String team) {
		return teamservice.findTeam(team);
	}

	@Override
	public HPlayerPO getPlayerBase(String playername) {
		return playerservice.findPlayer(playername);
	}

	@Override
	public String[] getTeamNames() {
		TeamPO[] allTeams = teamservice.getAllTeamData();
		String[] names = new String[allTeams.length];
		for (int i = 0; i != allTeams.length; i++) {
			names[i] = allTeams[i].getNameAbridge();
		}
		return names;
	}

	@Override
	// 得到该队有史以来所有球员的名字
	public String[] getPlayers(String team) {
		PlayerPO[] players = playerservice.getPlayersOfTeam(team);
		int length = players.length;
		String[] playernames = new String[players.length];
		for (int i = 0; i != length; i++) {
			playernames[i] = players[i].getName();
		}
		return playernames;
	}

	@Override
	// 得到赛季所有球队的所有数据 低阶
	public TeamNormalPO[] getAllTeamTotal(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamNormalPO> result = new ArrayList<TeamNormalPO>(length);
		TeamNormalPO thisTeam;
		for (int i = 0; i != length; i++) {
			thisTeam = this.getTotalTeam(season, teamNames[i], type);
			if (thisTeam != null) {
				result.add(thisTeam);
			}
		}
		TeamNormalPO[] resultR = (TeamNormalPO[]) result
				.toArray(new TeamNormalPO[result.size()]);
		return resultR;
	}

	@Override
	// 得到赛季所有球队的场均数据 低阶
	public TeamNormalPO[] getAllTeamAve(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamNormalPO> result = new ArrayList<TeamNormalPO>(length);
		TeamNormalPO thisTeam;
		for (int i = 0; i != length; i++) {
			thisTeam = this.getAveTeam(season, teamNames[i], type);
			if (thisTeam != null) {
				result.add(thisTeam);
			}
		}
		return (TeamNormalPO[]) result.toArray(new TeamNormalPO[result.size()]);
	}

	@Override
	public TeamHighPO[] getAllTeamHigh(int season, SeasonType type) {
		String[] teamNames = this.getTeamNames();
		int length = teamNames.length;
		ArrayList<TeamHighPO> result = new ArrayList<TeamHighPO>(length);
		TeamHighPO thisTeam;
		for (int i = 0; i != length; i++) {
			thisTeam = this.getHighTeam(season, teamNames[i], type);
			if (thisTeam != null) {
				result.add(this.getHighTeam(season, teamNames[i], type));
			}
		}
		return (TeamHighPO[]) result.toArray(new TeamHighPO[result.size()]);
	}

	@Override
	// 根据球队简称查找赛季数据 低阶
	public TeamNormalPO getTotalTeam(int season, String teamname,
			SeasonType type) {
		TeamNormalPO result = teamservice.getTeamNormalTotal(season, teamname,
				type);
		return result;
	}

	@Override
	// 根据球队简称查找场均数据 低阶
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type) {
		return teamservice.getTeamNormalAve(season, teamname, type);
	}

	@Override
	// 根据球队简称查找高阶数据
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type) {
		return teamservice.getTeamHigh(season, teamname, type);
	}

	@Override
	// 根据球队简称查找其下的球员的场均数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerNormalPO> players = new ArrayList<PlayerNormalPO>(
				playernames.length);
		PlayerNormalPO thisPlayer;
		for (String p : playernames) {
			thisPlayer = playerservice.getPlayerNormalAve(season, p, type);
			if (thisPlayer != null) {
				players.add(thisPlayer);
			}
		}
		return (PlayerNormalPO[]) players.toArray(new PlayerNormalPO[players
				.size()]);
	}

	@Override
	// 根据球队简称查找其下的球员的赛季数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerNormalPO> players = new ArrayList<PlayerNormalPO>(
				playernames.length);
		PlayerNormalPO thisPlayer;
		for (String p : playernames) {
			thisPlayer = playerservice.getPlayerNormalTotal(season, p, type);
			if (thisPlayer != null) {
				players.add(thisPlayer);
			}
		}
		return (PlayerNormalPO[]) players.toArray(new PlayerNormalPO[players
				.size()]);
	}

	@Override
	// 根据球队简称查找其下球员的高阶赛季数据
	public PlayerHighPO[] getAllPlayerHighMatch(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerHighPO> players = new ArrayList<PlayerHighPO>(
				playernames.length);
		PlayerHighPO thisPlayer;
		for (String p : playernames) {
			thisPlayer = playerservice.getPlayerHigh(season, p, type);
			if (thisPlayer != null) {
				players.add(thisPlayer);
			}
		}
		return (PlayerHighPO[]) players
				.toArray(new PlayerHighPO[players.size()]);
	}

	@Override
	// 获得某个球队的所有赛季的总数据
	public TeamNormalPO[] getTeamSeasonNormalTotal(String teama, SeasonType type) {
		return teamservice.getTeamSeasonNormalTotal(teama, type);
	}

	@Override
	// 获得某个球员所有赛季的场均数据
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama, SeasonType type) {
		return teamservice.getTeamSeasonNormalAve(teama, type);
	}

	@Override
	// 获得某个球员所有赛季的高阶数据
	public TeamHighPO[] getTeamSeasonHigh(String teama, SeasonType type) {
		return teamservice.getTeamSeasonHigh(teama, type);
	}

	@Override
	public Image getTeamImage(String name) {
		TeamPO team = this.getTeamData(name);
		return team.getImage();
	}

	@Override
	public Image getTeamBar(int season, String teamname, SeasonType type) {
		TeamNormalPO team = teamservice
				.getTeamNormalAve(season, teamname, type);
		TeamNormalPO[] allteams = this.getAllTeamAve(season, type);
		int len = allteams.length;
		StringBuffer PTS = new StringBuffer(len * 4);
		StringBuffer REB = new StringBuffer(len * 4);
		StringBuffer AST = new StringBuffer(len * 4);
		StringBuffer STL = new StringBuffer(len * 4);
		StringBuffer BLK = new StringBuffer(len * 4);
		for (int i = 0; i != len - 1; i++) {
			PTS.append(allteams[i].getPoints() + ",");
			REB.append(allteams[i].getRebs() + ",");
			AST.append(allteams[i].getAssistNo() + ",");
			STL.append(allteams[i].getStealsNo() + ",");
			BLK.append(allteams[i].getBlockNo() + ",");
		}
		PTS.append(allteams[len - 1].getPoints());
		REB.append(allteams[len - 1].getRebs());
		AST.append(allteams[len - 1].getAssistNo());
		STL.append(allteams[len - 1].getStealsNo());
		BLK.append(allteams[len - 1].getBlockNo());
		String toWrite = teamname + "\n" + PTS.toString() + "\n"
				+ REB.toString() + "\n" + AST.toString() + "\n"
				+ STL.toString() + "\n" + BLK.toString() + "\n"
				+ team.getPoints() + "," + team.getRebs() + ","
				+ team.getAssistNo() + "," + team.getStealsNo() + ","
				+ team.getBlockNo();
		BufferedWriter output;
		try {
			output = new BufferedWriter(new FileWriter(new File(filenameB)));
			output.write(toWrite);
			output.close();
			Process pr = Runtime.getRuntime().exec("python python\\teamBar.py");
			pr.waitFor();
			Image bar = ImageIO.read(new File(ImageB));
			return bar;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Image getTeamCompare(int season, String teamname1, String teamname2,
			SeasonType type) {
		TeamNormalPO team1 = teamservice.getTeamNormalAve(season, teamname1, type);
		TeamNormalPO team2 = teamservice.getTeamNormalAve(season, teamname2, type);
		BufferedWriter output;
		String toWrite = teamname1 + "\n" + teamname2 + "\n" + Double.toString(team1.getPoints()) + "," +
				Double.toString(team1.getRebs()) + "," + Double.toString(team1.getAssistNo()) + "," + 
				Double.toString(team1.getPenaltyHandNo() == 0 ? 0 : team1.getPenaltyHitNo()/team1.getPenaltyHandNo()*100) + "," + 
				Double.toString(team1.getThreeHandNo() == 0 ? 0 : team1.getThreeHitNo()/team1.getThreeHandNo()*100) + "\n" +
				Double.toString(team2.getPoints()) + "," + Double.toString(team2.getRebs()) + "," + Double.toString(team2.getAssistNo()) + "," + 
				Double.toString(team2.getPenaltyHandNo() == 0 ? 0 : team2.getPenaltyHitNo()/team2.getPenaltyHandNo()*100) + "," + 
				Double.toString(team2.getThreeHandNo() == 0 ? 0 : team2.getThreeHitNo()/team2.getThreeHandNo()*100);
		try {
			output = new BufferedWriter(new FileWriter(new File(
					filenameC)));
			output.write(toWrite);
			output.close();
			Process pr = Runtime.getRuntime().exec("python python\\teamCompare.py");
			pr.waitFor();
			Image compare = ImageIO.read(new File(ImageC));
			return compare;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
