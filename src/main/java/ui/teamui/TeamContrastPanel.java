package ui.teamui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class TeamContrastPanel extends JPanel{
	public TeamContrastPanel(){
	this.setLayout(null);
	this.setBounds(0, 0,
			 FrameSize.width , FrameSize.height * 3 / 4);
	this.setBackground(Color.white);
	this.repaint();
	}
}
