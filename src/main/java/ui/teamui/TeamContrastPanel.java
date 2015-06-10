package ui.teamui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;

public class TeamContrastPanel extends JPanel{
	Teamblservice tc=new TeamController();
	JLabel imagelabel=new JLabel();
	
	public TeamContrastPanel(){
	this.setLayout(null);
	this.setBounds(0, 0,
			 FrameSize.width , FrameSize.height * 3 / 4);
	this.setBackground(Color.white);
	this.repaint();
	}
	
	public void setChart(){
		new Thread()
		{
			public void run()
			{
		String teamname=MyFrame.teampanel.nameAbridgeresult.getText();
		Image image=tc.getTeamBar(2014, teamname, SeasonType.REGULAR);
		
		imagelabel.setIcon(new ImageIcon(image));
		imagelabel.setBounds((FrameSize.width-560)/2, 30, 560, 420);
		imagelabel.setOpaque(false);		
		imagelabel.repaint();
		add(imagelabel);
		repaint();
		validate();
		imagelabel.setVisible(true);
		imagelabel.repaint();
			}
		}.start();
	}
}
