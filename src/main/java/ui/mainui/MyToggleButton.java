package ui.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class MyToggleButton extends JToggleButton{

	Color backcolor = null;
	Color focuscolor = null;

	public MyToggleButton(ImageIcon image, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
		this.addMouseListener(new button());
//		this.setContentAreaFilled(false);
		this.setIcon(image);
		this.setBackground(back);
		this.setFocusPainted(false);
	}
	
	public MyToggleButton(String text, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
//		this.setContentAreaFilled(false);
		this.addMouseListener(new button());
		this.setText(text);
		this.setBackground(back);
		this.setForeground(focus);
		this.setFocusPainted(false);
	}

	class button implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			 ((JToggleButton)e.getSource()).setBackground(focuscolor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JToggleButton) e.getSource()).setBackground(focuscolor);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JToggleButton) e.getSource()).setBackground(backcolor);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			((JToggleButton) e.getSource()).setBackground(focuscolor);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
//			((JToggleButton) e.getSource()).setBackground(FrameSize.darkbluecolor);
		}

	}
}
