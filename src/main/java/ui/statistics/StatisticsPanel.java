package ui.statistics;

import java.awt.Color;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

public class StatisticsPanel extends JPanel {
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	JTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);
	JComboBox type=new MyComboBox(new String[]{"数据偏离度","得分","篮板","助攻"});
	JLabel imagelabel=new JLabel();
	JLabel text=new JLabel();
	public StatisticsPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		type.addActionListener(e->setTable());
		type.setBounds(10, 10, 100, 30);
		type.setBackground(Color.white);
		type.setForeground(Color.black);
		imagelabel.setBounds(FrameSize.width/2-280,275 ,560,420);
		
		this.add(text);
		this.add(type);
		this.add(imagelabel);
		setTableOne();
	}

	void setTableOne() {
		columnsName.clear();
		columnsName.add("年龄段");
		columnsName.add("球员数");
		columnsName.add("球员占数比%");
		columnsName.add("场均得分平均");
		columnsName.add("得分偏离度%");
		columnsName.add("场均篮板平均");
		columnsName.add("篮板偏离度%");
		columnsName.add("场均助攻平均");
		columnsName.add("助攻偏离度%");
		
		rowimage.clear();
		Vector data1=new Vector();
		data1.add("19-21");
		data1.add(28);
		data1.add(7.27);
		data1.add(7.41);
		data1.add(0.25);
		data1.add(3.55);
		data1.add(1.05);
		data1.add(1.31);
		data1.add(-1.37);
		Vector data2=new Vector();
		data2.add("22-24");
		data2.add(100);
		data2.add(25.97);
		data2.add(7.64);
		data2.add(1.76);
		data2.add(3.23);
		data2.add(1.06);
		data2.add(1.64);
		data2.add(1.02);
		Vector data3=new Vector();
		data3.add("25-27");
		data3.add(105);
		data3.add(27.27);
		data3.add(7.11);
		data3.add(-0.17);
		data3.add(3.12);
		data3.add(0.16);
		data3.add(1.52);
		data3.add(-1.06);
		Vector data4=new Vector();
		data4.add("28-30");
		data4.add(74);
		data4.add(19.23);
		data4.add(8.15);
		data4.add(2.67);
		data4.add(3.30);
		data4.add(1.22);
		data4.add(1.94);
		data4.add(4.33);
		Vector data5=new Vector();
		data5.add("31-33");
		data5.add(46);
		data5.add(11.95);
		data5.add(5.86);
		data5.add(-2.17);
		data5.add(2.70);
		data5.add(-1.55);
		data5.add(1.45);
		data5.add(-0.97);
		Vector data6=new Vector();
		data6.add("34-36");
		data6.add(26);
		data6.add(6.75);
		data6.add(5.20);
		data6.add(-1.84);
		data6.add(2.26);
		data6.add(-1.83);
		data6.add(1.07);
		data6.add(-2.2);
		Vector data7=new Vector();
		data7.add("37+");
		data7.add(6);
		data7.add(1.56);
		data7.add(4.88);
		data7.add(-0.5);
		data7.add(2.89);
		data7.add(-0.11);
		data7.add(1.70);
		data7.add(-0.56);
		rowimage.add(data1);
		rowimage.add(data2);
		rowimage.add(data3);
		rowimage.add(data4);
		rowimage.add(data5);
		rowimage.add(data6);
		rowimage.add(data7);
		table.setDataVector(rowimage, columnsName);
		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
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
				if (Double.parseDouble(o1) < Double.parseDouble(o2)) {
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

		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 50, FrameSize.width,240);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		this.add(jScrollPane);
		imagelabel.setIcon(null);
		imagelabel.setText("得分偏离度=某年龄段得分占比-球员数占比，偏离度正值越大，表示该年龄段球员的数据贡献越大");
//		text.setVisible(true);
		this.repaint();
	}

	void setTableAge(){
		columnsName.clear();
		columnsName.add("年龄/得分");
		columnsName.add("0-3");
		columnsName.add("4-7");
		columnsName.add("8-11");
		columnsName.add("12-15");
		columnsName.add("16-19");
		columnsName.add("20+");
		rowimage.clear();
		Vector data1=new Vector();
		data1.add("19-21");
		data1.add(8);
		data1.add(9);
		data1.add(5);
		data1.add(5);
		data1.add(0);
		data1.add(1);
		Vector data2=new Vector();
		data2.add("22-24");
		data2.add(34);
		data2.add(24);
		data2.add(18);
		data2.add(14);
		data2.add(7);
		data2.add(3);
		Vector data3=new Vector();
		data3.add("25-27");
		data3.add(30);
		data3.add(32);
		data3.add(28);
		data3.add(11);
		data3.add(2);
		data3.add(2);
		Vector data4=new Vector();
		data4.add("28-30");
		data4.add(16);
		data4.add(27);
		data4.add(15);
		data4.add(11);
		data4.add(3);
		data4.add(2);
		Vector data5=new Vector();
		data5.add("31-33");
		data5.add(21);
		data5.add(12);
		data5.add(9);
		data5.add(2);
		data5.add(2);
		data5.add(0);
		Vector data6=new Vector();
		data6.add("34-36");
		data6.add(16);
		data6.add(8);
		data6.add(5);
		data6.add(6);
		data6.add(1);
		data6.add(0);
		rowimage.add(data1);
		rowimage.add(data2);
		rowimage.add(data3);
		rowimage.add(data4);
		rowimage.add(data5);
		rowimage.add(data6);
		jScrollPane.setBounds(0, 50, FrameSize.width,210);
		table.setDataVector(rowimage, columnsName);
		imagelabel.setIcon(new ImageIcon("image/ptsScatter.png"));
		text.setVisible(false);
	}

	void setTableReb(){
		columnsName.clear();
		columnsName.add("年龄/篮板");
		columnsName.add("0-2");
		columnsName.add("3-5");
		columnsName.add("6-8");
		columnsName.add("9+");
		rowimage.clear();
		Vector data1=new Vector();
		data1.add("19-21");
		data1.add(17);
		data1.add(6);
		data1.add(4);
		data1.add(1);
		Vector data2=new Vector();
		data2.add("22-24");
		data2.add(52);
		data2.add(34);
		data2.add(11);
		data2.add(3);
		Vector data3=new Vector();
		data3.add("25-27");
		data3.add(58);
		data3.add(37);
		data3.add(8);
		data3.add(2);
		Vector data4=new Vector();
		data4.add("28-30");
		data4.add(39);
		data4.add(24);
		data4.add(11);
		data4.add(0);
		Vector data5=new Vector();
		data5.add("31-33");
		data5.add(31);
		data5.add(13);
		data5.add(0);
		data5.add(3);
		Vector data6=new Vector();
		data6.add("34-36");
		data6.add(23);
		data6.add(7);
		data6.add(2);
		data6.add(0);
		
		rowimage.add(data1);
		rowimage.add(data2);
		rowimage.add(data3);
		rowimage.add(data4);
		rowimage.add(data5);
		rowimage.add(data6);
		jScrollPane.setBounds(0, 50, FrameSize.width,210);
		table.setDataVector(rowimage, columnsName);
		imagelabel.setIcon(new ImageIcon("image/rebScatter.png"));
		text.setVisible(false);
	}

	void setTableAss(){
		columnsName.clear();
		columnsName.add("年龄/篮板");
		columnsName.add("0-2");
		columnsName.add("3-5");
		columnsName.add("6-8");
		columnsName.add("9+");
		rowimage.clear();
		Vector data1=new Vector();
		data1.add("19-21");
		data1.add(23);
		data1.add(4);
		data1.add(1);
		data1.add(0);
		Vector data2=new Vector();
		data2.add("22-24");
		data2.add(73);
		data2.add(18);
		data2.add(4);
		data2.add(5);
		Vector data3=new Vector();
		data3.add("25-27");
		data3.add(79);
		data3.add(17);
		data3.add(5);
		data3.add(4);
		Vector data4=new Vector();
		data4.add("28-30");
		data4.add(47);
		data4.add(19);
		data4.add(5);
		data4.add(3);
		Vector data5=new Vector();
		data5.add("31-33");
		data5.add(33);
		data5.add(11);
		data5.add(2);
		data5.add(0);
		Vector data6=new Vector();
		data6.add("34-36");
		data6.add(26);
		data6.add(6);
		data6.add(0);
		data6.add(0);
		
		rowimage.add(data1);
		rowimage.add(data2);
		rowimage.add(data3);
		rowimage.add(data4);
		rowimage.add(data5);
		rowimage.add(data6);
		table.setDataVector(rowimage, columnsName);
		jScrollPane.setBounds(0, 50, FrameSize.width,210);
		imagelabel.setIcon(new ImageIcon("image/astScatter.png"));
		text.setVisible(false);
	}
	
	void setTable(){
		if(type.getSelectedItem().equals("数据偏离度")){
			setTableOne();
		}else if(type.getSelectedItem().equals("得分")){
			setTableAge();
		}else if(type.getSelectedItem().equals("篮板")){
			setTableReb();
		}else if(type.getSelectedItem().equals("助攻")){
			setTableAss();
		}
	
	}
}
