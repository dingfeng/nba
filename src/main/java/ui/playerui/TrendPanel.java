package ui.playerui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class TrendPanel extends JPanel{

	public TrendPanel(){
	
			this.setLayout(null);
			this.setBounds(0, 0,
					 FrameSize.width , FrameSize.height * 3 / 4);
			this.setBackground(Color.white);
//			setText();
		
	}
}
