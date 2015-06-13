package ui.teamui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;

public class PastMatchPanel extends JPanel {

	MatchController mc = new MatchController();

	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane pastjScrollPane = new JScrollPane(mytable);
	JComboBox season = new MyComboBox(new String[] { "2014", "2013", "2012",
			"2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004",
			"2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996",
			"1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988",
			"1987", "1986", "1985" });

	public PastMatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		setText();
	}

	/** 设置界面提示文字 */
	void setText() {
		JLabel recent = new JLabel("过往比赛");
		recent.setBounds(0, 0, FrameSize.width, 30);

		recent.setOpaque(true);
		recent.setBackground(FrameSize.bluecolor);
		recent.setForeground(Color.white);
		season.setBounds(FrameSize.width - 150, 0, 100, 30);
		season.setBackground(Color.white);
		season.addActionListener(e -> setPastTable());
		season.setForeground(Color.black);
		pastjScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pastjScrollPane.setBounds(0, 30, FrameSize.width,
				FrameSize.height * 3 / 4 - 180);
		pastjScrollPane.setOpaque(false);
		pastjScrollPane.getViewport().setOpaque(false);

		this.add(recent);
		recent.add(season);

	}

	/** 过往查询 */
	void setPastTable() {
		String teamname = MyFrame.teampanel.nameAbridgeresult.getText();
		columnsName.clear();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");

		MatchesPO[] match = mc.getRegularTeamMatches(
				Integer.parseInt((String) season.getSelectedItem()), teamname);
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
		

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int j = match.length-6-mytable.getSelectedRow();
					int id = match[j].getMatchId();
					MatchesPO match = mc.getMatchById(id);
					MyFrame.onematchpanel.setOneNowMatch(match.getTeam1(), match.getTeam2());
					MyFrame.setMatch();
					MyFrame.card.show(MyFrame.mainpanel, "onematch");
				}
			}

		});

		this.add(pastjScrollPane);
		this.repaint();
	}

}
