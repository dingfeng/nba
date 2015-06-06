package ui.playerui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;

public class RecentMatchPanel extends JPanel{
	Matchblservice mc = new MatchController();
	String playerName;

	Vector<String> columnsName = new Vector<String>();
	
	JScrollPane pastjScrollPane;
	JScrollPane recentjScrollPane;

	public RecentMatchPanel(String playername) {
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height*7/8*3/4-50);
		this.setBackground(Color.white);
		
		this.playerName = playername;

		setText();
		setRecentTable();
		this.repaint();

	}

	/** 设置界面提示文字 */
	void setText() {
		JLabel recent = new JLabel("近期比赛");
		recent.setBounds(0,0, FrameSize.width, 30);
		
		recent.setOpaque(true);
		recent.setBackground(FrameSize.bluecolor);
		recent.setForeground(Color.white);
		
		this.add(recent);

	}

	/** 近期比赛 */
	void setRecentTable() {
		Vector rowimage = new Vector();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
//		MatchesPO[] match = mc.getTimeMatches(null);

//		for (int i = 0; i < 5; i++) {
//			Vector data = new Vector();
//			data.add(match[i].getDate());
//			data.add(match[i].getTeam1().getName() + "-"
//					+ match[i].getTeam2().getName());
//			data.add(match[i].getTeam1().getTotalScores() + "-"
//					+ match[i].getTeam2().getTotalScores());
//
//			rowimage.add(data);
//		}

		DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
		MyTable recenttable = new MyTable(table);

		recentjScrollPane = new JScrollPane(recenttable);

		recentjScrollPane.setBounds(0, 30, FrameSize.width, FrameSize.height*3/4-180);
		recentjScrollPane.setOpaque(false);
		recentjScrollPane.getViewport().setOpaque(false);

		recenttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

//					MyFrame.matchpanel.findMatchAccordingMatch(match,
//							recenttable.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});

		this.add(recentjScrollPane);
	}


}
