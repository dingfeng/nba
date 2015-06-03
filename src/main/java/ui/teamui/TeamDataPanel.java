package ui.teamui;

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

public class TeamDataPanel extends JPanel{

	MyToggleButton alldata;
	MyToggleButton avedata;
	JTextField[] teamlabel=new UneditableTextField[54];
	public TeamDataPanel(){
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
		// JTextField[] teamlabel=new UneditableTextField[54];
		for (int i = 0; i < 54; i++) {
			teamlabel[i] = new UneditableTextField();
			teamdata.add(teamlabel[i]);
			teamlabel[i].setText("第"+i);
			teamlabel[i].setFont(new Font("", Font.PLAIN, 15));
			teamlabel[i].setBorder(BorderFactory.createLineBorder(Color.white));
		}
		this.add(teamdata);
	}

	/** 一个球队信息（右侧） */
	void TeamMessage(TeamNormalPO str) {

		teamlabel[0].setText("比赛场数");
		teamlabel[2].setText("比赛得分");
		teamlabel[4].setText("胜率(%)");
		teamlabel[6].setText("投篮命中数");
		teamlabel[8].setText("投篮出手次数");
		teamlabel[10].setText("投篮命中率(%)");
		teamlabel[12].setText("三分命中数");
		teamlabel[14].setText("三分出手数");
		teamlabel[16].setText("三分命中率(%)");
		teamlabel[18].setText("罚球命中数");
		teamlabel[20].setText("罚球出手数");
		teamlabel[22].setText("罚球命中率(%)");
		teamlabel[24].setText("进攻篮板数");
		teamlabel[26].setText("防守篮板数");
		teamlabel[28].setText("篮板数");
		teamlabel[30].setText("助攻数");
		teamlabel[32].setText("抢断数");
		teamlabel[34].setText("盖帽数");
		teamlabel[36].setText("失误数");
		teamlabel[38].setText("犯规数");
		teamlabel[40].setText("进攻回合");
		teamlabel[42].setText("进攻效率");
		teamlabel[44].setText("防守效率");
		teamlabel[46].setText("进攻篮板效率");
		teamlabel[48].setText("防守篮板效率");
		teamlabel[50].setText("抢断效率");
		teamlabel[52].setText("助攻率");
	}
}
