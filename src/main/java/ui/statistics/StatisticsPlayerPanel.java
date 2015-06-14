package ui.statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dataservice.playerdataservice.SeasonType;
import bl.playerbl.PlayerController;
import blservice.playerblservice.PlayerBlService;
import po.PlayerHighPO;
import po.PlayerNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import vo.Area;

public class StatisticsPlayerPanel extends JPanel {

	Vector<String> columnsName = new Vector<String>();
	Vector data = new Vector();
	DefaultTableModel table = new DefaultTableModel(data, columnsName);
	MyTable mytable = new MyTable(table);{
		((MyTable)mytable).setShowImage(true);
	
	mytable.getTableHeader().setPreferredSize(new Dimension(1,40));;
	}
	JScrollPane jScrollPane = new JScrollPane(mytable);

	PlayerBlService playerController = new PlayerController();
	public StatisticsPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		setTable("F",Area.EASTERN,SeasonType.REGULAR,2013,"场均","基本");
	}

	/** 筛选栏 */
	private JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		MyComboBox screenPlayerAccordingLocation,screenPlayerAccordingZone,seasonType,screenPlayerAccordingSeason,aveOrAll,lowOrHigh;
		// 根据球员位置筛选
		screenPlayerAccordingLocation = new MyComboBox("球员位置",
				new String[] { "F", "C", "G" });
		screenPlayerAccordingLocation.setBounds(0, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingLocation);

		// 根据球员联盟筛选
		screenPlayerAccordingZone = new MyComboBox("球员联盟",
				new String[] { "EAST", "E-ATLANTIC", "E-CENTRAL",
						"E-SOUTHEAST", "WEST", "W-PACIFIC", "W-SOUTHWEST",
						"W-NORTHWEST" });
		screenPlayerAccordingZone.setBounds(150, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingZone);

		//常规赛or季后赛
		seasonType = new MyComboBox(new String[]{"常规赛","季后赛"});
		seasonType.setBounds(300,5,150,30);
		headerPanel.add(seasonType);
		
		// 根据赛季筛选
		String[] seasons = new String[70];
		for(int i=0;i<70;i++){
			seasons[i]=String.valueOf(2014-i);
		}
		screenPlayerAccordingSeason = new MyComboBox(seasons);
		screenPlayerAccordingSeason.setBounds(450, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingSeason);

		
		// 场均or总数
		aveOrAll = new MyComboBox(new String[] { "场均", "总数" });
		aveOrAll.setBounds(600, 5, 150, 30);
		headerPanel.add(aveOrAll);

		// 低阶or高阶
		lowOrHigh = new MyComboBox(new String[] { "基本", "高阶" });
		lowOrHigh.setBounds(750, 5, 150, 30);
		headerPanel.add(lowOrHigh);

		screenPlayerAccordingLocation.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));
		screenPlayerAccordingZone.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));
		seasonType.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));
		screenPlayerAccordingSeason.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));
		aveOrAll.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));
		lowOrHigh.addActionListener(e->setTable(((String)screenPlayerAccordingLocation.getSelectedItem()),chooseArea((String)screenPlayerAccordingZone.getSelectedItem()),chooseType((String)seasonType.getSelectedItem()),Integer.parseInt((String)screenPlayerAccordingSeason.getSelectedItem()),(String)aveOrAll.getSelectedItem(),(String)lowOrHigh.getSelectedItem()));

		return headerPanel;
	}

	/**设置表格*/
	private void setTable(String location,Area zone,SeasonType seasonType,int season,String aveOrAll,String lowOrHigh) {

		if(location.equals("球员位置")){
			location=null;
		}
		if(lowOrHigh.equals("基本")){
			if (aveOrAll.equals("场均")){
			PlayerNormalPO[] playerNormal = playerController.screenNormalAvePlayers(season, location, zone, seasonType);
			setLowTable(playerNormal);
			}
			else{
				PlayerNormalPO[] playerNormal = playerController.screenNormalTotalPlayers(season, location, zone, seasonType);
				setLowTable(playerNormal);
			}
		}else{
			PlayerHighPO[] playerHigh = playerController.screenHighPlayers(season, location, zone, seasonType);
			setHighTable(playerHigh);
		}
	}

	/** 低阶表格 */
	private void setLowTable(PlayerNormalPO[] playerMatchVOs) {
		if (playerMatchVOs != null) {
			columnsName.removeAllElements();
//			/* 00排名 */columnsName.add("排名");
			/* 01球员 */columnsName.add("球员");
			/* 02姓名 */columnsName.add("姓名");
			/* 03球队 */columnsName.add("球队");
			/* 04场数 */columnsName.add("场数");
			/* 05篮板 */columnsName.add("篮板");
			/* 06助攻 */columnsName.add("助攻");
			/* 07分钟 */columnsName.add("分钟");
			/* 09三分命中率 */columnsName.add("三分%");
			/* 10罚球命中率 */columnsName.add("罚球%");
			/* 11投篮命中率 */columnsName.add("投篮%");
			/* 12进攻 */columnsName.add("进攻");
			/* 13防守 */columnsName.add("防守");
			/* 14抢断 */columnsName.add("抢断");
			/* 15盖帽 */columnsName.add("盖帽");
			/* 16失误 */columnsName.add("失误");
			/* 17犯规 */columnsName.add("犯规");
			/* 18两双 */columnsName.add("两双");
			/* 19得分 */columnsName.add("得分");

			data.clear();
			for (int i = 0; i < playerMatchVOs.length; i++) {
				Vector rowData = new Vector();
//				/* 00排名 */rowData.add(i + 1);
				/* 01球员 */rowData.add(playerController.getPlayerImage(playerMatchVOs[i].getName()));
				/* 02姓名 */rowData.add(playerMatchVOs[i].getName());
				/* 03球队 */rowData.add(playerMatchVOs[i].getTeam());
				/* 04场数 */rowData.add(playerMatchVOs[i].getMatchNo());
				/* 05篮板 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getRebs(),0));
				/* 06助攻 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getAssistNo(),1));
				/* 07分钟 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getTime(),1));
				/* 09三分命中率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getThreeHitRate(),1));
				/* 10罚球命中率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getPenaltyHitRate(),1));
				/* 11投篮命中率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getHitRate(),1));
				/* 12进攻 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getOffendRebsNo(),1));
				/* 13防守 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getDefenceRebsNo(),1));
				/* 14抢断 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getStealsNo(),1));
				/* 15盖帽 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getBlockNo(),1));
				/* 16失误 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getMistakesNo(),1));
				/* 17犯规 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getFoulsNo(),1));
				/* 18两双 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getTwoPair(),1));
				/* 19得分 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getPoints(),1));
				data.add(rowData);
			}
			mytable.setRowHeight(50);
			mytable.setRowSorter(new TableRowSorter<TableModel>(table));
//			mytable.getColumnModel().getColumn(1).setPreferredWidth(150);
			mytable.updateUI();

			TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();  
			 Comparator<String> numberComparator = new Comparator<String>() {  
		            @Override  
		            public int compare(String o1, String o2) {  
		                if ( o1 == null ) {  
		                    return -1;  
		                }  
		                if ( o2 == null ) {  
		                    return 1;  
		                }  
		                if ( Double.parseDouble(o1) < Double.parseDouble(o2)) {  
		                    return -1;  
		                }  
		                if ( Double.parseDouble(o1) > Double.parseDouble(o2) ) {  
		                    return 1;  
		                }  
		                return 0;  
		            }  
		        };  
		        for (int col = 3; col < mytable.getColumnCount(); col++) {  
		            rowSorter.setComparator(col, numberComparator);  
		        }  
			setScrollPane();
		}
	}

	/** 高阶表格 */
	private void setHighTable(PlayerHighPO[] playerMatchVOs) {
		if (playerMatchVOs != null) {
			columnsName.removeAllElements();
//			/* 00排名 */columnsName.add("排名");
			/* 01球员 */columnsName.add("球员");
			/* 02姓名 */columnsName.add("姓名");
			/* 03球队 */columnsName.add("球队");
			/* 04效率 */columnsName.add("效率");
			/* 05真实命中率 */columnsName.add("真实命中率");
			/* 06GmSc */columnsName.add("GmSc");			
			/* 07篮板效率 */columnsName.add("篮板%");
			/* 08进攻篮板率 */columnsName.add("进攻%");
			/* 09防守篮板率 */columnsName.add("防守%");
			/* 10助攻率 */columnsName.add("助攻%");
			/* 11抢断率 */columnsName.add("抢断%");
			/* 12盖帽率 */columnsName.add("盖帽%");
			/* 13失误率 */columnsName.add("失误%");
			/* 14使用率 */columnsName.add("使用%");

			data.clear();
			for (int i = 0; i < playerMatchVOs.length; i++) {
				Vector rowData = new Vector();
//				/* 00排名 */rowData.add(i+1);
				/* 01球员 */rowData.add(playerController.getPlayerImage(playerMatchVOs[i].getPlayerName()));
				/* 02姓名 */rowData.add(playerMatchVOs[i].getPlayerName());
				/* 03球队 */rowData.add(playerMatchVOs[i].getTeamName());
				/* 04效率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getEfficiency(),1));
				/* 05真实命中率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getTrueHitRate(),1));
				/* 06GmSc */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getGmScEfficiency(),1));
				/* 07篮板效率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getRebEfficiency(),1));
				/* 08进攻篮板率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getDefenceRebsEfficiency(),1));
				/* 09防守篮板率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getOffenseRebsEfficiency(),1));
				/* 10助攻率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getAssistEfficiency(),1));
				/* 11抢断率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getStealsEfficiency(),1));
				/* 12盖帽率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getBlockEfficiency(),1));
				/* 13失误率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getMistakeEfficiency(),1));
				/* 14使用率 */rowData.add(FrameSize.roundForNumber(playerMatchVOs[i].getUseEfficiency(),1));	
				data.add(rowData);
			}
			mytable.setRowHeight(50);
			setScrollPane();
		} 
	}

	/**判断分区*/
	private Area chooseArea(String zone){
		if(zone.equals("EAST")){
			return Area.EASTERN;
		}
		else if(zone.equals("E-ATLANTIC")){
			return Area.ATLANTIC;
		}
		else if(zone.equals("E-CENTRAL")){
			return Area.CENTRAL;
		}
		else if(zone.equals("E-SOUTHEAST")){
			return Area.SOUTHEAST;
		}
		else if(zone.equals("WEST")){
			return Area.WESTERN;
		}
		else if(zone.equals("W-PACIFIC")){
			return Area.PACIFIC;
		}
		else if(zone.equals("W-SOUTHWEST")){
			return Area.SOUTHWEST;
		}
		else if(zone.equals("W-NORTHWEST")){
			return Area.NORTHWEST;
		}
		else{
			return null;
		}
	}
	
	/**判断常规赛or季后赛*/
	private SeasonType chooseType(String seasonType){
		if(seasonType.equals("常规赛")){
			return SeasonType.REGULAR;
		}
		else{
			return SeasonType.PLAYOFF;
		}
	}

	/** 设置jScrollPanel */
 	private void setScrollPane() {
		table.setDataVector(data, columnsName);
		mytable.updateUI();
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(0, 40, FrameSize.width,
				FrameSize.height * 7 / 8 - 40);
		jScrollPane.setBackground(Color.white);
		jScrollPane.getViewport().setOpaque(false);
//		resizeTable(false,jScrollPane,mytable);
		this.add(jScrollPane);
		this.repaint();
	}

	/**设置表格*/
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
}
