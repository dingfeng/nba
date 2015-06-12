package ui.statistics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import po.TeamHighPO;
import po.TeamNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;


public class StatisticsTeamPanel extends JPanel {
	
	
	;
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	JTable mytable = new MyTable(table);
	{
		((MyTable)mytable).setShowImage(true);
	}
	JScrollPane jScrollPane;
	MyComboBox aveOrAll = new MyComboBox(new String[] { "场均", "总数" });
	MyComboBox lowOrHigh;
	MyComboBox season;
	MyComboBox SeasonTypebox;
	int num=0;
	Teamblservice tc=new TeamController();
	public StatisticsTeamPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		setTable();
//		setHighTable();
		this.add(headerPanel);
	}

	private  JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		SeasonTypebox= new MyComboBox(
				new String[] { "常规赛", "季后赛" });
		SeasonTypebox.setBounds(0, 5, 150, 30);
		SeasonTypebox.addActionListener(e->setTable());
		headerPanel.add(SeasonTypebox);

		//赛季
		String[] seasons=new String[20];
		for(int i=0;i<20;i++){
			seasons[i]=2014-i+"";
		}
		season = new MyComboBox(seasons);
		season.setBounds(150, 5, 150, 30);
		season.addActionListener(e->setTable());
		headerPanel.add(season);
		
		// 场均or总数
		
		aveOrAll.setBounds(600, 5, 150, 30);
		aveOrAll.addActionListener(e->setTable());
		headerPanel.add(aveOrAll);

		// 低阶or高阶
		lowOrHigh = new MyComboBox(new String[] { "基本", "高阶" });
		lowOrHigh.setBounds(750, 5, 150, 30);
		lowOrHigh.addActionListener(e->setTable());
		headerPanel.add(lowOrHigh);

		return headerPanel;
	}
	public class ColorTableCellRenderer extends DefaultTableCellRenderer {
	    DefaultTableCellRenderer renderer=new DefaultTableCellRenderer(); 
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,   
	            boolean isSelected, boolean hasFocus, int row, int column) {  
	        if(column==0){
	            return new JLabel((ImageIcon)value);
	        }else{
	            return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
	        }
	    }   
	
}
	//低阶数据
	void setLowTable(TeamNormalPO[] team){
		columnsName.removeAllElements();
		columnsName.add("图标");
		columnsName.add("球队");
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
//		columnsName.add("比赛场数");
		rowimage.clear();
		for (int i = 0; i <team.length; i++) {
			TeamNormalPO str = team[i];
			Vector data = new Vector();
			data.add(tc.getTeamImage(str.getName()));
			data.add(str.getName());
			data.add(FrameSize.roundForNumber(str.getPoints(), num));
			data.add(FrameSize.roundForNumber(str.getWinRate() * 100, 1));
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
//			data.add(FrameSize.roundForNumber(str.getHandNo(), 1));
//			data.add(FrameSize.roundForNumber(str.getThreeHitNo(), 1));
//			data.add(FrameSize.roundForNumber(str.getThreeHandNo(), 1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHitNo(), 1));
//			data.add(FrameSize.roundForNumber(str.getPenaltyHandNo(), 1));
			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
//		getColumnModel().getColumn(0).setCellRenderer(myRenderer);
		mytable.updateUI();

		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();  
		 Comparator<String> numberComparator = new Comparator<String>() {  
	            @Override  
	            public int compare(String o1, String o2) {  
	                if ( o1 == null ) {  
	                    return -1;  
	                }  
	                if ( o2 == null ) {  
	                    return 1;  
	                }  
	                if ( Double.parseDouble(o1) < Double.parseDouble(o2)) {  
	                    return -1;  
	                }  
	                if ( Double.parseDouble(o1) > Double.parseDouble(o2) ) {  
	                    return 1;  
	                }  
	                return 0;  
	            }  
	        };  
	        for (int col = 1; col < mytable.getColumnCount(); col++) {  
	            rowSorter.setComparator(col, numberComparator);  
	        }  
			TableCellRenderer myRenderer = new ColorTableCellRenderer();
			mytable.setDefaultRenderer(Object.class, myRenderer);
			mytable.setRowHeight(50);
	    jScrollPane=new JScrollPane(mytable);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 40,FrameSize.width , FrameSize.height * 7 / 8-40);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		this.add(jScrollPane);
		this.repaint();
	}
	
	//高阶数据
	void setHighTable(TeamHighPO[] team){
		columnsName.removeAllElements();
//		columnsName.add("排名");
		columnsName.add("球队");
		
		columnsName.add("进攻回合");
		columnsName.add("进攻效率");
		columnsName.add("防守效率");
		columnsName.add("进攻篮板效率");
		columnsName.add("防守篮板效率");
		columnsName.add("抢断效率");
		columnsName.add("助攻效率");
		
		rowimage.clear();
		for (int i = 0; i <team.length; i++) {
			TeamHighPO str=team[i];
			Vector data = new Vector();
			data.add(str.getName());
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
	                if ( o1 == null ) {  
	                    return -1;  
	                }  
	                if ( o2 == null ) {  
	                    return 1;  
	                }  
	                if ( o1.doubleValue() < o2.doubleValue() ) {  
	                    return -1;  
	                }  
	                if ( o1.doubleValue() > o2.doubleValue() ) {  
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
	
	void setTable(){
		TeamNormalPO[] team=new TeamNormalPO[30];
		TeamHighPO[] teamhigh=new TeamHighPO[30];
		SeasonType type=SeasonType.REGULAR;
		if(((String)SeasonTypebox.getSelectedItem()).equals("常规赛")){
			type=SeasonType.REGULAR;
		}else if(((String)SeasonTypebox.getSelectedItem()).equals("季后赛")){
			type=SeasonType.PLAYOFF;
		}
		if(((String)aveOrAll.getSelectedItem()).equals("场均")&&((String)lowOrHigh.getSelectedItem()).equals("基本")){
			team=tc.getAllTeamAve(Integer.parseInt((String)season.getSelectedItem()), type);
			num=1;
			setLowTable(team);
		}else if(((String)aveOrAll.getSelectedItem()).equals("总数")&&((String)lowOrHigh.getSelectedItem()).equals("基本")){
			team=tc.getAllTeamTotal(Integer.parseInt((String)season.getSelectedItem()), type);
			num=0;
			setLowTable(team);
		}else if(((String)lowOrHigh.getSelectedItem()).equals("高阶")){
			teamhigh=tc.getAllTeamHigh(Integer.parseInt((String)season.getSelectedItem()), type);
			setHighTable(teamhigh);
		}
	}
}
