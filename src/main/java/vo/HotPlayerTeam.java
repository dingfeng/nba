package vo;

import java.awt.Image;

public class HotPlayerTeam {
	Image action;
	String name;
	double hotData;
	
	public HotPlayerTeam(Image action, String name, double hotData) {
		super();
		this.action = action;
		this.name = name;
		this.hotData = hotData;
	}
	
	public Image getAction() {
		return action;
	}
	
	public String getName() {
		return name;
	}
	
	public double getHotData() {
		return hotData;
	}
}
