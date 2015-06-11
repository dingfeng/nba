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
		System.out.print("statistics");
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
		rowimage.add(data1);
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
