package bl.teambl;

import java.awt.Image;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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
//	private String filenameB;
//	private String filenameC;
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
//		filenameB = "D:/teamBar";
//		filenameC = "D:/teamCompare";
		ImageB = "D:/Team/teamB";
		ImageC = "D:/Team/teamC";
	}

	@Override
	public HotPlayerTeam[] getHotTeams(int season, String sortby,
			SeasonType type) {
		String sortBy = sortby + " desc";
		TeamNormalPO[] teams = null;
		try {
			teams = teamservice.sortTeamNormalAven(season, sortBy, 5, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		TeamPO thisTeam = null;
		for (int i = 0; i != 5; i++) {
			name = teams[i].getName();
			try {
				thisTeam = teamservice.findTeam(name);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hotTeams[i] = new HotPlayerTeam(thisTeam.getImage(), name, data[i]);
		}
		return hotTeams;
	}

	@Override
	public TeamPO getTeamData(String team) {
		try {
			return teamservice.findTeam(team);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HPlayerPO getPlayerBase(String playername) {
		try {
			return playerservice.findPlayer(playername);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] getTeamNames() {
		TeamPO[] allTeams = null;
		try {
			allTeams = teamservice.getAllTeamData();
			String[] names = new String[allTeams.length];
			for (int i = 0; i != allTeams.length; i++) {
				names[i] = allTeams[i].getNameAbridge();
			}
			return names;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 得到该队有史以来所有球员的名字
	public String[] getPlayers(String team) {
		PlayerPO[] players;
		try {
			players = playerservice.getPlayersOfTeam(team);
			int length = players.length;
			String[] playernames = new String[players.length];
			for (int i = 0; i != length; i++) {
				playernames[i] = players[i].getName();
			}
			return playernames;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		TeamNormalPO result;
		try {
			result = teamservice.getTeamNormalTotal(season, teamname, type);
			return result;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 根据球队简称查找场均数据 低阶
	public TeamNormalPO getAveTeam(int season, String teamname, SeasonType type) {
		try {
			return teamservice.getTeamNormalAve(season, teamname, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 根据球队简称查找高阶数据
	public TeamHighPO getHighTeam(int season, String teamname, SeasonType type) {
		try {
			return teamservice.getTeamHigh(season, teamname, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 根据球队简称查找其下的球员的场均数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchAve(int season, String teamname,
			SeasonType type) {
		try {
			return playerservice.getSeasonPlayerNormalOfTeam(season, type,
					teamname);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 根据球队简称查找其下的球员的赛季数据 低阶
	public PlayerNormalPO[] getAllPlayerMatchTotal(int season, String teamname,
			SeasonType type) {
		String[] playernames = this.getPlayers(teamname);
		ArrayList<PlayerNormalPO> players = new ArrayList<PlayerNormalPO>(
				playernames.length);
		PlayerNormalPO thisPlayer = null;
		for (String p : playernames) {
			try {
				thisPlayer = playerservice
						.getPlayerNormalTotal(season, p, type);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		PlayerHighPO thisPlayer = null;
		for (String p : playernames) {
			try {
				thisPlayer = playerservice.getPlayerHigh(season, p, type);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try {
			return teamservice.getTeamSeasonNormalTotal(teama, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 获得某个球员所有赛季的场均数据
	public TeamNormalPO[] getTeamSeasonNormalAve(String teama, SeasonType type) {
		try {
			return teamservice.getTeamSeasonNormalAve(teama, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// 获得某个球员所有赛季的高阶数据
	public TeamHighPO[] getTeamSeasonHigh(String teama, SeasonType type) {
		try {
			return teamservice.getTeamSeasonHigh(teama, type);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Image getTeamImage(String name) {
		TeamPO team = this.getTeamData(name);
		return team.getImage();
	}

	@Override
	public Image getTeamBar(int season, String teamname, SeasonType type) {
		String filename = ImageB + teamname + Integer.toString(season)
				+ (type == SeasonType.REGULAR ? "REGULAR" : "PLAYEROFF")
				+ ".png";
		// TeamNormalPO team = null;
		// try {
		// team = teamservice
		// .getTeamNormalAve(season, teamname, type);
		// } catch (RemoteException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// TeamNormalPO[] allteams = this.getAllTeamAve(season, type);
		// int len = allteams.length;
		// StringBuffer PTS = new StringBuffer(len * 4);
		// StringBuffer REB = new StringBuffer(len * 4);
		// StringBuffer AST = new StringBuffer(len * 4);
		// StringBuffer STL = new StringBuffer(len * 4);
		// StringBuffer BLK = new StringBuffer(len * 4);
		// for (int i = 0; i != len - 1; i++) {
		// PTS.append(allteams[i].getPoints() + ",");
		// REB.append(allteams[i].getRebs() + ",");
		// AST.append(allteams[i].getAssistNo() + ",");
		// STL.append(allteams[i].getStealsNo() + ",");
		// BLK.append(allteams[i].getBlockNo() + ",");
		// }
		// PTS.append(allteams[len - 1].getPoints());
		// REB.append(allteams[len - 1].getRebs());
		// AST.append(allteams[len - 1].getAssistNo());
		// STL.append(allteams[len - 1].getStealsNo());
		// BLK.append(allteams[len - 1].getBlockNo());
		// String toWrite = "";
		// if (team != null) {
		// toWrite = filename + "\n" + teamname + "\n" + PTS.toString() + "\n"
		// + REB.toString() + "\n" + AST.toString() + "\n"
		// + STL.toString() + "\n" + BLK.toString() + "\n"
		// + team.getPoints() + "," + team.getRebs() + ","
		// + team.getAssistNo() + "," + team.getStealsNo() + ","
		// + team.getBlockNo();
		// } else {
		// toWrite = filename + "\n" + teamname + "\n" + PTS.toString() + "\n"
		// + REB.toString() + "\n" + AST.toString() + "\n"
		// + STL.toString() + "\n" + BLK.toString() + "\n"
		// + "0,0,0,0,0";
		// }
		//
		// BufferedWriter output;
		// try {
		// output = new BufferedWriter(new FileWriter(new File(filenameB)));
		// output.write(toWrite);
		// output.close();
		// Process pr = Runtime.getRuntime().exec("python python\\teamBar.py");
		// pr.waitFor();
		ImageIcon imageIcon = new ImageIcon(filename);
		Image bar = imageIcon.getImage();
		return bar;
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Override
	public Image getTeamCompare(int season, String teamname1, String teamname2,
			SeasonType type) {
		String filename = ImageC + teamname1 + teamname2
				+ Integer.toString(season)
				+ (type == SeasonType.REGULAR ? "REGULAR" : "PLAYEROFF")
				+ ".png";
		// TeamNormalPO team1 = null;
		// try {
		// team1 = teamservice.getTeamNormalAve(season, teamname1, type);
		// } catch (RemoteException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// TeamNormalPO team2 = null;
		// try {
		// team2 = teamservice.getTeamNormalAve(season, teamname2, type);
		// } catch (RemoteException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// BufferedWriter output;
		// StringBuffer toWriteBuffer = new StringBuffer(100);
		// toWriteBuffer.append(filename + "\n" + teamname1 + "\n" + teamname2
		// + "\n");
		// if (team1 != null && team2 != null) {
		// toWriteBuffer
		// .append(Double.toString(team1.getPoints())
		// + ","
		// + Double.toString(team1.getRebs())
		// + ","
		// + Double.toString(team1.getAssistNo())
		// + ","
		// + Double.toString(team1.getPenaltyHandNo() == 0 ? 0
		// : team1.getPenaltyHitNo()
		// / team1.getPenaltyHandNo() * 100)
		// + ","
		// + Double.toString(team1.getThreeHandNo() == 0 ? 0
		// : team1.getThreeHitNo()
		// / team1.getThreeHandNo() * 100)
		// + "\n"
		// + Double.toString(team2.getPoints())
		// + ","
		// + Double.toString(team2.getRebs())
		// + ","
		// + Double.toString(team2.getAssistNo())
		// + ","
		// + Double.toString(team2.getPenaltyHandNo() == 0 ? 0
		// : team2.getPenaltyHitNo()
		// / team2.getPenaltyHandNo() * 100)
		// + ","
		// + Double.toString(team2.getThreeHandNo() == 0 ? 0
		// : team2.getThreeHitNo()
		// / team2.getThreeHandNo() * 100));
		// } else if (team1 != null) {
		// toWriteBuffer
		// .append(Double.toString(team1.getPoints())
		// + ","
		// + Double.toString(team1.getRebs())
		// + ","
		// + Double.toString(team1.getAssistNo())
		// + ","
		// + Double.toString(team1.getPenaltyHandNo() == 0 ? 0
		// : team1.getPenaltyHitNo()
		// / team1.getPenaltyHandNo() * 100)
		// + ","
		// + Double.toString(team1.getThreeHandNo() == 0 ? 0
		// : team1.getThreeHitNo()
		// / team1.getThreeHandNo() * 100)
		// + "\n" + "0,0,0,0,0");
		// } else if (team2 != null) {
		// toWriteBuffer
		// .append("0,0,0,0,0"
		// + "\n"
		// + Double.toString(team2.getPoints())
		// + ","
		// + Double.toString(team2.getRebs())
		// + ","
		// + Double.toString(team2.getAssistNo())
		// + ","
		// + Double.toString(team2.getPenaltyHandNo() == 0 ? 0
		// : team2.getPenaltyHitNo()
		// / team2.getPenaltyHandNo() * 100)
		// + ","
		// + Double.toString(team2.getThreeHandNo() == 0 ? 0
		// : team2.getThreeHitNo()
		// / team2.getThreeHandNo() * 100));
		// } else {
		// toWriteBuffer.append("0,0,0,0,0\n0,0,0,0,0");
		// }
		//
		// String toWrite = toWriteBuffer.toString();
		//
		// try {
		// output = new BufferedWriter(new FileWriter(new File(filenameC)));
		// output.write(toWrite);
		// output.close();
		// Process pr = Runtime.getRuntime().exec(
		// "python python\\teamCompare.py");
		// pr.waitFor();
		ImageIcon imageIcon = new ImageIcon(filename);
		Image compare = imageIcon.getImage();
		return compare;
		// return null;
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return null;
	}
}
