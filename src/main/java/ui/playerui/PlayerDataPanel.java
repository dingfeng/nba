package ui.playerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import po.TeamNormalPO;
import ui.mainui.FrameSize;
import ui.mainui.MyToggleButton;
import ui.mainui.UneditableTextField;

public class PlayerDataPanel extends JPanel{

	MyToggleButton alldata;
	MyToggleButton avedata;
	JTextField[] playerlabel=new UneditableTextField[54];
	public PlayerDataPanel(){
		this.setLayout(null);
		this.setBounds(0, 30,
				 FrameSize.width , FrameSize.height * 3 / 4-80);
		this.setBackground(Color.white);
		setText();
		setMessage();
		TeamMessage(null);
		this.repaint();
	}
	public void setText(){
		alldata=new MyToggleButton("总数",FrameSize.bluecolor,FrameSize.darkbluecolor);
		avedata=new MyToggleButton("场均",FrameSize.bluecolor,FrameSize.darkbluecolor);
		alldata.setBounds(FrameSize.width-100, 0, 50,30);
		avedata.setBounds(FrameSize.width-50, 0, 50,30);
		alldata.addActionListener(e->showall());
		avedata.addActionListener(e->showave());
		alldata.setForeground(Color.white);
		avedata.setForeground(Color.white);
		this.add(alldata);
		this.add(avedata);
	}
	
	void showall(){
		avedata.setSelected(false);
		showData();
	}
	void showave(){
		alldata.setSelected(false);
		showData();
	}
	void showData(){
		if(alldata.isSelected()){
			System.out.println("all");
		}else if(avedata.isSelected()){
			System.out.println("ave");
		}
	}

	/** 设置单个球队的panel */
	void setMessage() {
		JPanel teamdata=new JPanel();
		teamdata.setLayout(new GridLayout(9, 6, -1, -1));
		teamdata.setBounds(0, 30, FrameSize.width, FrameSize.height*3/4-180);
		// teammessage.setBorder(BorderFactory.createLineBorder(Color.white));
		// JTextField[] playerlabel=new UneditableTextField[54];
		for (int i = 0; i < 54; i++) {
			playerlabel[i] = new UneditableTextField();
			teamdata.add(playerlabel[i]);
			playerlabel[i].setText("第"+i);
			playerlabel[i].setFont(new Font("", Font.PLAIN, 15));
			playerlabel[i].setBorder(BorderFactory.createLineBorder(Color.white));
		}
		this.add(teamdata);
	}

	/** 一个球队信息（右侧） */
	void TeamMessage(TeamNormalPO str) {

		playerlabel[0].setText("比赛场数");
		playerlabel[2].setText("比赛得分");
		playerlabel[4].setText("胜率(%)");
		playerlabel[6].setText("投篮命中数");
		playerlabel[8].setText("投篮出手次数");
		playerlabel[10].setText("投篮命中率(%)");
		playerlabel[12].setText("三分命中数");
		playerlabel[14].setText("三分出手数");
		playerlabel[16].setText("三分命中率(%)");
		playerlabel[18].setText("罚球命中数");
		playerlabel[20].setText("罚球出手数");
		playerlabel[22].setText("罚球命中率(%)");
		playerlabel[24].setText("进攻篮板数");
		playerlabel[26].setText("防守篮板数");
		playerlabel[28].setText("篮板数");
		playerlabel[30].setText("助攻数");
		playerlabel[32].setText("抢断数");
		playerlabel[34].setText("盖帽数");
		playerlabel[36].setText("失误数");
		playerlabel[38].setText("犯规数");
		playerlabel[40].setText("进攻回合");
		playerlabel[42].setText("进攻效率");
		playerlabel[44].setText("防守效率");
		playerlabel[46].setText("进攻篮板效率");
		playerlabel[48].setText("防守篮板效率");
		playerlabel[50].setText("抢断效率");
		playerlabel[52].setText("助攻率");
	}
}
