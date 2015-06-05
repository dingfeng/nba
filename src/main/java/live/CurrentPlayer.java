package live;

import java.util.Arrays;

public class CurrentPlayer {
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
    public String[] getDatas()
    {
    	return datas;
    }
	public String getName() {
		return name;
	}
	public String getPosition() {
		return position;
	}
	public String getTime() {
		return time;
	}
	public String getShot() {
		return shot;
	}
	public String getThreeShot() {
		return threeShot;
	}
	public String getPenalty() {
		return penalty;
	}
	public String getOffenseRebs() {
		return offenseRebs;
	}
	public String getDefenceRebs() {
		return defenceRebs;
	}
	public String getRebs() {
		return rebs;
	}
	public String getAssist() {
		return assist;
	}
	public String getFouls() {
		return fouls;
	}
	public String getSteals() {
		return steals;
	}
	public String getMistakes() {
		return mistakes;
	}
	public String getBlock() {
		return block;
	}
	public String getScores() {
		return scores;
	}
	public String getEfficiency() {
		return efficiency;
	}
      
    
}
