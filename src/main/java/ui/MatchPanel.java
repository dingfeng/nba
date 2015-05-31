package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;

public class MatchPanel extends JPanel {

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		setTodayMatches();
	}

	/**查找栏*/
	private  JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		/*
		DateChooseButton dateButton = new DateChooseButton();
		dateButton.setBounds(0,5,150,30);
//		dateButton.setEnd(this);
		headerPanel.add(dateButton);
		*/
		
		return headerPanel;
				
	}
	
	/**一天的所有比赛*/
	private void setTodayMatches(){
		JLabel[] jLabel = new JLabel[100];
		JScrollPane jScrollPane = new JScrollPane();
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.red);
		jPanel.setBounds(0,0,FrameSize.width,3000);
		jScrollPane.setBounds(0,40,FrameSize.width,FrameSize.height*7/8-40);
		for(int i=0;i<100;i++){
			jLabel[i] = new JLabel(String.valueOf(i));
			jLabel[i].setBounds(0,i*30,100,30);
			jPanel.add(jLabel[i]);
		}
		jScrollPane.add(jPanel);
		jPanel.setPreferredSize(new Dimension(FrameSize.width, 3000));
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setVisible(true);
		this.add(jScrollPane);
		this.repaint();
	}
	
	
}