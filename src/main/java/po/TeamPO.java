package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import tool.ImageTool;


public class TeamPO implements Comparable<TeamPO>, Serializable{
	private byte[] image; // 队伍图标
	private String name; // 队伍名称
	private String nameAbridge; // 名称缩写
	private String address; // 所在地
	private String matchArea; // 赛
	private String playerArea;// 分区
	private String manage; // 主场
	private int foundYear; // 建立时间

	public String toString()
	{
	   return name+" "+nameAbridge+" "+address+" "+matchArea+" "+manage+" "+foundYear;
	}
	public TeamPO(Image image, String name, String nameAbridge, String address,
			String matchArea, String playerArea, String manage, int foundYear) {
		super();
		if (image != null)
		this.image = ImageTool.imageToBytes_player(image, "png", BufferedImage.TYPE_INT_ARGB);
		this.name = name;
		this.nameAbridge = nameAbridge;
		this.address = address;
		this.playerArea = playerArea;
		this.manage = manage;
		this.foundYear = foundYear;
		this.matchArea = matchArea;
	}
   /**
    * 获得图片
    * @return 图片
    */
	public Image getImage() {
		if (image != null)
		return ImageTool.bytesToImage(image);
		else return null;
	}
/**
 * 获得球队名字
 * @return 球队名字
 */
	public String getName() {
		return name;
	}
/**
 * 获得球队缩写名
 * @return  球队缩写名
 */
	public String getNameAbridge() {
		return nameAbridge;
	}
/**
 * 获得地址
 * @return 地址
 */
	public String getAddress() {
		return address;
	}
/**
 * 获得联盟
 * @return 联盟
 */
	public String getMatchArea() {
		return matchArea;
	}
/**
 * 获得赛区
 * @return 赛区
 */
	public String getPlayerArea() {
		return playerArea;
	}
/**
 * 获得主场
 * @return 主场
 */
	public String getManage() {
		return manage;
	}
/**
 * 获得建立年份
 * @return   建立年份
 */
	public int getFoundYear() {
		return foundYear;
	}
	/**
	 * 比较
	 */
	@Override
	public int compareTo(TeamPO o) {
		return name.compareTo(o.getName());
	}
}
