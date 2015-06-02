package ui.teamui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyToggleButton;

public class TeamDataPanel extends JPanel{

	MyToggleButton alldata;
	MyToggleButton avedata;
	public TeamDataPanel(){
		this.setLayout(null);
		this.setBounds(0, 0,
				 FrameSize.width , FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		setText();
		this.repaint();
	}
	public void setText(){
		alldata=new MyToggleButton("总数",FrameSize.bluecolor,FrameSize.darkbluecolor);
		avedata=new MyToggleButton("场均",FrameSize.bluecolor,FrameSize.darkbluecolor);
		alldata.setBounds(FrameSize.width-100, 0, 50,30);
		avedata.setBounds(FrameSize.width-50, 0, 50,30);
		
		this.add(alldata);
		this.add(avedata);
	}
}
