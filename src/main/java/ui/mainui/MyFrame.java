package ui.mainui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import po.TeamNormalPO;
import po.TeamPO;
import ui.HelpUtil;
import ui.HotPanel;
import ui.IndexPanel;
import ui.LivePanel;
import ui.MatchPanel;
import ui.OneNowMatch;
import ui.OneOldMatch;
import ui.playerui.ShowAllPlayerPanel;
import ui.playerui.ShowOnePlayerPanel;
import ui.statistics.StatisticsPanel;
import ui.statistics.StatisticsPlayerPanel;
import ui.statistics.StatisticsTeamPanel;
import ui.teamui.TeamPanel;
import bl.teambl.TeamController;
import dataservice.playerdataservice.SeasonType;
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

	public static IndexPanel indexpanel = new IndexPanel();
	public static TeamPanel teampanel ;
	public static ShowAllPlayerPanel allPlayerPanel;
	public static ShowOnePlayerPanel onePlayerPanel;
	public static HotPanel hotpanel;
	public static MatchPanel matchpanel;
	public static OneNowMatch onematchpanel;
	public static OneOldMatch oldmatchpanel;
	public static StatisticsPlayerPanel statisticsPlayerPanel;
	public static StatisticsTeamPanel statisticsTeamPanel;
	public static LivePanel livepanel;
	public static StatisticsPanel statisticspanel;
	TeamController tc = new TeamController();

	// MatchController mc = new MatchController();

	public MyFrame() {
		this.setUndecorated(true);

		// 修改图标
		Image image = Toolkit.getDefaultToolkit().getImage(
				"image/basketball.png");
		this.setIconImage(image);

		setFrame();
		System.out.print(getLocalIP());
		this.repaint();
	}
	public static String getLocalIP(){     
        InetAddress addr = null;     
                    try {  
                        addr = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }     
                  
                byte[] ipAddr = addr.getAddress();     
                String ipAddrStr = "";     
                for (int i = 0; i < ipAddr.length; i++) {     
                    if (i > 0) {     
                        ipAddrStr += ".";     
                    }     
                    ipAddrStr += ipAddr[i] & 0xFF;     
                }     
                //System.out.println(ipAddrStr);     
                        return ipAddrStr;     
        }    
	void setFrame() {

		frame.setLayout(null);
		frame.setBackground(FrameSize.bluecolor);

		mainpanel.setBounds(0, FrameSize.height / 8, FrameSize.width,
				FrameSize.height * 7 / 8);
		mainpanel.setLayout(card);
		mainpanel.add(indexpanel, "index");
		mainpanel.setBackground(Color.white);
         new Thread()
         {
        	 public void run()
        	 {
        		 teampanel = new TeamPanel();
    			 mainpanel.add(teampanel, "team");
        	 }
         }.start();
         
         new Thread()
         {
        	 public void run()
        	 {
        		 allPlayerPanel = new ShowAllPlayerPanel();
        		 mainpanel.add(allPlayerPanel, "allplayer");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 onePlayerPanel = new ShowOnePlayerPanel();
        		 mainpanel.add(onePlayerPanel, "oneplayer");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 hotpanel = new HotPanel();
        		 mainpanel.add(hotpanel, "hot");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 matchpanel = new MatchPanel();
        		 mainpanel.add(matchpanel, "match");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 statisticsPlayerPanel = new StatisticsPlayerPanel();
        		 mainpanel.add(statisticsPlayerPanel, "statisticsPlayer");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 statisticsTeamPanel = new StatisticsTeamPanel();
        		 mainpanel.add(statisticsTeamPanel, "statisticsTeam");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
        		 livepanel = new LivePanel();
        		 mainpanel.add(livepanel, "live");
        	 }
         }.start();
         new Thread()
         {
        	 public void run()
        	 {
    			 statisticspanel = new StatisticsPanel();
    			 mainpanel.add(statisticspanel, "statistics");
        	 }
         }.start();
         new Thread(){
        	 public void run(){
        		 onematchpanel = new OneNowMatch();
        		 mainpanel.add(onematchpanel,"onematch");
        	 }
         }.start();
         new Thread(){
        	 public void run(){
        		 oldmatchpanel = new OneOldMatch();
        		 mainpanel.add(oldmatchpanel,"oldmatch");
        	 }
         }.start();
         
		setExit();
		setMini();
		setTitle();
		setHeadButton();
		setTeambutton();
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

	public static MyToggleButton index;
	public static MyToggleButton playerbutton;
	public static MyToggleButton teambutton;
	public static MyToggleButton hotbutton;
	public static MyToggleButton matchbutton;
	public static MyToggleButton helpbutton;
	public static MyToggleButton staticsbutton;
	public static MyToggleButton livebutton;
	JPopupMenu teams = new JPopupMenu();

	void setHeadButton() {

		index = new MyToggleButton(new ImageIcon("image/index.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		playerbutton = new MyToggleButton(new ImageIcon("image/player.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		teambutton = new MyToggleButton(new ImageIcon("image/图片1.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		hotbutton = new MyToggleButton(new ImageIcon("image/hot.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		matchbutton = new MyToggleButton(new ImageIcon("image/match.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		helpbutton = new MyToggleButton(new ImageIcon("image/help.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		staticsbutton = new MyToggleButton(new ImageIcon("image/statics.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);
		livebutton = new MyToggleButton(new ImageIcon("image/live.png"),
				FrameSize.bluecolor, FrameSize.darkbluecolor);

		teambutton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				teams.show(e.getComponent(), 0, 50);
			}

		});
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
		teamitem.setFont(MyFont.font1);
		teamitem.setBackground(Color.white);
		teamitem.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(mainpanel, "statisticsTeam");
			}

		});
		JMenuItem item = new JMenuItem("统计数据");
		item.setFont(MyFont.font1);
		item.setBackground(Color.white);
		item.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				card.show(mainpanel, "statistics");
			}

		});

		staticstype.add(playeritem);
		staticstype.add(teamitem);
		staticstype.add(item);
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
		livebutton.setBounds(500, 3 * FrameSize.height / 80, 50, 50);

		index.setToolTipText("主页");
		playerbutton.setToolTipText("球员");
		teambutton.setToolTipText("球队");
		hotbutton.setToolTipText("热点");
		matchbutton.setToolTipText("比赛");
		helpbutton.setToolTipText("帮助");
		staticsbutton.setToolTipText("统计");
		livebutton.setToolTipText("直播");

		index.addActionListener(e -> setIndex());
		playerbutton.addActionListener(e -> setPlayer());
		teambutton.addActionListener(e -> setTeam());
		hotbutton.addActionListener(e -> setHot());
		matchbutton.addActionListener(e -> setMatch());
		helpbutton.addActionListener(e -> HelpUtil.startHelp());
		staticsbutton.addActionListener(e -> setStatics());
		livebutton.addActionListener(e -> setLive());
		index.setSelected(true);
		frame.add(livebutton);
		frame.add(helpbutton);
		frame.add(index);
		frame.add(playerbutton);
		frame.add(teambutton);
		frame.add(hotbutton);
		frame.add(matchbutton);
		frame.add(staticsbutton);
	}

	void setTeambutton() {

		teams.setLayout(new GridLayout(15, 2));
		JMenuItem[] normal = new JMenuItem[30];
		for (int i = 0; i < 30; i++) {
			normal[i] = new JMenuItem();
			normal[i].setBackground(Color.white);
			normal[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					teampanel.showOne(((JMenuItem) e.getSource()).getText());
					card.show(mainpanel, "team");
				}
			});
			teams.add(normal[i]);
		}
		TeamNormalPO[] teammatch = tc.getAllTeamTotal(2013, SeasonType.REGULAR);
		int West = 0;
		int East = 1;
		for (int i = 0; i < teammatch.length; i++) {
			TeamPO team = tc.getTeamData(teammatch[i].getName());
			String playerArea = team.getMatchArea();

			switch (playerArea) {
			case "E":
				normal[East].setText(team.getName());
				normal[East].setIcon(FrameSize.scaleImage(
						new ImageIcon(team.getImage()), 40, 40));
				East = East + 2;
				break;

			case "W":
				normal[West].setText(team.getName());
				normal[West].setIcon(FrameSize.scaleImage(
						new ImageIcon(team.getImage()), 40, 40));
				West = West + 2;
				break;
			}

		}
	}

	public static void setIndex() {

		card.show(mainpanel, "index");
		index.setSelected(true);
		playerbutton.setSelected(false);
		teambutton.setSelected(false);
		hotbutton.setSelected(false);
		matchbutton.setSelected(false);
		helpbutton.setSelected(false);
		staticsbutton.setSelected(false);

	}

	public static void setPlayer() {
		card.show(mainpanel, "allplayer");
		index.setSelected(false);
		playerbutton.setSelected(true);
		teambutton.setSelected(false);
		hotbutton.setSelected(false);
		matchbutton.setSelected(false);
		helpbutton.setSelected(false);
		staticsbutton.setSelected(false);
	}

	public static void setTeam() {
		// card.show(mainpanel, "team");
		index.setSelected(false);
		playerbutton.setSelected(false);
		hotbutton.setSelected(false);
		teambutton.setSelected(true);
		matchbutton.setSelected(false);
		helpbutton.setSelected(false);
		staticsbutton.setSelected(false);
	}

	public static void setHot() {
		card.show(mainpanel, "hot");
		index.setSelected(false);
		playerbutton.setSelected(false);
		teambutton.setSelected(false);
		hotbutton.setSelected(true);
		matchbutton.setSelected(false);
		helpbutton.setSelected(false);
		staticsbutton.setSelected(false);
		livebutton.setSelected(false);
	}

	public static void setMatch() {
		card.show(mainpanel, "match");
		index.setSelected(false);
		playerbutton.setSelected(false);
		teambutton.setSelected(false);
		matchbutton.setSelected(true);
		hotbutton.setSelected(false);
		helpbutton.setSelected(false);
		staticsbutton.setSelected(false);
		livebutton.setSelected(false);
	}

	public static void setStatics() {
		index.setSelected(false);
		playerbutton.setSelected(false);
		teambutton.setSelected(false);
		hotbutton.setSelected(false);
		helpbutton.setSelected(false);
		matchbutton.setSelected(false);
		staticsbutton.setSelected(true);
		livebutton.setSelected(false);

	}

	public static void setLive() {
		card.show(mainpanel, "live");
		index.setSelected(false);
		playerbutton.setSelected(false);
		teambutton.setSelected(false);
		hotbutton.setSelected(false);
		helpbutton.setSelected(false);
		matchbutton.setSelected(false);
		staticsbutton.setSelected(false);
		livebutton.setSelected(true);
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
			((JButton) arg0.getSource()).setBackground(Color.red);
		}

		public void mouseExited(MouseEvent arg0) {
			((JButton) arg0.getSource()).setBackground(FrameSize.bluecolor);
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
			((JButton) e.getSource()).setBackground(FrameSize.darkbluecolor);
		}

		public void mouseExited(MouseEvent e) {
			((JButton) e.getSource()).setBackground(FrameSize.bluecolor);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}
	}

}
