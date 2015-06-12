package ui.playerui;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dataservice.playerdataservice.PlayerDataService;
import po.HPlayerPO;
import po.PlayerPO;
import DataFactory.DataFactory;
import bl.playerbl.PlayerController;
import bl.teambl.TeamController;
import blservice.playerblservice.PlayerBlService;
import ui.mainui.CharacterButton;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;


public class ShowAllPlayerPanel extends JPanel {

	
	DataFactory dataFactory = new DataFactory();
	PlayerDataService playerService = dataFactory.getPlayerData();
	
	PlayerBlService playerController = new PlayerController();
	TeamController teamController = new TeamController();
	DefaultTableModel allPlayerTable = new DefaultTableModel();
	MyTable myAllPlayerTable = new MyTable(allPlayerTable);
	JScrollPane jAllPlayerScrollPane = new JScrollPane(myAllPlayerTable);
	
	Vector columnsName = new Vector();
	Vector data = new Vector();


	
	public ShowAllPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		try {
			setNowPlayerTable(playerService.fuzzilySearchAvtivePlayerPO("A"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 查找栏 */
	private JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		// 根据球队查找球员
		MyComboBox newOrOld = new MyComboBox(new String[]{"现役","退役"});
		newOrOld.setBounds(0,5,100,30);
		headerPanel.add(newOrOld);
		
		// 根据首字母查找球员
		CharacterButton[] character = new CharacterButton[26];
		for (int i = 0; i < 26; i++) {
			character[i] = new CharacterButton((char) ('A' + i));
		}
		for (int i = 0; i < 26; i++) {
			character[i].setBounds(100+30 * i, 5,30,30);
			headerPanel.add(character[i]);
		}
		
		character[0].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"A"));
		character[1].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"B"));
		character[2].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"C"));
		character[3].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"D"));
		character[4].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"E"));
		character[5].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"F"));
		character[6].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"G"));
		character[7].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"H"));
		character[8].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"I"));
		character[9].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"J"));
		character[10].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"K"));
		character[11].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"L"));
		character[12].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"M"));
		character[13].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"N"));
		character[14].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"O"));
		character[15].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"P"));
		character[16].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"Q"));
		character[17].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"R"));
		character[18].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"S"));
		character[19].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"T"));
		character[20].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"U"));
		character[21].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"V"));
		character[22].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"W"));
		character[23].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"X"));
		character[24].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"Y"));
		character[25].addActionListener(e->setTable((String)newOrOld.getSelectedItem(),"Z"));


		// 根据姓名查找球员
		JTextField playerNameTextField = new EditableTextField();
		playerNameTextField.setText("按姓名查找");
		playerNameTextField.setBackground(new Color(69, 69, 69));
		playerNameTextField.setForeground(Color.white);
		playerNameTextField.setBounds(100+26 * 30+5, 5,
				(FrameSize.width - 26 * 30-100) / 2 - 10, 30);
		playerNameTextField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					PlayerPO [] player = new PlayerPO[1];
					player[0] = playerController.getplayerPObyName(playerNameTextField.getText());
					setNowPlayerTable(player);
				}
			}
		});
//		playerNameTextField.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				playerNameTextField.setText("");
//			}
//		});
		headerPanel.add(playerNameTextField);

		// 根据球队查找球员
		String team[] = teamController.getTeamNames();
		MyComboBox findPlayerAccordingTeam = new MyComboBox("按球队查找", team);
		findPlayerAccordingTeam.setBounds(
				100+26 * 30+5+(FrameSize.width - 26 * 30-100) / 2 - 10+5, 5, (FrameSize.width - 26 * 30-100) / 2 - 10, 30);
		findPlayerAccordingTeam.addActionListener(e->setNowPlayerTable(playerController.getPlayerOfTeam((String)findPlayerAccordingTeam.getSelectedItem())));
		headerPanel.add(findPlayerAccordingTeam);

		return headerPanel;
	}

	private void setTable(String playerType ,String playerStart){
		if(playerType.endsWith("退役")){
			setOldPlayerTable(playerController.getPlayersWithStart(2014, playerStart));
		}
		else{
			try {
				setNowPlayerTable(playerService.fuzzilySearchAvtivePlayerPO(playerStart));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**显示现役球员的基本信息*/
	private void setNowPlayerTable(PlayerPO[] playerVOs){
		if (playerVOs != null) {
			columnsName.removeAllElements();
//			columnsName.add(" ");
//			/*01球员图片*/columnsName.add("球员");
			/*02姓名*/columnsName.add("姓名");
			/*03球队*/columnsName.add("球队");
			/*04位置*/columnsName.add("位置");
			/*05身高*/columnsName.add("身高");
			/*06体重*/columnsName.add("体重");
			/*07学校*/columnsName.add("学校");
			/*09生日*/columnsName.add("生日");
			/*10年龄*/columnsName.add("年龄");
			/*11球龄*/columnsName.add("球龄");
			/*12赛区*/columnsName.add("赛区");
			

			data.clear();
			for (int i = 0; i < playerVOs.length; i++) {
				Vector rowData = new Vector();
//				rowData.add(i+1);
//				/*01球员图片*/rowData.add(playerController.getPlayerImage(playerVOs[i].getName()));
				/*02姓名*/rowData.add(playerVOs[i].getName());
				/*03球队*/rowData.add(playerVOs[i].getTeamA());
				/*04位置*/rowData.add(playerVOs[i].getPosition());
				/*05身高*/rowData.add(playerVOs[i].getHeightfeet()+'-'+playerVOs[i].getHeightinch());
				/*06体重*/rowData.add(playerVOs[i].getWeight());
				/*07学校*/rowData.add(playerVOs[i].getSchool());
				/*09生日*/rowData.add(playerVOs[i].getBirth());
				/*10年龄*/rowData.add(playerVOs[i].getAge());
				/*11球龄*/rowData.add(playerVOs[i].getExp());
				/*12赛区*/rowData.add(playerVOs[i].getGameArea());
				data.add(rowData);
			}
			allPlayerTable = new DefaultTableModel(data, columnsName);
			myAllPlayerTable = new MyTable(allPlayerTable);
			jAllPlayerScrollPane = new JScrollPane(myAllPlayerTable);
			jAllPlayerScrollPane.setBounds(0, 40, FrameSize.width,
					FrameSize.width * 7 / 8 - 40);
			this.add(jAllPlayerScrollPane);
			this.repaint();
			myAllPlayerTable.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						try {
							MyFrame.onePlayerPanel.showOne((String) myAllPlayerTable.getModel().getValueAt(
									myAllPlayerTable.getSelectedRow(), 0));
							MyFrame.card.show(MyFrame.mainpanel, "oneplayer");
						} catch (NullPointerException e1) {
						
						}
					}
				}

			});
		}
	}
	
	/** 显示退役球员的基本信息 */
	private void setOldPlayerTable(HPlayerPO[] playerVOs) {
		if (playerVOs != null) {
			columnsName.removeAllElements();
//			columnsName.add(" ");
//			/*01球员图片*/columnsName.add("球员");
			/*02姓名*/columnsName.add("姓名");
			/*03球队*/columnsName.add("球队");
			/*04位置*/columnsName.add("位置");
			/*05身高*/columnsName.add("身高");
			/*06体重*/columnsName.add("体重");
			/*07学校*/columnsName.add("学校");
			/*08城市*/columnsName.add("城市");
			/*09生日*/columnsName.add("生日");

			data.clear();
			for (int i = 0; i < playerVOs.length; i++) {
				Vector rowData = new Vector();
//				rowData.add(i+1);
//				/*01球员图片*/rowData.add(playerController.getPlayerImage(playerVOs[i].getName()));
				/*02姓名*/rowData.add(playerVOs[i].getName());
				/*03球队*/rowData.add(playerVOs[i].getTeama());
				/*04位置*/rowData.add(playerVOs[i].getPosition());
				/*05身高*/rowData.add(playerVOs[i].getHeight());
				/*06体重*/rowData.add(playerVOs[i].getWeight());
				/*07学校*/rowData.add(playerVOs[i].getHigh_school());
				/*08城市*/rowData.add(playerVOs[i].getBirthCity());
				/*09生日*/rowData.add(playerVOs[i].getBirthday());

				data.add(rowData);
			}
			allPlayerTable = new DefaultTableModel(data, columnsName);
			myAllPlayerTable = new MyTable(allPlayerTable);
			jAllPlayerScrollPane = new JScrollPane(myAllPlayerTable);
			jAllPlayerScrollPane.setBounds(0, 40, FrameSize.width,
					FrameSize.width * 7 / 8 - 40);
			this.add(jAllPlayerScrollPane);
			this.repaint();
			myAllPlayerTable.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						try {
							
						} catch (NullPointerException e1) {
						
						}
					}
				}

			});
		}
	}

}
