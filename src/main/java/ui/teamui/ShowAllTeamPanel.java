package ui.teamui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class ShowAllTeamPanel extends JPanel{

	public ShowAllTeamPanel(){
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		setText();
		setEastTeam();
		setWestTeam();
		this.repaint();
	}
	/**东部联盟 西部联盟*/
	void setText(){
		JLabel eastpic=new JLabel();
		eastpic.setBounds(2, 0, FrameSize.width, 50);
		eastpic.setIcon(new ImageIcon("image/east.png"));
		JLabel easttext=new JLabel();
		easttext.setBackground(FrameSize.darkbluecolor);
		easttext.setBounds(0, 5, FrameSize.width, 50);
		easttext.setForeground(Color.white);
		easttext.setBackground(new Color(30,81,140));
		easttext.setOpaque(true);
		
		JLabel westpic=new JLabel();
		westpic.setBounds(2, 0, FrameSize.width, 50);
		westpic.setIcon(new ImageIcon("image/west.png"));
		JLabel westtext=new JLabel();
		westtext.setBounds(0, FrameSize.height *7 / 16, FrameSize.width, 50);
		westtext.setForeground(Color.white);
		westtext.setBackground(new Color(30,81,140));
		westtext.setOpaque(true);
		
		this.add(easttext);
		easttext.add(eastpic);
		this.add(westtext);
		westtext.add(westpic);
	}
	
	/**东部球队*/
	void setEastTeam(){
		JPanel eastpanel=new JPanel();
		eastpanel.setLayout(new GridLayout(5, 3, 1, 1));
		eastpanel.setBounds(0, 55, FrameSize.width, FrameSize.height / 2);
		eastpanel.setOpaque(false);
		JButton j1=new JButton("222",new ImageIcon("image/east.png"));
	
		eastpanel.add(j1);
		this.add(eastpanel);
		
	}
	/**西部球队*/
	void setWestTeam(){
		JPanel westpanel=new JPanel();
		westpanel.setLayout(new GridLayout(5, 3, 1, 1));
		westpanel.setBounds(0, FrameSize.height *7 / 16+50, FrameSize.width, FrameSize.height / 2);
		westpanel.setOpaque(false);
		JButton j1=new JButton("22",new ImageIcon("image/west.png"));
		westpanel.add(j1);
		this.add(westpanel);
	}
}
