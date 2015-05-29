package ui.statistics;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import vo.PlayerMatchVO;

public class StatisticsPlayerPanel extends JPanel {
	public StatisticsPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		JScrollPane low = LowTable(null);
		this.add(low);
	}

	/**筛选栏*/
	private static JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));


		  //根据球员位置筛选 
		  MyComboBox screenPlayerAccordingLocation = new MyComboBox("球员位置",new String[]{"F","C","G"});
		  screenPlayerAccordingLocation.setBounds(0,5,150,30);
		  headerPanel.add(screenPlayerAccordingLocation);
		  
		  //根据球员联盟筛选 
		  MyComboBox screenPlayerAccordingZone = new MyComboBox("球员联盟",new
		  String[]{"EAST","E-ATLANTIC","E-CENTRAL","E-SOUTHEAST"
		  ,"WEST","W-PACIFIC","W-SOUTHWEST","W-SOUTHEAST"});
		  screenPlayerAccordingZone.setBounds(150,5,150,30);
		  headerPanel.add(screenPlayerAccordingZone);
		  
		  //根据排序依据筛选 
		  MyComboBox screenPlayerAccordingScreen = new MyComboBox("排序依据",new String[] { "得分", "篮板", "助攻", "得分/篮板/助攻",
		  "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双" });
		  screenPlayerAccordingScreen.setBounds(300,5,150,30);
		  headerPanel.add(screenPlayerAccordingScreen);
		  
		  //根据赛季筛选
		  MyComboBox screenPlayerAccordingSeason = new MyComboBox("赛季",new String[]{"2013-2014","2014-2015"});
		  screenPlayerAccordingSeason.setBounds(450,5,150,30);
		  headerPanel.add(screenPlayerAccordingSeason);
		  
		  //场均or总数
		  MyComboBox aveOrAll= new MyComboBox(new String[]{"场均","总数"});
		  aveOrAll.setBounds(600,5,150,30);
		  headerPanel.add(aveOrAll);
		  
		  //低阶or高阶
		  MyComboBox lowOrHigh= new MyComboBox(new String[]{"低阶","高阶"});
		  lowOrHigh.setBounds(750,5,150,30);
		  headerPanel.add(lowOrHigh);
		  
		return headerPanel;
	}

	/**低阶表格*/
	private JScrollPane LowTable(PlayerMatchVO[] playerMatchVOs){
		if(playerMatchVOs!=null){
			Vector columnsName = new Vector();
			columnsName.add("排名");
			columnsName.add("球员");
			columnsName.add("场数");
			columnsName.add("球队");
			columnsName.add("得分");
			columnsName.add("篮板");
			columnsName.add("助攻");
			columnsName.add("盖帽");
			columnsName.add("抢断");
			columnsName.add("犯规");
			columnsName.add("失误");
			columnsName.add("分钟");
			columnsName.add("效率");
			columnsName.add("投篮%");
			columnsName.add("三分%");
			columnsName.add("罚球%");
			columnsName.add("两双");

		}
		else{
			Vector columnsName = new Vector();
			columnsName.add("排名");
			columnsName.add("球员");
			columnsName.add("球队");
			columnsName.add("场数");
			columnsName.add("得分");
			columnsName.add("篮板");
			columnsName.add("助攻");
			columnsName.add("盖帽");
			columnsName.add("抢断");
			columnsName.add("犯规");
			columnsName.add("失误");
			columnsName.add("分钟");
			columnsName.add("效率");
			columnsName.add("投篮%");
			columnsName.add("三分%");
			columnsName.add("罚球%");
			columnsName.add("两双");
			
			Vector data = new Vector();
			for(int i=0;i<100;i++){
				Vector rowData = new Vector();
				rowData.add(100);
				rowData.add("AbCdEfG");
				rowData.add("aBcDeFg");
				rowData.add(100);
				rowData.add(400);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(100);
				rowData.add(99.9);
				rowData.add(99.9);
				rowData.add(99.9);
				rowData.add(100);
				
				data.add(rowData);
			}
			DefaultTableModel table = new DefaultTableModel(data, columnsName);
			MyTable myTable = new MyTable(table);
			JScrollPane jScrollPane = new JScrollPane(myTable);
			jScrollPane.setBounds(0, 40, FrameSize.width,
					FrameSize.height* 7 / 8 - 40);
			return jScrollPane;
		}
		return null;
	}
}
