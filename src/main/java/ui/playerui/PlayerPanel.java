package ui.playerui;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.mainui.CharacterButton;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;

public class PlayerPanel extends JPanel{
	
	public PlayerPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width,FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		
	}
	
	private static JPanel HeaderPanel(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0,0,FrameSize.width,60);
		headerPanel.setBackground(new Color(87,89,91));
		CharacterButton [] character= new CharacterButton[27];
		character[0]=new CharacterButton("全部");
		for(int i=1;i<27;i++){
			character[i]=new CharacterButton((char)('A'+i-1));
		}
		for(int i=0;i<27;i++){
			character[i].setLocation(30*i,0);
			headerPanel.add(character[i]);
		}
		return headerPanel;
	}
}
