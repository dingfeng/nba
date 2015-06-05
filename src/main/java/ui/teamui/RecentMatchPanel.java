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
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	
	JScrollPane recentjScrollPane;

	public RecentMatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);

		setText();
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
	public void setRecentTable(String teamname) {
		columnsName.clear();
		rowimage.clear();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		MatchesPO[] match = mc.getRegularTeamMatches(2014,teamname);

		for (int i = match.length-1; i >match.length-6; i--) {
			Vector data = new Vector();
			data.add(match[i].getDate());
			data.add(match[i].getTeam1().getName() + "-"
					+ match[i].getTeam2().getName());
			data.add(match[i].getTeam1().getTotalScores() + "-"
					+ match[i].getTeam2().getTotalScores());

			rowimage.add(data);
		}
		table.setDataVector(rowimage, columnsName);
		
		mytable.updateUI();
		recentjScrollPane = new JScrollPane(mytable);

		recentjScrollPane.setBounds(0, 30, FrameSize.width, FrameSize.height*3/4-180);
		recentjScrollPane.setOpaque(false);
		recentjScrollPane.getViewport().setOpaque(false);

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

//					MyFrame.matchpanel.findMatchAccordingMatch(match,
//							recenttable.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});

		this.add(recentjScrollPane);
		this.repaint();
	}


}
