package ui.playerui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class PlayerContrastPanel extends JPanel{

	public PlayerContrastPanel(){
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		this.repaint();
	}
}