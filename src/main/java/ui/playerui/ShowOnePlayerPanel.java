package ui.playerui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ui.mainui.FrameSize;
import ui.mainui.MyToggleButton;

public class ShowOnePlayerPanel extends JPanel{
	
	public static CardLayout card = new CardLayout();
	public static JPanel playermain = new JPanel();
	public static PastMatchPanel pastMatchPanel = new PastMatchPanel();
	public static RecentMatchPanel recentMatchPanel = new RecentMatchPanel(null);
	public static PlayerContrastPanel playerContrastPanel = new PlayerContrastPanel();
	public static PlayerDataPanel playerDataPanel = new PlayerDataPanel();

	JPanel findPanel = new JPanel();

	

	
	public ShowOnePlayerPanel(){
		this.setLayout(null);
		this.setBounds(0,0,FrameSize.width,FrameSize.height*7/8);
		this.setOpaque(false);
		setPlayerMain();
//		setHeader();
//		setFind();
		
		this.add(playermain);
//		this.add(find);
		

		
	}
	
	private void setPlayerMain(){
		playermain.setBounds(0,FrameSize.height*7/8/4+50,FrameSize.width,FrameSize.height*7/8*3/4-50);
		playermain.setLayout(card);
		playermain.add("data",playerDataPanel);
		playermain.add("past",pastMatchPanel);
		playermain.add("recent",recentMatchPanel);
		playermain.add("contrast",playerContrastPanel);
	}
	
	private void setheaderPanel(){
		JPanel headerPanel = new JPanel();
		MyToggleButton dataButton = new MyToggleButton("数据", Color.black, Color.gray);
		MyToggleButton recentButton = new MyToggleButton("近期比赛", Color.black, Color.gray);
		MyToggleButton pastButton = new MyToggleButton("过往查询", Color.black, Color.gray);
		MyToggleButton contrastButton = new MyToggleButton("对比", Color.black, Color.gray);
		
		dataButton.setBounds(0, 0, FrameSize.width/4, 50);
		recentButton.setBounds(FrameSize.width / 4, 0, FrameSize.width/4, 50);
		pastButton.setBounds(FrameSize.width / 2, 0, FrameSize.width/4, 50);
		contrastButton.setBounds(FrameSize.width *3/ 4, 0, FrameSize.width/4, 50);
		
		dataButton.setForeground(Color.white);
		recentButton.setForeground(Color.white);
		pastButton.setForeground(Color.white);
		contrastButton.setForeground(Color.white);
		
		dataButton.addActionListener(e->setTeamdata());
		recentButton.addActionListener(e->setRecent());
		pastButton.addActionListener(e->setPast());
		contrastButton.addActionListener(e->setContrast());
		
		JPopupMenu type = new JPopupMenu();
		JMenuItem normal = new JMenuItem("基本数据");
		normal.setBackground(Color.white);
		normal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.print("?");
			}

		});
		JMenuItem higher = new JMenuItem("高阶数据");
		higher.setBackground(Color.white);
		higher.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.print("?？");
			}

		});
		
		type.add(normal);
		type.add(higher);
		type.setBackground(Color.white);

		dataButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				type.show(e.getComponent(), 0, 50);
			}

		});
		
		headerPanel.add(dataButton);
		headerPanel.add(recentButton);
		headerPanel.add(pastButton);
		headerPanel.add(contrastButton);
	
		headerPanel.setBounds(0,FrameSize.height*7/8/4,FrameSize.width,50);
		this.add(headerPanel);
	}

	private Object setContrast() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object setPast() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object setRecent() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object setTeamdata() {
		// TODO Auto-generated method stub
		return null;
	}
}