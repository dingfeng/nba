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

public class IndexPanel extends JPanel {

	// MatchController mc=new MatchController();
	IndexPanel ip = this;
	JLabel[] teams=new JLabel[30];
	JLabel background;
	public IndexPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(Color.white);
		background = new JLabel(scaleImage(new ImageIcon(
				"image/indexback.png"), 921, FrameSize.height * 7 / 8));
		background.setBackground(Color.white);
		;
		background.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.add(background);
		setChart();
		this.setOpaque(false);

	}

	void setChart() {
		for (int i = 0; i < 30; i++) {
			teams[i] = new JLabel();
			teams[i].setOpaque(true);
			teams[i].setBackground(Color.red);
			background.add(teams[i]);
		}
		teams[0].setBounds(FrameSize.width *17/ 120, FrameSize.height * 7 / 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[1].setBounds(FrameSize.width *17/ 120, FrameSize.height * 18 / 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[2].setBounds(FrameSize.width *17/ 120, FrameSize.height  *40/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[3].setBounds(FrameSize.width *17/ 120, FrameSize.height  *51/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[4].setBounds(FrameSize.width *17/ 120, FrameSize.height  *75/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[5].setBounds(FrameSize.width *17/ 120, FrameSize.height  *86/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[6].setBounds(FrameSize.width *17/ 120, FrameSize.height  *108/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[7].setBounds(FrameSize.width *17/ 120, FrameSize.height  *119/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		
		teams[8].setBounds(FrameSize.width *13/ 48, FrameSize.height  *24/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[9].setBounds(FrameSize.width *13/ 48, FrameSize.height  *35/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[10].setBounds(FrameSize.width *13/ 48, FrameSize.height  *92/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[11].setBounds(FrameSize.width *13/ 48, FrameSize.height  *103/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		
		teams[12].setBounds(FrameSize.width *78/ 240, FrameSize.height  *57/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[13].setBounds(FrameSize.width *78/ 240, FrameSize.height  *68/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		
		teams[14].setBounds(FrameSize.width *107/ 240, FrameSize.height  *41/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[15].setBounds(FrameSize.width *107/ 240, FrameSize.height  *52/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		
		teams[16].setBounds(FrameSize.width *137/ 240, FrameSize.height  *57/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[17].setBounds(FrameSize.width *137/ 240, FrameSize.height  *68/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);

		teams[18].setBounds(FrameSize.width *15/ 24, FrameSize.height  *24/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[19].setBounds(FrameSize.width *15/ 24, FrameSize.height  *35/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[20].setBounds(FrameSize.width *15/ 24, FrameSize.height  *92/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[21].setBounds(FrameSize.width *15/ 24, FrameSize.height  *103/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		
		teams[22].setBounds(FrameSize.width *181/240, FrameSize.height * 7 / 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[23].setBounds(FrameSize.width *181/240, FrameSize.height * 18 / 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[24].setBounds(FrameSize.width *181/240, FrameSize.height  *40/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[25].setBounds(FrameSize.width *181/240, FrameSize.height  *51/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[26].setBounds(FrameSize.width *181/240, FrameSize.height  *75/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[27].setBounds(FrameSize.width *181/240, FrameSize.height  *86/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[28].setBounds(FrameSize.width *181/240, FrameSize.height  *108/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
		teams[29].setBounds(FrameSize.width *181/240, FrameSize.height  *119/ 160,
				FrameSize.width * 13 / 120, FrameSize.height * 11 / 160);
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
