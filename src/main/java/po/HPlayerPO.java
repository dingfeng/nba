package po;

import java.awt.Image;

public class HPlayerPO {
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
   Image image;
   public HPlayerPO(String name, String totalName, String position, String height,
		String weight, String birthday, String birthCity, String high_school,
		String university, String num, String teama, String gameArea,Image image) {
	super();
	this.image = image;
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
public Image getImage()
{
	return image;
}
public String getTeama()
{
	return teama;
}
public String getGameArea()
{
	return gameArea;
}
public String getName() {
	return name;
}
public String getTotalName() {
	return totalName;
}
public String getPosition() {
	return position;
}
public String getHeight() {
	return height;
}
public String getWeight() {
	return weight;
}
public String getBirthday() {
	return birthday;
}
public String getBirthCity() {
	return birthCity;
}
public String getHigh_school() {
	return high_school;
}
public String getUniversity() {
	return university;
}
public String getNum() {
	return num;
}

public String toString()
   {
	   return name;
   }
}
