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
import ui.mainui.MyTable;

public class StatisticsPanel extends JPanel {
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	JTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	public StatisticsPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		setTable();
	}

	void setTable() {
		columnsName.add("年龄段");
		columnsName.add("球员数");
		columnsName.add("球员占数比");
		columnsName.add("场均得分平均");
		columnsName.add("得分偏离度");
		columnsName.add("场均篮板平均");
		columnsName.add("篮板偏离度");
		columnsName.add("场均助攻平均");
		columnsName.add("助攻偏离度");
		
		Vector data1=new Vector();
		data1.add("19-21");
		data1.add(28);
		data1.add("7.27%");
		data1.add(7.41);
		data1.add("0.25%");
		data1.add(3.55);
		data1.add("1.05%");
		data1.add("1.31");
		data1.add("-1.37%");
		Vector data2=new Vector();
		data2.add("22-24");
		data2.add(100);
		data2.add("25.97%");
		data2.add(7.64);
		data2.add("1.76%");
		data2.add(3.23);
		data2.add("1.06%");
		data2.add(1.64);
		data2.add("1.02%");
		Vector data3=new Vector();
		data3.add("25-27");
		data3.add(105);
		data3.add("27.27%");
		data3.add(7.11);
		data3.add("-0.17%");
		data3.add(3.12);
		data3.add("0.16%");
		data3.add(1.52);
		data3.add("-1.06%");
		Vector data4=new Vector();
		data4.add("28-30");
		data4.add(74);
		data4.add("19.23%");
		data4.add(8.15);
		data4.add("2.67%");
		data4.add(3.30);
		data4.add("1.22%");
		data4.add(1.94);
		data4.add("4.33%");
		Vector data5=new Vector();
		data5.add("31-33");
		data5.add(46);
		data5.add("11.95%");
		data5.add(5.86);
		data5.add("-2.17%");
		data5.add(2.70);
		data5.add("-1.55%");
		data5.add(1.45);
		data5.add("-0.97%");
		Vector data6=new Vector();
		data6.add("34-36");
		data6.add(26);
		data6.add("6.75%");
		data6.add(5.20);
		data6.add("-1.84%");
		data6.add(2.26);
		data6.add("-1.83%");
		data6.add(1.07);
		data6.add("-2.2%");
		Vector data7=new Vector();
		data7.add("37+");
		data7.add(6);
		data7.add("1.56%");
		data7.add(4.88);
		data7.add("-0.5%");
		data7.add(2.89);
		data7.add("-0.11%");
		data7.add(1.70);
		data7.add("-0.56%");
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
		jScrollPane.setBounds(0, FrameSize.height/4, FrameSize.width,
				FrameSize.height * 5 / 8);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		this.add(jScrollPane);
		this.repaint();
	}
}
