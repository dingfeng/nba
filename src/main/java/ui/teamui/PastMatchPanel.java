package ui.teamui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import bl.matchbl.MatchController;
import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;

public class PastMatchPanel extends JPanel{

	MatchController mc = new MatchController();
	String teamName;

	Vector<String> columnsName = new Vector<String>();
	
	JScrollPane pastjScrollPane;
	
	public PastMatchPanel(){
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		setPastTable();
	}
	/** 过往查询 */
	void setPastTable() {
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");

//		MatchesPO[] match = mc.getRegularTeamMatches(2012,teamName);
		Vector rowimage = new Vector();
//		for (int i = match.length - 6; i >= 0; i--) {
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
		MyTable pasttable = new MyTable(table);
		pastjScrollPane = new JScrollPane(pasttable);
		pastjScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pastjScrollPane.setBounds(0, 30, FrameSize.width,FrameSize.height -180);
		pastjScrollPane.setOpaque(false);
		pastjScrollPane.getViewport().setOpaque(false);

		pasttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

//					MyFrame.matchpanel.findMatchAccordingMatch(match,
//							pasttable.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});

		this.add(pastjScrollPane);
	}

}
