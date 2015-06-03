package ui.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

public class MyButton extends JButton {

	Color backcolor = null;
	Color focuscolor = null;

	public MyButton(ImageIcon image, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
//		this.setContentAreaFilled(false);
		this.setIcon(image);
		this.setBackground(back);
		this.setFocusPainted(false);
	}
	
	public MyButton(String text, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
//		this.setContentAreaFilled(false);
		this.setText(text);
		this.setBackground(back);
		this.setForeground(focus);
		this.setFocusPainted(false);
	}


}
