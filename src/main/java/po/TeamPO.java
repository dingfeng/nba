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
	private String matchArea; // 赛区
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
		this.matchArea = getMatchArea(playerArea);
	}

	public Image getImage() {
		if (image != null)
		return ImageTool.bytesToImage(image);
		else return null;
	}

	public String getName() {
		return name;
	}

	public String getNameAbridge() {
		return nameAbridge;
	}

	public String getAddress() {
		return address;
	}

	public String getMatchArea() {
		return matchArea;
	}

	public String getPlayerArea() {
		return playerArea;
	}

	public String getManage() {
		return manage;
	}

	public int getFoundYear() {
		return foundYear;
	}
	@Override
	public int compareTo(TeamPO o) {
		return name.compareTo(o.getName());
	}
	
	 public static  String getMatchArea(String playerArea)
	 {
		 String result = null;
		 switch (playerArea)
		 {
		 case "Central":
			 result = "E";
			 break;
		 case "Atlantic":
			 result  = "E";
			 break;
	     case "Pacific":
	    	 result = "W";
	    	 break;
		 case "Southwest":
			 result = "W";
			 break;
		 case "Southeast":
			 result = "E";
			 break;
		 case "Northwest":
			 result = "W";
			 break;
		 }
		 return result;
	 }
}
