package ui.teamui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import po.TeamPO;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyFrame;
import bl.teambl.TeamController;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;

public class TeamContrastPanel extends JPanel{
	Teamblservice tc=new TeamController();
	JLabel imagelabel=new JLabel();
	JLabel pic1=new JLabel();
	JLabel pic2=new JLabel();
	JLabel name1=new JLabel();
	JLabel name2=new JLabel();
	JTextField t2;
	public TeamContrastPanel(){
	this.setLayout(null);
	this.setBounds(0, 0,
			 FrameSize.width , FrameSize.height * 3 / 4);
	this.setBackground(Color.white);
	setchoose();
	this.repaint();
	
	}
	
	void setchoose(){
		t2=new EditableTextField();
		t2.setText("NBA联盟平均");
		t2.setBounds(FrameSize.width-200,5,150,30);
		JButton enter=new MyButton("确定",Color.white,Color.gray);
		enter.addActionListener(e->setCompare());
		enter.setBounds(FrameSize.width-50,5,30,30);
		t2.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) // 按回车键执行相应操作;
				{
					setCompare();
				}
			}
		});
		JLabel t=new JLabel("队名");
		t.setBounds(FrameSize.width-250, 5, 50, 30);
		pic1.setBounds(FrameSize.width/2-300-FrameSize.height/5, FrameSize.height/8, FrameSize.height/5, FrameSize.height/5);
		pic2.setBounds(FrameSize.width/2+300, FrameSize.height/8, FrameSize.height/5, FrameSize.height/5);
		name1.setBounds(FrameSize.width/2-300-FrameSize.height/5, FrameSize.height/3, 100, 50);
		name2.setBounds(FrameSize.width/2+300, FrameSize.height/3, 100,50);
		pic1.setOpaque(false);
		this.add(enter);
		this.add(t2);
		this.add(name1);
		this.add(name2);
		this.add(pic1);
		this.add(pic2);
	}
	
	public void setChart(){
		String teamname=MyFrame.teampanel.nameAbridgeresult.getText();
		TeamPO team=tc.getTeamData(teamname);
		pic1.setIcon(FrameSize.scaleImage(new ImageIcon(team.getImage()),FrameSize.height/5, FrameSize.height/5));
		name1.setText(team.getName());
		name2.setText("NBA联盟平均");
		new Thread()
		{
			public void run()
			{
		
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

	void setCompare(){
		String teamname=t2.getText();
		try {
			TeamPO team=tc.getTeamData(teamname);
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null, "未找到该球队", "查找失败",
					JOptionPane.ERROR_MESSAGE);

			return;
		}
		TeamPO team=tc.getTeamData(teamname);
		name2.setText(team.getName());
		pic2.setIcon(FrameSize.scaleImage(new ImageIcon(team.getImage()),FrameSize.height/5, FrameSize.height/5));
		Image image=tc.getTeamCompare(2014, MyFrame.teampanel.nameAbridgeresult.getText(),teamname, SeasonType.REGULAR);
		
		imagelabel.setIcon(new ImageIcon(image));
		this.add(name2);
		this.add(pic2);
		this.add(imagelabel);
		this.repaint();
		
	}
}
