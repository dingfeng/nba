package ui.playerui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import po.HPlayerPO;
import bl.playerbl.PlayerController;
import blservice.playerblservice.PlayerBlService;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;
import ui.mainui.UneditableTextField;
import ui.playerui.PastMatchPanel;
import ui.playerui.RecentMatchPanel;
import ui.playerui.PlayerContrastPanel;
import ui.playerui.PlayerDataPanel;

public class ShowOnePlayerPanel extends JPanel{
	DefaultTableModel table;
	JScrollPane jScrollPane;
	JPanel find = new JPanel();
	JPanel playermessage = new JPanel();
	MyTable mytable;
	JComboBox<String> box;
	MyButton dataType;
	JLabel image = new JLabel();// 图片
	JTextField nameresult = new UneditableTextField();// 球员名称
	JTextField locationresult = new UneditableTextField();//位置
	JTextField heightresult = new UneditableTextField();//身高
	JTextField weightresult = new UneditableTextField();//体重
	JTextField cityresult = new UneditableTextField();//城市
	JTextField birthresult = new UneditableTextField();//生日
	JTextField numberresult = new UneditableTextField();//球衣
	JButton match;
	JButton teamplayers;

	boolean matchpanel = false;
	Vector<String> columnsName = new Vector<String>();
	public static CardLayout card = new CardLayout();
	public static JPanel playermain = new JPanel();
	public static PastMatchPanel pastmatchpanel = new PastMatchPanel();
	public static RecentMatchPanel recentmatchpanel = new RecentMatchPanel();
	public static PlayerContrastPanel playercontrastpanel = new PlayerContrastPanel();
	public static PlayerDataPanel playerdatapanel = new PlayerDataPanel();
	public static TrendPanel trendpanel = new TrendPanel();
	
	MyToggleButton databutton;
	MyToggleButton recentbutton;
	MyToggleButton pastbutton;
	MyToggleButton contrastbutton;
	MyToggleButton trendbutton;
	MyToggleButton alldata;
	MyToggleButton avedata;
	boolean high=false;
	PlayerBlService playerController = new PlayerController();


	public ShowOnePlayerPanel() {

		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);

		playermain.setBounds(0, FrameSize.height / 4 + 50, FrameSize.width,
				FrameSize.height * 3 / 4 - 50);
		playermain.setLayout(card);
		playermain.add("data", playerdatapanel);
		playermain.add("past", pastmatchpanel);
		playermain.add("recent", recentmatchpanel);
		playermain.add("contrast", playercontrastpanel);
		playermain.add("trend",trendpanel);
		setHeader();
		setFind();
		this.add(playermain);
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

		trendbutton = new MyToggleButton("趋势",Color.black,Color.gray);
		trendbutton.setBounds(FrameSize.width*4/5,FrameSize.height/4,FrameSize.width/5,50);
		databutton.setForeground(Color.white);
		recentbutton.setForeground(Color.white);
		pastbutton.setForeground(Color.white);
		contrastbutton.setForeground(Color.white);
		trendbutton.setForeground(Color.white);

		recentbutton.addActionListener(e -> setRecent());
		pastbutton.addActionListener(e -> setPast());
		contrastbutton.addActionListener(e -> setContrast());
		trendbutton.addActionListener(e->setTrend());

		JPopupMenu type = new JPopupMenu();
		JMenuItem normal = new JMenuItem("基本数据");
		normal.setBackground(Color.white);
		normal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				high=false;
				setPlayerdata();
			}

		});
		JMenuItem higher = new JMenuItem("高阶数据");
		higher.setBackground(Color.white);
		higher.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				high=true;
				setPlayerdata();
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
		playerdatapanel.add(alldata);
		playerdatapanel.add(avedata);
		this.add(databutton);
		this.add(recentbutton);
		this.add(pastbutton);
		this.add(contrastbutton);

	}



	void showall() {
		alldata.setSelected(true);
		avedata.setSelected(false);
		setPlayerdata();
	}

	void showave() {
		avedata.setSelected(true);
		alldata.setSelected(false);
		setPlayerdata();
	}
	
	void setTrend() {
		
		card.show(playermain, "trend");
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		contrastbutton.setSelected(false);
		trendbutton.setSelected(true);
	}
	
	void setPlayerdata() {
		card.show(playermain, "data");
		playerdatapanel.setTable(nameresult.getText(),high,showAllData());
		databutton.setSelected(true);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		contrastbutton.setSelected(false);
		trendbutton.setSelected(false);
	}

	void setRecent() {
		recentmatchpanel.setRecentTable(nameresult.getText());
		card.show(playermain, "recent");
		
		recentbutton.setSelected(true);
		databutton.setSelected(false);
		pastbutton.setSelected(false);
		contrastbutton.setSelected(false);
		trendbutton.setSelected(false);

	}

	void setPast() {
		pastmatchpanel.setPastTable(nameresult.getText());
		card.show(playermain, "past");
		
		pastbutton.setSelected(true);
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		contrastbutton.setSelected(false);
		trendbutton.setSelected(false);
	}

	void setContrast() {
		playercontrastpanel.setChart();
		card.show(playermain, "contrast");
		contrastbutton.setSelected(true);
		databutton.setSelected(false);
		recentbutton.setSelected(false);
		pastbutton.setSelected(false);
		trendbutton.setSelected(false);
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

		JLabel name = new JLabel("姓名");// 球员姓名
		JLabel location = new JLabel("位置");// 位置
		JLabel height = new JLabel("身高");// 身高
		JLabel weight = new JLabel("体重");// 体重
		JLabel city = new JLabel("城市");// 城市
		JLabel birth = new JLabel("生日");// 生日
		JLabel number = new JLabel("球衣");// 球衣
		
		name.setForeground(Color.black);
		location.setForeground(Color.black);
		height.setForeground(Color.black);
		weight.setForeground(Color.black);
		city.setForeground(Color.black);
		birth.setForeground(Color.black);
		number.setForeground(Color.black);

		name.setBounds(FrameSize.width / 2, FrameSize.height / 40,
				FrameSize.width / 12, 30);
		location.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 30,
				FrameSize.width / 24, 30);
		height.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 60,
				FrameSize.width / 12, 30);
		weight.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 90,
				FrameSize.width / 12, 30);
		city.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 120,
				FrameSize.width / 12, 30);
		birth.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 150,
				FrameSize.width / 12, 30);
		number.setBounds(FrameSize.width / 2, FrameSize.height / 40 + 180,
				FrameSize.width / 12, 30);

		find.add(name);
		find.add(location);
		find.add(height);
		find.add(weight);
		find.add(city);
		find.add(birth);
		find.add(number);

	}

	/** 在findPanel上显示一个球队的信息 */
	public void showOne(String playerName) {
		
		HPlayerPO player = playerController.findPlayer(playerName);
		image.setIcon(scaleImage(new ImageIcon(playerController.getPlayerImage(playerName)),
				FrameSize.width / 6, FrameSize.width / 6));
		nameresult.setText(player.getName());//球员姓名
		locationresult.setText(player.getPosition());//位置
		heightresult.setText(player.getHeight());//身高
		weightresult.setText(player.getWeight());//体重
		cityresult.setText(player.getBirthCity());//城市
		birthresult.setText(player.getBirthday());//生日
		numberresult.setText(player.getNum());//球衣

		image.setOpaque(false);

		image.setBounds(30, 5, FrameSize.width / 6, FrameSize.width / 6);
		nameresult.setBounds(FrameSize.width / 2 + 50, FrameSize.height / 40,
				FrameSize.width / 12, 30);
		locationresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 30, FrameSize.width / 12, 30);
		heightresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 60, FrameSize.width / 12, 30);
		weightresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 90, FrameSize.width / 12, 30);
		cityresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 120, FrameSize.width / 12, 30);
		birthresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 150, FrameSize.width / 12, 30);
		numberresult.setBounds(FrameSize.width / 2 + 50,
				FrameSize.height / 40 + 180, FrameSize.width / 12, 30);

		find.add(image);
		find.add(nameresult);
		find.add(locationresult);
		find.add(heightresult);
		find.add(weightresult);
		find.add(cityresult);
		find.add(birthresult);
		find.add(numberresult);
		find.setVisible(true);
		find.repaint();
		this.add(find);
		this.repaint();
		this.validate();
		image.setVisible(true);
		image.repaint();
		setPlayerdata();

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