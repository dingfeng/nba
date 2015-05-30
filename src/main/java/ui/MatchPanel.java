package ui;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;

public class MatchPanel extends JPanel {

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
	}

	private static JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		DateChooseButton dateButton = new DateChooseButton();
		dateButton.setBounds(0,5,150,30);
//		dateButton.setEnd(this);
		headerPanel.add(dateButton);
		
		return headerPanel;
				
	}
}