package ui.statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import vo.PlayerMatchVO;

public class StatisticsPlayerPanel extends JPanel {

	Vector columnsName = new Vector();
	Vector data = new Vector();
	DefaultTableModel table = new DefaultTableModel(data, columnsName);
	MyTable mytable = new MyTable(table);
	JScrollPane jScrollPane = new JScrollPane(mytable);

	public StatisticsPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		setLowTable(null);
	}

	/** 筛选栏 */
	private JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		// 根据球员位置筛选
		MyComboBox screenPlayerAccordingLocation = new MyComboBox("球员位置",
				new String[] { "F", "C", "G" });
		screenPlayerAccordingLocation.setBounds(0, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingLocation);

		// 根据球员联盟筛选
		MyComboBox screenPlayerAccordingZone = new MyComboBox("球员联盟",
				new String[] { "EAST", "E-ATLANTIC", "E-CENTRAL",
						"E-SOUTHEAST", "WEST", "W-PACIFIC", "W-SOUTHWEST",
						"W-SOUTHEAST" });
		screenPlayerAccordingZone.setBounds(150, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingZone);

		// 根据赛季筛选
		MyComboBox screenPlayerAccordingSeason = new MyComboBox("赛季",
				new String[] { "2013-2014", "2014-2015" });
		screenPlayerAccordingSeason.setBounds(450, 5, 150, 30);
		headerPanel.add(screenPlayerAccordingSeason);

		// 场均or总数
		MyComboBox aveOrAll = new MyComboBox(new String[] { "场均", "总数" });
		aveOrAll.setBounds(600, 5, 150, 30);
		headerPanel.add(aveOrAll);

		// 低阶or高阶
		MyComboBox lowOrHigh = new MyComboBox(new String[] { "低阶", "高阶" });
		lowOrHigh.setBounds(750, 5, 150, 30);
		lowOrHigh.addActionListener(e->setLowOrHigh((String)lowOrHigh.getSelectedItem()));
		headerPanel.add(lowOrHigh);

		return headerPanel;
	}

	/** 低阶表格 */
	private void setLowTable(PlayerMatchVO[] playerMatchVOs) {
		if (playerMatchVOs != null) {
			columnsName.removeAllElements();
			/* 00排名 */columnsName.add("排名");
			/* 01球员 */columnsName.add("球员");
			/* 02姓名 */columnsName.add("姓名");
			/* 03球队 */columnsName.add("球队");
			/* 04场数 */columnsName.add("场数");
			/* 05篮板 */columnsName.add("篮板");
			/* 06助攻 */columnsName.add("助攻");
			/* 07分钟 */columnsName.add("分钟");
			/* 08效率 */columnsName.add("效率");
			/* 09三分% */columnsName.add("三分%");
			/* 10罚球% */columnsName.add("罚球%");
			/* 11进攻 */columnsName.add("进攻");
			/* 12防守 */columnsName.add("防守");
			/* 13抢断 */columnsName.add("抢断");
			/* 14盖帽 */columnsName.add("盖帽");
			/* 15失误 */columnsName.add("失误");
			/* 16犯规 */columnsName.add("犯规");
			/* 17投篮% */columnsName.add("投篮%");
			/* 18两双 */columnsName.add("两双");
			/* 19得分 */columnsName.add("得分");

			data.clear();
			for (int i = 0; i < playerMatchVOs.length; i++) {
				Vector rowData = new Vector();
				/* 00排名 */rowData.add(i);
				/* 01球员 */rowData.add("放个照片");
				/* 02姓名 */rowData.add(playerMatchVOs[i].getName());
				/* 03球队 */rowData.add(playerMatchVOs[i].getTeam());
				/* 04场数 */rowData.add(playerMatchVOs[i].getMatchNo());
				/* 05篮板 */rowData.add(playerMatchVOs[i].getRebs());
				/* 06助攻 */rowData.add(playerMatchVOs[i].getHelp());
				/* 07分钟 */rowData.add(playerMatchVOs[i].getMinute());
				/* 08效率 */rowData.add(playerMatchVOs[i].getEfficiency());
				/* 09三分% */rowData.add(playerMatchVOs[i].getThreeHitRate());
				/* 10罚球% */rowData.add(playerMatchVOs[i].getPenaltyHitRate());
				/* 11进攻 */rowData.add(playerMatchVOs[i].getOffendNo());
				/* 12防守 */rowData.add(playerMatchVOs[i].getDefenceNo());
				/* 13抢断 */rowData.add(playerMatchVOs[i].getStealsNo());
				/* 14盖帽 */rowData.add(playerMatchVOs[i].getBlockNo());
				/* 15失误 */rowData.add(playerMatchVOs[i].getMistakesNo());
				/* 16犯规 */rowData.add(playerMatchVOs[i].getFoulsNo());
				/* 17投篮% */rowData.add(playerMatchVOs[i].getHandNo());
				/* 18两双 */rowData.add(playerMatchVOs[i].getTwoPair());
				/* 19得分 */rowData.add(playerMatchVOs[i].getPoints());

				data.add(rowData);
			}
			// mytable.setRowSorter(new TableRowSorter<TableModel>(table));
			setScrollPane();
			this.add(jScrollPane);
			this.repaint();
		} else {
			columnsName.removeAllElements();
			columnsName.add("排名");
			columnsName.add("球员");
			columnsName.add("姓名");
			columnsName.add("球队");
			columnsName.add("场数");
			columnsName.add("篮板");
			columnsName.add("助攻");
			columnsName.add("分钟");
			columnsName.add("效率");
			columnsName.add("三分%");
			columnsName.add("罚球%");
			columnsName.add("进攻");
			columnsName.add("防守");
			columnsName.add("抢断");
			columnsName.add("盖帽");
			columnsName.add("失误");
			columnsName.add("犯规");
			columnsName.add("投篮%");
			columnsName.add("两双");
			columnsName.add("得分");

			data.clear();
			for (int i = 0; i < 100; i++) {
				Vector rowData = new Vector();
				/* 00排名 */rowData.add(i);
				/* 01球员 */rowData.add("放个照片");
				/* 02姓名 */rowData.add("辣辣");
				/* 03球队 */rowData.add("宇宙无敌工作组");
				/* 04场数 */rowData.add("100");
				/* 05篮板 */rowData.add("100");
				/* 06助攻 */rowData.add("100");
				/* 07分钟 */rowData.add("100");
				/* 08效率 */rowData.add("100");
				/* 09三分% */rowData.add("100");
				/* 10罚球% */rowData.add("100");
				/* 11进攻 */rowData.add("100");
				/* 12防守 */rowData.add("100");
				/* 13抢断 */rowData.add("100");
				/* 14盖帽 */rowData.add("100");
				/* 15失误 */rowData.add("100");
				/* 16犯规 */rowData.add("100");
				/* 17投篮% */rowData.add("100");
				/* 18两双 */rowData.add("100");
				/* 19得分 */rowData.add("1000");

				data.add(rowData);
			}
			setScrollPane();
			// mytable.setRowSorter(new TableRowSorter<TableModel>(table));
			this.add(jScrollPane);
			this.repaint();
		}
	}
	
	/** 高阶表格 */
	private void setHighTable(PlayerMatchVO[] playerMatchVOs) {
		if (playerMatchVOs != null) {
			columnsName.removeAllElements();
			/* 00排名 */columnsName.add("排名");
			/* 01球员 */columnsName.add("球员");
			/* 02姓名 */columnsName.add("姓名");
			/* 03球队 */columnsName.add("球队");
			/* 04真实投篮命中率 */columnsName.add("真实投篮%");
			/* 05GmSc效率值 */columnsName.add("GmSc%");
			/* 06投篮效率 */columnsName.add("投篮%");
			/* 07篮板效率 */columnsName.add("篮板%");
			/* 08进攻篮板率 */columnsName.add("进攻篮板%");
			/* 09防守篮板率 */columnsName.add("防守篮板%");
			/* 10助攻率 */columnsName.add("助攻%");
			/* 11抢断率 */columnsName.add("抢断%");
			/* 12盖帽率 */columnsName.add("盖帽%");
			/* 13失误率 */columnsName.add("失误%");
			/* 14使用率 */columnsName.add("使用%");

			data.clear();
			for (int i = 0; i < playerMatchVOs.length; i++) {
				Vector rowData = new Vector();
				/* 00排名 */rowData.add(i);
				/* 01球员 */rowData.add("放个照片");
				/* 02姓名 */rowData.add(playerMatchVOs[i].getName());
				/* 03球队 */rowData.add(playerMatchVOs[i].getTeam());
				/* 04真实投篮命中率 */rowData.add(playerMatchVOs[i].getTrueHitRate());
				/* 05GmSc效率值 */rowData.add(playerMatchVOs[i]
						.getGmScEfficiency());
				/* 06投篮效率 */rowData.add(playerMatchVOs[i].getHitEfficiency());
				/* 07篮板效率 */rowData.add(playerMatchVOs[i].getRebEfficiency());
				/* 08进攻篮板率 */rowData.add(playerMatchVOs[i]
						.getOffenseRebsEfficiency());
				/* 09防守篮板率 */rowData.add(playerMatchVOs[i]
						.getDefenceRebsEfficiency());
				/* 10助攻率 */rowData.add(playerMatchVOs[i].getAssistEfficiency());
				/* 11抢断率 */rowData.add(playerMatchVOs[i].getStealsEfficiency());
				/* 12盖帽率 */rowData.add(playerMatchVOs[i].getBlockEfficiency());
				/* 13失误率 */rowData
						.add(playerMatchVOs[i].getMistakeEfficiency());
				/* 14使用率 */rowData.add(playerMatchVOs[i].getUseEfficiency());
				data.add(rowData);
			}
			// mytable.setRowSorter(new TableRowSorter<TableModel>(table));
			setScrollPane();
			this.add(jScrollPane);
			this.repaint();
		} else {
			columnsName.removeAllElements();
			/* 00排名 */columnsName.add("排名");
			/* 01球员 */columnsName.add("球员");
			/* 02姓名 */columnsName.add("姓名");
			/* 03球队 */columnsName.add("球队");
			/* 04真实投篮命中率 */columnsName.add("真实投篮%");
			/* 05GmSc效率值 */columnsName.add("GmSc%");
			/* 06投篮效率 */columnsName.add("投篮%");
			/* 07篮板效率 */columnsName.add("篮板%");
			/* 08进攻篮板率 */columnsName.add("进攻篮板%");
			/* 09防守篮板率 */columnsName.add("防守篮板%");
			/* 10助攻率 */columnsName.add("助攻%");
			/* 11抢断率 */columnsName.add("抢断%");
			/* 12盖帽率 */columnsName.add("盖帽%");
			/* 13失误率 */columnsName.add("失误%");
			/* 14使用率 */columnsName.add("使用%");

			data.clear();
			for (int i = 0; i < 100; i++) {
				Vector rowData = new Vector();
				/* 00排名 */rowData.add(i);
				/* 01球员 */rowData.add("一张照片");
				/* 02姓名 */rowData.add("辣辣");
				/* 03球队 */rowData.add("宇宙无敌工作组");
				/* 04真实投篮命中率 */rowData.add("99.9");
				/* 05GmSc效率值 */rowData.add("99.9");
				/* 06投篮效率 */rowData.add("99.9");
				/* 07篮板效率 */rowData.add("99.9");
				/* 08进攻篮板率 */rowData.add("99.9");
				/* 09防守篮板率 */rowData.add("99.9");
				/* 10助攻率 */rowData.add("99.9");
				/* 11抢断率 */rowData.add("99.9");
				/* 12盖帽率 */rowData.add("99.9");
				/* 13失误率 */rowData.add("99.9");
				/* 14使用率 */rowData.add("99.9");

				data.add(rowData);
			}
			// mytable.setRowSorter(new TableRowSorter<TableModel>(table));
			setScrollPane();
			this.add(jScrollPane);
			this.repaint();
		}
	}
	
	/**低阶高阶转换*/
	private void setLowOrHigh(String type) {
		if (type.equals("低阶")) {
			setLowTable(null);
		} else {
			setHighTable(null);
		}
	}

	/**设置滚动框*/
	private void setScrollPane(){
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
		resizeTable(false,jScrollPane,mytable);
	}
	
	/** 设置表格 */
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
