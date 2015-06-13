package ui.playerui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bl.playerbl.PlayerController;
import bl.teambl.TeamController;
import blservice.playerblservice.PlayerBlService;
import blservice.teamblservice.Teamblservice;
import dataservice.playerdataservice.SeasonType;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;

public class PlayerContrastPanel extends JPanel {

	PlayerBlService playerController = new PlayerController();
	JLabel imagelabel = new JLabel();
	JLabel playerImage1 = new JLabel();
	JLabel playerImage2 = new JLabel();
	JLabel playerName1 = new JLabel();
	JLabel playerName2 = new JLabel();
	JTextField player2;
	boolean one = true;
	JComboBox seasontype = new MyComboBox(new String[] { "常规赛", "季后赛" });
	SeasonType season = SeasonType.REGULAR;

	public PlayerContrastPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 3 / 4);
		this.setBackground(Color.white);
		setchoose();
		this.repaint();
	}

	private void setchoose() {
		JLabel header = new JLabel();
		header.setBackground(FrameSize.bluecolor);
		header.setBounds(0, 0, FrameSize.width, 30);
		header.setOpaque(true);
		this.add(header);
		player2 = new EditableTextField();
		player2.setText("NBA联盟平均");
		player2.setBounds(FrameSize.width - 200, 0, 150, 30);
		JButton enter = new MyButton("确定", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		enter.addActionListener(e -> setCompare());
		enter.setBounds(FrameSize.width - 50, 0, 30, 30);
		enter.setForeground(Color.white);
		player2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) // 按回车键执行相应操作;
				{
					setCompare();
				}
			}
		});
		JLabel playerName = new JLabel("球员");
		playerName.setBounds(FrameSize.width - 250, 0, 50, 30);
		playerImage1.setBounds(
				FrameSize.width / 2 - 300 - FrameSize.height / 5,
				FrameSize.height / 8, FrameSize.height / 5,
				FrameSize.height / 5);
		playerImage2.setBounds(FrameSize.width / 2 + 300, FrameSize.height / 8,
				FrameSize.height / 5, FrameSize.height / 5);
		playerName1.setBounds(FrameSize.width / 2 - 300 - FrameSize.height / 5,
				FrameSize.height / 3, 100, 50);
		playerName2.setBounds(FrameSize.width / 2 + 300, FrameSize.height / 3,
				100, 50);
		playerImage2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});
		playerName2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		seasontype.setBounds(FrameSize.width * 2 / 3, 0, 100, 30);
		seasontype.addActionListener(e -> setOneOrTwo());
		seasontype.setBackground(Color.white);
		seasontype.setForeground(Color.black);
		header.add(seasontype);
		header.add(enter);
		header.add(player2);
		this.add(playerName1);
		this.add(playerName2);
		this.add(playerImage1);
		this.add(playerImage2);

	}

	private void setOneOrTwo() {
		if (seasontype.getSelectedItem().equals("常规赛")) {
			season = SeasonType.REGULAR;
		} else {
			season = SeasonType.PLAYOFF;
		}
		if (one) {
			setChart(MyFrame.onePlayerPanel.nameresult.getText());
		} else {
			setCompare();
		}
	}

	private void setCompare() {
		one = false;
		String player = player2.getText();
		try{
			Image image = playerController.getCompareImage(2014, MyFrame.onePlayerPanel.nameresult.getText(), player2.getText(), season);
			playerName2.setText(player);
			playerImage2.setIcon(FrameSize.scaleImage(new ImageIcon(playerController.getPlayerImage(player)),FrameSize.height/5, FrameSize.height/5 ));
			imagelabel.setIcon(new ImageIcon(image));
			add(playerName2);
			add(playerImage2);
			add(imagelabel);
			repaint();
		}catch(NullPointerException e){
//			JOptionPane.showMessageDialog(null, "未找到该球员", "查找失败",
//					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setChart(String playerName){
		one=true;
		playerName1.setText(MyFrame.onePlayerPanel.nameresult.getText());
		playerName2.setText("NBA联盟平均");
		playerImage1.setIcon(FrameSize.scaleImage(new ImageIcon(playerController.getPlayerImage(playerName1.getText())), FrameSize.height/5, FrameSize.height/5));
		playerImage2.setIcon(FrameSize.scaleImage(new ImageIcon("image/nbabig.png"), FrameSize.height/5, FrameSize.height/5));
		new Thread(){
			public void run(){
		Image image=playerController.getPlayerBar(2014, playerName, season);
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
	
}
