package ui.teamui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ui.mainui.FrameSize;
import ui.mainui.MyButton;

public class TeamPanelTry extends JPanel {

	public TeamPanelTry() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		setText();
	}

	public void setText() {
		MyButton jb1 = new MyButton("数据", FrameSize.bluecolor, FrameSize.lightbluecolor);
		jb1.setBounds(0, FrameSize.height / 4, FrameSize.width/4, 50);
	
		
		
		MyButton jb2 = new MyButton("比赛",FrameSize.bluecolor, FrameSize.lightbluecolor);
		jb2.setBounds(FrameSize.width / 4, FrameSize.height / 4, FrameSize.width/4, 50);
		
		
		MyButton jb3 = new MyButton("",FrameSize.bluecolor, FrameSize.lightbluecolor);
		jb3.setBounds(FrameSize.width / 2, FrameSize.height / 4, FrameSize.width/4, 50);
		
		MyButton jb4 = new MyButton("",FrameSize.bluecolor, FrameSize.lightbluecolor);
		jb4.setBounds(FrameSize.width *3/ 4, FrameSize.height / 4, FrameSize.width/4, 50);
		
		JPopupMenu datatype = new JPopupMenu();
		JMenuItem jmi1 = new JMenuItem("基本数据");
		jmi1.setBackground(FrameSize.bluecolor);
		jmi1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.print("?");
			}

		});
		datatype.add(jmi1);
		datatype.add(new JMenuItem("高阶数据"));
		datatype.setBackground(FrameSize.bluecolor);

		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				datatype.show(e.getComponent(), 0, 50);
			}

		});

		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
	}
}
