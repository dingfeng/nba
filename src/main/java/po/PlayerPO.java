package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import tool.ImageTool;


public class PlayerPO implements Comparable<PlayerPO>, Serializable{

	private String name;// 姓名
	private int number;// 球衣号码
	private String position;// 位置
	private int heightfeet;// 身高的英尺
	private int heightinch;// 身高的英寸
	private int weight;// 体重（磅）
	private String birth;// 生日
	private int age;// 年龄
	private int exp;// 球龄
	private String school;// 毕业学校
	private String teama;//球队缩写
	private String gameArea;//赛区
    //private String teamnameAbridge; //球队
	
	public PlayerPO( String name, int number,
			String position, int heightfeet, int heightinch, int weight,
			String birth, int age, int exp, String school,String teama, String gameArea) {
		super();
		this.name = name;
		this.number = number;
		this.position = position;
		this.heightfeet = heightfeet;
		this.heightinch = heightinch;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
		this.teama = teama;
		this.gameArea = gameArea;
	}

//	public String getTeamnameAbridge()
//	{
//		return teamnameAbridge;
//	}
	
	public String getTeamA()
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

	public int getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public int getHeightfeet() {
		return heightfeet;
	}

	public int getHeightinch() {
		return heightinch;
	}

	public int getWeight() {
		return weight;
	}

	public String getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public int getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name+" ");
		sb.append(position+" ");
		sb.append(heightfeet+" ");
		sb.append(heightinch+" ");
		sb.append(weight+" ");
		sb.append(birth+" ");
		sb.append(age+" ");
		sb.append(school+" ");
		sb.append(teama +" ");
		sb.append(gameArea+" ");
		return sb.toString();
	}

	@Override
	public int compareTo(PlayerPO e) {
		return name.compareTo(e.getName());
	}
}
