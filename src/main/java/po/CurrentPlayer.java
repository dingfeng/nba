package po;

import java.io.Serializable;
import java.util.Arrays;

/**
 * ClassName : CurrentPlayer
 * discription: 用于存储直播球员对象
 * @author FD
 *
 */

public class CurrentPlayer implements Serializable{
	  String name;    //姓名
      String position;//位置
      String time;    //时间
      String shot;    //投篮
      String threeShot; //三分
      String penalty;   //罚球
      String offenseRebs; //前场篮板
      String defenceRebs; //后场篮板
      String rebs;        //篮板
      String assist;      //助攻
      String fouls;       //犯规
      String steals;      //抢断
      String mistakes;    //失误
      String block;       //盖帽 
      String scores;      //得分
      String efficiency;  //效率值
      String[] datas;
    public CurrentPlayer(String[] datas1)
    {
    	name = datas1[0];
    	position = datas1[1];
    	time = datas1[2];
    	shot = datas1[3];
    	threeShot = datas1[4];
    	penalty = datas1[5];
    	offenseRebs = datas1[6];
    	defenceRebs = datas1[7];
    	rebs = datas1[8];
    	assist = datas1[9];
    	fouls = datas1[10];
    	steals = datas1[11];
    	mistakes = datas1[12];
    	block = datas1[13];
    	scores = datas1[14];
    	efficiency = datas1[15];
    	datas = datas1;
    }
    public String toString()
    {
    	return Arrays.toString(datas);
    }
    /**
     * 获得所有数据组成的数组
     * @return    所有数据组成的数组
     */
    public String[] getDatas()
    {
    	return datas;
    }
    /**
     * 获得球员名
     * @return   球员名
     */
	public String getName() {
		return name;
	}
	/**
	 * 获得球员位置
	 * @return   球员位置
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 获得球员在场时间
	 * @return   球员在场时间
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 获得投篮数
	 * @return   投篮数
	 */
	public String getShot() {
		return shot;
	}
	/**
	 * 获得三分球数
	 * @return  三分球数
	 */
	public String getThreeShot() {
		return threeShot;
	}
	/**
	 * 获得罚球数
	 * @return  罚球数
	 */
	public String getPenalty() {
		return penalty;
	}
	/**
	 * 获得进攻篮板数
	 * @return   进攻篮板数
	 */
	public String getOffenseRebs() {
		return offenseRebs;
	}
	/**
	 * 获得防守篮板数
	 * @return  防守篮板数
	 */
	public String getDefenceRebs() {
		return defenceRebs;
	}
	/**
	 * 获得篮板数
	 * @return   篮板数
	 */
	public String getRebs() {
		return rebs;
	}
	/**
	 * 获得助攻数
	 * @return  助攻数
	 */
	public String getAssist() {
		return assist;
	}
	/**
	 * 获得犯规数
	 * @return  犯规数
	 */
	public String getFouls() {
		return fouls;
	}
	/**
	 * 获得助攻数
	 * @return  助攻数
	 */
	public String getSteals() {
		return steals;
	}
	/**
	 * 获得失误数
	 * @return 失误数
	 */
	public String getMistakes() {
		return mistakes;
	}
	/**
	 * 获得盖帽数
	 * @return   盖帽数
	 */
	public String getBlock() {
		return block;
	}
	/**
	 * 获得得分
	 * @return   得分
	 */
	public String getScores() {
		return scores;
	}
	/**
	 * 获得效率
	 * @return  效率
	 */
	public String getEfficiency() {
		return efficiency;
	}
      
    
}
