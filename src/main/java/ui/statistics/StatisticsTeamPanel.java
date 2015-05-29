package ui.statistics;

import java.awt.Color;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;


public class StatisticsTeamPanel extends JPanel {
	
	
	;
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane=new JScrollPane(mytable);
	
	public StatisticsTeamPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		setLowTable();
//		setHighTable();
		this.add(headerPanel);
	}

	private  JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		MyComboBox screenPlayerAccordingLocation = new MyComboBox("球队联盟",
				new String[] { "东部", "西部" });
		screenPlayerAccordingLocation.setBounds(0, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingLocation);

		//赛季
		MyComboBox season = new MyComboBox(new String[] { "赛季", "12-13" });
		season.setBounds(150, 5, 150, 30);
		headerPanel.add(season);
		
		// 场均or总数
		MyComboBox aveOrAll = new MyComboBox(new String[] { "场均", "总数" });
		aveOrAll.setBounds(600, 5, 150, 30);
		headerPanel.add(aveOrAll);

		// 低阶or高阶
		MyComboBox lowOrHigh = new MyComboBox(new String[] { "低阶", "高阶" });
		lowOrHigh.setBounds(750, 5, 150, 30);
		lowOrHigh.addActionListener(e->setLowOrHigh((String)lowOrHigh.getSelectedItem()));
		headerPanel.add(lowOrHigh);

		return headerPanel;
	}
	
	//低阶数据
	void setLowTable(){
		columnsName.removeAllElements();
		
		columnsName.add("排名");
		columnsName.add("球队");
		columnsName.add("比赛场数");
		columnsName.add("投篮命中数");
		columnsName.add("投篮出手数");
		columnsName.add("三分命中数");
		columnsName.add("三分出手数");
		columnsName.add("罚球命中数");
		columnsName.add("罚球出手数");
		columnsName.add("进攻篮板数");
		columnsName.add("防守篮板数");
		columnsName.add("篮板数");
		columnsName.add("助攻数");
		columnsName.add("抢断数");
		columnsName.add("盖帽数");
		columnsName.add("失误数");
		columnsName.add("犯规数");
		columnsName.add("比赛得分");
		
		rowimage.clear();
		for (int i = 0; i <30; i++) {
	
			Vector data = new Vector();
			data.add(i+1);
			data.add("球队");
			data.add("比赛场数");
			data.add("投篮命中数");
			data.add("投篮出手数");
			data.add("三分命中数");
			data.add("三分出手数");
			data.add("罚球命中数");
			data.add("罚球出手数");
			data.add("进攻篮板数");
			data.add("防守篮板数");
			data.add("篮板数");
			data.add("助攻数");
			data.add("抢断数");
			data.add("盖帽数");
			data.add("失误数");
			data.add("犯规数");
			data.add("比赛得分");
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
	        for (int col = 0; col < mytable.getColumnCount(); col++) {  
	            rowSorter.setComparator(col, numberComparator);  
	        }  
		
		 
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
	void setHighTable(){
		columnsName.removeAllElements();
		columnsName.add("排名");
		columnsName.add("球队");
		columnsName.add("投篮命中率(%)");
		columnsName.add("三分命中率(%)");
		columnsName.add("罚球命中率(%)");
		columnsName.add("胜率(%)");
		columnsName.add("进攻回合");
		columnsName.add("进攻效率");
		columnsName.add("防守效率");
		columnsName.add("进攻篮板效率");
		columnsName.add("防守篮板效率");
		columnsName.add("抢断效率");
		columnsName.add("助攻率");
		
		rowimage.clear();
		for (int i = 0; i <30; i++) {
	
			Vector data = new Vector();
			data.add(i+1);
			data.add("球队");
			data.add("投篮命中率(%)");
			data.add("三分命中率(%)");
			data.add("罚球命中率(%)");
			data.add("胜率(%)");
			data.add("进攻回合");
			data.add("进攻效率");
			data.add("防守效率");
			data.add("进攻篮板效率");
			data.add("防守篮板效率");
			data.add("抢断效率");
			data.add("助攻率");
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
	        for (int col = 0; col < mytable.getColumnCount(); col++) {  
	            rowSorter.setComparator(col, numberComparator);  
	        }  
		
		
		this.add(jScrollPane);
		this.repaint();
	}
	
	void setLowOrHigh(String type){
		if(type.equals("低阶")){
			setLowTable();
		}else{
			setHighTable();
		}
	}
}
