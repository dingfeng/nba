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
	
//	JScrollPane oneMatchScrollPane = new JScrollPane();
	JPanel oldPanel = new JPanel();
	public OneOldMatch(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
//		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
//				FrameSize.height * 7 / 8);
		oldPanel.setBounds(0, 0, FrameSize.width,
				FrameSize.height * 7 / 8);
//		this.add(oneMatchScrollPane);
		this.add(oldPanel);
	}
	
	/** 一场旧比赛的信息图 */
	public void setOneOldMatch(Image picture) {
		System.out.println("oldmatch");
//		oneMatchScrollPane.getViewport().removeAll();
		oldPanel.removeAll();
		JLabel jLabel = new JLabel();
		jLabel.setIcon(new ImageIcon(picture));
		jLabel.setOpaque(false);
		jLabel.setBounds((FrameSize.width-800)/2,0,800,800);
//		oneMatchScrollPane.getViewport().add(jLabel);
//		jLabel.setPreferredSize(new Dimension(800, 800));
//		oneMatchScrollPane.setBounds(0, 0, FrameSize.width,
//				FrameSize.height * 7 / 8 - 40);
//		oneMatchScrollPane.setOpaque(false);
//		oneMatchScrollPane.getViewport().setOpaque(false);
//		oneMatchScrollPane
//				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		oneMatchScrollPane
//				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		oldPanel.add(jLabel);
		this.repaint();

	}

}
