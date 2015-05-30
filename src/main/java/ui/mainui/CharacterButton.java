package ui.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class CharacterButton extends JButton{
	
	public CharacterButton(String text){
		this.setText(text);
		figure();
	}
	
	public CharacterButton(char c){
		this.setText(String.valueOf(c));
		figure();
	}
	
	private void figure(){
		this.setBorder(null);
		this.setForeground(Color.white);
		this.setBackground(new Color(87,89,91));
		this.setSize(30,30);
		this.addMouseListener(new button());
	}
	
	class button implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 ((JButton)e.getSource()).setBackground(new Color(69,69,69));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(new Color(69,69,69));

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(new Color(87,89,91));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(new Color(69,69,69));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(new Color(87,89,91));


		}

	}
	


}
