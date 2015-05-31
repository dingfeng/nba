package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import po.MatchesPO;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;
import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;

public class MatchPanel extends JPanel {
	Matchblservice matchController = new MatchController();

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
//		setTodayMatches();
		test();
	}

	/**查找栏*/
	private  JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		
		DateChooseButton dateButton = new DateChooseButton();
		dateButton.setBounds(0,5,150,30);
//		dateButton.setEnd(this);
		headerPanel.add(dateButton);
		
		
		return headerPanel;
				
	}
	
	/**一天的所有比赛*/
	public void setTodayMatches(Date date){
		MatchesPO [] matches = matchController.getTimeMatches(date);
		for(int i=0;i<matches.length;i++){
			
		}
	}
	
	private void test(){
		JLabel[] jLabel = new JLabel[100];
		JScrollPane jScrollPane = new JScrollPane();
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.red);
		jScrollPane.setBounds(0,40,FrameSize.width,FrameSize.height*7/8-40);
		for(int i=0;i<100;i++){
			jLabel[i] = new JLabel(String.valueOf(i));
			jLabel[i].setBounds(0,i*30,100,30);
			jPanel.add(jLabel[i]);
		}
		jScrollPane.getViewport().add(jPanel);
		jPanel.setPreferredSize(new Dimension(FrameSize.width, 3000));
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jScrollPane);
		this.repaint();
	}
	
	
}