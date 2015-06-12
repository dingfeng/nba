package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import tool.ImageTool;

public class HotPlayerTeam implements Serializable{
	byte[] action;
	String name;
	double hotData;
	
	public HotPlayerTeam(Image action, String name, double hotData) {
		super();
		this.action = ImageTool.imageToBytes_player(action, "png", BufferedImage.TYPE_INT_ARGB);
		this.name = name;
		this.hotData = hotData;
	}
	
	/**
	 * 获得热点球队图片<br/>
	 * @return          热点球队图片
	 */
	public Image getAction() {
		return ImageTool.bytesToImage(action);
	}
	/**
	 * 获得热点球队名字<br/>
	 * @return           热点球队名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 获得热点数据<br/>
	 * @return      热点数据  
	 */
	public double getHotData() {
		return hotData;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name+" ");
		sb.append(hotData+" ");
		return sb.toString();
	}
}
