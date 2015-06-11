package ui.playerui;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import po.HPlayerPO;
import po.PlayerPO;
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

	
	PlayerBlService playerController = new PlayerController();
	DefaultTableModel allPlayerTable = new DefaultTableModel();
	MyTable myAllPlayerTable = new MyTable(allPlayerTable);
	JScrollPane jAllPlayerScrollPane = new JScrollPane(myAllPlayerTable);

	JPanel onePlayerPanel = new JPanel();
	
	public ShowAllPlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		setAllPlayerTable(playerController.getPlayersWithStart(2014, "A"));
	}

	/** 查找栏 */
	private static JPanel HeaderPanel() {
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBounds(0, 0, FrameSize.width, 40);
		headerPanel.setBackground(new Color(87, 89, 91));

		// 根据首字母查找球员
		CharacterButton[] character = new CharacterButton[27];
		character[0] = new CharacterButton("全部");
		for (int i = 1; i < 27; i++) {
			character[i] = new CharacterButton((char) ('A' + i - 1));
		}
		for (int i = 0; i < 27; i++) {
			character[i].setBounds(30 * i, 5,30,30);
			headerPanel.add(character[i]);
			character[i].addActionListener(e->);
		}

		// 根据姓名查找球员
		EditableTextField playerNameTextField = new EditableTextField();
		playerNameTextField.setText("按姓名查找");
		playerNameTextField.setBackground(new Color(69, 69, 69));
		playerNameTextField.setForeground(Color.white);
		playerNameTextField.setBounds(27 * 30, 5,
				(FrameSize.width - 27 * 30) / 2 - 10, 30);
		playerNameTextField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				playerNameTextField.setText("");
			}
		});
		headerPanel.add(playerNameTextField);

		// 根据球队查找球员
		String team[] = new String[30];// 所有球队名
		MyComboBox findPlayerAccordingTeam = new MyComboBox("按球队查找", team);
		findPlayerAccordingTeam.setBounds(
				27 * 30 + (FrameSize.width - 27 * 30) / 2, 5, FrameSize.width
						- (27 * 30 + (FrameSize.width - 27 * 30) / 2), 30);
		headerPanel.add(findPlayerAccordingTeam);

		return headerPanel;
	}

	/** 显示查找到的球员的基本信息 */
	private void setAllPlayerTable(HPlayerPO[] playerVOs) {
		if (playerVOs != null) {
			Vector columnsName = new Vector();
//			columnsName.add(" ");
			/*01球员图片*/columnsName.add("球员");
			/*02姓名*/columnsName.add("姓名");
			/*03球队*/columnsName.add("球队");
			/*04位置*/columnsName.add("位置");
			/*05身高*/columnsName.add("身高");
			/*06体重*/columnsName.add("体重");
			/*07学校*/columnsName.add("学校");
			/*08城市*/columnsName.add("城市");
			/*09生日*/columnsName.add("生日");
			/*10球衣*/columnsName.add("球衣");

			Vector data = new Vector();
			for (int i = 0; i < playerVOs.length; i++) {
				Vector rowData = new Vector();
//				rowData.add(i+1);
				/*01球员图片*/rowData.add(playerController.getPlayerImage(playerVOs[i].getName()));
				/*02姓名*/rowData.add(playerVOs[i].getName());
				/*03球队*/rowData.add(playerVOs[i].getTeama());
				/*04位置*/rowData.add(playerVOs[i].getPosition());
				/*05身高*/rowData.add(playerVOs[i].getHeight());
				/*06体重*/rowData.add(playerVOs[i].getWeight());
				/*07学校*/rowData.add(playerVOs[i].getHigh_school());
				/*08城市*/rowData.add(playerVOs[i].getBirthCity());
				/*09生日*/rowData.add(playerVOs[i].getBirthday());
				/*10球衣*/rowData.add(playerVOs[i].getNum());

				System.out.println(playerVOs[i].getNum());
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

	/**显示单个球员的所有信息*/
	private void setOnePlayerPanel(){
		onePlayerPanel.removeAll();
		onePlayerPanel.setBackground(Color.red);
		onePlayerPanel.setBounds(0,0,FrameSize.width,FrameSize.height*7/8-40);
		this.add(onePlayerPanel);
	}
}
