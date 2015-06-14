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
		JLabel score=new JLabel("2 - 2");
		score.setForeground(Color.gray);
		score.setBounds(585, 330, 80, 30);
		score.setFont(new Font("微软雅黑",Font.BOLD,14));
		this.add(background);
		background.add(score);
		setChart();
		this.setOpaque(false);

	}

	void setChart() {
		for (int i = 0; i < 30; i++) {
			teams[i] = new JLabel();
//			teams[i].setOpaque(true);
//			teams[i].setBackground(Color.red);
			teams[i].addMouseListener(new listener());
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
	
	void findteam(int index){
		 switch(index){
		 case 0:MyFrame.teampanel.showOne("GSW");
		 break;
		 case 1:MyFrame.teampanel.showOne("NOP");
		 break;
		 case 2:MyFrame.teampanel.showOne("POR");
		 break;
		 case 3:MyFrame.teampanel.showOne("MEM");
		 break;
		 case 4:MyFrame.teampanel.showOne("HOU");
		 break;
		 case 5:MyFrame.teampanel.showOne("DAL");
		 break;
		 case 6:MyFrame.teampanel.showOne("LAC");
		 break;
		 case 7:MyFrame.teampanel.showOne("SAS");
		 break;
		 case 8:MyFrame.teampanel.showOne("GSW");
		 break;
		 case 9:MyFrame.teampanel.showOne("MEM");
		 break;
		 case 10:MyFrame.teampanel.showOne("HOU");
		 break;
		 case 11:MyFrame.teampanel.showOne("LAC");
		 break;
		 case 12:MyFrame.teampanel.showOne("GSW");
		 break;
		 case 13:MyFrame.teampanel.showOne("HOU");
		 break;
		 case 14:MyFrame.teampanel.showOne("GSW");
		 break;
		 case 15:MyFrame.teampanel.showOne("CLE");
		 break;
		 case 16:MyFrame.teampanel.showOne("ATL");
		 break;
		 case 17:MyFrame.teampanel.showOne("CLE");
		 break;
		 case 18:MyFrame.teampanel.showOne("ATL");
		 break;
		 case 19:MyFrame.teampanel.showOne("WAS");
		 break;
		 case 20:MyFrame.teampanel.showOne("CLE");
		 break;
		 case 21:MyFrame.teampanel.showOne("CHI");
		 break;
		 case 22:MyFrame.teampanel.showOne("ATL");
		 break;
		 case 23:MyFrame.teampanel.showOne("BKN");
		 break;
		 case 24:MyFrame.teampanel.showOne("TOR");
		 break;
		 case 25:MyFrame.teampanel.showOne("WAS");
		 break;
		 case 26:MyFrame.teampanel.showOne("CLE");
		 break;
		 case 27:MyFrame.teampanel.showOne("BOS");
		 break;
		 case 28:MyFrame.teampanel.showOne("CHI");
		 break;
		 case 29:MyFrame.teampanel.showOne("MIL");
		 break;
		 }
		 MyFrame.card.show(MyFrame.mainpanel, "team");
	}

	class listener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {   
			// TODO Auto-generated method stub
			JLabel tmp = (JLabel)e.getSource();
	        int index = 0;
	        for(int i=0;i<teams.length;i++){
	            if(teams[i]==tmp){
	                index = i;
	            }
	        }
	        findteam(index);
			MyFrame.setTeam();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
