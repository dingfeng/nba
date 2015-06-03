package ui.teamui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;

public class RecentMatchPanel extends JPanel {

	MatchController mc = new MatchController();
	String teamName;

	Vector<String> columnsName = new Vector<String>();
	
	JScrollPane pastjScrollPane;
	JScrollPane recentjScrollPane;

	public RecentMatchPanel(String teamname) {
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		
		this.teamName = teamname;

		setText();
		setRecentTable();
		this.repaint();

	}

	/** 设置界面提示文字 */
	void setText() {
		JLabel recent = new JLabel("近期比赛");
		JLabel team = new JLabel();
		team.setText(teamName);

		team.setBounds(80, 20, 50, 50);
		recent.setBounds(0,0, FrameSize.width, 30);
		
		recent.setOpaque(true);
		recent.setBackground(FrameSize.bluecolor);
		recent.setForeground(Color.white);
		team.setForeground(Color.white);

		this.add(team);
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
