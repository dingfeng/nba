package ui.teamui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import po.TeamNormalPO;
import po.TeamPO;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.SortType;
import vo.TeamSortBy;
import bl.matchbl.MatchController;
import bl.playerbl.PlayerController;
import bl.teambl.TeamController;
import dataservice.playerdataservice.SeasonType;

public class TeamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel header = new JPanel();
	DefaultTableModel table;
	JScrollPane jScrollPane;
	JComboBox searchBox;
	JPanel find = new JPanel();
	JPanel welcome = new JPanel();
	JPanel teammessage = new JPanel();
	MyTable mytable;
	JComboBox<String> box;
	MyButton dataType;
	boolean sorttype;
	JLabel image = new JLabel();// 图片
	JTextField nameresult = new UneditableTextField();// 队伍名称
	JTextField nameAbridgeresult = new UneditableTextField();// 名称缩写
	JTextField addressresult = new UneditableTextField();// 所在地
	JTextField matchArearesult = new UneditableTextField();// 赛区
	JTextField playerArearesult = new UneditableTextField();// 分区
	JTextField manageresult = new UneditableTextField();// 主场
	JTextField foundYearresult = new UneditableTextField();// 建立时间
	JButton match;
	JButton teamplayers;
	TeamMatchPanel teammatch;
	boolean matchpanel = false;
	Vector<String> columnsName = new Vector<String>();
	JTextField[] teamlabel = new UneditableTextField[54];

	TeamController tc = new TeamController();
	MatchController mc = new MatchController();

	public TeamPanel() {
		String[] teamNames = tc.getTeamNames();
//		searchBox = new MyComboBox(teamNames);
//		AutoCompleteDecorator.decorate(searchBox);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
//		new Thread() {
//			public void run() {
//				setTable(tc
//						.getAllTeamTotal(2012, SeasonType.REGULAR));
//			}
//		}.start();

		setHeader();
		setFind();
		setMessage();
//		showOne("ATL");
		this.add(find);
		this.add(header);
		this.repaint();
	}

	/** 设置表格 */
//	void setTable(TeamNormalPO[] team) {
//		columnsName.removeAllElements();
//		columnsName.add("球队名");
//		columnsName.add("比赛场数");
//		columnsName.add("投篮命中数");
//		columnsName.add("投篮出手次数");
//		columnsName.add("三分命中数");
//		columnsName.add("三分出手数");
//		columnsName.add("罚球命中数");
//		columnsName.add("罚球出手数");
//		columnsName.add("进攻篮板数");
//		columnsName.add("防守篮板数");
//		columnsName.add("篮板数");
//		columnsName.add("助攻数");
//		columnsName.add("抢断数");
//		columnsName.add("盖帽数");
//		columnsName.add("失误数");
//		columnsName.add("犯规数");
//		columnsName.add("比赛得分");
//		columnsName.add("投篮命中率(%)");
//		columnsName.add("三分命中率(%)");
//		columnsName.add("罚球命中率(%)");
//		columnsName.add("胜率(%)");
//		columnsName.add("进攻回合");
//		columnsName.add("进攻效率");
//		columnsName.add("防守效率");
//		columnsName.add("进攻篮板效率");
//		columnsName.add("防守篮板效率");
//		columnsName.add("抢断效率");
//		columnsName.add("助攻率");
//
//		Vector rowimage = new Vector();
//		for (int i = 0; i < team.length; i++) {
//			TeamNormalPO str = team[i];
//			if (str == null) {
//				continue;
//			}
//			Vector data = new Vector();
//			data.add(str.getName());
//			data.add(str.getMatchNo());
//			data.add(FrameSize.roundForNumber(str.getHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getThreeHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getThreeHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getOffenseRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getDefenceRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getAssistNo(),1));
//			data.add(FrameSize.roundForNumber(str.getStealsNo(),1));
//			data.add(FrameSize.roundForNumber(str.getBlockNo(),1));
//			data.add(FrameSize.roundForNumber(str.getMistakesNo(),1));
//			data.add(FrameSize.roundForNumber(str.getFoulsNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPoints(),1));
//			data.add(FrameSize.roundForNumber(str.getHitRate()* 100,1));
//			data.add(FrameSize.roundForNumber(str.getThreeHitRate()* 100 ,1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHitRate()* 100,1 ));
//			data.add(FrameSize.roundForNumber(str.getWinRate()* 100 ,1));
//			data.add(FrameSize.roundForNumber(str.getOffenseRound(),1));
//			data.add(FrameSize.roundForNumber(str.getOffenseEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getDefenceEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getoRebsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getdRebsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getStealsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getAssistEfficiency(),1));
//
//			rowimage.add(data);
//		}
//
//		table = new DefaultTableModel(rowimage, columnsName);
//		mytable = new MyTable(table);
//		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
//		mytable.updateUI();
//
//		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();  
//		 Comparator<Number> numberComparator = new Comparator<Number>() {  
//	            @Override  
//	            public int compare(Number o1, Number o2) {  
//	                if ( o1 == null ) {  
//	                    return -1;  
//	                }  
//	                if ( o2 == null ) {  
//	                    return 1;  
//	                }  
//	                if ( o1.doubleValue() < o2.doubleValue() ) {  
//	                    return -1;  
//	                }  
//	                if ( o1.doubleValue() > o2.doubleValue() ) {  
//	                    return 1;  
//	                }  
//	                return 0;  
//	            }  
//	        };  
//	        for (int col = 1; col < mytable.getColumnCount(); col++) {  
//	            rowSorter.setComparator(col, numberComparator);  
//	        }  
//		
//		
//		jScrollPane = new JScrollPane(mytable);
//		jScrollPane
//				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		jScrollPane
//				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jScrollPane.setBounds(FrameSize.width / 3, FrameSize.height / 12,
//				2 * FrameSize.width / 3 - 5, FrameSize.height * 7 / 8
//						- FrameSize.height / 12 - 5);
//		jScrollPane.setBackground(FrameSize.backColor);
//		jScrollPane.getViewport().setOpaque(false);
//		resizeTable(false, jScrollPane, mytable);
//
//		mytable.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					showOne((String) mytable.getModel().getValueAt(
//							mytable.getSelectedRow(), 0));
//				}
//			}
//
//		});
//
//		this.add(jScrollPane);
//		this.repaint();
//	}
//
//	/** 实时更新 */
//	public void update() {
//		TeamMatchVO teamresult;
//		String teamname=searchBox.getSelectedItem().toString();
//		try{
//		if (dataType.getSelectedItem().equals("赛季总数据")) {
//			updateTable(tc.getSortedTotalTeams(TeamSortBy.name, SortType.ASEND));
//			teamresult=tc.getTotalTeam(teamname);
//		} else {
//			updateTable(tc.getSortedAveTeams(TeamSortBy.name, SortType.ASEND));
//			teamresult=tc.getAveTeam(teamname);
//		}}catch (Exception e) {
//			TeamPO teamresult1 = tc.getTeamData(teamname);
//			teamname = teamresult1.getNameAbridge();
//			if (teamname.equals("NOP")) {
//				teamname = "NOH";
//			}
//		}
//		if (dataType.getSelectedItem().equals("赛季总数据")) {
//			teamresult = tc.getTotalTeam(teamname);
//		} else {
//			teamresult = tc.getAveTeam(teamname);
//		}
//	
//		TeamMessage(teamresult);
//		jScrollPane.repaint();
//		 
//		this.repaint();
//	}
//
//	/** 更新表格 */
//	void updateTable(TeamNormalPO[] team) {
//		Vector rowimage = new Vector();
//		for (int i = 0; i < team.length; i++) {
//			TeamNormalPO str = team[i];
//			if(str==null){
//				continue;
//			} 
//			Vector data = new Vector();
//			data.add(str.getName());
//			data.add(str.getMatchNo());
//			data.add(FrameSize.roundForNumber(str.getHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getThreeHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getThreeHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHitNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHandNo(),1));
//			data.add(FrameSize.roundForNumber(str.getOffenseRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getDefenceRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getRebs(),1));
//			data.add(FrameSize.roundForNumber(str.getAssistNo(),1));
//			data.add(FrameSize.roundForNumber(str.getStealsNo(),1));
//			data.add(FrameSize.roundForNumber(str.getBlockNo(),1));
//			data.add(FrameSize.roundForNumber(str.getMistakesNo(),1));
//			data.add(FrameSize.roundForNumber(str.getFoulsNo(),1));
//			data.add(FrameSize.roundForNumber(str.getPoints(),1));
//			data.add(FrameSize.roundForNumber(str.getHitRate()* 100 ,1));
//			data.add(FrameSize.roundForNumber(str.getThreeHitRate()* 100,1 ));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHitRate() * 100,1 ));
//			data.add(FrameSize.roundForNumber(str.getWinRate()* 100 ,1));
//			data.add(FrameSize.roundForNumber(str.getOffenseRound(),1));
//			data.add(FrameSize.roundForNumber(str.getOffenseEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getDefenceEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getoRebsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getdRebsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getStealsEfficiency(),1));
//			data.add(FrameSize.roundForNumber(str.getAssistEfficiency(),1));
//
//			rowimage.add(data);
//		}
//		table.setDataVector(rowimage, columnsName);
//		resizeTable(false, jScrollPane, mytable);
//		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();  
//		 Comparator<Number> numberComparator = new Comparator<Number>() {  
//	            @Override  
//	            public int compare(Number o1, Number o2) {  
//	                if ( o1 == null ) {  
//	                    return -1;  
//	                }  
//	                if ( o2 == null ) {  
//	                    return 1;  
//	                }  
//	                if ( o1.doubleValue() < o2.doubleValue() ) {  
//	                    return -1;  
//	                }  
//	                if ( o1.doubleValue() > o2.doubleValue() ) {  
//	                    return 1;  
//	                }  
//	                return 0;  
//	            }  
//	        };  
//	        for (int col = 1; col < mytable.getColumnCount(); col++) {  
//	            rowSorter.setComparator(col, numberComparator);  
//	        }  
//	}

	/** 设置标题 */
	void setHeader() {
		
		MyButton jb1 = new MyButton("数据", Color.darkGray, Color.white);
		jb1.setBounds(0, FrameSize.height / 4, FrameSize.width/4, 50);
	
		
		MyButton jb2 = new MyButton("近期比赛",Color.darkGray, Color.white);
		jb2.setBounds(FrameSize.width / 4, FrameSize.height / 4, FrameSize.width/4, 50);
		
		
		MyButton jb3 = new MyButton("过往查询",Color.darkGray, Color.white);
		jb3.setBounds(FrameSize.width / 2, FrameSize.height / 4, FrameSize.width/4, 50);
		
		MyButton jb4 = new MyButton("对比",Color.darkGray, Color.white);
		jb4.setBounds(FrameSize.width *3/ 4, FrameSize.height / 4, FrameSize.width/4, 50);
		
		JPopupMenu type = new JPopupMenu();
		JMenuItem jmi1 = new JMenuItem("基本数据");
		jmi1.setBackground(FrameSize.bluecolor);
		jmi1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.print("?");
			}

		});
		type.add(jmi1);
		type.add(new JMenuItem("高阶数据"));
		type.setBackground(FrameSize.bluecolor);

		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				type.show(e.getComponent(), 0, 50);
			}

		});

		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);

	}

	/** 显示场均数据/总数据 */
	public void showAllData() {
		if (matchpanel && teammatch != null) {
			this.remove(teammatch);
			matchpanel = false;
		}
		if (matchpanel)
		{
			matchpanel = false;
		}
		this.remove(teammessage);
		jScrollPane.setVisible(false);
		if (dataType.getText().equals("赛季总数据")) {
//			setTable(tc.getSortedTotalTeams(TeamSortBy.name, SortType.ASEND));
		} else {
//			setTable(tc.getSortedAveTeams(TeamSortBy.name, SortType.ASEND));
		}

		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

		this.repaint();

	}

	/** 搜索 */
	void setFind() {
		find.setBackground(Color.white);
		find.setBounds(0, 0, FrameSize.width ,
				 FrameSize.height / 4);
		find.setLayout(null);

		JLabel name = new JLabel("队名");// 队伍名称
		JLabel nameAbridge = new JLabel("缩写");// 名称缩写
		JLabel address = new JLabel("所在地");// 所在地
		JLabel matchArea = new JLabel("赛区");// 赛区
		JLabel playerArea = new JLabel("分区");// 分区
		JLabel manage = new JLabel("主场");// 主场
		JLabel foundYear = new JLabel("建立时间");// 建立时间

		name.setForeground(Color.black);
		nameAbridge.setForeground(Color.black);
		address.setForeground(Color.black);
		matchArea.setForeground(Color.black);
		playerArea.setForeground(Color.black);
		manage.setForeground(Color.black);
		foundYear.setForeground(Color.black);

		name.setBounds(FrameSize.width / 2, FrameSize.height / 40,
				FrameSize.width / 12, 30);
		nameAbridge.setBounds(FrameSize.width / 2, FrameSize.height / 40+30,
				FrameSize.width / 24, 30);
		address.setBounds(FrameSize.width / 2, FrameSize.height / 40+60,
				FrameSize.width / 12, 30);
		matchArea.setBounds(FrameSize.width / 2, FrameSize.height / 40+90,
				FrameSize.width / 12, 30);
		playerArea.setBounds(FrameSize.width / 2, FrameSize.height / 40+120,
				FrameSize.width / 12, 30);
		manage.setBounds(FrameSize.width / 2, FrameSize.height / 40+150,
				FrameSize.width / 12, 30);
		foundYear.setBounds(FrameSize.width / 2, FrameSize.height / 40+180,
				FrameSize.width / 12, 30);

		for (int i = 0; i < 54; i++) {
			teamlabel[i] = new UneditableTextField();
		}

		find.add(name);
		find.add(nameAbridge);
		find.add(address);
		find.add(matchArea);
		find.add(playerArea);
		find.add(manage);
		find.add(foundYear);

	}

	/** 在findPanel上显示一个球队的信息 */
	void showOne(String teamname) {
		if (matchpanel && teammatch != null) {
			this.remove(teammatch);
			matchpanel = false;
		}
		if (matchpanel)
		{
			matchpanel = false;
		}
		this.remove(welcome);
		this.remove(teammessage);

		TeamPO teamresult = tc.getTeamData(teamname);
		image.setIcon(scaleImage(new ImageIcon(teamresult.getImage()),
				3 * FrameSize.height / 20, 3 * FrameSize.height / 20));
		nameresult.setText(teamresult.getName());// 队伍名称
		String nameAbridge = teamresult.getNameAbridge();
		if (nameAbridge.equals("NOP")) {
			nameAbridge = "NOH";
		}
		nameAbridgeresult.setText(nameAbridge);// 名称缩写
		addressresult.setText(teamresult.getAddress());// 所在地
		matchArearesult.setText(teamresult.getMatchArea());// 赛区
		playerArearesult.setText(teamresult.getPlayerArea().toString());// 分区
		manageresult.setText(teamresult.getManage());// 主场
		foundYearresult.setText(String.valueOf(teamresult.getFoundYear()));// 建立时间

		match = new MyButton("比赛", Color.black, Color.DARK_GRAY);
		teamplayers = new MyButton("球员", Color.black, Color.DARK_GRAY);

		match.setFont(new Font("幼圆", Font.BOLD, 12));
		teamplayers.setFont(new Font("幼圆", Font.BOLD, 12));

		match.setForeground(Color.white);
		teamplayers.setForeground(Color.white);

		image.setOpaque(false);

		teamplayers.setBounds(FrameSize.width / 3 - 60,
				11 * FrameSize.height / 16, 55, 30);
		match.setBounds(FrameSize.width / 3 - 130, 11 * FrameSize.height / 16,
				58, 30);
		image.setBounds(FrameSize.width / 10, FrameSize.height / 8,
				FrameSize.width / 4, 3 * FrameSize.height / 20);
		nameresult.setBounds(FrameSize.width / 20, FrameSize.height / 8 - 50,
				100, 3 * FrameSize.height / 80);
		nameAbridgeresult.setBounds(9 * FrameSize.width / 40,
				FrameSize.height / 8 - 50, 50, 30);
		addressresult.setBounds(FrameSize.width / 10,
				FrameSize.height / 8 + 130, FrameSize.width / 8,
				3 * FrameSize.height / 80);
		matchArearesult.setBounds(FrameSize.width / 10,
				FrameSize.height / 8 + 190, FrameSize.width / 8,
				3 * FrameSize.height / 80);
		playerArearesult.setBounds(FrameSize.width / 10,
				FrameSize.height / 8 + 250, FrameSize.width / 8,
				3 * FrameSize.height / 80);
		manageresult.setBounds(FrameSize.width / 10,
				FrameSize.height / 8 + 310, FrameSize.width / 8,
				3 * FrameSize.height / 80);
		foundYearresult.setBounds(FrameSize.width / 10,
				FrameSize.height / 8 + 370, FrameSize.width / 8,
				3 * FrameSize.height / 80);

		match.addActionListener(e -> setMatch());
		teamplayers.addActionListener(e -> setTeamPlayers(nameAbridgeresult
				.getText()));

		find.add(teamplayers);
		find.add(match);
		find.add(image);
		find.add(nameresult);
		find.add(nameAbridgeresult);
		find.add(addressresult);
		find.add(matchArearesult);
		find.add(playerArearesult);
		find.add(manageresult);
		find.add(foundYearresult);
		find.setVisible(true);
		find.repaint();
		this.add(find);
		this.repaint();
		this.validate();
		image.setVisible(true);
		image.repaint();

	}

	/** 点击查找按钮 */
	public void findClick(String teamname) {
		try {
			showOne(teamname);
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "未找到该球队的基本信息", "查找失败",
					JOptionPane.ERROR_MESSAGE);

			return;
		}

		
		TeamNormalPO teamresult;

		find.setVisible(false);

		showOne(teamname);
		try {
			if (dataType.getSelectedItem().equals("赛季总数据")) {
				teamresult = tc.getTotalTeam(teamname);
			} else {
				teamresult = tc.getAveTeam(teamname);
			}
		} catch (Exception e) {
			TeamPO teamresult1 = tc.getTeamData(teamname);
			teamname = teamresult1.getNameAbridge();
			if (teamname.equals("NOP")) {
				teamname = "NOH";
			}
			if (dataType.getSelectedItem().equals("赛季总数据")) {
				teamresult = tc.getTotalTeam(teamname);
			} else {
				teamresult = tc.getAveTeam(teamname);
			}
		}
		try{
		TeamMessage(teamresult);
		}catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "未找到该球队的比赛数据", "查找失败",
					JOptionPane.ERROR_MESSAGE);

			return;
		}
		this.remove(jScrollPane);
		this.add(teammessage);
		this.repaint();
	}

	/** 设置单个球队的panel */
	void setMessage() {
		teammessage.setLayout(new GridLayout(9, 6, -1, -1));
		// teammessage.setBorder(BorderFactory.createLineBorder(Color.white));
		teammessage.setBackground(FrameSize.backColor);
		teammessage.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height * 7 / 8
						- FrameSize.height / 12);
		// JTextField[] teamlabel=new UneditableTextField[54];
		for (int i = 0; i < 54; i++) {
			teamlabel[i] = new UneditableTextField();
			teammessage.add(teamlabel[i]);
			teamlabel[i].setFont(new Font("", Font.PLAIN, 15));
			teamlabel[i].setBorder(BorderFactory.createLineBorder(Color.white));
		}

	}

	/** 一个球队信息（右侧） */
	void TeamMessage(TeamNormalPO str) {

		teamlabel[0].setText("比赛场数");
		teamlabel[2].setText("比赛得分");
		teamlabel[4].setText("胜率(%)");
		teamlabel[6].setText("投篮命中数");
		teamlabel[8].setText("投篮出手次数");
		teamlabel[10].setText("投篮命中率(%)");
		teamlabel[12].setText("三分命中数");
		teamlabel[14].setText("三分出手数");
		teamlabel[16].setText("三分命中率(%)");
		teamlabel[18].setText("罚球命中数");
		teamlabel[20].setText("罚球出手数");
		teamlabel[22].setText("罚球命中率(%)");
		teamlabel[24].setText("进攻篮板数");
		teamlabel[26].setText("防守篮板数");
		teamlabel[28].setText("篮板数");
		teamlabel[30].setText("助攻数");
		teamlabel[32].setText("抢断数");
		teamlabel[34].setText("盖帽数");
		teamlabel[36].setText("失误数");
		teamlabel[38].setText("犯规数");
		teamlabel[40].setText("进攻回合");
		teamlabel[42].setText("进攻效率");
		teamlabel[44].setText("防守效率");
		teamlabel[46].setText("进攻篮板效率");
		teamlabel[48].setText("防守篮板效率");
		teamlabel[50].setText("抢断效率");
		teamlabel[52].setText("助攻率");

		teamlabel[1].setText(str.getMatchNo() + "");
		teamlabel[3].setText(String.format("%.1f", str.getPoints()));
		teamlabel[5].setText(String.format("%.1f", str.getWinRate() * 100));
		teamlabel[7].setText(String.format("%.1f", str.getHitNo()));
		teamlabel[9].setText(String.format("%.1f", str.getHandNo()));
		teamlabel[11].setText(String.format("%.1f", str.getHitRate() * 100));
		teamlabel[13].setText(String.format("%.1f", str.getThreeHitNo()));
		teamlabel[15].setText(String.format("%.1f", str.getThreeHandNo()));
		teamlabel[17]
				.setText(String.format("%.1f", str.getThreeHitRate() * 100));
		teamlabel[19].setText(String.format("%.1f", str.getPenaltyHitNo()));
		teamlabel[21].setText(String.format("%.1f", str.getPenaltyHandNo()));
		teamlabel[23].setText(String.format("%.1f",
				str.getPenaltyHitRate() * 100));
		teamlabel[25].setText(String.format("%.1f", str.getOffenseRebs()));
		teamlabel[27].setText(String.format("%.1f", str.getDefenceRebs()));
		teamlabel[29].setText(String.format("%.1f", str.getRebs()));
		teamlabel[31].setText(String.format("%.1f", str.getAssistNo()));
		teamlabel[33].setText(String.format("%.1f", str.getStealsNo()));
		teamlabel[35].setText(String.format("%.1f", str.getBlockNo()));
		teamlabel[37].setText(String.format("%.1f", str.getMistakesNo()));
		teamlabel[39].setText(String.format("%.1f", str.getFoulsNo()));
		teamlabel[41].setText(String.format("%.1f", str.getOffenseRound()));
		teamlabel[43]
				.setText(String.format("%.1f", str.getOffenseEfficiency()));
		teamlabel[45]
				.setText(String.format("%.1f", str.getDefenceEfficiency()));
		teamlabel[47].setText(String.format("%.1f", str.getoRebsEfficiency()));
		teamlabel[49].setText(String.format("%.1f", str.getdRebsEfficiency()));
		teamlabel[51].setText(String.format("%.1f", str.getStealsEfficiency()));
		teamlabel[53].setText(String.format("%.1f", str.getAssistEfficiency()));
		teammessage.repaint();

		
	}

	/** 查看该球队队员 */
	void setTeamPlayers(String team) {
		String[] playernames = tc.getPlayers(team);
		PlayerController pc = new PlayerController(2012);
		PlayerMatchVO[] players = new PlayerMatchVO[playernames.length];
		for (int i = 0; i < playernames.length; i++) {
//			players[i] = pc.findPlayerMatchAve(playernames[i]);
		}
//		MyFrame.playerpanel.showTeamPlayers(players);

		MyFrame.card.show(MyFrame.mainpanel, "player");
		
	}

	/** 点击查看比赛按钮 */
	void setMatch() {
		if (matchpanel && teammatch != null) {
			this.remove(teammatch);
		}
		this.remove(teammessage);
		matchpanel = true;
		teammatch = new TeamMatchPanel(nameAbridgeresult.getText());
		this.remove(jScrollPane);
		this.add(teammatch);
		this.repaint();
	}

	private void resizeTable(boolean bool, JScrollPane jsp, JTable table) {
		Dimension containerwidth = null;
		if (!bool) {
			// 初始化时，父容器大小为首选大小，实际大小为0
			containerwidth = jsp.getPreferredSize();
		} else {
			// 界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
			containerwidth = jsp.getSize();
		}
		// 计算表格总体宽度 getTable().
		int allwidth = table.getIntercellSpacing().width;
		for (int j = 0; j < table.getColumnCount(); j++) {
			// 计算该列中最长的宽度
			int max = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				int width = table
						.getCellRenderer(i, j)
						.getTableCellRendererComponent(table,
								table.getValueAt(i, j), false, false, i, j)
						.getPreferredSize().width;
				if (width > max) {
					max = width;
				}
			}
			// 计算表头的宽度
			int headerwidth = table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(
							table,
							table.getColumnModel().getColumn(j).getIdentifier(),
							false, false, -1, j).getPreferredSize().width;
			// 列宽至少应为列头宽度
			max += headerwidth;
			// 设置列宽
			table.getColumnModel().getColumn(j).setPreferredWidth(max);
			// 给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
			allwidth += max + table.getIntercellSpacing().width;
		}
		allwidth += table.getIntercellSpacing().width;
		// 如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
		if (allwidth > containerwidth.width) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} else {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		}
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
