package ui.mainui;

import java.awt.Color;

import javax.swing.JTextField;

public class EditableTextField extends JTextField {

	public EditableTextField(){
//		this.setBorder(null);
		this.addFocusListener(new TextFieldAction());
		this.setBackground(Color.white);
	}
	

}
