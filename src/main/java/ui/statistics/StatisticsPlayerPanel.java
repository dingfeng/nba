package ui.statistics;

import java.awt.Color;

import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;

public class StatisticsPlayerPanel extends JPanel {
	public StatisticsPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
	}

	private static JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 30);
		headerPanel.setBackground(new Color(87, 89, 91));


		  //根据球员位置筛选 
		  MyComboBox screenPlayerAccordingLocation = new MyComboBox("球员位置",new String[]{"F","C","G"});
		  screenPlayerAccordingLocation.setBounds(0,0,150,30);
		  headerPanel.add(screenPlayerAccordingLocation);
		  
		  //根据球员联盟筛选 
		  MyComboBox screenPlayerAccordingZone = new MyComboBox("球员联盟",new
		  String[]{"EAST","E-ATLANTIC","E-CENTRAL","E-SOUTHEAST"
		  ,"WEST","W-PACIFIC","W-SOUTHWEST","W-SOUTHEAST"});
		  screenPlayerAccordingZone.setBounds(150,0,150,30);
		  headerPanel.add(screenPlayerAccordingZone);
		  
		  //根据排序依据筛选 
		  MyComboBox screenPlayerAccordingScreen = new MyComboBox("排序依据",new String[] { "得分", "篮板", "助攻", "得分/篮板/助攻",
		  "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双" });
		  screenPlayerAccordingScreen.setBounds(300,0,150,30);
		  headerPanel.add(screenPlayerAccordingScreen);
		  
		  //根据赛季筛选
		  MyComboBox screenPlayerAccordingSeason = new MyComboBox("赛季",new String[]{"2013-2014","2014-2015"});
		  screenPlayerAccordingSeason.setBounds(450,0,150,30);
		  headerPanel.add(screenPlayerAccordingSeason);
		  
		  //场均or总数
		  MyComboBox aveOrAll= new MyComboBox(new String[]{"场均","总数"});
		  aveOrAll.setBounds(600,0,150,30);
		  headerPanel.add(aveOrAll);
		  
		  //低阶or高阶
		  MyComboBox lowOrHigh= new MyComboBox(new String[]{"低阶","高阶"});
		  lowOrHigh.setBounds(750,0,150,30);
		  headerPanel.add(lowOrHigh);
		  
		return headerPanel;
	}

}
