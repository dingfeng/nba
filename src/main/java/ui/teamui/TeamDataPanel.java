package ui.teamui;

import java.awt.Color;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import po.TeamHighPO;
import po.TeamNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;

public class TeamDataPanel extends JPanel {

	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);
	JComboBox seasontype=new MyComboBox(new String[]{"常规赛","季后赛"});
	SeasonType type = SeasonType.REGULAR;
	Teamblservice tc = new TeamController();

	boolean ave = true;
	JTextField[] teamlabel = new UneditableTextField[54];
	int num=0;
	public TeamDataPanel() {
		this.setLayout(null);
		this.setBounds(0, 30, FrameSize.width, FrameSize.height * 3 / 4 - 180);
		this.setBackground(Color.white);
		setText();
		this.repaint();
	}

	public void setText() {
		JLabel blue=new JLabel("球队总数据");
		blue.setBackground(FrameSize.bluecolor);
		blue.setBounds(0, 0, FrameSize.width-100, 30);
		blue.setForeground(Color.white);
		blue.setOpaque(true);
		this.add(blue);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 30, FrameSize.width,
				420);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
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
			data.add(FrameSize.roundForNumber(str.getPoints(),num));
			data.add(FrameSize.roundForNumber(str.getWinRate() * 100, 1));
			// data.add(str.getMatchNo());
			data.add(FrameSize.roundForNumber(str.getHitNo(), num));
			data.add(FrameSize.roundForNumber(str.getRebs(), num));
			data.add(FrameSize.roundForNumber(str.getAssistNo(), num));
			data.add(FrameSize.roundForNumber(str.getBlockNo(), num));
			data.add(FrameSize.roundForNumber(str.getStealsNo(), num));
			data.add(FrameSize.roundForNumber(str.getFoulsNo(), num));
			data.add(FrameSize.roundForNumber(str.getMistakesNo(), num));
			data.add(FrameSize.roundForNumber(str.getThreeHitRate() * 100, 1));
			data.add(FrameSize.roundForNumber(str.getPenaltyHitRate() * 100, 1));
			data.add(FrameSize.roundForNumber(str.getOffenseRebs(), num));
			data.add(FrameSize.roundForNumber(str.getDefenceRebs(), num));
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
		Comparator<String> numberComparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1 == null) {
					return -1;
				}
				if (o2 == null) {
					return 1;
				}
				if (Double.parseDouble(o1) <Double.parseDouble(o2)) {
					return -1;
				}
				if (Double.parseDouble(o1) > Double.parseDouble(o2)) {
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

	public void setTable(boolean high,boolean all) {

		String teamname=MyFrame.teampanel.nameAbridgeresult.getText();
		TeamNormalPO[] team = new TeamNormalPO[70];
		TeamHighPO[] teamhigh = new TeamHighPO[70];
		
		 if(((String)seasontype.getSelectedItem()).equals("常规赛")){
		 type=SeasonType.REGULAR;
		 }else if(((String)seasontype.getSelectedItem()).equals("季后赛")){
		 type=SeasonType.PLAYOFF;
		 }
		if (!all&&!high) {
			team=tc.getTeamSeasonNormalAve(teamname,type);
			num=1;
			setLowTable(team);
		} else if (all&&!high) {
			team=tc.getTeamSeasonNormalTotal(teamname,type);
			num=0;
			setLowTable(team);
		} else if (high) {
			teamhigh = tc.getTeamSeasonHigh(teamname, type);
			setHighTable(teamhigh);
		}
	}
}
