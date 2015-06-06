package ui.teamui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dataservice.playerdataservice.SeasonType;
import po.TeamHighPO;
import po.TeamNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import ui.mainui.UneditableTextField;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;

public class TeamDataPanel extends JPanel {

	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	Teamblservice tc = new TeamController();
	MyToggleButton alldata;
	MyToggleButton avedata;
	boolean ave = true;
	JTextField[] teamlabel = new UneditableTextField[54];

	public TeamDataPanel() {
		this.setLayout(null);
		this.setBounds(0, 30, FrameSize.width, FrameSize.height * 3 / 4 - 80);
		this.setBackground(Color.white);
		setText();
		this.repaint();
	}

	public void setText() {
		alldata = new MyToggleButton("总数", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		avedata = new MyToggleButton("场均", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		alldata.setBounds(FrameSize.width - 100, 0, 50, 30);
		avedata.setBounds(FrameSize.width - 50, 0, 50, 30);
		alldata.addActionListener(e -> showall());
		avedata.addActionListener(e -> showave());
		alldata.setForeground(Color.white);
		avedata.setForeground(Color.white);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 30, FrameSize.width,
				FrameSize.height * 7 / 8 - 30);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		this.add(alldata);
		this.add(avedata);
	}

	void showall() {
		alldata.setSelected(true);
		avedata.setSelected(false);
		ave = false;
	}

	void showave() {
		avedata.setSelected(true);
		alldata.setSelected(false);
		ave = true;
	}

	// 低阶数据
	void setLowTable(TeamNormalPO[] team) {
		columnsName.removeAllElements();

		// columnsName.add("排名");
		columnsName.add("赛季");
		columnsName.add("得分");
		columnsName.add("胜率(%)");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("盖帽");
		columnsName.add("抢断");
		columnsName.add("犯规");
		columnsName.add("失误");
		columnsName.add("投篮命中率");
		columnsName.add("三分命中率");
		columnsName.add("罚球命中率");
		columnsName.add("进攻篮板数");
		columnsName.add("防守篮板数");
		columnsName.add("篮板数");
		// columnsName.add("比赛场数");

		rowimage.clear();

		for (int i = 1; i < team.length; i++) {
			TeamNormalPO str = team[i];
			Vector data = new Vector();
			data.add(2014 - i + "");
			System.out.println(str.getPoints());
			data.add(FrameSize.roundForNumber(str.getPoints(), 1));
			data.add(FrameSize.roundForNumber(str.getWinRate() * 100, 1));
			// data.add(str.getMatchNo());
			data.add(FrameSize.roundForNumber(str.getHitNo(), 1));
			data.add(FrameSize.roundForNumber(str.getRebs(), 1));
			data.add(FrameSize.roundForNumber(str.getAssistNo(), 1));
			data.add(FrameSize.roundForNumber(str.getBlockNo(), 1));
			data.add(FrameSize.roundForNumber(str.getStealsNo(), 1));
			data.add(FrameSize.roundForNumber(str.getFoulsNo(), 1));
			data.add(FrameSize.roundForNumber(str.getMistakesNo(), 1));
			data.add(FrameSize.roundForNumber(str.getThreeHitRate() * 100, 1));
			data.add(FrameSize.roundForNumber(str.getPenaltyHitRate() * 100, 1));
			data.add(FrameSize.roundForNumber(str.getOffenseRebs(), 1));
			data.add(FrameSize.roundForNumber(str.getDefenceRebs(), 1));
			data.add(FrameSize.roundForNumber(str.getHitRate() * 100, 1));

			// data.add(FrameSize.roundForNumber(str.getHandNo(), 1));
			// data.add(FrameSize.roundForNumber(str.getThreeHitNo(), 1));
			// data.add(FrameSize.roundForNumber(str.getThreeHandNo(), 1));
			// data.add(FrameSize.roundForNumber(str.getPenaltyHitNo(), 1));
			// data.add(FrameSize.roundForNumber(str.getPenaltyHandNo(), 1));

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
		mytable.updateUI();

		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();
		Comparator<Number> numberComparator = new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1 == null) {
					return -1;
				}
				if (o2 == null) {
					return 1;
				}
				if (o1.doubleValue() < o2.doubleValue()) {
					return -1;
				}
				if (o1.doubleValue() > o2.doubleValue()) {
					return 1;
				}
				return 0;
			}
		};
		for (int col = 1; col < mytable.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}

		this.add(jScrollPane);
		this.repaint();
	}

	// 高阶数据
	void setHighTable(TeamHighPO[] team) {
		columnsName.removeAllElements();
		// columnsName.add("排名");
		columnsName.add("赛季");
		columnsName.add("进攻回合");
		columnsName.add("进攻效率");
		columnsName.add("防守效率");
		columnsName.add("进攻篮板效率");
		columnsName.add("防守篮板效率");
		columnsName.add("抢断效率");
		columnsName.add("助攻效率");

		rowimage.clear();
		for (int i = 0; i < team.length; i++) {
			TeamHighPO str = team[i];
			Vector data = new Vector();
			data.add(2014-i);
			data.add(FrameSize.roundForNumber(str.getOffenseRound(), 1));
			data.add(FrameSize.roundForNumber(str.getOffenseEfficiency(), 1));
			data.add(FrameSize.roundForNumber(str.getDefenceEfficiency(), 1));
			data.add(FrameSize.roundForNumber(str.getOrebsEfficiency(), 1));
			data.add(FrameSize.roundForNumber(str.getDrebsEfficiency(), 1));
			data.add(FrameSize.roundForNumber(str.getStealsEfficiency(), 1));
			data.add(FrameSize.roundForNumber(str.getAssistEfficiency(), 1));
			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);

		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
		mytable.updateUI();

		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();
		Comparator<Number> numberComparator = new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1 == null) {
					return -1;
				}
				if (o2 == null) {
					return 1;
				}
				if (o1.doubleValue() < o2.doubleValue()) {
					return -1;
				}
				if (o1.doubleValue() > o2.doubleValue()) {
					return 1;
				}
				return 0;
			}
		};
		for (int col = 1; col < mytable.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}

		this.add(jScrollPane);
		this.repaint();
	}

	public void setTable(String teamname, boolean high) {

		showave();
		TeamNormalPO[] team = new TeamNormalPO[20];
		TeamHighPO[] teamhigh = new TeamHighPO[20];
		SeasonType type = SeasonType.REGULAR;
		// if(((String)SeasonTypebox.getSelectedItem()).equals("常规赛")){
		// type=SeasonType.REGULAR;
		// }else if(((String)SeasonTypebox.getSelectedItem()).equals("季后赛")){
		// type=SeasonType.PLAYOFF;
		// }
		if (avedata.isSelected() && !high) {
			for (int i = 0; i < 20; i++) {
				team[i] = tc.getAveTeam(2014 - i, teamname, type);
			}
			setLowTable(team);
		} else if (alldata.isSelected() && !high) {
			for (int i = 0; i < 20; i++) {
				team[i] = tc.getTotalTeam(2014 - i, teamname, type);
			}
			setLowTable(team);
		} else if (high) {
			for (int i = 0; i < 20; i++) {
				teamhigh[i] = tc.getHighTeam(2014 - i, teamname, type);
			}
			setHighTable(teamhigh);
		}
	}
}
