package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyFrame;
import vo.HotPlayerTeam;
import vo.TeamSortBy;
import bl.playerbl.PlayerController;
import dataservice.playerdataservice.SeasonType;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show = new JPanel();

	JLabel[] name = new JLabel[5];

	JLabel portrait_1 = new JLabel();
	JLabel portrait_2 = new JLabel();
	JLabel portrait_3 = new JLabel();
	JLabel portrait_4 = new JLabel();
	JLabel portrait_5 = new JLabel();

	JLabel score_1 = new JLabel();
	JLabel score_2 = new JLabel();
	JLabel score_3 = new JLabel();
	JLabel score_4 = new JLabel();
	JLabel score_5 = new JLabel();

	JLabel crown=new JLabel();
	JButton[] choose=new JButton[8];
//	TeamController tc = new TeamController(2012);
	PlayerController pc = new PlayerController();
	int hottype = 1;

	HotPanel hotpanel = this;

	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setOpaque(false);

		setTag();
		setShow();
		showchoose(1);
		this.add(tag);
		this.add(show);
		this.repaint();

	}

	/** 设置左侧选择框 */
	void setTag() {

		tag.setLayout(null);
		tag.setBounds(0, 0, FrameSize.width / 6, 11 * FrameSize.height / 12);
		int height = 11 * FrameSize.height / 12;
		tag.setBackground(Color.white);
		JButton today_player = new MyButton("当天热点球员",
				FrameSize.buttonbackColor, Color.GRAY);
		JButton season_player = new MyButton("赛季热点球员",
				FrameSize.buttonbackColor, Color.GRAY);
		JButton season_team = new MyButton("赛季热点球队", FrameSize.buttonbackColor,
				Color.GRAY);
		JButton fast_player = new MyButton("进步最快球员", FrameSize.buttonbackColor,
				Color.GRAY);

		today_player.setBounds(10, 40, 150, 50);
		season_player.setBounds(10, 40 + height / 4, 150, 50);
		season_team.setBounds(10, 40 + height / 2, 150, 50);
		fast_player.setBounds(10, 40 + 3 * height / 4, 150, 50);

		today_player.setBackground(FrameSize.buttonbackColor);
		season_player.setBackground(FrameSize.buttonbackColor);
		season_team.setBackground(FrameSize.buttonbackColor);
		fast_player.setBackground(FrameSize.buttonbackColor);

		today_player.setForeground(Color.white);
		season_player.setForeground(Color.white);
		season_team.setForeground(Color.white);
		fast_player.setForeground(Color.white);
		
		today_player.setFont(new Font("微软雅黑",Font.PLAIN,18));
		season_player.setFont(new Font("微软雅黑",Font.PLAIN,18));
		season_team.setFont(new Font("微软雅黑",Font.PLAIN,18));
		fast_player.setFont(new Font("微软雅黑",Font.PLAIN,18));

		today_player.addActionListener(e -> showchoose(1));
		season_player.addActionListener(e -> showchoose(2));
		season_team.addActionListener(e -> showchoose(3));
		fast_player.addActionListener(e -> showchoose(4));

		tag.add(today_player);
		tag.add(season_player);
		tag.add(season_team);
		tag.add(fast_player);
	}

	/** 设置右侧显示panel */
	void setShow() {
		show.setLayout(null);
		show.setBackground(Color.white);
		show.setBounds(FrameSize.width / 6, 0, 5 * FrameSize.width / 6,
				11 * FrameSize.height / 12);
		for(int i=0;i<8;i++){
		choose[i]=new JButton();
		choose[i].setBackground(Color.gray);
		}
		for (int i = 0; i < 5; i++) {
			name[i] = new JLabel();
			name[i].setBounds(FrameSize.width / 3, (i + 1) * FrameSize.height
					/ 7, FrameSize.width / 6, FrameSize.height / 16);
			name[i].setForeground(Color.white);
			name[i].setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			name[i].addMouseListener(new show());
			show.add(name[i]);
		}
		name[0].setBounds(FrameSize.width / 3, FrameSize.height / 10,
				FrameSize.width / 5, FrameSize.height / 10);
		name[0].setFont(new Font("Comic Sans MS", Font.BOLD, 24));

		score_1.setBounds(FrameSize.width / 3 + 250, FrameSize.height / 10,
				100, 100);
		score_2.setBounds(FrameSize.width / 3 + 250, 2 * FrameSize.height / 7,
				80, 80);
		score_3.setBounds(FrameSize.width / 3 + 250, 3 * FrameSize.height / 7,
				80, 80);
		score_4.setBounds(FrameSize.width / 3 + 250, 4 * FrameSize.height / 7,
				80, 80);
		score_5.setBounds(FrameSize.width / 3 + 250, 5 * FrameSize.height / 7,
				80, 80);

		score_1.setForeground(Color.white);
		score_2.setForeground(Color.white);
		score_3.setForeground(Color.white);
		score_4.setForeground(Color.white);
		score_5.setForeground(Color.white);

		
		crown.setBounds(FrameSize.width / 3 - 185, FrameSize.height / 15, 72, 72);
		crown.setIcon(new ImageIcon("image/crown.png"));
		
		show.add(score_1);
		show.add(score_2);
		show.add(score_3);
		show.add(score_4);
		show.add(score_5);
		show.add(crown);

	}

	/** 设置选择 */
	void showchoose(int type) {
		hottype = type;
//		choose.setVisible(false);
		switch (type) {
		case 3:
			choose[0].setText("得分");
			choose[1].setText("篮板");
			choose[2].setText("助攻");
			choose[3].setText("盖帽");
			choose[4].setText("抢断");
			choose[5].setText("三分%");
			choose[6].setText("%");
			choose[7].setText("罚球%");
			for(int i=0;i<8;i++){
				choose[i].setBounds(FrameSize.width*5/48*i,0,FrameSize.width*5/48 , 50);
				show.add(choose[i]);
				String sortBy=choose[i].getText();
//				choose[i].addActionListener(e -> showMessage_team(sortBy));
			}
			
			
			

//			showMessage_team();
//			choose.addActionListener(e -> showMessage_team());
			break;
		case 4:
			choose[0].setText("得分提升率");
			choose[1].setText("篮板提升率");
			choose[2].setText("助攻提升率");
			for(int i=0;i<8;i++){
				choose[i].setBounds(FrameSize.width*5/18*i,0,FrameSize.width*5/18 , 50);
				String sortBy=choose[i].getText();
//				choose[i].addActionListener(e -> showMessage_player(sortBy));
				show.add(choose[i]);
			}
			
			

//			showMessage_player("得分提升率");
//			choose.addActionListener(e -> showMessage_player());
			break;
		default:
			choose[0].setText("得分");
			choose[1].setText("篮板");
			choose[2].setText("助攻");
			choose[3].setText("盖帽");
			choose[4].setText("抢断");
			for(int i=0;i<8;i++){
				choose[i].setBounds(FrameSize.width/6*i,0,FrameSize.width/6 , 50);
				String sortBy=choose[i].getText();
//				choose[i].addActionListener(e -> showMessage_player(sortBy));
				show.add(choose[i]);
			}
			

//			showMessage_player("得分");
//			choose.addActionListener(e -> showMessage_player());

			break;

		}

		
		show.repaint();
		this.repaint();
	}

	/** 热点球员 */
	void showMessage_player(String sort) {

		String sortBy=sortby(sort);
		HotPlayerTeam[] players = new HotPlayerTeam[5];
		switch (hottype) {
		case 1:
			players = pc.getDayHotPlayer(sortBy);
			break;
		case 2:
			players = pc.getSeasonHotPlayer(2012,sortBy,SeasonType.REGULAR);
			break;
		case 4:
			players = pc.getPromotePlayer(2012,sortBy);
		}
		try {
			portrait_1.setIcon(scaleImage(
					new ImageIcon(pc.findPlayer(players[0].getName())
							.getPortrait()), 115, 92));
		} catch (Exception e) {
			portrait_1.setIcon(scaleImage(new ImageIcon(Toolkit
					.getDefaultToolkit().getImage("image/noimage.png")), 115,
					92));
		}

		portrait_1.setBounds(FrameSize.width / 3 - 228, FrameSize.height / 10,
				185, 123);
		try {
			portrait_2.setIcon(scaleImage(
					new ImageIcon(pc.findPlayer(players[1].getName())
							.getPortrait()), 69, 55));
		} catch (Exception e) {
			portrait_2
					.setIcon(scaleImage(
							new ImageIcon(Toolkit.getDefaultToolkit().getImage(
									"image/noimage.png")), 69, 55));
		}
		portrait_2.setBounds(FrameSize.width / 3 - 100,
				2 * FrameSize.height / 7, 69, 55);
		try {
			portrait_3.setIcon(scaleImage(
					new ImageIcon(pc.findPlayer(players[2].getName())
							.getPortrait()), 69, 55));
		} catch (Exception e) {
			portrait_3
					.setIcon(scaleImage(
							new ImageIcon(Toolkit.getDefaultToolkit().getImage(
									"image/noimage.png")), 69, 55));
		}
		portrait_3.setBounds(FrameSize.width / 3 - 100,
				3 * FrameSize.height / 7, 69, 55);
		try {
			portrait_4.setIcon(scaleImage(
					new ImageIcon(pc.findPlayer(players[3].getName())
							.getPortrait()), 69, 55));
		} catch (Exception e) {
			portrait_4
					.setIcon(scaleImage(
							new ImageIcon(Toolkit.getDefaultToolkit().getImage(
									"image/noimage.png")), 69, 55));
		}
		portrait_4.setBounds(FrameSize.width / 3 - 100,
				4 * FrameSize.height / 7, 69, 55);
		try {
			portrait_5.setIcon(scaleImage(
					new ImageIcon(pc.findPlayer(players[4].getName())
							.getPortrait()), 69, 55));
		} catch (Exception e) {
			portrait_5
					.setIcon(scaleImage(
							new ImageIcon(Toolkit.getDefaultToolkit().getImage(
									"image/noimage.png")), 69, 55));
		}
		portrait_5.setBounds(FrameSize.width / 3 - 100,
				5 * FrameSize.height / 7, 69, 55);

		for (int i = 0; i < 5; i++) {
			name[i].setText(players[i].getName());
			// name[i].addMouseListener(new showPlayer());
		}

		score_1.setText(String.format("%.3f", players[0].getHotData()));
		score_2.setText(String.format("%.3f", players[1].getHotData()));
		score_3.setText(String.format("%.3f", players[2].getHotData()));
		score_4.setText(String.format("%.3f", players[3].getHotData()));
		score_5.setText(String.format("%.3f", players[4].getHotData()));

		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
		show.add(crown);
		show.repaint();
		this.repaint();
	}

	/** 热点球队 */
	void showMessage_team(String sortby) {
		TeamSortBy teamSortBy = null;
		
		if (sortby.equals("比赛场数")) {
			teamSortBy = TeamSortBy.matchNo;
		} else if (sortby.equals("投篮命中数")) {
			teamSortBy = TeamSortBy.hitNo;
		} else if (sortby.equals("投篮出手次数")) {
			teamSortBy = TeamSortBy.handNo;
		} else if (sortby.equals("三分命中数")) {
			teamSortBy = TeamSortBy.threeHitNo;
		} else if (sortby.equals("三分出手数")) {
			teamSortBy = TeamSortBy.threeHandNo;
		} else if (sortby.equals("罚球命中数")) {
			teamSortBy = TeamSortBy.penaltyHitNo;
		} else if (sortby.equals("罚球出手数")) {
			teamSortBy = TeamSortBy.penaltyHandNo;
		} else if (sortby.equals("进攻篮板数")) {
			teamSortBy = TeamSortBy.offenseRebs;
		} else if (sortby.equals("防守篮板数")) {
			teamSortBy = TeamSortBy.defenceRebs;
		} else if (sortby.equals("篮板数")) {
			teamSortBy = TeamSortBy.rebs;
		} else if (sortby.equals("助攻数")) {
			teamSortBy = TeamSortBy.assistNo;
		} else if (sortby.equals("抢断数")) {
			teamSortBy = TeamSortBy.stealsNo;
		} else if (sortby.equals("盖帽数")) {
			teamSortBy = TeamSortBy.blockNo;
		} else if (sortby.equals("失误数")) {
			teamSortBy = TeamSortBy.mistakesNo;
		} else if (sortby.equals("犯规数")) {
			teamSortBy = TeamSortBy.foulsNo;
		} else if (sortby.equals("比赛得分")) {
			teamSortBy = TeamSortBy.points;
		} else if (sortby.equals("投篮命中率")) {
			teamSortBy = TeamSortBy.hitRate;
		} else if (sortby.equals("三分命中率")) {
			teamSortBy = TeamSortBy.threeHitRate;
		} else if (sortby.equals("罚球命中率")) {
			teamSortBy = TeamSortBy.penaltyHitRate;
		} else if (sortby.equals("胜率")) {
			teamSortBy = TeamSortBy.winRate;
		} else if (sortby.equals("进攻回合")) {
			teamSortBy = TeamSortBy.offenseRound;
		} else if (sortby.equals("进攻效率")) {
			teamSortBy = TeamSortBy.offenseEfficiency;
		} else if (sortby.equals("防守效率")) {
			teamSortBy = TeamSortBy.defenceEfficiency;
		} else if (sortby.equals("进攻篮板效率")) {
			teamSortBy = TeamSortBy.drebsEfficiency;
		} else if (sortby.equals("防守篮板效率")) {
			teamSortBy = TeamSortBy.orebsEfficiency;
		} else if (sortby.equals("抢断效率")) {
			teamSortBy = TeamSortBy.stealsEfficiency;
		} else if (sortby.equals("助攻率")) {
			teamSortBy = TeamSortBy.assistEfficiency;
		}

		TeamMatchVO[] hotteam = tc.getHotTeams(teamSortBy);

		for (int i = 0; i < 5; i++) {
			name[i].setText(tc.getTeamData(hotteam[i].getName()).getName()
					+ " / " + hotteam[i].getName());
			// name[i].addMouseListener(new showTeam());
		}

		portrait_1.setIcon(scaleImage(
				new ImageIcon(tc.getTeamData(hotteam[0].getName()).getImage()),
				FrameSize.width / 8, FrameSize.width / 8));
		portrait_2.setIcon(scaleImage(
				new ImageIcon(tc.getTeamData(hotteam[1].getName()).getImage()),
				FrameSize.width / 12, FrameSize.width / 12));
		portrait_3.setIcon(scaleImage(
				new ImageIcon(tc.getTeamData(hotteam[2].getName()).getImage()),
				FrameSize.width / 12, FrameSize.width / 12));
		portrait_4.setIcon(scaleImage(
				new ImageIcon(tc.getTeamData(hotteam[3].getName()).getImage()),
				FrameSize.width / 12, FrameSize.width / 12));
		portrait_5.setIcon(scaleImage(
				new ImageIcon(tc.getTeamData(hotteam[4].getName()).getImage()),
				FrameSize.width / 12, FrameSize.width / 12));

		portrait_1.setBounds(11 * FrameSize.width / 60, FrameSize.height / 10,
				FrameSize.width / 8, FrameSize.width / 8);
		portrait_2.setBounds(7 * FrameSize.width / 30,
				2 * FrameSize.height / 7, FrameSize.width / 12,
				FrameSize.width / 12);
		portrait_3.setBounds(7 * FrameSize.width / 30,
				3 * FrameSize.height / 7, FrameSize.width / 12,
				FrameSize.width / 12);
		portrait_4.setBounds(7 * FrameSize.width / 30,
				4 * FrameSize.height / 7, FrameSize.width / 12,
				FrameSize.width / 12);
		portrait_5.setBounds(7 * FrameSize.width / 30,
				5 * FrameSize.height / 7, FrameSize.width / 12,
				FrameSize.width / 12);

		score_1.setText(String.format("%.3f", hotteam[0].getHotData()));
		score_2.setText(String.format("%.3f", hotteam[1].getHotData()));
		score_3.setText(String.format("%.3f", hotteam[2].getHotData()));
		score_4.setText(String.format("%.3f", hotteam[3].getHotData()));
		score_5.setText(String.format("%.3f", hotteam[4].getHotData()));

		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
		show.remove(crown);
		show.repaint();
		this.repaint();
	}

	/** 跳转 */
	class show implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String info = ((JLabel) e.getSource()).getText();
			switch (hottype) {
			case 3:
//				MyFrame.teampanel.findClick(info.substring(info.length() - 3));
				MyFrame.card.show(MyFrame.mainpanel, "team");
				break;
			default:
//				MyFrame.playerpanel.findPlayerClick(info);
				MyFrame.card.show(MyFrame.mainpanel, "player");
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JLabel) e.getSource()).setOpaque(true);
			((JLabel) e.getSource()).setBackground(Color.gray);
			show.repaint();
			hotpanel.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JLabel) e.getSource()).setOpaque(false);
			((JLabel) e.getSource()).repaint();
			show.repaint();
			hotpanel.repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private String sortby(String sortBy) {
		String playerSortBy = null;

		if (sortBy.equals("得分")) {
			playerSortBy = "points";
		} else if (sortBy.equals("篮板")) {
			playerSortBy = "rebs";
		} else if (sortBy.equals("助攻")) {
			playerSortBy = "assist";
		} else if (sortBy.equals("得分/篮板/助攻")) {
			playerSortBy = "scoring_rebound_assist";
		} else if (sortBy.equals("盖帽")) {
			playerSortBy = "block";
		} else if (sortBy.equals("抢断")) {
			playerSortBy = "steal";
		} else if (sortBy.equals("犯规")) {
			playerSortBy = "foul";
		} else if (sortBy.equals("失误")) {
			playerSortBy = "mistake";
		} else if (sortBy.equals("分钟")) {
			playerSortBy = "minute";
		} else if (sortBy.equals("效率")) {
			playerSortBy = "efficiency";
		} else if (sortBy.equals("投篮")) {
			playerSortBy = "shot";
		} else if (sortBy.equals("三分")) {
			playerSortBy = "three_points";
		} else if (sortBy.equals("罚球")) {
			playerSortBy = "freeThrow";
		} else if (sortBy.equals("两双")) {
			playerSortBy = "twoPair";
		} else if (sortBy.equals("得分提升率")) {
			playerSortBy = "points_uprate";
		} else if (sortBy.equals("篮板提升率")) {
			playerSortBy = "rebs_uprate";
		} else if (sortBy.equals("助攻提升率")) {
			playerSortBy = "help_uprate";
		}
		return playerSortBy;

	}

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
