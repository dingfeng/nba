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

import bl.teambl.TeamController;
import ui.mainui.CharacterButton;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import vo.PlayerMatchVO;
import vo.PlayerVO;

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
		headerPanel.setBounds(0, 0, FrameSize.width, 30);
		headerPanel.setBackground(new Color(87, 89, 91));

		// 根据首字母查找球员
		CharacterButton[] character = new CharacterButton[27];
		character[0] = new CharacterButton("全部");
		for (int i = 1; i < 27; i++) {
			character[i] = new CharacterButton((char) ('A' + i - 1));
		}
		for (int i = 0; i < 27; i++) {
			character[i].setLocation(30 * i, 0);
			headerPanel.add(character[i]);
		}

		// 根据姓名查找球员
		EditableTextField playerNameTextField = new EditableTextField("按姓名查找");
		playerNameTextField.setBackground(new Color(69, 69, 69));
		playerNameTextField.setForeground(Color.white);
		playerNameTextField.setBounds(27 * 30, 0,
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
				27 * 30 + (FrameSize.width - 27 * 30) / 2, 0, FrameSize.width
						- (27 * 30 + (FrameSize.width - 27 * 30) / 2), 30);
		headerPanel.add(findPlayerAccordingTeam);

		return headerPanel;
	}

	/** 显示查找到的球员的基本信息 */
	private JScrollPane TablePanel(PlayerVO[] playerVOs) {
		if (playerVOs != null) {
			Vector columnsName = new Vector();
			columnsName.add(" ");
			columnsName.add("球员");
			columnsName.add("姓名");
			columnsName.add("球队");
			// columnsName.add("位置");
			columnsName.add("身高");
			columnsName.add("学校");
			columnsName.add("生日");
			columnsName.add("年龄");
			columnsName.add("球龄");

			Vector data = new Vector();
			for (int i = 0; i < playerVOs.length; i++) {
				Vector rowData = new Vector();
				rowData.add(i);
				rowData.add(playerVOs[i].getPortrait());
				rowData.add(playerVOs[i].getName());
				rowData.add(playerVOs[i].getTeam());
				// rowData.add(playerVOs[i].getLocation());
				rowData.add(playerVOs[i].getHeightfeet() + "-"
						+ playerVOs[i].getHeightinch());
				rowData.add(playerVOs[i].getSchool());
				rowData.add(playerVOs[i].getBirth());
				rowData.add(String.valueOf(playerVOs[i].getAge()));
				rowData.add(String.valueOf(playerVOs[i].getExp()));

				data.add(rowData);
			}
			DefaultTableModel table = new DefaultTableModel(data, columnsName);
			MyTable myTable = new MyTable(table);
			JScrollPane jScrollPane = new JScrollPane(myTable);
			jScrollPane.setBounds(0, 30, FrameSize.width,
					FrameSize.width * 7 / 8 - 30);
			return jScrollPane;
		}
		return null;

	}
}
