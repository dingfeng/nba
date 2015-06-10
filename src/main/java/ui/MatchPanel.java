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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import bl.matchbl.MatchController;
import bl.teambl.TeamController;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;
import ui.mainui.MyTable;

public class MatchPanel extends JPanel {
	Matchblservice matchController = new MatchController();
	Teamblservice teamController = new TeamController();
	static JScrollPane todatyMatchScrollPane = new JScrollPane();
	static JScrollPane oneMatchScrollPane = new JScrollPane();
	static CardLayout card = new CardLayout();
	static JPanel matchmain = new JPanel();

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		matchmain.setLayout(card);
		matchmain.setBounds(0, 40, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		matchmain.add("all", todatyMatchScrollPane);
		matchmain.add("one", oneMatchScrollPane);
		card.show(matchmain, "all");
		this.add(matchmain);

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
		System.out.println(date);
		todatyMatchScrollPane.getViewport().removeAll();
		JPanel matchPanel = new JPanel();
		matchPanel.setLayout(null);
		MatchesPO[] matches = matchController.getTimeMatches(date);
		JLabel[] matchLabel = new JLabel[matches.length];
		for (int i = 0; i < matches.length; i++) {
			MatchTeamPO team1 = matches[i].getTeam1();
			MatchTeamPO team2 = matches[i].getTeam2();
			System.out.println(team1.getName()+"-"+team2.getName());

			matchLabel[i] = new JLabel();
			matchLabel[i].setBounds(0, i * 200, FrameSize.width, 200);
			JLabel teamImage1 = new JLabel(scaleImage(new ImageIcon(
					teamController.getTeamData(team1.getName())
							.getImage()), 150, 150));
			teamImage1.setBounds(200, 25, 150, 150);
			JLabel teamImage2 = new JLabel(scaleImage(new ImageIcon(
					teamController.getTeamData(team2.getName())
							.getImage()), 150, 150));
			teamImage2.setBounds(FrameSize.width - 350, 25, 150, 150);
			matchLabel[i].add(teamImage1);
			matchLabel[i].add(teamImage2);

			JLabel[][] scores = new JLabel[3][4];
			for (int j = 0; j < 4; j++) {
				scores[0][j] = new JLabel(String.valueOf(j + 1));
				scores[1][j] = new JLabel(String.valueOf(team1.getScores()[j]));
				scores[2][j] = new JLabel(String.valueOf(team2.getScores()[j]));
				if (team1.getScores()[j] > team2.getScores()[j]) {
					scores[1][j].setForeground(Color.red);
				} else {
					scores[2][j].setForeground(Color.red);
				}
				for (int k = 0; k < 3; k++) {
					scores[k][j].setBounds(350 + (FrameSize.width - 700 - 120)
							* (j + 1) / 5 + j * 30, 40 + k * 50, 30, 30);
					matchLabel[i].add(scores[k][j]);
				}

			}

			matchLabel[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					setOneMatch(team1, team2);
				}
			});
			matchPanel.add(matchLabel[i]);
		}
		matchPanel.setOpaque(false);
		todatyMatchScrollPane.getViewport().add(matchPanel);
		matchPanel.setPreferredSize(new Dimension(FrameSize.width, 200*(matches.length+1)));
		todatyMatchScrollPane.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		todatyMatchScrollPane.setOpaque(false);
		todatyMatchScrollPane.getViewport().setOpaque(false);
		todatyMatchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		todatyMatchScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		this.add(todatyMatchScrollPane);
		card.show(matchmain, "all");
		this.repaint();
	}

	/** 一场比赛的具体信息 */
	public void setOneMatch(MatchTeamPO team1,MatchTeamPO team2){
		oneMatchScrollPane.getViewport().removeAll();
		JPanel matchPanel = new JPanel();
		matchPanel.setLayout(null);
		JLabel teamImage1 = new JLabel(scaleImage(new ImageIcon(teamController.getTeamData(team1.getName())
							.getImage()),150,150));
		teamImage1.setBounds(200,25,150,150);
		JLabel teamImage2 = new JLabel(scaleImage(
					new ImageIcon(teamController.getTeamData(team2.getName())
							.getImage()),150,150));
		teamImage2.setBounds(FrameSize.width-350,25,150,150);
		matchPanel.add(teamImage1);
		matchPanel.add(teamImage2);
		
		JLabel [][] scores = new JLabel[3][4];
		for(int j=0;j<4;j++){
			scores[0][j]=new JLabel(String.valueOf(j+1));
			scores[1][j]=new JLabel(String.valueOf(team1.getScores()[j]));
			scores[2][j]=new JLabel(String.valueOf(team2.getScores()[j]));
			if(team1.getScores()[j]>team2.getScores()[j]){
				scores[1][j].setForeground(Color.red);
			}else{
				scores[2][j].setForeground(Color.red);
			}
			for(int k=0;k<3;k++){
				scores[k][j].setBounds(350+(FrameSize.width-700-120)*(j+1)/5+j*30,40+k*50,30,30);
				matchPanel.add(scores[k][j]);
			}
		}
		
		MyTable playerTable1 = setPlayerTable(team1.getPlayers());
		MyTable playerTable2 = setPlayerTable(team2.getPlayers());
		playerTable1.setBounds(0,300,FrameSize.width,200);
		playerTable2.setBounds(0,600,FrameSize.width,200);
		
		matchPanel.setOpaque(false);
		oneMatchScrollPane.getViewport().add(matchPanel);
		matchPanel.setPreferredSize(new Dimension(FrameSize.width,900));
		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		oneMatchScrollPane.setOpaque(false);
		oneMatchScrollPane.getViewport().setOpaque(false);
		oneMatchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		oneMatchScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// this.add(todatyMatchScrollPane);
		card.show(matchmain, "one");
		this.repaint();
	}
	
	/**每个队伍的球员表现*/
	private MyTable setPlayerTable(MatchPlayerPO[] players){

		Vector columnsName = new Vector();
		/*01球员图片*/columnsName.add("球员");
		/*02球员姓名*/columnsName.add("姓名");
		/*03位置*/columnsName.add("位置");
		/*04分钟*/columnsName.add("分钟");
		/*05投篮命中数*/columnsName.add("命中");
		/*06投篮出手数*/columnsName.add("出手");
		/*07三分命中率*/columnsName.add("三分%");
		/*08三分命中*/columnsName.add("三分命中");
		/*09三分出手*/columnsName.add("三分出手");
		/*10罚球命中率*/columnsName.add("罚球%");
		/*11罚球命中*/columnsName.add("罚球命中");
		/*12罚球出手*/columnsName.add("罚球出手");
		/*13进攻*/columnsName.add("进攻");
		/*14防守*/columnsName.add("防守");
		/*15篮板*/columnsName.add("篮板");
		/*16助攻*/columnsName.add("助攻");
		/*17犯规*/columnsName.add("犯规");
		/*18抢断*/columnsName.add("抢断");
		/*19失误*/columnsName.add("失误");
		/*20盖帽*/columnsName.add("盖帽");
		/*21得分*/columnsName.add("得分");
		
		Vector data = new Vector();
		for(int i=0;i<players.length;i++){
			Vector rowData = new Vector();
			/*01球员图片*/rowData.add("图片");
			/*02球员姓名*/rowData.add(players[i].getName());
			/*03位置*/rowData.add(players[i].getLocation());
			/*04分钟*/rowData.add(players[i].getTime());
			/*05投篮命中数*/rowData.add(players[i].getHitNo());
			/*06投篮出手数*/rowData.add(players[i].getHandNo());
			/*07三分命中率*/rowData.add((double)players[i].getThreeHitNo()/players[i].getThreeHandNo());
			/*08三分命中*/rowData.add(players[i].getThreeHitNo());
			/*09三分出手*/rowData.add(players[i].getThreeHandNo());
			/*10罚球命中率*/rowData.add((double)players[i].getPenaltyHitNo()/players[i].getPenaltyHandNo());
			/*11罚球命中*/rowData.add(players[i].getHitNo());
			/*12罚球出手*/rowData.add(players[i].getHandNo());
			/*13进攻*/rowData.add(players[i].getOffenseRebs());
			/*14防守*/rowData.add(players[i].getDefenceRebs());
			/*15篮板*/rowData.add(players[i].getRebs());
			/*16助攻*/rowData.add(players[i].getHelp());
			/*17犯规*/rowData.add(players[i].getFoulsNo());
			/*18抢断*/rowData.add(players[i].getStealsNo());
			/*19失误*/rowData.add(players[i].getMistakesNo());
			/*20盖帽*/rowData.add(players[i].getBlockNo());
			/*21得分*/rowData.add(players[i].getPoints());
			data.add(rowData);
		}
		DefaultTableModel playerTable = new DefaultTableModel(data,columnsName);
		MyTable myPlayerTable = new MyTable(playerTable);
		myPlayerTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					
				}
			}
		});
		return myPlayerTable;
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