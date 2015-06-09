package ui.teamui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;

public class TeamContrastPanel extends JPanel{
	Teamblservice tc=new TeamController();
	
	public TeamContrastPanel(){
	this.setLayout(null);
	this.setBounds(0, 0,
			 FrameSize.width , FrameSize.height * 3 / 4);
	this.setBackground(Color.white);
//	setChart();
	this.repaint();
	}
	
	void setChart(){
		Image image=tc.getTeamBar(2013, "ATL", SeasonType.REGULAR);
		JLabel imagelabel=new JLabel(new ImageIcon(image));
		imagelabel.setBounds(0, 0, 100, 100);
		imagelabel.setOpaque(true);
		this.add(imagelabel);
	}
}
