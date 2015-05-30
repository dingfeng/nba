package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
//import bl.matchbl.MatchController;

public class IndexPanel extends JPanel{

//	MatchController mc=new MatchController();
	IndexPanel ip=this;
	JLabel[] teams;
	
	public IndexPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(Color.white);
		JLabel background=new JLabel(scaleImage(new ImageIcon("image/indexback.png"),921, FrameSize.height * 7 / 8));
		background.setBackground(Color.white);;
		background.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.add(background);
		this.setOpaque(false);

	}
	void setChart(){
		for(int i=0;i<30;i++){
			teams[i]=new JLabel();
		}
	}
	
	private ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (width == iconWidth && height == iconHeight) {
			return icon;
		}
		Image image = icon.getImage();
		image = image.getScaledInstance(iconWidth, iconHeight,
				Image.SCALE_DEFAULT);

		return new ImageIcon(image);
	}
}
