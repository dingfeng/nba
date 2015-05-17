package ui.playerui;

import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;

public class PlayerPanel extends JPanel{
	
	public PlayerPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);		
	}
}
