package ui.playerui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bl.playerbl.PlayerController;
import bl.teambl.TeamController;
import blservice.playerblservice.PlayerBlService;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;

public class PlayerContrastPanel extends JPanel{

	PlayerBlService playerController=new PlayerController();
	JLabel imagelabel=new JLabel();
	JLabel player1 = new JLabel();
	JLabel player2 = new JLabel();
	
	public PlayerContrastPanel(){
	this.setLayout(null);
	this.setBounds(0, 0,
			 FrameSize.width , FrameSize.height * 3 / 4);
	this.setBackground(Color.white);
	this.repaint();
	}
	
	public void setChart(String playerName){
		Image image=playerController.getPlayerBar(2014, playerName, SeasonType.REGULAR);
		
		imagelabel.setIcon(new ImageIcon(image));
		imagelabel.setBounds((FrameSize.width-560)/2, 30, 560, 420);
		imagelabel.setOpaque(false);		
		imagelabel.repaint();
		this.add(imagelabel);
		this.repaint();
		this.validate();
		imagelabel.setVisible(true);
		imagelabel.repaint();
	}
}
