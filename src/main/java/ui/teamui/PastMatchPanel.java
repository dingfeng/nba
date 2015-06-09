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

import bl.matchbl.MatchController;
import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;

public class PastMatchPanel extends JPanel{

	MatchController mc = new MatchController();

	
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane pastjScrollPane;
	
	public PastMatchPanel(){
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		setText();
	}
	
	/** 设置界面提示文字 */
	void setText() {
		JLabel recent = new JLabel("过往比赛");
		recent.setBounds(0,0, FrameSize.width, 30);
		
		recent.setOpaque(true);
		recent.setBackground(FrameSize.bluecolor);
		recent.setForeground(Color.white);
		
		this.add(recent);

	}
	/** 过往查询 */
	void setPastTable(String teamname) {
		columnsName.clear();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");

		MatchesPO[] match = mc.getRegularTeamMatches(2012,teamname);
		rowimage.clear();
		for (int i = match.length - 6; i >= 0; i--) {
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
		pastjScrollPane = new JScrollPane(mytable);
		pastjScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pastjScrollPane.setBounds(0, 30, FrameSize.width,FrameSize.height*3/4 -180);
		pastjScrollPane.setOpaque(false);
		pastjScrollPane.getViewport().setOpaque(false);

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

//					MyFrame.matchpanel.findMatchAccordingMatch(match,
//							table.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});

		this.add(pastjScrollPane);
		this.repaint();
	}

}
