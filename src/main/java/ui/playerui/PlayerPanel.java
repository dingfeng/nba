package ui.playerui;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import po.HPlayerPO;
import po.PlayerPO;
import bl.teambl.TeamController;
import ui.mainui.CharacterButton;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;


public class PlayerPanel extends JPanel {

	public PlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel headerPanel = HeaderPanel();
		this.add(headerPanel);
		JScrollPane jScrollPane = TablePanel(null);
		if (jScrollPane != null) {
			this.add(jScrollPane);
		}
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
		}

		// 根据姓名查找球员
		EditableTextField playerNameTextField = new EditableTextField("按姓名查找");
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
	private JScrollPane TablePanel(HPlayerPO[] playerVOs) {
		if (playerVOs != null) {
			Vector columnsName = new Vector();
			columnsName.add(" ");
			columnsName.add("球员");
			columnsName.add("姓名");
			columnsName.add("球队");
			columnsName.add("位置");
			columnsName.add("身高");
			columnsName.add("体重");
			columnsName.add("学校");
			columnsName.add("城市");
			columnsName.add("生日");
			columnsName.add("球衣");

			Vector data = new Vector();
			for (int i = 0; i < playerVOs.length; i++) {
				Vector rowData = new Vector();
				rowData.add(i+1);
				rowData.add("大头照");
				rowData.add(playerVOs[i].getName());
				rowData.add(playerVOs[i].getTeama());
				rowData.add(playerVOs[i].getPosition());
				rowData.add(playerVOs[i].getWeight());
				rowData.add(playerVOs[i].getHigh_school());
				rowData.add(playerVOs[i].getBirthCity());
				rowData.add(playerVOs[i].getBirthday());
				rowData.add(playerVOs[i].getNum());

				data.add(rowData);
			}
			DefaultTableModel table = new DefaultTableModel(data, columnsName);
			MyTable myTable = new MyTable(table);
			JScrollPane jScrollPane = new JScrollPane(myTable);
			jScrollPane.setBounds(0, 60, FrameSize.width,
					FrameSize.width * 7 / 8 - 40);
			return jScrollPane;
		}
		else{
			Vector columnsName = new Vector();
			columnsName.add(" ");
			columnsName.add("球员");
			columnsName.add("姓名");
			columnsName.add("球队");
			columnsName.add("位置");
			columnsName.add("身高");
			columnsName.add("学校");
			columnsName.add("生日");
			columnsName.add("年龄");
			columnsName.add("球龄");

			Vector data = new Vector();
			for (int i = 0; i < 100; i++) {
				Vector rowData = new Vector();
				rowData.add(i);
				rowData.add("辣");
				rowData.add("辣");
				rowData.add("还");
				rowData.add(" ");
				rowData.add("没");
				rowData.add("给");
				rowData.add("俺");
				rowData.add("接");
				rowData.add("口");

				data.add(rowData);
			}
			DefaultTableModel table = new DefaultTableModel(data, columnsName);
			MyTable myTable = new MyTable(table);
			JScrollPane jScrollPane = new JScrollPane(myTable);
			jScrollPane.setBounds(0, 40, FrameSize.width,
					FrameSize.height* 7 / 8 - 40);
			return jScrollPane;
		}
//		return null;

	}
}
