package ui.mainui;

import java.awt.Color;

import javax.swing.JButton;

public class CharacterButton extends JButton{
	
	public CharacterButton(String text){
		this.setText(text);
		this.setBorder(null);
		this.setForeground(Color.white);
		this.setBackground(new Color(87,89,91));
	}
	
	public CharacterButton(char c){
		this.setText(String.valueOf(c));
		this.setBorder(null);
		this.setForeground(Color.white);
		this.setBackground(new Color(87,89,91));
	}


}
