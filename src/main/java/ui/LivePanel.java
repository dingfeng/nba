package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import dataservice.matchdataservice.MatchDataService;
import DataFactory.DataFactory;
import live.CurrentMatch;
import live.CurrentTeam;
import ui.mainui.FrameSize;
import ui.mainui.MyTable;
import ui.mainui.MyToggleButton;

public class LivePanel extends JPanel {

	MyToggleButton livebutton;
	MyToggleButton databutton;
	JLabel guestTeamImage;
	JLabel guestTeamName;
	JLabel guestTeamInfo;
	JLabel guestTeamScores;
	
	JLabel hostTeamImage ;
	JLabel hostTeamName;
	JLabel hostTeamInfo;
	JLabel hostTeamScores;
	
	JLabel gameDate;
	JLabel gameTime;
	JLabel gameGym;
	JLabel gameAudience;
	
	
	
	Vector<String> columnsName = new Vector<String>();
	Vector rowimage = new Vector();
	DefaultTableModel liveModel = new DefaultTableModel(rowimage, columnsName);
	MyTable liveTable = new MyTable(liveModel);
	JScrollPane jScrollPane_live = new JScrollPane(liveTable);
	DefaultTableModel dataModel1 = new DefaultTableModel(rowimage, columnsName);
	MyTable dataTable1 = new MyTable(dataModel1);
	JScrollPane jScrollPane_data1 = new JScrollPane(dataTable1);
	DefaultTableModel dataModel2 = new DefaultTableModel(rowimage, columnsName);
	MyTable dataTable2 = new MyTable(dataModel2);
	JScrollPane jScrollPane_data2 = new JScrollPane(dataTable2);
	
	DefaultTableModel pointModel = new DefaultTableModel(rowimage, columnsName);
	MyTable pointsTable = new MyTable(pointModel);
	{
		pointsTable.setRowHeight(40);
		pointsTable.getTableHeader().setPreferredSize(new Dimension(1,28));;
	}
	JScrollPane jScrollPane_points = new JScrollPane(pointsTable);
	String[] tableHeads = {"一","二","三","四","五","六","七","八","九"};
	
    int matchId;
    Timer updateTimer;
	public LivePanel() {
		this.setBackground(Color.white);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		System.out.println("width : "+FrameSize.width + " height : "+FrameSize.height);
		setText();
		setData();
		setLive();
		initComponent();
		setPoints();
	}
	
	private void initComponent()
	{
		int w = FrameSize.width;
		int h = FrameSize.height;
		Font f1 = new Font("Blackoak Std",Font.BOLD,30);
		Font f2 = new Font("宋体",Font.BOLD,20);
		Font f3 = new Font("宋体",Font.PLAIN,13);
		JLabel guestTeamImage = new JLabel("guestTeamImage");;
		guestTeamImage.setBounds((int)((990-150)/1200.0 * w), 0, (int)(150/1200.0 * w), (int)(150/800.0 * h));
		guestTeamImage.setOpaque(true);
		JLabel guestTeamName = new JLabel("骑士（1）");
		guestTeamName.setBounds((int)((990-300)/1200.0 *w), (int)(75/800.0 * h), (int)(150/1200.0 * w), (int)(37/800.0 * h));
		guestTeamName.setOpaque(true);
		guestTeamName.setFont(f2);
		JLabel guestTeamInfo = new JLabel("客队（53胜29负）");
		guestTeamInfo.setBounds((int)((990-300) / 1200.0 * w),(int) ((75 + 37) / 800.0 * h), (int)(150/1200.0 * w), (int)(37/800.0 * h));
		guestTeamInfo.setOpaque(true);
		guestTeamInfo.setFont(f3);
		JLabel guestTeamScores = new JLabel("100");
		guestTeamScores.setBounds((int)((990-300)/1200.0 * w), 0, (int)(150/800.0 * h), (int)(75/1200.0 * w));
		guestTeamScores.setOpaque(true);
		guestTeamScores.setFont(f1);
		JLabel hostTeamImage = new JLabel("hostTeamImage");
		hostTeamImage.setBounds(0, 0, (int)(150/1200.0 * w), (int)(150/800.0 * h));
		hostTeamImage.setOpaque(true);
		JLabel hostTeamName = new JLabel("勇士（1）");
		hostTeamName.setBounds((int)(150/1200.0 * w), (int)(75/800.0 * h),(int)(150/1200.0 * w), (int)(37/800.0 * h));
		hostTeamName.setOpaque(true);
		hostTeamName.setFont(f2);
		JLabel hostTeamInfo = new JLabel("主队（67胜15负）");
		hostTeamInfo.setBounds((int)(150/1200.0 * w), (int)((75+37)/800.0 * h), (int)(150/1200.0 * w),(int) (37/800.0 * h));
		hostTeamInfo.setOpaque(true);
		hostTeamInfo.setFont(f3);
		JLabel hostTeamScores = new JLabel("108");
		hostTeamScores.setBounds((int)(150/1200.0 * w), 0, (int)(150/1200.0 * w), (int)(75/800.0 * h));
		hostTeamScores.setOpaque(true);
		hostTeamScores.setFont(f1);
		JLabel gameDate = new JLabel("2015年6月5日 9：00");
		gameDate.setBounds((int)(300/1200.0 * w), 0, (int)((390/4+40) / 1200.0 * w),(int)( 37/800.0 * h ));
		gameDate.setOpaque(true);
		gameDate.setFont(f3);
		JLabel gameTime = new JLabel("2：41");
		gameTime.setBounds((int)((300+390/4+40) /1200.0 * w), 0, (int)((390/4-40) / 1200.0 * w), (int)(37/800.0 * h));
		gameTime.setOpaque(true);
		gameTime.setFont(f3);
		JLabel gameGym = new JLabel("甲骨文球馆");
		gameGym.setBounds((int)((300+390/4+390/4)/ 1200.0 * w), 0,(int) (390/4/1200.0 * w), (int)(37/800.0 * h));
		gameGym.setOpaque(true);
		gameGym.setFont(f3);
		JLabel gameAudience = new JLabel("19596人");
		gameAudience.setBounds((int)((300+390/4 * 3)/1200.0 * w), 0, (int)(390/4/1200.0 * w), (int)(37/800.0 * h));
		gameAudience.setOpaque(true);
		gameAudience.setFont(f3);
		 guestTeamImage.setBackground(Color.white);;
		 guestTeamName.setBackground(Color.white);
		 guestTeamInfo.setBackground(Color.white);
		 guestTeamScores.setBackground(Color.white);
		 hostTeamImage.setBackground(Color.white) ;
		 hostTeamName.setBackground(Color.white);
		 hostTeamInfo.setBackground(Color.white);
		 hostTeamScores.setBackground(Color.white);
		 gameDate.setBackground(Color.white);
		 gameTime.setBackground(Color.white);
		 gameGym.setBackground(Color.white);
		 gameAudience.setBackground(Color.white);
		 guestTeamImage.setHorizontalAlignment(JLabel.CENTER);
		 guestTeamName.setHorizontalAlignment(JLabel.CENTER);
		 guestTeamInfo.setHorizontalAlignment(JLabel.CENTER);
		 guestTeamScores.setHorizontalAlignment(JLabel.CENTER);
		 hostTeamImage.setHorizontalAlignment(JLabel.CENTER);
		 hostTeamName.setHorizontalAlignment(JLabel.CENTER);
		 hostTeamInfo.setHorizontalAlignment(JLabel.CENTER);
		 hostTeamScores.setHorizontalAlignment(JLabel.CENTER);
		 gameDate.setHorizontalAlignment(JLabel.CENTER);
		 gameTime.setHorizontalAlignment(JLabel.CENTER);
		 gameGym.setHorizontalAlignment(JLabel.CENTER);
		 gameAudience.setHorizontalAlignment(JLabel.CENTER);
		this.add(guestTeamImage);
		this.add(guestTeamName);
		this.add(guestTeamInfo);
		this.add(guestTeamScores);
		this.add(hostTeamImage);
		this.add(hostTeamName);
		this.add(hostTeamInfo);
		this.add(hostTeamScores);
		this.add(gameDate);
		this.add(gameTime);
		this.add(gameGym);
		this.add(gameAudience);
	}
	
	public void setMatchId(int matchId)
	{
		if (matchId != this.matchId)
		{
		 update(matchId);
		 this.matchId = matchId;
		}
	}
	
	private void update(int matchId)
	{
		if (updateTimer != null)
		{
			updateTimer.cancel();
		}
		updateTimer = new Timer();
		TimerTask task = new TimerTask()
		{

			public void run() {
				try {
					DataFactory factory = (DataFactory) DataFactory.instance();
					MatchDataService matchData = factory.getMatchData();
					CurrentMatch match = matchData.getLiveMatchesById(matchId);
					updateGameInfo(match);
					updateGameTable(match);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		};
	}
    
	private void updateGameInfo(CurrentMatch match)
	{
		  String date = match.getDate();
		  String time = match.getTime();
		  String gym = match.getGym();
		  String audience = match.getAudience();
		  CurrentTeam team1 = match.getTeam1();
		  CurrentTeam team2 = match.getTeam2();
		  String teamName1 = team1.getTeamName();
		  String teamName2 = team2.getTeamName();
		  String totalScores1 = team1.getTotalScores();
		  String totalScores2 = team2.getTotalScores();
		  String teamInfo1 = team1.getDisc();
		  String teamInfo2 = team2.getDisc();
		  String win1 = team1.getWin();
		  String win2 = team2.getWin();
		  Image teamImg1 = team1.getImg();
		  Image teamImg2 = team2.getImg();
		  hostTeamImage.setIcon(new ImageIcon(teamImg1));;
		  hostTeamName.setText(teamName1+" "+win1);;
		  hostTeamInfo.setText(teamInfo1);;
		  hostTeamScores.setText(totalScores1);;
		  guestTeamImage.setIcon(new ImageIcon(teamImg2));;
		 guestTeamName.setText(teamName2+" "+win2);;
		 guestTeamInfo.setText(teamInfo2);
		 guestTeamScores.setText(totalScores2);;
		 gameDate.setText(date);;
		 gameTime.setText(time);;
		 gameGym.setText(gym);;
		 gameAudience.setText(audience);
		  
	}
	int pointsSize = 0;
	private void updateGameTable(CurrentMatch match)
	{
		CurrentTeam team1 = match.getTeam1();
		CurrentTeam team2 = match.getTeam2();
		String[] pts1 = team1.getPoints();
		String[] pts2 = team2.getPoints();
		String totalScores1 = team1.getTotalScores();
		String totalScores2 = team2.getTotalScores();
		int ptsSize = pts1.length;
		if (ptsSize != pointsSize)
		{
			columnsName.removeAllElements();
			columnsName.add(" ");
			for (int i = 0; i < ptsSize; ++i)
			{
				columnsName.add(tableHeads[i]);
			}
			columnsName.add("总分");
			rowimage.clear();
			Vector t = new Vector();
			t.add(team1.getTeamName());
			for (int i = 0; i < pts1.length; ++i)
			{
				t.add(pts1[i]);
			}
			t.add(totalScores1);
			rowimage.add(t);
			t.clear();
			t.add(team2.getTeamName());
			for (int i = 0; i < pts2.length; ++i)
			{
				t.add(pts1[i]);
			}
			t.add(totalScores2);
			rowimage.add(t);
			pointModel.setDataVector(rowimage, columnsName);
			pointsSize = ptsSize;
		}
		else 
		{
			pointModel.setValueAt(team1.getTeamName(), 0, 0);
			for (int i = 0; i < pts1.length; ++i)
			{
				pointModel.setValueAt(pts1[i], 0, 1+i);
			}
			pointModel.setValueAt(totalScores1, 0, 1+pts1.length);
			
			pointModel.setValueAt(team2.getTeamName(), 1, 0);
			for (int i = 0; i < pts1.length; ++i)
			{
				pointModel.setValueAt(pts2[i], 0, 1+i);
			}
			pointModel.setValueAt(totalScores1, 0, 1+pts1.length);
		}
	}
	
	void setText() {
		livebutton = new MyToggleButton(new ImageIcon("image/live1.png"),
				Color.white, Color.white);
		databutton = new MyToggleButton(new ImageIcon("image/data1.png"),
				Color.white, Color.white);
		livebutton.addActionListener(e -> showLive());
		databutton.addActionListener(e -> showData());
		livebutton.setSelectedIcon(new ImageIcon("image/live2.png"));
		databutton.setSelectedIcon(new ImageIcon("image/data2.png"));
		livebutton.setBounds(FrameSize.width * 5 / 6, 10, 100, 40);
		databutton.setBounds(FrameSize.width * 11 / 12, 10, 100, 40);
		this.add(livebutton);
		this.add(databutton);

		jScrollPane_live
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_live
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_live.setBounds(0, FrameSize.height/5, FrameSize.width,
				FrameSize.height * 27 / 40 );
		jScrollPane_live.setBackground(Color.white);
		jScrollPane_live.getViewport().setOpaque(false);
		this.add(jScrollPane_live);
        jScrollPane_live.setVisible(true);
		jScrollPane_data1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_data1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_data1.setBounds(0, FrameSize.height/5, FrameSize.width,
				FrameSize.height * 27 / 40  / 2);
		jScrollPane_data1.setBackground(Color.white);
		jScrollPane_data1.getViewport().setOpaque(false);
		this.add(jScrollPane_data1);
		jScrollPane_data1.setVisible(false);
				
				jScrollPane_data2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_data2
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_data2.setBounds(0, FrameSize.height/5 + FrameSize.height * 27 / 40 / 2 , FrameSize.width,
				FrameSize.height * 27 / 40 / 2 );
		jScrollPane_data2.setBackground(Color.white);
		jScrollPane_data2.getViewport().setOpaque(false);
		this.add(jScrollPane_data2);
		
		jScrollPane_data2.setVisible(false);
		jScrollPane_data2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_points
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		int w = FrameSize.width;
		int h = FrameSize.height;
		jScrollPane_points.setBounds((int)(300/1200.0 * w), (int)(37/800.0 * h), (int)(390/1200.0 * w),
				 (int)((150-37)/800.0 * h));
		jScrollPane_points.setBackground(Color.BLACK);
		jScrollPane_points.getViewport().setOpaque(false);
		this.add(jScrollPane_points);
		this.repaint();
	}

	void setLive() {
		livebutton.setSelected(true);
		databutton.setSelected(false);
		columnsName.removeAllElements();
		columnsName.add("时间");
		columnsName.add("球队");
		columnsName.add("事件");
		columnsName.add("比分");
		rowimage = new Vector();
		liveModel.setDataVector(rowimage, columnsName);
		this.repaint();

	}

	void setData() {
		columnsName.removeAllElements();
        columnsName.add("球队");
		columnsName.add("首发");
		columnsName.add("时间");
		columnsName.add("投篮");
		columnsName.add("三分");
		columnsName.add("罚球");
		columnsName.add("前场");
		columnsName.add("后场");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("犯规");
		columnsName.add("抢断");
		columnsName.add("封盖");
		columnsName.add("得分");
		columnsName.add("+/-");
		rowimage = new Vector();
	    dataModel1.setDataVector(rowimage, columnsName);
	    dataModel2.setDataVector(rowimage, columnsName);
		this.repaint();
	}
	void showLive()
	{
		livebutton.setSelected(true);
		databutton.setSelected(false);
		this.jScrollPane_data1.setVisible(false);
		this.jScrollPane_data2.setVisible(false);
		this.jScrollPane_live.setVisible(true);
	}
	void setPoints()
	{
		columnsName.removeAllElements();
        columnsName.add(" ");
		columnsName.add("一");
		columnsName.add("二");
		columnsName.add("三");
		columnsName.add("四");
		columnsName.add("总分");
		rowimage = new Vector();
		Vector v  = new Vector();
		v.add("骑士");
		v.add("23");
		v.add("23");
		v.add("23");
		v.add("24");
		v.add("95");
		rowimage.add(v);
		v = new Vector();
		v.add("勇士");
		v.add("23");
		v.add("23");
		v.add("23");
		v.add("24");
		v.add("93");
		rowimage.add(v);
		pointModel.setDataVector(rowimage, columnsName);
	}
	void showData()
	{
		livebutton.setSelected(false);
		databutton.setSelected(true);
		this.jScrollPane_data1.setVisible(true);
		this.jScrollPane_data2.setVisible(true);
		this.jScrollPane_live.setVisible(false);
	}
	int count;
}
