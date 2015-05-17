package ui.playerui;

import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.mainui.CharacterButton;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;

public class PlayerPanel extends JPanel{
	
	public PlayerPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);		
	}
	
	private void HeaderPanel(){
		CharacterButton [] character= new CharacterButton[27];
		character[0]=new CharacterButton("全部");
		for(int i=1;i<27;i++){
			character[i]=new CharacterButton((char)('A'+i-1));
		}
	}
}
