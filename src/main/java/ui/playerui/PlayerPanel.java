package ui.playerui;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bl.teambl.TeamController;
import ui.mainui.CharacterButton;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;

public class PlayerPanel extends JPanel{
		
	public PlayerPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width,FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		
	}
	
	private static JPanel HeaderPanel(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0,0,FrameSize.width,60);
		headerPanel.setBackground(new Color(87,89,91));
		
		//根据首字母查找球员
		CharacterButton [] character= new CharacterButton[27];
		character[0]=new CharacterButton("全部");
		for(int i=1;i<27;i++){
			character[i]=new CharacterButton((char)('A'+i-1));
		}
		for(int i=0;i<27;i++){
			character[i].setLocation(30*i,0);
			headerPanel.add(character[i]);
		}
		
		//根据姓名查找球员
		EditableTextField playerNameTextField = new EditableTextField("根据球员姓名查找");
		playerNameTextField.setBackground(new Color(69,69,69));
		playerNameTextField.setForeground(Color.white);
		playerNameTextField.setBounds(27*30+50,0,150,30);
		playerNameTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				playerNameTextField.setText("");
			}
		});
		headerPanel.add(playerNameTextField);
		
		//根据球队查找球员
		String team[] = new String[30];//所有球队名
		MyComboBox findPlayerAccordingTeam = new MyComboBox("根据球队查找",team);
		findPlayerAccordingTeam.setBounds(0,30,150,30);
		headerPanel.add(findPlayerAccordingTeam);
		
		//根据球员位置筛选
		MyComboBox screenPlayerAccordingLocation = new MyComboBox("根据球员位置筛选",new String[]{"F","C","G"});
		screenPlayerAccordingLocation.setBounds(300,30,150,30);
		headerPanel.add(screenPlayerAccordingLocation);
		
		//根据球员联盟筛选
		MyComboBox screenPlayerAccordingZone = new MyComboBox("根据赛区筛选",new String[]{"E-ATLANTIC","E-CENTRAL","E-SOUTHEAST","W-PACIFIC","W-SOUTHWEST","W-SOUTHEAST"});
		screenPlayerAccordingLocation.setBounds(500,30,150,30);
		headerPanel.add(screenPlayerAccordingZone);
		
		//根据排序依据筛选
		return headerPanel;
	}
}
