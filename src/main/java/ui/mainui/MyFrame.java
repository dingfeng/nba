package ui.mainui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ui.HelpUtil;
import ui.IndexPanel;
import ui.playerui.PlayerPanel;
import ui.statistics.StatisticsPlayerPanel;
import ui.statistics.StatisticsTeamPanel;
import ui.teamui.ShowAllTeamPanel;
import ui.teamui.TeamPanelTry;
//import bl.matchbl.MatchController;
//import bl.matchbl.MatchController;

public class MyFrame extends JFrame {

	boolean flag = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String titleText = new String();
	public JPanel mainPanel;
	JPanel frame = new JPanel();
	MyFrame thisFrame = this;
	public static JPanel mainpanel = new JPanel();
	public static CardLayout card = new CardLayout();

	public static ShowAllTeamPanel showteampanel = new ShowAllTeamPanel();
	public static IndexPanel indexpanel = new IndexPanel();
	public static TeamPanelTry teampanel = new TeamPanelTry();
	public static PlayerPanel playerpanel = new PlayerPanel();
	// public static HotPanel hotpanel = new HotPanel();
	// public static MatchPanel matchpanel = new MatchPanel();
	public static StatisticsPlayerPanel statisticsPlayerPanel = new StatisticsPlayerPanel();
	public static StatisticsTeamPanel statisticsTeamPanel = new StatisticsTeamPanel();
	

	// MatchController mc = new MatchController();

	public MyFrame() {
		this.setUndecorated(true);

		// 修改图标
		Image image = Toolkit.getDefaultToolkit().getImage(
				"image/basketball.png");
		this.setIconImage(image);

		setFrame();

		this.repaint();
	}

	void setFrame() {

		frame.setLayout(null);
		frame.setBackground(FrameSize.bluecolor);

		mainpanel.setBounds(0, FrameSize.height / 8, FrameSize.width,
				FrameSize.height * 7 / 8);
		mainpanel.setLayout(card);
		mainpanel.add(indexpanel, "index");
		mainpanel.add(playerpanel, "player");
		mainpanel.add(teampanel, "team");
		// mainpanel.add(matchpanel, "match");
		// mainpanel.add(hotpanel, "hot");
		mainpanel.add(statisticsPlayerPanel, "statisticsPlayer");
		mainpanel.add(statisticsTeamPanel,"statisticsTeam");
		mainpanel.setBackground(Color.white);
		
		setExit();
		setMini();
		setTitle();
		setHeadButton();
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		this.setBounds((screenWidth - FrameSize.width) / 2,
				(screenHeight - FrameSize.height) / 2, FrameSize.width,
				FrameSize.height);

		CardLayout card = new CardLayout();
		this.setLayout(new BorderLayout());
		this.add(frame, BorderLayout.CENTER);
		frame.add(mainpanel);
		this.setVisible(true);

	}

	public void setTitle() {
		JLabel icon = new JLabel(new ImageIcon("image/basketball.png"));

		icon.setBounds(5, -15, 24, 59);

		frame.add(icon);

	}
	
	JButton index;
	JButton playerbutton;
	JButton teambutton;
	JButton hotbutton;
	JButton matchbutton;
	JButton helpbutton;
	JButton staticsbutton;
	
	void setHeadButton() {

		index = new MyButton(new ImageIcon("image/index.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		playerbutton = new MyButton(new ImageIcon("image/player.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		teambutton = new MyButton(new ImageIcon("image/图片1.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		hotbutton = new MyButton(new ImageIcon("image/hot.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		matchbutton = new MyButton(new ImageIcon("image/match.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		helpbutton = new MyButton(new ImageIcon("image/help.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		staticsbutton = new MyButton(new ImageIcon("image/statics.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		
		JPopupMenu staticstype = new JPopupMenu();
		JMenuItem playeritem = new JMenuItem("球员数据");
		playeritem.setFont(MyFont.font1);
		playeritem.setBackground(Color.white);
		playeritem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(mainpanel, "statisticsPlayer");
			}

		});
		JMenuItem teamitem = new JMenuItem("球队数据");
		playeritem.setFont(MyFont.font1);
		teamitem.setBackground(Color.white);
		teamitem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(mainpanel, "statisticsTeam");
			}

		});
		staticstype.add(playeritem);
		staticstype.add(teamitem);
		staticstype.setBackground(Color.white);

		staticsbutton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				staticstype.show(e.getComponent(), 0, 50);
			}

		});

		index.setBounds(10, 3 * FrameSize.height / 80, 50, 50);
		hotbutton.setBounds(80, 3 * FrameSize.height / 80, 50, 50);
		playerbutton.setBounds(150, 3 * FrameSize.height / 80, 50, 50);
		teambutton.setBounds(220, 3 * FrameSize.height / 80, 50, 50);
		matchbutton.setBounds(290, 3 * FrameSize.height / 80, 50, 50);
		helpbutton.setBounds(360, 3 * FrameSize.height / 80, 50, 50);
		staticsbutton.setBounds(430, 3 * FrameSize.height / 80, 50, 50);
		
		index.setToolTipText("主页");
		playerbutton.setToolTipText("球员");
		teambutton.setToolTipText("球队");
		hotbutton.setToolTipText("热点");
		matchbutton.setToolTipText("比赛");
		helpbutton.setToolTipText("帮助");
		staticsbutton.setToolTipText("统计");
		

		index.addActionListener(e -> setIndex());
		playerbutton.addActionListener(e -> setPlayer());
		teambutton.addActionListener(e -> setTeam());
		hotbutton.addActionListener(e -> setHot());
		matchbutton.addActionListener(e -> setMatch());
		helpbutton.addActionListener(e -> HelpUtil.startHelp());
		staticsbutton.addActionListener(e->setStatics());
		
		index.addMouseListener(new button());
		playerbutton.addMouseListener(new button());
		teambutton.addMouseListener(new button());
		hotbutton.addMouseListener(new button());
		matchbutton.addMouseListener(new button());
		helpbutton.addMouseListener(new button());
		staticsbutton.addMouseListener(new button());
		

		frame.add(helpbutton);
		frame.add(index);
		frame.add(playerbutton);
		frame.add(teambutton);
		frame.add(hotbutton);
		frame.add(matchbutton);
		frame.add(staticsbutton);
	}

	class button implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			 ((JButton)e.getSource()).setBackground(FrameSize.bluecolor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
//			((JButton) e.getSource()).setBackground(FrameSize.lightbluecolor);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
//			((JButton) e.getSource()).setBackground(FrameSize.bluecolor);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			((JButton) e.getSource()).setBackground(FrameSize.darkbluecolor);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(FrameSize.darkbluecolor);
		}

	}

	void setIndex() {
		
		card.show(mainpanel, "index");
		playerbutton.setBackground(FrameSize.bluecolor);
		teambutton.setBackground(FrameSize.bluecolor);
		hotbutton.setBackground(FrameSize.bluecolor);
		matchbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		staticsbutton.setBackground(FrameSize.bluecolor);

	}

	void setPlayer() {
		card.show(mainpanel, "player");
		index.setBackground(FrameSize.bluecolor);
		teambutton.setBackground(FrameSize.bluecolor);
		hotbutton.setBackground(FrameSize.bluecolor);
		matchbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		staticsbutton.setBackground(FrameSize.bluecolor);
	}

	public void setTeam() {
		card.show(mainpanel, "team");
		index.setBackground(FrameSize.bluecolor);
		playerbutton.setBackground(FrameSize.bluecolor);
		hotbutton.setBackground(FrameSize.bluecolor);
		matchbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		staticsbutton.setBackground(FrameSize.bluecolor);
	}

	void setHot() {
		card.show(mainpanel, "hot");
		index.setBackground(FrameSize.bluecolor);
		playerbutton.setBackground(FrameSize.bluecolor);
		teambutton.setBackground(FrameSize.bluecolor);
		matchbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		staticsbutton.setBackground(FrameSize.bluecolor);
	}

	void setMatch() {
		card.show(mainpanel, "match");
		index.setBackground(FrameSize.bluecolor);
		playerbutton.setBackground(FrameSize.bluecolor);
		teambutton.setBackground(FrameSize.bluecolor);
		hotbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		staticsbutton.setBackground(FrameSize.bluecolor);
	}

	void setStatics(){
		index.setBackground(FrameSize.bluecolor);
		playerbutton.setBackground(FrameSize.bluecolor);
		teambutton.setBackground(FrameSize.bluecolor);
		hotbutton.setBackground(FrameSize.bluecolor);
		helpbutton.setBackground(FrameSize.bluecolor);
		matchbutton.setBackground(FrameSize.bluecolor);
	}
	
	void setExit() {
		JButton el = new MyButton(new ImageIcon("image/close.png"),
				FrameSize.bluecolor, Color.red);
		// JLabel el = new JLabel();
		// ImageIcon image = new ImageIcon("image/close.png");
		// el.setIcon(image);
		el.setBounds(FrameSize.width - 30, 0, 30, 30);
		el.addMouseListener(new ExitButtonAction());
		frame.add(el);
		// this.add(el);
	}

	void setMini() {
		JButton el = new MyButton(new ImageIcon("image/mini.png"),
				FrameSize.bluecolor, new Color(20, 34, 160));
		// JLabel el = new JLabel();
		// ImageIcon image = new ImageIcon("image/mini.png");
		// el.setIcon(image);
		el.setBounds(FrameSize.width - 60, 0, 30, 30);
		el.addMouseListener(new MiniButtonAction());
		frame.add(el);
	}

	void addElements() {
		/*
		 * JLabel psisL = new JLabel(); psisL.setBounds(150, 80, 400, 300);
		 * psisL.setIcon(new ImageIcon("psis.png")); mainPanel.add(psisL);
		 * repaint(); try { Thread.sleep(2000); } catch (InterruptedException e)
		 * { e.printStackTrace(); } psisL.setLocation(350, 80); repaint();
		 */
	}

	class ExitButtonAction implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {
			thisFrame.dispose();
			System.exit(DISPOSE_ON_CLOSE);
		}

		public void mouseEntered(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(Color.red);
		}

		public void mouseExited(MouseEvent arg0) {
			((JButton)arg0.getSource()).setBackground(FrameSize.bluecolor);
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
	}

	class MiniButtonAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			thisFrame.setExtendedState(JFrame.ICONIFIED);
			;
		}

		public void mouseEntered(MouseEvent e) {
			((JButton)e.getSource()).setBackground(FrameSize.darkbluecolor);
		}

		public void mouseExited(MouseEvent e) {
			((JButton)e.getSource()).setBackground(FrameSize.bluecolor);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

}
