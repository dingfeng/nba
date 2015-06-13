package ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ui.mainui.FrameSize;

public class OneOldMatch extends JPanel{
	
	JScrollPane oneMatchScrollPane = new JScrollPane();
	
	public OneOldMatch(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8);
		this.add(oneMatchScrollPane);
	}
	
	/** 一场旧比赛的信息图 */
	public void setOneOldMatch(Image picture) {
		oneMatchScrollPane.getViewport().removeAll();
		JLabel jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(picture));
		jLabel.setOpaque(false);

		oneMatchScrollPane.getViewport().add(jLabel);
		jLabel.setPreferredSize(new Dimension(FrameSize.width, 900));
		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		oneMatchScrollPane.setOpaque(false);
		oneMatchScrollPane.getViewport().setOpaque(false);
		oneMatchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		oneMatchScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.repaint();

	}

}
