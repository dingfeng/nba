package ui.mainui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UneditableTextField extends JTextField {

	public UneditableTextField() {
		this.setBorder(null);
		this.setEditable(false);
		this.addFocusListener(new TextFieldAction());
		this.setBackground(Color.white);
		this.setForeground(Color.black);
		this.setHorizontalAlignment(SwingConstants.CENTER);//居中
	}

}
