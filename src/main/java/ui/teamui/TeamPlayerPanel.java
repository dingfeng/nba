package ui.teamui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import po.PlayerNormalPO;
import po.PlayerPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import bl.playerbl.PlayerController;
import bl.teambl.TeamController;
import blservice.playerblservice.PlayerBlService;
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
	PlayerBlService pc=new PlayerController();
	public TeamPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 30, FrameSize.width, FrameSize.height * 3 / 4 - 80);
		this.setBackground(Color.white);
		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MyFrame.onePlayerPanel.showOne((String) mytable.getModel().getValueAt(
							mytable.getSelectedRow(), 0));
					MyFrame.setPlayer();
					MyFrame.card.show(MyFrame.mainpanel,"oneplayer");
				}
			}

		});
		
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
		JLabel blue=new JLabel("队员信息");
		blue.setBounds(0, 0,FrameSize.width-100,30);
		blue.setBackground(FrameSize.bluecolor);
		blue.setOpaque(true);
		blue.setForeground(Color.white);
		this.add(blue);
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

	void setBase(PlayerPO[] players) {
		columnsName.clear();
		columnsName.add("姓名");
		columnsName.add("位置");
		columnsName.add("身高");
		columnsName.add("体重");
		columnsName.add("生日");
		columnsName.add("毕业学校");
		columnsName.add("球号");
		columnsName.add("球队");
		columnsName.add("比赛地");
		rowimage.clear();

		for (int i = 1; i < players.length; i++) {
			PlayerPO player = players[i];
			Vector data = new Vector();
			data.add(player.getName());
			data.add(player.getPosition());
			data.add(player.getHeightfeet());
			data.add(player.getWeight());
			data.add(player.getBirth());
			data.add(player.getSchool());
			data.add(player.getName());
			data.add(player.getTeamA());
			data.add(player.getGameArea());

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		TableColumn firsetColumn = mytable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(150);
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
			data.add(FrameSize.roundForNumber(player.getMatchNo(),0));
			data.add(FrameSize.roundForNumber(player.getFirstServiceNo(),1));
			data.add(FrameSize.roundForNumber(player.getTime(),1));
			data.add(FrameSize.roundForNumber(player.getHitRate()*100,1));
			data.add(FrameSize.roundForNumber(player.getThreeHitRate()*100,1));
			data.add(FrameSize.roundForNumber(player.getPenaltyHitRate()*100,1));
			data.add(FrameSize.roundForNumber(player.getOffendRebsNo(),1));
			data.add(FrameSize.roundForNumber(player.getDefenceRebsNo(),1));
			data.add(FrameSize.roundForNumber(player.getRebs(),1));
			data.add(FrameSize.roundForNumber(player.getAssistNo(),1));
			data.add(FrameSize.roundForNumber(player.getStealsNo(),1));
			data.add(FrameSize.roundForNumber(player.getBlockNo(),1));
			data.add(FrameSize.roundForNumber(player.getMistakesNo(),1));
			data.add(FrameSize.roundForNumber(player.getFoulsNo(),1));
			data.add(FrameSize.roundForNumber(player.getPoints(),1));
			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		TableColumn firsetColumn = mytable.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(150);
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

	public void setTable() {
		
		String teamname = MyFrame.teampanel.nameAbridgeresult.getText();
		PlayerPO[] players  = pc.getPlayerOfTeam(teamname);
		
		if (base.isSelected()) {
			setBase(players);
		} else {
			setData(tc.getAllPlayerMatchAve(2014, teamname, SeasonType.REGULAR));
		}
	}

}
