package ui.teamui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import po.TeamPO;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import ui.mainui.UneditableTextField;
import bl.teambl.TeamController;

public class TeamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel table;
	JScrollPane jScrollPane;
	JPanel find = new JPanel();
	JPanel teammessage = new JPanel();
	MyTable mytable;
	JComboBox<String> box;
	MyButton dataType;
	JLabel image = new JLabel();// 图片
	JTextField nameresult = new UneditableTextField();// 队伍名称
	public JTextField nameAbridgeresult = new UneditableTextField();// 名称缩写
	JTextField addressresult = new UneditableTextField();// 所在地
	JTextField matchArearesult = new UneditableTextField();// 赛区
	JTextField playerArearesult = new UneditableTextField();// 分区
	JTextField manageresult = new UneditableTextField();// 主场
	JTextField foundYearresult = new UneditableTextField();// 建立时间
	JButton match;
	JButton teamplayers;

	boolean matchpanel = false;
	Vector<String> columnsName = new Vector<String>();
	JTextField[] teamlabel = new UneditableTextField[54];
	public static CardLayout card = new CardLayout();
	public static JPanel teammain = new JPanel();
	public static PastMatchPanel pastmatchpanel = new PastMatchPanel();
	public static RecentMatchPanel recentmatchpanel = new RecentMatchPanel();
	public static TeamContrastPanel teamcontrastpanel = new TeamContrastPanel();
	public static TeamDataPanel teamdatapanel = new TeamDataPanel();
	public static TeamPlayerPanel teamplayerpanel=new TeamPlayerPanel();
	
	MyToggleButton databutton;
	MyToggleButton recentbutton;
	MyToggleButton pastbutton;
	MyToggleButton contrastbutton;
	MyToggleButton playerbutton;
	MyToggleButton alldata;
	MyToggleButton avedata;
	boolean high=false;
	TeamController tc = new TeamController();

	// MatchController mc = new MatchController();

	public TeamPanel() {
		// String[] teamNames = tc.getTeamNames();
		// searchBox = new MyComboBox(teamNames);
		// AutoCompleteDecorator.decorate(searchBox);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		// new Thread() {
		// public void run() {
		// setTable(tc
		// .getAllTeamTotal(2012, SeasonType.REGULAR));
		// }
		// }.start();
		teammain.setBounds(0, FrameSize.height / 4 + 50, FrameSize.width,
				FrameSize.height * 3 / 4 - 50);
		teammain.setLayout(card);
		teammain.add("data", teamdatapanel);
		teammain.add("past", pastmatchpanel);
		teammain.add("recent", recentmatchpanel);
		teammain.add("contrast", teamcontrastpanel);
		teammain.add("player",teamplayerpanel);
		setHeader();
		setFind();
		
		this.add(teammain);
		this.add(find);
		
		this.repaint();
	}

	/** 设置标题 */
	void setHeader() {	
		
		databutton = new MyToggleButton("数据", Color.black, Color.gray);
		databutton.setBounds(0, FrameSize.height / 4, FrameSize.width / 5, 50);

		recentbutton = new MyToggleButton("近期比赛", Color.black, Color.gray);
		recentbutton.setBounds(FrameSize.width / 5, FrameSize.height / 4,
				FrameSize.width / 5, 50);

		pastbutton = new MyToggleButton("过往查询", Color.black, Color.gray);
		pastbutton.setBounds(FrameSize.width *2/ 5, FrameSize.height / 4,
				FrameSize.width / 5, 50);

		contrastbutton = new MyToggleButton("对比", Color.black, Color.gray);
		contrastbutton.setBounds(FrameSize.width * 3 / 5, FrameSize.height / 4,
				FrameSize.width / 5, 50);
		
		playerbutton = new MyToggleButton("队员", Color.black, Color.gray);
		playerbutton.setBounds(FrameSize.width * 4 / 5, FrameSize.height / 4,
				FrameSize.width / 5, 50);

		playerbutton.setForeground(Color.white);
		databutton.setForeground(Color.white);
		recentbutton.setForeground(Color.white);
		pastbutton.setForeground(Color.white);
		contrastbutton.setForeground(Color.white);

		playerbutton.addActionListener(e -> setPlayer());
		recentbutton.addActionListener(e -> setRecent());
		pastbutton.addActionListener(e -> setPast());
		contrastbutton.addActionListener(e -> setContrast());

		JPopupMenu type = new JPopupMenu();
		JMenuItem normal = new JMenuItem("基本数据");
		normal.setBackground(Color.white);
		normal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				high=false;
				setTeamdata();
			}

		});
		JMenuItem higher = new JMenuItem("高阶数据");
		higher.setBackground(Color.white);
		higher.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				high=true;
				setTeamdata();
			}

		});

		type.add(normal);
		type.add(higher);
		type.setBackground(Color.white);

		databutton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				type.show(e.getComponent(), 0, 50);
			}

		});

		alldata = new MyToggleButton("总数", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		avedata = new MyToggleButton("场均", FrameSize.bluecolor,
				FrameSize.darkbluecolor);
		alldata.setBounds(FrameSize.width - 100, 0, 50, 30);
		avedata.setBounds(FrameSize.width - 50, 0, 50, 30);
		alldata.addActionListener(e -> showall());
		avedata.addActionListener(e -> showave());
		alldata.setForeground(Color.white);
		avedata.setForeground(Color.white);
		alldata.setSelected(true);
		teamdatapanel.add(alldata);
		teamdatapanel.add(avedata);
		this.add(playerbutton);
		this.add(databutton);
		this.add(recentbutton);
		this.add(pastbutton);
		this.add(contrastbutton);

	}

	void showall() {
		alldata.setSelected(true);
		avedata.setSelected(false);
		setTeamdata();
	}

	void showave() {
		avedata.setSelected(true);
		alldata.setSelected(false);
		setTeamdata();
	}
	
	void setTeamdata() {
		card.show(teammain, "data");
		teamdatapanel.setTable(high,showAllData());
		databutton.setSelected(true);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		contrastbutton.setSelected(false);
		playerbutton.setSelected(false);
	}

	void setRecent() {
		recentmatchpanel.setRecentTable();
		card.show(teammain, "recent");
		
		recentbutton.setSelected(true);
		databutton.setSelected(false);
		pastbutton.setSelected(false);
		contrastbutton.setSelected(false);
		playerbutton.setSelected(false);
	}

	void setPast() {
		pastmatchpanel.setPastTable();
		card.show(teammain, "past");
		
		pastbutton.setSelected(true);
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		contrastbutton.setSelected(false);
		playerbutton.setSelected(false);
	}

	void setContrast() {
//		teamcontrastpanel.setChart();
		card.show(teammain, "contrast");
		contrastbutton.setSelected(true);
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		playerbutton.setSelected(false);
	}
	
	void setPlayer(){
		teamplayerpanel.setTable();
		card.show(teammain, "player");
		contrastbutton.setSelected(false);
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		playerbutton.setSelected(true);
	}

	/** 显示场均数据/总数据 */
	public boolean showAllData() {
		if (alldata.isSelected()) {
			return true;
		} else {
			return false;
		}


	}

	/** 搜索 */
	void setFind() {
		find.setBackground(Color.white);
		find.setBounds(0, 0, FrameSize.width, FrameSize.height / 4);
		find.setLayout(null);

//		JLabel name = new JLabel("队名");// 队伍名称
//		JLabel nameAbridge = new JLabel("缩写");// 名称缩写
		JLabel address = new JLabel("所在地");// 所在地
		JLabel matchArea = new JLabel("赛区");// 赛区
		JLabel playerArea = new JLabel("分区");// 分区
		JLabel manage = new JLabel("主场");// 主场
		JLabel foundYear = new JLabel("建立时间");// 建立时间

//		name.setForeground(Color.black);
//		nameAbridge.setForeground(Color.black);
		address.setForeground(Color.black);
		matchArea.setForeground(Color.black);
		playerArea.setForeground(Color.black);
		manage.setForeground(Color.black);
		foundYear.setForeground(Color.black);

//		name.setBounds(FrameSize.width / 3, FrameSize.height / 40,
//				FrameSize.width / 12, 30);
//		nameAbridge.setBounds(FrameSize.width / 3, FrameSize.height / 40 + 30,
//				FrameSize.width / 24, 30);
		address.setBounds(FrameSize.width / 2, FrameSize.height / 40 ,
				FrameSize.width / 12, 30);
		matchArea.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 30,
				FrameSize.width / 12, 30);
		playerArea.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 60,
				FrameSize.width / 12, 30);
		manage.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 90,
				FrameSize.width / 12, 30);
		foundYear.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 120,
				FrameSize.width / 12, 30);

		for (int i = 0; i < 54; i++) {
			teamlabel[i] = new UneditableTextField();
		}

//		find.add(name);
//		find.add(nameAbridge);
		find.add(address);
		find.add(matchArea);
		find.add(playerArea);
		find.add(manage);
		find.add(foundYear);

	}

	/** 在findPanel上显示一个球队的信息 */
	public void showOne(String teamname) {
		
		TeamPO teamresult = tc.getTeamData(teamname);
		image.setIcon(scaleImage(new ImageIcon(teamresult.getImage()),
				FrameSize.width / 6, FrameSize.width / 6));
		nameresult.setText(teamresult.getName());// 队伍名称
		String nameAbridge = teamresult.getNameAbridge();
		if (nameAbridge.equals("NOP")) {
			nameAbridge = "NOH";
		}
		nameAbridgeresult.setText(teamresult.getNameAbridge());// 名称缩写
		addressresult.setText(teamresult.getAddress());// 所在地
		matchArearesult.setText(teamresult.getMatchArea());// 赛区
		playerArearesult.setText(teamresult.getPlayerArea().toString());// 分区
		manageresult.setText(teamresult.getManage());// 主场
		foundYearresult.setText(String.valueOf(teamresult.getFoundYear()));// 建立时间

		image.setOpaque(false);

		image.setBounds(30, 5, FrameSize.width / 6, FrameSize.width / 6);
		nameresult.setBounds(FrameSize.width / 4-30 , FrameSize.height / 40+30,
				FrameSize.width / 6, 40);
		nameAbridgeresult.setBounds(FrameSize.width / 4 ,
				FrameSize.height / 40 + 90, FrameSize.width / 10, 40);
		addressresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 , FrameSize.width / 10, 30);
		matchArearesult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 30, FrameSize.width / 10, 30);
		playerArearesult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 60, FrameSize.width / 10, 30);
		manageresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 90, FrameSize.width / 8, 30);
		foundYearresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 120, FrameSize.width / 10, 30);

		nameresult.setFont(new Font("微软雅黑",Font.BOLD,24));
		nameAbridgeresult.setFont(new Font("微软雅黑",Font.BOLD,24));
		
		find.add(image);
		find.add(nameresult);
		find.add(nameAbridgeresult);
		find.add(addressresult);
		find.add(matchArearesult);
		find.add(playerArearesult);
		find.add(manageresult);
		find.add(foundYearresult);
		find.setVisible(true);
		find.repaint();
		this.add(find);
		this.repaint();
		this.validate();
		image.setVisible(true);
		image.repaint();
		setTeamdata();
		teamcontrastpanel.setChart();

	}



	private void resizeTable(boolean bool, JScrollPane jsp, JTable table) {
		Dimension containerwidth = null;
		if (!bool) {
			// 初始化时，父容器大小为首选大小，实际大小为0
			containerwidth = jsp.getPreferredSize();
		} else {
			// 界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
			containerwidth = jsp.getSize();
		}
		// 计算表格总体宽度 getTable().
		int allwidth = table.getIntercellSpacing().width;
		for (int j = 0; j < table.getColumnCount(); j++) {
			// 计算该列中最长的宽度
			int max = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				int width = table
						.getCellRenderer(i, j)
						.getTableCellRendererComponent(table,
								table.getValueAt(i, j), false, false, i, j)
						.getPreferredSize().width;
				if (width > max) {
					max = width;
				}
			}
			// 计算表头的宽度
			int headerwidth = table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(
							table,
							table.getColumnModel().getColumn(j).getIdentifier(),
							false, false, -1, j).getPreferredSize().width;
			// 列宽至少应为列头宽度
			max += headerwidth;
			// 设置列宽
			table.getColumnModel().getColumn(j).setPreferredWidth(max);
			// 给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
			allwidth += max + table.getIntercellSpacing().width;
		}
		allwidth += table.getIntercellSpacing().width;
		// 如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
		if (allwidth > containerwidth.width) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} else {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
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
