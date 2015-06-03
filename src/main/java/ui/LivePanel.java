package ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import ui.mainui.FrameSize;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;

public class LivePanel extends JPanel {

	MyToggleButton livebutton;
	MyToggleButton databutton;
	JLabel team1 = new JLabel();
	JLabel team2 = new JLabel();
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	public LivePanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		setText();
		setLive();
	}

	void setText() {
		livebutton = new MyToggleButton(new ImageIcon("image/live1.png"),
				Color.white, Color.white);
		databutton = new MyToggleButton(new ImageIcon("image/data1.png"),
				Color.white, Color.white);
		livebutton.addActionListener(e -> setLive());
		databutton.addActionListener(e -> setData());
		livebutton.setSelectedIcon(new ImageIcon("image/live2.png"));
		databutton.setSelectedIcon(new ImageIcon("image/data2.png"));
		livebutton.setBounds(FrameSize.width * 5 / 6, 10, 100, 40);
		databutton.setBounds(FrameSize.width * 11 / 12, 10, 100, 40);
		this.add(livebutton);
		this.add(databutton);

		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, FrameSize.height/5, FrameSize.width,
				FrameSize.height * 27 / 40 );
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
		this.add(jScrollPane);
		this.repaint();

		team1.setBounds(20, 20, 100, 100);
		team2.setBounds(FrameSize.width-20,20,100,100);
	}

	void setLive() {
		livebutton.setSelected(true);
		databutton.setSelected(false);

		columnsName.removeAllElements();

		columnsName.add("时间");
		columnsName.add("球队");
		columnsName.add("事件");
		columnsName.add("比分");
		rowimage.clear();
		for (int i = 0; i < 30; i++) {

			Vector data = new Vector();
			data.add(i + 1);
			data.add("球队");
			data.add("blablablabla");
			data.add("0-0");

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		this.repaint();

	}

	void setData() {
		livebutton.setSelected(false);
		databutton.setSelected(true);
		
		columnsName.removeAllElements();

		columnsName.add("首发");
		columnsName.add("时间");
		columnsName.add("投篮");
		columnsName.add("三分");
		columnsName.add("罚球");
		columnsName.add("前场");
		columnsName.add("后场");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("犯规");
		columnsName.add("抢断");
		columnsName.add("封盖");
		columnsName.add("得分");
		columnsName.add("+/-");
		rowimage.clear();
		for (int i = 0; i < 30; i++) {

			Vector data = new Vector();
			data.add(i + 1);
			data.add("球队");
			data.add("blablablabla");
			data.add("0-0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");
			data.add("0");

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		this.repaint();

	}
}
