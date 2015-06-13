package ui.playerui;

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
import blservice.matchblservice.Matchblservice;

public class PastMatchPanel extends JPanel{

	Matchblservice matchController = new MatchController();

	
	Vector<String> columnsName = new Vector<String>();
	Vector data = new Vector();
	DefaultTableModel table = new DefaultTableModel(data, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane pastjScrollPane;
	JComboBox season=new MyComboBox(new String[]{"2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985"});

	
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
		season.setBounds(FrameSize.width-150,0 ,100 ,30 );
		season.setBackground(Color.white);
		season.addActionListener(e->setPastTable(MyFrame.onePlayerPanel.nameresult.getText()));
		season.setForeground(Color.black);
		recent.add(season);
		this.add(recent);

	}
	/** 过往查询 */
	void setPastTable(String playerName) {
		columnsName.clear();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");

		MatchesPO[] match = matchController.getRegularPlayerMatches(Integer.parseInt((String) season.getSelectedItem()),playerName);
		data.clear();
		for (int i = match.length - 6; i >= 0; i--) {
			Vector rowData = new Vector();
			rowData.add(match[i].getDate());
			rowData.add(match[i].getTeam1().getName() + "-"
					+ match[i].getTeam2().getName());
			rowData.add(match[i].getTeam1().getTotalScores() + "-"
					+ match[i].getTeam2().getTotalScores());

			data.add(rowData);
		}

		
		table.setDataVector(data, columnsName);
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
