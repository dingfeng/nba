package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import po.MatchesPO;
import bl.matchbl.MatchController;
import bl.teambl.TeamController;
import blservice.matchblservice.Matchblservice;
import blservice.teamblservice.Teamblservice;
import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;

public class MatchPanel extends JPanel {
	Matchblservice matchController = new MatchController();
	Teamblservice teamController = new TeamController();
	JScrollPane todatyMatchScrollPane = new JScrollPane();

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		setTodayMatches(null);
//		test();
	}

	/**查找栏*/
	private  JPanel HeaderPanel() {
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
	
	/**一天的所有比赛*/
	public void setTodayMatches(Date date){
		todatyMatchScrollPane.getViewport().removeAll();
		JPanel matchPanel = new JPanel();
		matchPanel.setLayout(null);
		MatchesPO [] matches = matchController.getTimeMatches(date);
		JLabel[] matchLabel = new JLabel[matches.length];
		for(int i=0;i<matches.length;i++){ 
			matchLabel[i]=new JLabel();
			matchLabel[i].setBounds(0,i*200,FrameSize.width,200);
			JLabel team1 = new JLabel(scaleImage(
					new ImageIcon(teamController.getTeamData(matches[i].getTeam1().getName())
							.getImage()),150,150));
//			JLabel team1 = new JLabel();
//			team1.setOpaque(true);
//			team1.setBackground(Color.red);
			team1.setBounds(200,25,150,150);
			JLabel team2 = new JLabel(scaleImage(
					new ImageIcon(teamController.getTeamData(matches[i].getTeam2().getName())
							.getImage()),150,150));
//			JLabel team2 = new JLabel();
//			team2.setOpaque(true);
//			team2.setBackground(Color.blue);
			team2.setBounds(FrameSize.width-350,25,150,150);
//			team1.setText("1-"+String.valueOf(i));
//			team2.setText("2-"+String.valueOf(i));
			matchLabel[i].add(team1);
			matchLabel[i].add(team2);
			
			JLabel [][] scores = new JLabel[3][4];
			for(int j=0;j<4;j++){
				scores[0][j]=new JLabel(String.valueOf(j+1));
				scores[1][j]=new JLabel(String.valueOf(matches[i].getTeam1().getScores()[j]));
				scores[2][j]=new JLabel(String.valueOf(matches[i].getTeam2().getScores()[j]));
				if(matches[i].getTeam1().getScores()[j]>matches[i].getTeam2().getScores()[j]){
					scores[1][j].setForeground(Color.red);
				}else{
					scores[2][j].setForeground(Color.red);
				}
				for(int k=0;k<3;k++){
					scores[k][j].setBounds(200+(FrameSize.width-300-120)*(j+1)/5+j*30,40+k*50,30,30);
					matchLabel[i].add(scores[k][j]);
				}
				
			}
			matchPanel.add(matchLabel[i]);
		}
		matchPanel.setOpaque(false);
		todatyMatchScrollPane.getViewport().add(matchPanel);
		matchPanel.setPreferredSize(new Dimension(FrameSize.width,100*130));
		todatyMatchScrollPane.setBounds(0,40,FrameSize.width,FrameSize.height*7/8-40);
		todatyMatchScrollPane.setOpaque(false);
		todatyMatchScrollPane.getViewport().setOpaque(false);
		todatyMatchScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		todatyMatchScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(todatyMatchScrollPane);
		this.repaint();
		}
	
	private void test(){
		JLabel[] jLabel = new JLabel[100];
		JScrollPane jScrollPane = new JScrollPane();
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.red);
		jScrollPane.setBounds(0,40,FrameSize.width,FrameSize.height*7/8-40);
		for(int i=0;i<100;i++){
			jLabel[i] = new JLabel(String.valueOf(i));
			jLabel[i].setBounds(0,i*30,100,30);
			jPanel.add(jLabel[i]);
		}
		jScrollPane.getViewport().add(jPanel);
		jPanel.setPreferredSize(new Dimension(FrameSize.width, 3000));
		jScrollPane.getViewport().setOpaque(false);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(jScrollPane);
		this.repaint();
	}

	/**更改图片大小*/
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