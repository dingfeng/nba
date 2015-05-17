package ui.teamui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import po.TeamPO;
import ui.mainui.FrameSize;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import vo.TeamVO;
import bl.teambl.TeamController;

public class ShowAllTeamPanel extends JPanel {

	TeamController tc = new TeamController(2014);
	JButton[] eastTeam = new JButton[15];
	JButton[] westTeam = new JButton[15];
	JPanel eastpanel = new JPanel();
	JPanel westpanel = new JPanel();

	public ShowAllTeamPanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		setText();
		setEastTeam();
		setWestTeam();
		this.repaint();
	}

	/** 东部联盟 西部联盟 */
	void setText() {
		JLabel eastpic = new JLabel();
		eastpic.setBounds(2, 0, FrameSize.width, 50);
		eastpic.setIcon(new ImageIcon("image/east.png"));
		JLabel easttext = new JLabel();
		easttext.setBackground(FrameSize.darkbluecolor);
		easttext.setBounds(0, 5, FrameSize.width, 50);
		easttext.setForeground(Color.white);
		easttext.setBackground(new Color(30, 81, 140));
		easttext.setOpaque(true);

		JLabel eastArea = new JLabel();
		eastArea.setText("       东南分区                                                                                                                            中央分区                                                                                                                             大西洋分区");
		eastArea.setBounds(0, 55, FrameSize.width, 30);
		eastArea.setBackground(Color.LIGHT_GRAY);
		eastArea.setOpaque(true);

		JLabel westpic = new JLabel();
		westpic.setBounds(2, 0, FrameSize.width, 50);
		westpic.setIcon(new ImageIcon("image/west.png"));
		JLabel westtext = new JLabel();
		westtext.setBounds(0, FrameSize.height * 7 / 16, FrameSize.width, 50);
		westtext.setForeground(Color.white);
		westtext.setBackground(new Color(30, 81, 140));
		westtext.setOpaque(true);

		JLabel westArea = new JLabel();
		westArea.setText("       太平洋分区                                                                                                                            西北分区                                                                                                                            西南分区");
		westArea.setBounds(0, FrameSize.height * 7 / 16 + 50, FrameSize.width,
				30);
		westArea.setBackground(Color.LIGHT_GRAY);
		westArea.setOpaque(true);

		this.add(easttext);
		easttext.add(eastpic);
		this.add(westtext);
		westtext.add(westpic);
		this.add(eastArea);
		this.add(westArea);
	}

	/** 东部球队 */
	void setEastTeam() {

		eastpanel.setLayout(new GridLayout(5, 3, 1, 1));
		eastpanel.setBounds(0, 85, FrameSize.width, FrameSize.height / 2);
		eastpanel.setOpaque(false);
		for (int i = 0; i < 15; i++) {
			eastTeam[i] = new JButton();
			eastTeam[i].setFocusPainted(false);
			eastpanel.add(eastTeam[i]);
		}
		this.add(eastpanel);

	}

	/** 西部球队 */
	void setWestTeam() {

		westpanel.setLayout(new GridLayout(5, 3, 1, 1));
		westpanel.setBounds(0, FrameSize.height * 7 / 16 + 80, FrameSize.width,
				FrameSize.height / 2);
		westpanel.setOpaque(false);
		for (int i = 0; i < 15; i++) {
			westTeam[i] = new JButton();
			westTeam[i].setFocusPainted(false);
			westpanel.add(eastTeam[i]);
		}
		this.add(westpanel);
	}

	/** 加入信息 */
	void setTeam() {
		TeamMatchVO[] teammatch = tc.getSortedAveTeams(TeamSortBy.name,
				SortType.ASEND);
		int Southeast=0;
		int Central=5;
		int Atlantic=10;
		int Pacific=0;
		int Northwest=5;
		int Southwest=10;
		for (int i = 0; i < teammatch.length; i++) {
			TeamPO team = tc.getTeamData(teammatch[i].getName());
			String playerArea = team.getPlayerArea();
			
			switch (playerArea) {
			case "Southeast":
				eastTeam[Southeast].setText(team.getName());
				eastTeam[Southeast].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Southeast++;
				break;

			case "Central":
				eastTeam[Central].setText(team.getName());
				eastTeam[Central].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Central++;
				break;
			case "Atlantic":
				eastTeam[Atlantic].setText(team.getName());
				eastTeam[Atlantic].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Atlantic++;
				break;
			case "Pacific":
				westTeam[Pacific].setText(team.getName());
				westTeam[Pacific].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Pacific++;
				break;
			case "Southwest":
				westTeam[Southwest].setText(team.getName());
				westTeam[Southwest].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Southwest++;
				break;

			case "Northwest":
				westTeam[Northwest].setText(team.getName());
				westTeam[Northwest].setIcon(scaleImage(new ImageIcon(team.getImage()),30,30));
				Northwest++;
				break;
			}

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
