package ui.playerui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import po.MatchTeamPO;
import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;

public class RecentMatchPanel extends JPanel{

	MatchController mc = new MatchController();
	String playerName;

	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane recentjScrollPane = new JScrollPane(mytable);

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
		recentjScrollPane.setBounds(0, 30, FrameSize.width, FrameSize.height*3/4-180);
		recentjScrollPane.setOpaque(false);
		recentjScrollPane.getViewport().setOpaque(false);

		this.add(recent);


	}

	/** 近期比赛 */
	public void setRecentTable() {
		String playerName=MyFrame.onePlayerPanel.nameresult.getText();
		columnsName.clear();
		rowimage.clear();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		MatchesPO[] match = mc.getRegularPlayerMatchesn(2014,playerName);
      
		for (int i = 0; i < match.length; i++) {
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
		

		
		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int j = match.length-mytable.getSelectedRow()-1;
					int id = match[j].getMatchId();
					MatchesPO match = mc.getMatchById(id);
					MyFrame.onematchpanel.setOneNowMatch(match.getTeam1(), match.getTeam2());
					MyFrame.setMatch();
					MyFrame.card.show(MyFrame.mainpanel, "onematch");
					
				}
			}

		});

		this.add(recentjScrollPane);
		this.repaint();
	}


}
