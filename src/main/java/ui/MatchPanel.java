package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import dataservice.playerdataservice.SeasonType;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.OldMatch;
import bl.matchbl.MatchController;
import bl.teambl.TeamController;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;
import ui.mainui.MyFont;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;

public class MatchPanel extends JPanel {
	Matchblservice matchController = new MatchController();
	Teamblservice teamController = new TeamController();
	static JScrollPane todatyMatchScrollPane = new JScrollPane();

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		todatyMatchScrollPane.setBounds(0, 40, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		this.add(todatyMatchScrollPane);

	}

	/** 查找栏 */
	private JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		DateChooseButton dateButton = new DateChooseButton();
		dateButton.setBounds(0, 5, 150, 30);
		dateButton.setEnd(this);
		headerPanel.add(dateButton);

		return headerPanel;

	}

	/** 一天的所有比赛 */
	public void setTodayMatches(Date date) {
		if (date.getYear() + 1900 > 1984) {
			System.out.println(date);
			todatyMatchScrollPane.getViewport().removeAll();
			JPanel matchPanel = new JPanel();
			matchPanel.setLayout(null);
			MatchesPO[] matches = matchController.getTimeMatches(date);
			System.out.println(matches.length);
			if (matches.length != 0) {
				JLabel[] matchLabel = new JLabel[matches.length];
				for (int i = 0; i < matches.length; i++) {
					MatchesPO match = matchController.getMatchById(matches[i]
							.getMatchId());
					MatchTeamPO team1 = match.getTeam1();
					MatchTeamPO team2 = match.getTeam2();
					System.out.println(team1.getName() + "-" + team2.getName());

					matchLabel[i] = new JLabel();
					matchLabel[i].setBounds(0, i * 200, FrameSize.width, 200);
					JLabel teamImage1 = new JLabel(FrameSize.scaleImage(new ImageIcon(
							teamController.getTeamData(team1.getName())
									.getImage()), 150, 150));
					teamImage1.setBounds(200, 50, 150, 150);
					JLabel teamImage2 = new JLabel(FrameSize.scaleImage(new ImageIcon(
							teamController.getTeamData(team2.getName())
									.getImage()), 150, 150));
					teamImage2.setBounds(FrameSize.width - 350, 50, 150, 150);
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
					matchLabel[i].add(teamImage1);
					matchLabel[i].add(teamImage2);

					JLabel teamName1 = new JLabel(team1.getName());
					JLabel teamName2 = new JLabel(team2.getName());
					teamName1.setFont(MyFont.font2);
					teamName2.setFont(MyFont.font2);
					teamName1.setBounds(100, 60, 100, 30);
					teamName2.setBounds(FrameSize.width - 150, 60, 100, 30);
					matchLabel[i].add(teamName1);
					matchLabel[i].add(teamName2);

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
					matchLabel[i].add(teamScores1);
					matchLabel[i].add(teamScores2);

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
									/ (column + 2) + j * 30, 40 + k * 50, 30,
									30);
							matchLabel[i].add(scores[k][j]);
						}

					}

					matchLabel[i].addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							MyFrame.onematchpanel.setOneNowMatch(team1, team2);
							MyFrame.card.show(MyFrame.mainpanel, "onematch");
						}
					});
					matchPanel.add(matchLabel[i]);
				}
			}
			else{
				JLabel noMatch = new JLabel("该日无比赛");
				noMatch.setFont(MyFont.font2);
				noMatch.setForeground(Color.red);
				noMatch.setBounds(FrameSize.width/2-100,300,200,50);
				matchPanel.add(noMatch);
			}
			matchPanel.setOpaque(false);
			todatyMatchScrollPane.getViewport().add(matchPanel);
			matchPanel.setPreferredSize(new Dimension(FrameSize.width,
					200 * (matches.length + 1)));
			todatyMatchScrollPane.setBounds(0, 0, FrameSize.width,
					FrameSize.height * 7 / 8 - 40);
			todatyMatchScrollPane.setOpaque(false);
			todatyMatchScrollPane.getViewport().setOpaque(false);
			todatyMatchScrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			todatyMatchScrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			this.repaint();
		} else {
			System.out.println(date);
			System.out.println(date.getYear()+1900);
			System.out.println(date.getMonth()+1);
			System.out.println(date.getDate());
			todatyMatchScrollPane.getViewport().removeAll();
			JPanel matchPanel = new JPanel();
			matchPanel.setLayout(null);
			OldMatch[] matches = matchController.getOldMatch(date.getYear()+1900, 0,
					10, SeasonType.REGULAR);
			System.out.println(matches.length);
			JLabel[] matchLabel = new JLabel[matches.length];
			for (int i = 0; i < matches.length; i++) {
				OldMatch match = matchController.getOldMatchInfo(matches[i]
						.getMatchId());
				String team1 = match.getHost_team();
				String team2 = match.getGuestTeam();
				System.out.println(team1 + "-" + team2);

				matchLabel[i] = new JLabel();
				matchLabel[i].setBounds(0, i * 200, FrameSize.width, 200);
//				JLabel teamImage1 = new JLabel(
//						FrameSize.scaleImage(
//								new ImageIcon(teamController.getTeamData(team1)
//										.getImage()), 150, 150));
//				teamImage1.setBounds(200, 25, 150, 150);
//				JLabel teamImage2 = new JLabel(
//						FrameSize.scaleImage(
//								new ImageIcon(teamController.getTeamData(team2)
//										.getImage()), 150, 150));
//				teamImage2.setBounds(FrameSize.width - 350, 25, 150, 150);
//				matchLabel[i].add(teamImage1);
//				matchLabel[i].add(teamImage2);

				JLabel teamName1 = new JLabel(team1);
				JLabel teamName2 = new JLabel(team2);
				teamName1.setFont(MyFont.font2);
				teamName2.setFont(MyFont.font2);
				teamName1.setBounds(100, 60, 100, 30);
				teamName2.setBounds(FrameSize.width - 150, 60, 100, 30);
				matchLabel[i].add(teamName1);
				matchLabel[i].add(teamName2);

				String[] scores1 = match.getPt1();
				String[] scores2 = match.getPt2();
				JLabel teamScores1 = new JLabel(scores1[scores1.length - 1]);
				JLabel teamScores2 = new JLabel(scores2[scores2.length - 1]);
				teamScores1.setFont(MyFont.font3);
				teamScores2.setFont(MyFont.font3);				
//				if (Integer.parseInt(scores1[scores1.length - 1]) > Integer
//						.parseInt(scores2[scores2.length - 1])) {
//					teamScores1.setForeground(Color.red);
//				} else {
//					teamScores2.setForeground(Color.red);
//				}
				teamScores1.setBounds(100, 120, 50, 30);
				teamScores2.setBounds(FrameSize.width - 150, 120, 50, 30);
				matchLabel[i].add(teamScores1);
				matchLabel[i].add(teamScores2);

				int column = scores1.length-1;
				JLabel[][] scores = new JLabel[3][column];
				for (int j = 0; j < column; j++) {
					scores[0][j] = new JLabel(String.valueOf(j + 1));
					scores[1][j] = new JLabel(scores1[j]);
					scores[2][j] = new JLabel(scores2[j]);
//					if (Integer.parseInt(scores1[j]) > Integer
//							.parseInt(scores2[j])) {
//						scores[1][j].setForeground(Color.red);
//					} else {
//						scores[2][j].setForeground(Color.red);
//					}
					for (int k = 0; k < 3; k++) {
						scores[k][j].setBounds(350
								+ (FrameSize.width - 700 - 120) * (j + 1)
								/ (column + 2) + j * 30, 40 + k * 50, 30, 30);
						matchLabel[i].add(scores[k][j]);
					}

				}

				Image matchImage = match.getImg();
				matchLabel[i].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						MyFrame.oldmatchpanel.setOneOldMatch(matchImage);
						MyFrame.card.show(MyFrame.mainpanel, "oldmatch");
					}
				});
				matchPanel.add(matchLabel[i]);
			}
			matchPanel.setOpaque(false);
			todatyMatchScrollPane.getViewport().add(matchPanel);
			matchPanel.setPreferredSize(new Dimension(FrameSize.width,
					200 * (matches.length + 1)));
			todatyMatchScrollPane.setBounds(0, 0, FrameSize.width,
					FrameSize.height * 7 / 8 - 40);
			todatyMatchScrollPane.setOpaque(false);
			todatyMatchScrollPane.getViewport().setOpaque(false);
			todatyMatchScrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			todatyMatchScrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			this.repaint();
		}
	}


	private void test() {
		JLabel[] jLabel = new JLabel[100];
		JScrollPane jScrollPane = new JScrollPane();
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.red);
		jScrollPane.setBounds(0, 40, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		for (int i = 0; i < 100; i++) {
			jLabel[i] = new JLabel(String.valueOf(i));
			jLabel[i].setBounds(0, i * 30, 100, 30);
			jPanel.add(jLabel[i]);
		}
		jScrollPane.getViewport().add(jPanel);
		jPanel.setPreferredSize(new Dimension(FrameSize.width, 3000));
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jScrollPane);
		this.repaint();
	}


}