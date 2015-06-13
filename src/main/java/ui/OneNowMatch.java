package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFont;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;

public class OneNowMatch extends JPanel{
	
	Teamblservice teamController = new TeamController();
	JScrollPane oneMatchScrollPane = new JScrollPane();
	
	public OneNowMatch(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8);
		oneMatchScrollPane.setOpaque(false);
		oneMatchScrollPane.getViewport().setOpaque(false);
		oneMatchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		oneMatchScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(oneMatchScrollPane);
	}
	
	public void setOneNowMatch(MatchTeamPO team1, MatchTeamPO team2) {
		oneMatchScrollPane.getViewport().removeAll();
		JPanel matchPanel = new JPanel();
		matchPanel.setLayout(null);
		JLabel teamImage1 = new JLabel(scaleImage(new ImageIcon(teamController
				.getTeamData(team1.getName()).getImage()), 150, 150));
		teamImage1.setBounds(200, 25, 150, 150);
		JLabel teamImage2 = new JLabel(scaleImage(new ImageIcon(teamController
				.getTeamData(team2.getName()).getImage()), 150, 150));
		teamImage2.setBounds(FrameSize.width - 350, 25, 150, 150);
		matchPanel.add(teamImage1);
		matchPanel.add(teamImage2);
		teamImage1.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MyFrame.teampanel.showOne(team1.getName());
				MyFrame.card.show(MyFrame.mainpanel, "team");
			}
		}));
		teamImage2.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MyFrame.teampanel.showOne(team2.getName());
				MyFrame.card.show(MyFrame.mainpanel, "team");
			}
		}));
		
		System.out.println(team1.getName());
		JLabel teamName1 = new JLabel(team1.getName());
		JLabel teamName2 = new JLabel(team2.getName());
		teamName1.setFont(MyFont.font2);
		teamName2.setFont(MyFont.font2);
		teamName1.setBounds(100, 60, 100, 30);
		teamName2.setBounds(FrameSize.width - 150, 60, 100, 30);
		matchPanel.add(teamName1);
		matchPanel.add(teamName2);
		
		JLabel teamScores1 = new JLabel(String.valueOf(team1
				.getTotalScores()));
		JLabel teamScores2 = new JLabel(String.valueOf(team2
				.getTotalScores()));
		teamScores1.setFont(MyFont.font3);
		teamScores2.setFont(MyFont.font3);
		if (team1.getTotalScores() > team2.getTotalScores()) {
			teamScores1.setForeground(Color.red);
		} else {
			teamScores2.setForeground(Color.red);
		}
		teamScores1.setBounds(100, 120, 50, 30);
		teamScores2.setBounds(FrameSize.width - 150, 120, 50, 30);
		matchPanel.add(teamScores1);
		matchPanel.add(teamScores2);

		int column = team1.getScores().length;
		JLabel[][] scores = new JLabel[3][column];
		for (int j = 0; j < column; j++) {
			scores[0][j] = new JLabel(String.valueOf(j + 1));
			scores[1][j] = new JLabel(String.valueOf(team1
					.getScores()[j]));
			scores[2][j] = new JLabel(String.valueOf(team2
					.getScores()[j]));
			if (team1.getScores()[j] > team2.getScores()[j]) {
				scores[1][j].setForeground(Color.red);
			} else {
				scores[2][j].setForeground(Color.red);
			}
			for (int k = 0; k < 3; k++) {
				scores[k][j].setBounds(350
						+ (FrameSize.width - 700 - 120) * (j + 1)
						/ (column + 2) + j * 30, 40 + k * 50, 50,
						50);
				scores[k][j].setFont(MyFont.font4);
				matchPanel.add(scores[k][j]);
			}

		}

		JScrollPane playerTable1 = setPlayerTable(team1.getPlayers());
		JScrollPane playerTable2 = setPlayerTable(team2.getPlayers());
		playerTable1.setBounds(0, 300, FrameSize.width, (team1.getPlayers().length+1)*26+35);
		playerTable2.setBounds(0, 350+(team1.getPlayers().length+1)*26+20, FrameSize.width, (team2.getPlayers().length+1)*26+35);
		matchPanel.add(playerTable1);
		matchPanel.add(playerTable2);
		
		matchPanel.setOpaque(false);
		oneMatchScrollPane.getViewport().add(matchPanel);
		matchPanel.setPreferredSize(new Dimension(FrameSize.width,400+(team1.getPlayers().length+1)*26+20+(team2.getPlayers().length+1)*26+120));
		
		// this.add(todatyMatchScrollPane);
		this.add(oneMatchScrollPane);
		this.repaint();
	}
	
	/** 每个队伍的球员表现 */
	private JScrollPane setPlayerTable(MatchPlayerPO[] players) {
		
		System.out.println(players.length);

		Vector columnsName = new Vector();
//		/* 01球员图片 */columnsName.add("球员");
		/* 02球员姓名 */columnsName.add("姓名");
		/* 04分钟 */columnsName.add("分钟");
		/* 05投篮命中数 */columnsName.add("命中");
		/* 06投篮出手数 */columnsName.add("出手");
		/* 07三分命中率 */columnsName.add("三分%");
		/* 08三分命中 */columnsName.add("三分命中");
		/* 09三分出手 */columnsName.add("三分出手");
		/* 10罚球命中率 */columnsName.add("罚球%");
		/* 11罚球命中 */columnsName.add("罚球命中");
		/* 12罚球出手 */columnsName.add("罚球出手");
		/* 13进攻 */columnsName.add("进攻");
		/* 14防守 */columnsName.add("防守");
		/* 15篮板 */columnsName.add("篮板");
		/* 16助攻 */columnsName.add("助攻");
		/* 17犯规 */columnsName.add("犯规");
		/* 18抢断 */columnsName.add("抢断");
		/* 19失误 */columnsName.add("失误");
		/* 20盖帽 */columnsName.add("盖帽");
		/* 21得分 */columnsName.add("得分");

		Vector data = new Vector();
		for (int i = 0; i < players.length; i++) {
			Vector rowData = new Vector();
//			/* 01球员图片 */rowData.add("图片");
			/* 02球员姓名 */rowData.add(players[i].getName());
			/* 04分钟 */rowData.add(players[i].getTime());
			/* 05投篮命中数 */rowData.add(players[i].getHitNo());
			/* 06投篮出手数 */rowData.add(players[i].getHandNo());
			/* 07三分命中率 */rowData.add(FrameSize.roundForNumber((double)players[i].getThreeHitRate()*100,1));
			/* 08三分命中 */rowData.add(players[i].getThreeHitNo());
			/* 09三分出手 */rowData.add(players[i].getThreeHandNo());
			/* 10罚球命中率 */rowData.add(FrameSize.roundForNumber((double) players[i].getPenaltyHitRate()*100,1));
			/* 11罚球命中 */rowData.add(players[i].getHitNo());
			/* 12罚球出手 */rowData.add(players[i].getHandNo());
			/* 13进攻 */rowData.add(players[i].getOffenseRebs());
			/* 14防守 */rowData.add(players[i].getDefenceRebs());
			/* 15篮板 */rowData.add(players[i].getRebs());
			/* 16助攻 */rowData.add(players[i].getHelp());
			/* 17犯规 */rowData.add(players[i].getFoulsNo());
			/* 18抢断 */rowData.add(players[i].getStealsNo());
			/* 19失误 */rowData.add(players[i].getMistakesNo());
			/* 20盖帽 */rowData.add(players[i].getBlockNo());
			/* 21得分 */rowData.add(players[i].getPoints());
			data.add(rowData);
		}
		DefaultTableModel playerTable = new DefaultTableModel(data, columnsName);
		MyTable myPlayerTable = new MyTable(playerTable);
		JScrollPane playerScrollPane = new JScrollPane(myPlayerTable);
		myPlayerTable.getColumnModel().getColumn(0).setPreferredWidth(150);
		myPlayerTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MyFrame.onePlayerPanel.showOne((String) myPlayerTable.getModel().getValueAt(
							myPlayerTable.getSelectedRow(), 0));
					MyFrame.card.show(MyFrame.mainpanel, "oneplayer");
				}
			}
		});
		return playerScrollPane;
	}

	
	/** 更改图片大小 */
	private ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (width == iconWidth && height == iconHeight) {
			return icon;
		}
		Image image = icon.getImage();
		image = image.getScaledInstance(iconWidth, iconHeight,
				Image.SCALE_DEFAULT);

		return new ImageIcon(image);
	}
	
}
