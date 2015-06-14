package ui.playerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import bl.playerbl.PlayerController;
import blservice.playerblservice.PlayerBlService;
import dataservice.playerdataservice.SeasonType;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.TeamHighPO;
import po.TeamNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import ui.mainui.UneditableTextField;

public class PlayerDataPanel extends JPanel{


	Vector<String> columnsName = new Vector<String>();
	Vector data = new Vector();
	DefaultTableModel table = new DefaultTableModel(data, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	PlayerBlService playerController = new PlayerController();
	boolean ave = true;

	public PlayerDataPanel() {
		this.setLayout(null);
		this.setBounds(0, 30, FrameSize.width, FrameSize.height * 3 / 4 - 80);
		this.setBackground(Color.white);
		setText();
		this.repaint();
	}

	public void setText() {
		JLabel blue=new JLabel("球员总信息");
		blue.setBounds(0, 0,FrameSize.width,30);
		blue.setBackground(FrameSize.bluecolor);
		blue.setOpaque(true);
		blue.setForeground(Color.white);
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

	/**低阶数据*/
	void setLowTable(PlayerNormalPO[] player) {
		columnsName.removeAllElements();

		/* 00赛季 */columnsName.add("赛季");
//		/* 01球员 */columnsName.add("球员");
//		/* 02姓名 */columnsName.add("姓名");
		/* 03球队 */columnsName.add("球队");
		/* 04场数 */columnsName.add("场数");
		columnsName.add("首发");
		/* 05篮板 */columnsName.add("篮板");
		/* 06助攻 */columnsName.add("助攻");
		/* 07分钟 */columnsName.add("分钟");
		/* 08三分命中率 */columnsName.add("三分%");
		/* 09罚球命中率 */columnsName.add("罚球%");
		/* 10投篮命中率 */columnsName.add("投篮%");
		/* 11进攻 */columnsName.add("进攻");
		/* 12防守 */columnsName.add("防守");
		/* 13抢断 */columnsName.add("抢断");
		/* 14盖帽 */columnsName.add("盖帽");
		/* 15失误 */columnsName.add("失误");
		/* 16犯规 */columnsName.add("犯规");
		/* 17两双 */columnsName.add("两双");
		/* 18得分 */columnsName.add("得分");

		data.clear();

		for (int i = 1; i < player.length; i++) {
			PlayerNormalPO str = player[i];
			Vector rowData = new Vector();
			/*00赛季*/rowData.add(str.getSeason());
//			/*01球员*/rowData.add(playerController.getPlayerImage(str.getName()));
//			/*02姓名*/rowData.add(str.getName());
			/*03球队*/rowData.add(str.getTeam());
			/*04场数*/rowData.add(FrameSize.roundForNumber(str.getMatchNo(), 1));
			          rowData.add(FrameSize.roundForNumber(str.getFirstServiceNo(), 1));
			/*05篮板*/rowData.add(FrameSize.roundForNumber(str.getRebs(), 1));
			/* 06助攻 */rowData.add(FrameSize.roundForNumber(str.getAssistNo(), 1));
			/* 07分钟 */rowData.add(FrameSize.roundForNumber(str.getTime(), 1));
			/* 08三分命中率 */rowData.add(FrameSize.roundForNumber(str.getThreeHitRate(), 1));
			/* 09罚球命中率 */rowData.add(FrameSize.roundForNumber(str.getPenaltyHitRate(), 1));
			/* 10投篮命中率 */rowData.add(FrameSize.roundForNumber(str.getHitRate(), 1));
			/* 11进攻 */rowData.add(FrameSize.roundForNumber(str.getOffendRebsNo(), 1));
			/* 12防守 */rowData.add(FrameSize.roundForNumber(str.getDefenceRebsNo(), 1));
			/* 13抢断 */rowData.add(FrameSize.roundForNumber(str.getStealsNo(), 1));
			/* 14盖帽 */rowData.add(FrameSize.roundForNumber(str.getBlockNo(), 1));
			/* 15失误 */rowData.add(FrameSize.roundForNumber(str.getMistakesNo(), 1));
			/* 16犯规 */rowData.add(FrameSize.roundForNumber(str.getFoulsNo(), 1));
			/* 17两双 */rowData.add(FrameSize.roundForNumber(str.getTwoPair(), 1));
			/* 18得分 */rowData.add(FrameSize.roundForNumber(str.getPoints(),1));

			data.add(rowData);
		}
		table.setDataVector(data, columnsName);
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
		for (int col = 2; col < mytable.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}

		this.add(jScrollPane);
		this.repaint();
	}

	/**高阶数据*/
	void setHighTable(PlayerHighPO[] player) {
		columnsName.removeAllElements();
		/* 00赛季 */columnsName.add("赛季");
//		/* 01球员 */columnsName.add("球员");
//		/* 02姓名 */columnsName.add("姓名");
		/* 03球队 */columnsName.add("球队");
		/* 04效率 */columnsName.add("效率");
		/* 05真实命中率 */columnsName.add("真实命中率");
		/* 06GmSc */columnsName.add("GmSc");			
		/* 07篮板效率 */columnsName.add("篮板%");
		/* 08进攻篮板率 */columnsName.add("进攻%");
		/* 09防守篮板率 */columnsName.add("防守%");
		/* 10助攻率 */columnsName.add("助攻%");
		/* 11抢断率 */columnsName.add("抢断%");
		/* 12盖帽率 */columnsName.add("盖帽%");
		/* 13失误率 */columnsName.add("失误%");
		/* 14使用率 */columnsName.add("使用%");

		data.clear();
		for (int i = 0; i < player.length; i++) {
			PlayerHighPO str = player[i];
			Vector rowData = new Vector();
			/* 00赛季 */rowData.add(str.getSeason());
//			/* 01球员 */rowData.add(playerController.getPlayerImage(str.getPlayerName()));
//			/* 02姓名 */rowData.add(str.getPlayerName());
			/* 03球队 */rowData.add(str.getTeamName());
			/* 04效率 */rowData.add(FrameSize.roundForNumber(str.getEfficiency(), 1));
			/* 05真实命中率 */rowData.add(FrameSize.roundForNumber(str.getTrueHitRate(), 1));
			/* 06GmSc */rowData.add(FrameSize.roundForNumber(str.getGmScEfficiency(), 1));
			/* 07篮板效率*/rowData.add(FrameSize.roundForNumber(str.getRebEfficiency(), 1));
			/* 08进攻篮板率 */rowData.add(FrameSize.roundForNumber(str.getOffenseRebsEfficiency(), 1));
			/* 09防守篮板率 */rowData.add(FrameSize.roundForNumber(str.getDefenceRebsEfficiency(), 1));
			/* 10助攻率 */rowData.add(FrameSize.roundForNumber(str.getAssistEfficiency(), 1));
			/* 11抢断率 */rowData.add(FrameSize.roundForNumber(str.getStealsEfficiency(), 1));
			/* 12盖帽率 */rowData.add(FrameSize.roundForNumber(str.getBlockEfficiency(), 1));
			/* 13失误率 */rowData.add(FrameSize.roundForNumber(str.getMistakeEfficiency(), 1));
			/* 14使用率 */rowData.add(FrameSize.roundForNumber(str.getMistakeEfficiency(), 1));
			data.add(rowData);
		}
		table.setDataVector(data, columnsName);

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
		for (int col = 2; col < mytable.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}

		this.add(jScrollPane);
		this.repaint();
	}

	
	/**设置表格*/
	public void setTable(String playerName, boolean high,boolean all) {
		
		TeamNormalPO[] team = new TeamNormalPO[70];
		TeamHighPO[] teamhigh = new TeamHighPO[70];
		SeasonType type = SeasonType.REGULAR;
		// if(((String)SeasonTypebox.getSelectedItem()).equals("常规赛")){
		// type=SeasonType.REGULAR;
		// }else if(((String)SeasonTypebox.getSelectedItem()).equals("季后赛")){
		// type=SeasonType.PLAYOFF;
		// }
		if (!all&&!high) {			
			setLowTable(playerController.getPlayerAllSeasonsAve(playerName, type));
		} else if (all&&!high) {
			setLowTable(playerController.getPlayerAllSeasonsTotal(playerName, type));
		} else if (high) {
			setHighTable(playerController.getPlayerAllSeasons(playerName, type));
		}
	}
}
