package ui.playerui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bl.playerbl.PlayerController;
import blservice.playerblservice.PlayerBlService;
import ui.mainui.FrameSize;

public class TrendPanel extends JPanel{
	
	JLabel pic = new JLabel();
	PlayerBlService playerController = new PlayerController();

	public TrendPanel(){
	
			this.setLayout(null);
			this.setBounds(0, 0,
					 FrameSize.width , FrameSize.height * 3 / 4);
			this.setBackground(Color.white);
			JLabel blue=new JLabel("球员趋势");
			blue.setBounds(0, 0,FrameSize.width,30);
			blue.setBackground(FrameSize.bluecolor);
			blue.setOpaque(true);
			blue.setForeground(Color.white);
			this.add(blue);
//			setText();
		
	}
	
	public void setPic(String playerName){
		pic.setBounds((FrameSize.width-560)/2, 30, 560, 420);
		pic.setIcon(new ImageIcon(playerController.getLineChartImage(2014, playerName)));
		this.add(pic);
		this.repaint();
	}
}
