package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import tool.ImageTool;
public class HPlayerPO implements Serializable{
   String name;
   String totalName;
   String position;
   String height;
   String weight;
   String birthday;
   String birthCity;
   String high_school;
   String university;
   String num;
   String teama;
   String gameArea;
   byte[] image;
//   Image image;
   public HPlayerPO(String name, String totalName, String position, String height,
		String weight, String birthday, String birthCity, String high_school,
		String university, String num, String teama, String gameArea,Image image) {
	super();
	if (image != null)
	this.image = ImageTool.imageToBytes_player(image, "png",BufferedImage.TYPE_INT_ARGB);
//	this.image = image;
	this.teama = teama;
	this.gameArea = gameArea;
	this.name = name;
	this.totalName = totalName;
	this.position = position;
	this.height = height;
	this.weight = weight;
	this.birthday = birthday;
	this.birthCity = birthCity;
	this.high_school = high_school;
	this.university = university;
	this.num = num;
}
   /**
    * 获得图片
    * @return 图片
    */
public Image getImage()
{
	if (image != null)
	return ImageTool.bytesToImage(image);
	else return null;
	//	return image;
}
/**
 * 获得球队缩写
 * @return  球队缩写
 */
public String getTeama()
{
	return teama;
}
/**
 * 获得联盟
 * @return  联盟
 */
public String getGameArea()
{
	return gameArea;
}
/**
 * 获得名字
 * @return 名字
 */
public String getName() {
	return name;
}
/**
 * 获得全名
 * @return 全名
 */
public String getTotalName() {
	return totalName;
}
/**
 * 获得位置
 * @return 位置
 */
public String getPosition() {
	return position;
}
/**
 * 获得身高
 * @return  身高
 */
public String getHeight() {
	return height;
}
/**
 * 获得体重
 * @return 体重
 */
public String getWeight() {
	return weight;
}
/**
 * 获得生日
 * @return 生日
 */
public String getBirthday() {
	return birthday;
}
/**
 * 获得出生地
 * @return 出生地
 */
public String getBirthCity() {
	return birthCity;
}
/**
 * 获得高中
 * @return  高中
 */
public String getHigh_school() {
	return high_school;
}
/**
 * 获得大学
 * @return 大学
 */
public String getUniversity() {
	return university;
}
/**
 * 获得球衣号
 * @return  球衣号
 */
public String getNum() {
	return num;
}

public String toString()
   {
	   return name;
   }
}
