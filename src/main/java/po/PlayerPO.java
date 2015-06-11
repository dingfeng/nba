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
	/**
	 * 获得球队
	 * @return  球队
	 */
	public String getTeamA()
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
     * @return  名字
     */
	public String getName() {
		return name;
	}
    /**
     * 获得球衣号
     * @return  球衣号
     */
	public int getNumber() {
		return number;
	}
    /**
     * 获得位置
     * @return  位置
     */
	public String getPosition() {
		return position;
	}
    /**
     * 获得身高（英尺）
     * @return  身高（英尺）
     */
	public int getHeightfeet() {
		return heightfeet;
	}
    /**
     * 获得身高(英寸)
     * @return  身高（英寸）
     */
	public int getHeightinch() {
		return heightinch;
	}
    /**
     * 获得体重
     * @return  体重
     */
	public int getWeight() {
		return weight;
	}
    /**
     * 获得生日
     * @return  生日
     */
	public String getBirth() {
		return birth;
	}
    /**
     * 获得年龄
     * @return  年龄
     */
	public int getAge() {
		return age;
	}
    /**
     * 获得球龄
     * @return  球龄
     */
	public int getExp() {
		return exp;
	}
   /**
    * 获得学校
    * @return  学校
    */
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
   /**
    * 比较
    */
	@Override
	public int compareTo(PlayerPO e) {
		return name.compareTo(e.getName());
	}
}
