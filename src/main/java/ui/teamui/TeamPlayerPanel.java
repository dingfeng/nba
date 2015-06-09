package ui.teamui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.HPlayerPO;
import po.PlayerNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;

public class TeamPlayerPanel extends JPanel {

	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	MyToggleButton data;
	MyToggleButton base;

	Teamblservice tc = new TeamController();

	public TeamPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 30, FrameSize.width, FrameSize.height * 3 / 4 - 80);
		this.setBackground(Color.white);

		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 30, FrameSize.width,
				FrameSize.height * 3 / 4 - 180);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		setButton();
		this.repaint();
	}

	void setButton() {
		data = new MyToggleButton("数据", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		base = new MyToggleButton("信息", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		data.setBounds(FrameSize.width - 100, 0, 50, 30);
		base.setBounds(FrameSize.width - 50, 0, 50, 30);
		data.addActionListener(e -> setdata());
		base.addActionListener(e -> setbase());
		data.setForeground(Color.white);
		base.setForeground(Color.white);
		data.setSelected(true);
		this.add(data);
		this.add(base);
	}

	void setdata() {
		data.setSelected(true);
		base.setSelected(false);
		setTable();
	}

	void setbase() {
		data.setSelected(false);
		base.setSelected(true);
		setTable();
	}

	void setBase(HPlayerPO[] players) {
		columnsName.clear();
		columnsName.add("姓名");
		columnsName.add("位置");
		columnsName.add("身高");
		columnsName.add("体重");
		columnsName.add("生日");
		columnsName.add("出生地");
		columnsName.add("高中");
		columnsName.add("大学");
		columnsName.add("球号");
		columnsName.add("球队");
		columnsName.add("比赛地");
		rowimage.clear();

		for (int i = 1; i < players.length; i++) {
			HPlayerPO player = players[i];
			Vector data = new Vector();
			data.add(player.getName());
			data.add(player.getPosition());
			data.add(player.getHeight());
			data.add(player.getWeight());
			data.add(player.getBirthday());
			data.add(player.getBirthCity());
			data.add(player.getHigh_school());
			data.add(player.getUniversity());
			data.add(player.getName());
			data.add(player.getTeama());
			data.add(player.getGameArea());

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		this.add(jScrollPane);
		this.repaint();
	}

	void setData(PlayerNormalPO[] players) {
		columnsName.clear();
		columnsName.add("球员");
		columnsName.add("场数");
		columnsName.add("先发");
		columnsName.add("分钟");
		columnsName.add("%");
		columnsName.add("三分%");
		columnsName.add("罚球%");
		columnsName.add("进攻");
		columnsName.add("防守");
		columnsName.add("场均篮板");
		columnsName.add("场均助攻");
		columnsName.add("场均抢断");
		columnsName.add("场均盖帽");
		columnsName.add("失误");
		columnsName.add("犯规");
		columnsName.add("场均得分");

		rowimage.clear();
		for (int i = 1; i < players.length; i++) {
			PlayerNormalPO player = players[i];
			Vector data = new Vector();
			data.add(player.getName());
			data.add(player.getMatchNo());
			data.add(player.getFirstServiceNo());
			data.add(player.getTime());
			data.add(player.getHitRate());
			data.add(player.getThreeHitRate());
			data.add(player.getPenaltyHitRate());
			data.add(player.getOffendRebsNo());
			data.add(player.getDefenceRebsNo());
			data.add(player.getRebs());
			data.add(player.getAssistNo());
			data.add(player.getStealsNo());
			data.add(player.getBlockNo());
			data.add(player.getMistakesNo());
			data.add(player.getFoulsNo());
			data.add(player.getPoints());
			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		this.add(jScrollPane);
		this.repaint();
	}

	public void setTable() {
		
		String teamname = MyFrame.teampanel.nameAbridgeresult.getText();
		String[] playername = tc.getPlayers(teamname);
		HPlayerPO[] players = new HPlayerPO[playername.length];
		for (int i = 0; i < playername.length; i++) {
			players[i] = tc.getPlayerBase(playername[i]);
		}
		if (base.isSelected()) {
			setBase(players);
		} else {
			setData(tc.getAllPlayerMatchAve(2014, teamname, SeasonType.REGULAR));
		}
	}

}
