package bl.playerbl;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;
import po.HPlayerPO;
import po.MatchPlayerPO;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import vo.Area;
import vo.HotPlayerTeam;
import DataFactory.DataFactory;
import DataFactoryService.NBADataFactory;
import blservice.playerblservice.PlayerBlService;

public class PlayerController implements PlayerBlService {
	private String filenameR;
	private String filenameC;
	private String filenameL;
	private String imageR;
	private String imageC;
	private String imageL;
	private PlayerDataService playerService;

	// 排序球员数据 场均
	public PlayerController() {
		filenameR = "D:/dataToP";
		filenameC = "D:/dataToPC";
		filenameL = "D:/dataToPL";
		imageR = "D:/radar.png";
		imageC = "D:/compare.png";
		imageL = "D:/line.png";
		NBADataFactory dataFactory;
		try {
			dataFactory = DataFactory.instance();
			playerService = dataFactory.getPlayerData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized HotPlayerTeam[] getDayHotPlayer(String sortBy) {
		return null;
	}

	// 获得赛季热点球员
	@Override
	public synchronized HotPlayerTeam[] getSeasonHotPlayer(int season,
			String sortby, SeasonType type) {
		String sortBy = sortby + " desc";
		PlayerNormalPO[] players = playerService.sortPlayerNormalAven(season,
				sortBy, 5, type);
		HotPlayerTeam[] hotPlayers = new HotPlayerTeam[5];
		double[] data = new double[5];
		if (sortby.equals("points")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getPoints();
			}
		} else if (sortby.equals("rebs")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getRebs();
			}
		} else if (sortby.equals("assistNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getAssistNo();
			}
		} else if (sortby.equals("blockNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getBlockNo();
			}
		} else if (sortby.equals("stealsNo")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getStealsNo();
			}
		} else if (sortby.equals("threeHitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getThreeHitRate();
			}
		} else if (sortby.equals("hitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getHitRate();
			}
		} else if (sortby.equals("penaltyHitRate")) {
			for (int i = 0; i != 5; i++) {
				data[i] = players[i].getPenaltyHitRate();
			}
		}

		String name;
		for (int i = 0; i != 5; i++) {
			name = players[i].getName();
			hotPlayers[i] = new HotPlayerTeam(this.getPlayerImage(name), name,
					data[i]);
		}
		return hotPlayers;
	}

	// 获得进步最快球员
	@Override
	public synchronized HotPlayerTeam[] getPromotePlayer(int season,
			String sortby) {
		return null;
	}

	@Override
	// 根据球员名字查找球员
	public synchronized HPlayerPO findPlayer(String info) {
		return playerService.findPlayer(info);
	}

	@Override
	public synchronized HPlayerPO[] getPlayersWithStart(int season, String start) {
		String[] playersFit = playerService.fuzzilySearch(start);
		ArrayList<HPlayerPO> result = new ArrayList<HPlayerPO>(
				playersFit.length);
		HPlayerPO playerP;
		for (String s : playersFit) {
			playerP = playerService.findPlayer(s);
			if (playerP != null) {
				result.add(playerP);
			}
		}
		return (HPlayerPO[]) result.toArray(new HPlayerPO[result.size()]);
	}

	@Override
	public Image getPlayerImage(String name) {
		HPlayerPO playerP = playerService.findPlayer(name);
		Image result = playerP.getImage();
		if (result != null) {
			return result;
		} else {
			try {
				return ImageIO.read(new File("image/noimage.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String[] fuzzilyFind(String info) {
		String[] playersFit = playerService.fuzzilySearch(info);
		return playersFit;
	}

	@Override
	// 筛选球员 场均数据 低阶
	public PlayerNormalPO[] screenNormalAvePlayers(int season,
			String playerPosition, Area playerArea, SeasonType type) {
		String area = new String();
		if (playerArea == null) {
			area = null;
		} else {
			switch (playerArea) {
			case EASTERN:
				area = "EASTERN";
				break;
			case WESTERN:
				area = "WESTERN";
				break;
			case ATLANTIC:
				area = "ATLANTIC";
				break;
			case CENTRAL:
				area = "CENTRAL";
				break;
			case SOUTHEAST:
				area = "SOUTHEAST";
				break;
			case SOUTHWEST:
				area = "SOUTHWEST";
				break;
			case NORTHWEST:
				area = "NORTHWEST";
				break;
			case PACIFIC:
				area = "PACIFIC";
				break;
			default:
				area = null;
				break;
			}
		}
		PlayerPO[] players = playerService.screenPlayer(null, area,
				playerPosition, 100);
		PlayerNormalPO[] result = new PlayerNormalPO[players.length];
		for (int i = 0; i != players.length; i++) {
			result[i] = playerService.getPlayerNormalAve(season,
					players[i].getName(), type);
		}
		return result;
	}

	@Override
	// 筛选球员 赛季数据 低阶
	public PlayerNormalPO[] screenNormalTotalPlayers(int season,
			String playerPosition, Area playerArea, SeasonType type) {
		String area = new String();
		if (playerArea == null) {
			area = null;
		} else {
			switch (playerArea) {
			case EASTERN:
				area = "EASTERN";
				break;
			case WESTERN:
				area = "WESTERN";
				break;
			case ATLANTIC:
				area = "ATLANTIC";
				break;
			case CENTRAL:
				area = "CENTRAL";
				break;
			case SOUTHEAST:
				area = "SOUTHEAST";
				break;
			case SOUTHWEST:
				area = "SOUTHWEST";
				break;
			case NORTHWEST:
				area = "NORTHWEST";
				break;
			case PACIFIC:
				area = "PACIFIC";
				break;
			default:
				area = null;
				break;
			}
		}

		PlayerPO[] players = playerService.screenPlayer(null, area,
				playerPosition, 100);
		PlayerNormalPO[] result = new PlayerNormalPO[players.length];
		for (int i = 0; i != players.length; i++) {
			result[i] = playerService.getPlayerNormalTotal(season,
					players[i].getName(), type);
		}
		return result;
	}

	@Override
	// 筛选球员 数据 高阶
	public PlayerHighPO[] screenHighPlayers(int season, String playerPosition,
			Area playerArea, SeasonType type) {
		String area = new String();
		if (playerArea == null) {
			area = null;
		} else {
			switch (playerArea) {
			case EASTERN:
				area = "EASTERN";
				break;
			case WESTERN:
				area = "WESTERN";
				break;
			case ATLANTIC:
				area = "ATLANTIC";
				break;
			case CENTRAL:
				area = "CENTRAL";
				break;
			case SOUTHEAST:
				area = "SOUTHEAST";
				break;
			case SOUTHWEST:
				area = "SOUTHWEST";
				break;
			case NORTHWEST:
				area = "NORTHWEST";
				break;
			case PACIFIC:
				area = "PACIFIC";
				break;
			default:
				area = null;
				break;
			}
		}

		PlayerPO[] players = playerService.screenPlayer(null, area,
				playerPosition, 100);
		PlayerHighPO[] result = new PlayerHighPO[players.length];
		for (int i = 0; i != players.length; i++) {
			result[i] = playerService.getPlayerHigh(season,
					players[i].getName(), type);
		}
		return result;
	}

	@Override
	// 查找球员场均数据 低阶
	public PlayerNormalPO findPlayerMatchAve(int season, String playername,
			SeasonType type) {
		return playerService.getPlayerNormalAve(season, playername, type);
	}

	@Override
	// 查找球员赛季数据 低阶
	public PlayerNormalPO findPlayerTotal(int season, String playername,
			SeasonType type) {
		return playerService.getPlayerNormalTotal(season, playername, type);
	}

	@Override
	// 查找球员高阶数据
	public PlayerHighPO findPlayerHigh(int season, String playername,
			SeasonType type) {
		return playerService.getPlayerHigh(season, playername, type);
	}

	@Override
	// 获得所有球员场均数据 低阶
	public PlayerNormalPO[] getAveAllPlayers(int season, SeasonType type) {
		return playerService.getSeasonPlayerNormalAve(season, type);
	}

	@Override
	// 获得所有球员所有数据 低阶
	public PlayerNormalPO[] getTotalAllPlayers(int season, SeasonType type) {
		return playerService.getSeasonPlayerNormalTotal(season, type);
	}

	@Override
	// 获得所有球员高阶数据
	public PlayerHighPO[] getHighAllPlayers(int season, SeasonType type) {
		return playerService.getSeasonPlayerHigh(season, type);
	}

	@Override
	// 获得球员的所有赛季总数据
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName,
			SeasonType type) {
		return playerService.getPlayerAllSeasonsTotal(playerName, type);
	}

	@Override
	// 获得球员的所有赛季场均数据
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName,
			SeasonType type) {
		return playerService.getPlayerAllSeasonsAve(playerName, type);
	}

	@Override
	// 获得球员的所有的赛季高阶数据
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type) {
		return playerService.getPlayerAllSeasons(playerName, type);
	}

	@Override
	public PlayerPO[] getAllActivePlayerData() {
		return playerService.getAllActivePlayerData();
	}

	@Override
	public Image getRadarImage(int season, String name, SeasonType type) {
		PlayerNormalPO player = playerService.getPlayerNormalAve(season, name,
				type);
		double[] playerData = { player.getPoints(), player.getRebs(),
				player.getAssistNo(), player.getStealsNo(), player.getBlockNo() };
		PlayerNormalPO[] allPlayers = playerService.getSeasonPlayerNormalAve(
				season, type);
		int len = allPlayers.length;
		StringBuffer PTS = new StringBuffer(len * 4);
		StringBuffer REB = new StringBuffer(len * 4);
		StringBuffer AST = new StringBuffer(len * 4);
		StringBuffer STL = new StringBuffer(len * 4);
		StringBuffer BLK = new StringBuffer(len * 4);
		for (int i = 0; i != len - 1; i++) {
			PTS.append(allPlayers[i].getPoints() + ",");
			REB.append(allPlayers[i].getRebs() + ",");
			AST.append(allPlayers[i].getAssistNo() + ",");
			STL.append(allPlayers[i].getStealsNo() + ",");
			BLK.append(allPlayers[i].getBlockNo() + ",");
		}
		PTS.append(allPlayers[len - 1].getPoints());
		REB.append(allPlayers[len - 1].getRebs());
		AST.append(allPlayers[len - 1].getAssistNo());
		STL.append(allPlayers[len - 1].getStealsNo());
		BLK.append(allPlayers[len - 1].getBlockNo());
		PTS.trimToSize();
		REB.trimToSize();
		AST.trimToSize();
		STL.trimToSize();
		BLK.trimToSize();
		String toWrite = name + "\n" + "AVE PERF" + "\n" + PTS.toString()
				+ '\n' + REB.toString() + '\n' + AST.toString() + '\n'
				+ STL.toString() + '\n' + BLK.toString() + '\n'
				+ Double.toString(playerData[0]) + ","
				+ Double.toString(playerData[1]) + ","
				+ Double.toString(playerData[2]) + ","
				+ Double.toString(playerData[3]) + ","
				+ Double.toString(playerData[4]);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(new File(
					filenameR)));
			output.write(toWrite);
			output.close();
			Process pr = Runtime.getRuntime().exec("python python\\radar.py");
			pr.waitFor();
			Image radar = ImageIO.read(new File(imageR));
			return radar;
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Image getCompareImage(int season, String name1, String name2,
			SeasonType type) {
		MatchPlayerPO[] player1Matches = playerService.getSeasonMatches(season,
				name1, type);
		MatchPlayerPO[] player2Matches = playerService.getSeasonMatches(season,
				name2, type);
		if (player1Matches == null || player2Matches == null) {
			return null;
		} else {
			int len1 = player1Matches.length * 5;
			int len2 = player2Matches.length * 5;
			StringBuffer playerData1PTS = new StringBuffer(len1);
			StringBuffer playerData1REB = new StringBuffer(len1);
			StringBuffer playerData1AST = new StringBuffer(len1);
			StringBuffer playerData1FT = new StringBuffer(len1);
			StringBuffer playerData13PT = new StringBuffer(len1);
			StringBuffer playerData2PTS = new StringBuffer(len2);
			StringBuffer playerData2REB = new StringBuffer(len2);
			StringBuffer playerData2AST = new StringBuffer(len2);
			StringBuffer playerData2FT = new StringBuffer(len2);
			StringBuffer playerData23PT = new StringBuffer(len2);
			boolean p1Lagerp2 = (player1Matches.length > player2Matches.length) ? true
					: false;
			int loopNo = 0;
			if (p1Lagerp2) {
				loopNo = player2Matches.length - 1;
			} else {
				loopNo = player1Matches.length - 1;
			}
			for (int i = 0; i != loopNo; i++) {
				playerData1PTS.append(player1Matches[i].getPoints() + ",");
				playerData1REB.append(player1Matches[i].getRebs() + ",");
				playerData1AST.append(player1Matches[i].getHelp() + ",");
				playerData1FT
						.append(player1Matches[i].getPenaltyHandNo() == 0 ? 0
								: (100 * player1Matches[i].getPenaltyHitNo() / player1Matches[i]
										.getPenaltyHandNo()) + ",");
				playerData13PT
						.append(player1Matches[i].getThreeHandNo() == 0 ? 0
								: (100 * player1Matches[i].getThreeHitNo() / player1Matches[i]
										.getThreeHandNo()) + ",");
				playerData2PTS.append(player2Matches[i].getPoints() + ",");
				playerData2REB.append(player2Matches[i].getRebs() + ",");
				playerData2AST.append(player2Matches[i].getHelp() + ",");
				playerData2FT
						.append(player2Matches[i].getPenaltyHandNo() == 0 ? 0
								: (100 * player2Matches[i].getPenaltyHitNo() / player2Matches[i]
										.getPenaltyHandNo()) + ",");
				playerData23PT
						.append(player2Matches[i].getThreeHandNo() == 0 ? 0
								: (100 * player2Matches[i].getThreeHitNo() / player2Matches[i]
										.getThreeHandNo()) + ",");
			}
			if (p1Lagerp2) {
				playerData2PTS.append(player2Matches[loopNo].getPoints());
				playerData2REB.append(player2Matches[loopNo].getRebs());
				playerData2AST.append(player2Matches[loopNo].getHelp());
				playerData2FT
						.append(player2Matches[loopNo].getPenaltyHandNo() == 0 ? 0
								: (100 * player2Matches[loopNo]
										.getPenaltyHitNo() / player2Matches[loopNo]
										.getPenaltyHandNo()));
				playerData23PT
						.append(player2Matches[loopNo].getThreeHandNo() == 0 ? 0
								: (100 * player2Matches[loopNo].getThreeHitNo() / player2Matches[loopNo]
										.getThreeHandNo()));
				for (int i = loopNo; i != player1Matches.length - 1; i++) {
					playerData1PTS.append(player1Matches[i].getPoints() + ",");
					playerData1REB.append(player1Matches[i].getRebs() + ",");
					playerData1AST.append(player1Matches[i].getHelp() + ",");
					playerData1FT
							.append(player1Matches[i].getPenaltyHandNo() == 0 ? 0
									: (100 * player1Matches[i]
											.getPenaltyHitNo() / player1Matches[i]
											.getPenaltyHandNo())
											+ ",");
					playerData13PT
							.append(player1Matches[i].getThreeHandNo() == 0 ? 0
									: (100 * player1Matches[i].getThreeHitNo() / player1Matches[i]
											.getThreeHandNo()) + ",");
				}
				playerData1PTS.append(player1Matches[player1Matches.length - 1]
						.getPoints());
				playerData1REB.append(player1Matches[player1Matches.length - 1]
						.getRebs());
				playerData1AST.append(player1Matches[player1Matches.length - 1]
						.getHelp());
				playerData1FT
						.append(player1Matches[player1Matches.length - 1]
								.getPenaltyHandNo() == 0 ? 0
								: (100 * player1Matches[player1Matches.length - 1]
										.getPenaltyHitNo() / player1Matches[player1Matches.length - 1]
										.getPenaltyHandNo()));
				playerData13PT
						.append(player1Matches[player1Matches.length - 1]
								.getThreeHandNo() == 0 ? 0
								: (100 * player1Matches[player1Matches.length - 1]
										.getThreeHitNo() / player1Matches[player1Matches.length - 1]
										.getThreeHandNo()));
			} else {
				playerData1PTS.append(player1Matches[loopNo].getPoints());
				playerData1REB.append(player1Matches[loopNo].getRebs());
				playerData1AST.append(player1Matches[loopNo].getHelp());
				playerData1FT
						.append(player1Matches[loopNo].getPenaltyHandNo() == 0 ? 0
								: (100 * player1Matches[loopNo]
										.getPenaltyHitNo() / player1Matches[loopNo]
										.getPenaltyHandNo()));
				playerData13PT
						.append(player1Matches[loopNo].getThreeHandNo() == 0 ? 0
								: (100 * player1Matches[loopNo].getThreeHitNo() / player1Matches[loopNo]
										.getThreeHandNo()));
				for (int i = loopNo; i != player2Matches.length - 1; i++) {
					playerData2PTS.append(player2Matches[i].getPoints() + ",");
					playerData2REB.append(player2Matches[i].getRebs() + ",");
					playerData2AST.append(player2Matches[i].getHelp() + ",");
					playerData2FT
							.append(player2Matches[i].getPenaltyHandNo() == 0 ? 0
									: (100 * player2Matches[i]
											.getPenaltyHitNo() / player2Matches[i]
											.getPenaltyHandNo())
											+ ",");
					playerData23PT
							.append(player2Matches[i].getThreeHandNo() == 0 ? 0
									: (100 * player2Matches[i].getThreeHitNo() / player2Matches[i]
											.getThreeHandNo()) + ",");
				}
				playerData2PTS.append(player2Matches[player2Matches.length - 1]
						.getPoints());
				playerData2REB.append(player2Matches[player2Matches.length - 1]
						.getRebs());
				playerData2AST.append(player2Matches[player2Matches.length - 1]
						.getHelp());
				playerData2FT
						.append(player2Matches[player2Matches.length - 1]
								.getPenaltyHandNo() == 0 ? 0
								: (100 * player2Matches[player2Matches.length - 1]
										.getPenaltyHitNo() / player2Matches[player2Matches.length - 1]
										.getPenaltyHandNo()));
				playerData23PT
						.append(player2Matches[player2Matches.length - 1]
								.getThreeHandNo() == 0 ? 0
								: (100 * player2Matches[player2Matches.length - 1]
										.getThreeHitNo() / player2Matches[player2Matches.length - 1]
										.getThreeHandNo()));
			}

			// 将数据写入文件
			BufferedWriter output;
			try {
				output = new BufferedWriter(new FileWriter(new File(filenameC)));
				/*
				 * String names = name1 + "\n" + name2 + "\n"; String Data1 =
				 * playerData1PTS.toString() + "\n" + playerData1REB.toString()
				 * + "\n" +playerData1AST.toString() + "\n" +
				 * playerData1STL.toString() + "\n" + playerData1BLK.toString()
				 * + "\n"; String Data2 = playerData2PTS.toString() + "\n" +
				 * playerData2REB.toString() + "\n" +playerData2AST.toString() +
				 * "\n" + playerData2STL.toString() + "\n" +
				 * playerData2BLK.toString();
				 */
				String data = name1 + "\n" + name2 + "\n"
						+ playerData1PTS.toString() + "\n"
						+ playerData1REB.toString() + "\n"
						+ playerData1AST.toString() + "\n"
						+ playerData1FT.toString() + "\n"
						+ playerData13PT.toString() + "\n"
						+ playerData2PTS.toString() + "\n"
						+ playerData2REB.toString() + "\n"
						+ playerData2AST.toString() + "\n"
						+ playerData2FT.toString() + "\n"
						+ playerData23PT.toString();
				output.write(data);
				output.close();
				Process pr = Runtime.getRuntime().exec(
						"python python\\__init__.py");
				pr.waitFor();
				Image compare = ImageIO.read(new File(imageC));
				return compare;
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Image getLineChartImage(int season, String playername) {
		MatchPlayerPO[] newest = new MatchPlayerPO[10];
		MatchPlayerPO[] regularP = playerService.getSeasonMatches(season,
				playername, SeasonType.REGULAR);
		MatchPlayerPO[] playeroffP = playerService.getSeasonMatches(season,
				playername, SeasonType.PLAYOFF);
		int nowP = 0;
		if (regularP == null && playeroffP == null) {
			return null;
		} else {
			if (playeroffP != null && playeroffP.length != 0) {
				for (int i = 0; i != playeroffP.length && nowP != 10; i++) {
					newest[9 - nowP] = playeroffP[i];
					nowP++;
				}
			}
			if (regularP != null && nowP != 10 && regularP.length != 0) {
				for (int i = 0; i != regularP.length && nowP != 10; i++) {
					newest[9 - nowP] = regularP[i];
					nowP++;
				}
			}
			StringBuffer Date = new StringBuffer(60);
			StringBuffer PTS = new StringBuffer(44);
			StringBuffer REB = new StringBuffer(44);
			StringBuffer AST = new StringBuffer(44);
			StringBuffer FT = new StringBuffer(44);
			StringBuffer PT3 = new StringBuffer(44);
			for (int i = 10 - nowP; i != nowP - 1; i++) {
				Date.append(newest[i].getDate() + ",");
				PTS.append(newest[i].getPoints() + ",");
				REB.append(newest[i].getRebs() + ",");
				AST.append(newest[i].getHelp() + ",");
				FT.append(newest[i].getPenaltyHandNo() != 0 ? newest[i]
						.getPenaltyHitNo() / newest[i].getPenaltyHandNo() * 100
						: 0 + ",");
				PT3.append(newest[i].getThreeHandNo() != 0 ? newest[i]
						.getThreeHitNo() / newest[i].getThreeHandNo() * 100
						: 0 + ",");
			}
			Date.append(newest[nowP - 1].getDate() + "," + "future");
			PTS.append(newest[nowP - 1].getPoints());
			REB.append(newest[nowP - 1].getRebs());
			AST.append(newest[nowP - 1].getHelp());
			FT.append(newest[nowP - 1].getPenaltyHandNo() != 0 ? newest[10]
					.getPenaltyHitNo() / newest[10].getPenaltyHandNo() * 100
					: 0);
			PT3.append(newest[nowP - 1].getThreeHandNo() != 0 ? newest[10]
					.getThreeHitNo() / newest[10].getThreeHandNo() * 100 : 0);

			String toWrite = Integer.toString(nowP) + '\n' + Date.toString()
					+ '\n' + PTS.toString() + '\n' + REB.toString() + '\n'
					+ AST.toString() + '\n' + FT.toString() + '\n'
					+ PT3.toString();
			BufferedWriter output;

			try {
				output = new BufferedWriter(new FileWriter(new File(filenameL)));
				output.write(toWrite);
				output.close();
				Process pr = Runtime.getRuntime().exec(
						"python python\\errorbar.py");
				pr.waitFor();
				Image line = ImageIO.read(new File(imageL));
				return line;
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
}
