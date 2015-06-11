package po;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

import tool.ImageTool;

public class CurrentTeam implements Serializable{
	   String teamName;       //球队名称
       CurrentPlayer[] firsts; //首发
       CurrentPlayer[] benches; //替补
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
       String shotRate;     //投篮命中率
       String threeRate;    //三分命中率
       String penaltyRate;  //罚球命中率
       String primaryDatas[];
       String rates[];
       String totalScores;
       String points[];
       String disc;
       String win;
       byte[] img = null;
       public CurrentTeam(CurrentPlayer[] firsts1,CurrentPlayer[] benches1,String[] primaryDatas1,String[] rates1
    		  ,String totalScores,String points[] ,String teamName,Image img,String disc,String win)
       {
    	   this.disc = disc;
    	   this.win = win;
    	   this.firsts = firsts1;
    	   this.benches = benches1;
    	   shot = primaryDatas1[0];
    	   threeShot = primaryDatas1[1];
    	   penalty = primaryDatas1[2];
    	   offenseRebs = primaryDatas1[3];
    	   defenceRebs = primaryDatas1[4];
    	   rebs = primaryDatas1[5];
    	   assist = primaryDatas1[6];
    	   fouls = primaryDatas1[7];
    	   steals = primaryDatas1[8];
    	   mistakes = primaryDatas1[9];
    	   block = primaryDatas1[10];
    	   scores = primaryDatas1[11];
    	   shotRate = rates1[0];
    	   threeRate = rates1[1];
    	   penaltyRate = rates1[2];
    	   primaryDatas = primaryDatas1;
    	   rates = rates1;
    	   this.teamName = teamName;
    	   this.totalScores = totalScores;
    	   this.points = points;
    	   if (img != null)
    	   this.img = ImageTool.imageToBytes_player(img, "png", BufferedImage.TYPE_INT_ARGB);
       }
       public String toString()
       {
    	   StringBuilder sb = new StringBuilder();
    	   sb.append("teamName : "+teamName+"\n");
    	   sb.append("first : "+Arrays.toString(firsts)+" \n ");
    	   sb.append("benches : "+Arrays.toString(benches)+" \n");
    	   sb.append("primaryDatas : "+Arrays.toString(primaryDatas)+" \n");
    	   sb.append("rates : "+Arrays.toString(rates)+"\n");
    	   sb.append("points : "+Arrays.toString(points)+"\n");
    	   return sb.toString();
       }
    /**
     * 获得图片
     * @return  图片
     */
   public Image getImg()
   {
	   if (img!=null)
	   return  ImageTool.bytesToImage(img);
	   else return null;
   }
   /**
    * 获得描述
    * @return  描述
    */
   public String getDisc() 
   {
	return disc;
   }
   /**
    * 获得胜利场数
    * @return   胜利场数
    */
	public String getWin() {
		return win;
	}
	/**
	 * 获得小比分
	 * @return  小比分
	 */
	public String[] getPoints()
       {
    	   return points;
       }
	/**
	 * 设置小比分数量
	 * @param size 小比分数量
	 */
	public void changePoints(int size)
	{
		String[] pts = new String[size];
		for (int i = 0; i < size; ++i)
		{
			pts[i] = points[i];
		}
		points = pts;
	}
	/**
	 * 获得总得分
	 * @return  总得分
	 */
      public String getTotalScores()
      {
    	  return totalScores;
      }
      /**
       * 获得球队名
       * @return  球队名
       */
     public String getTeamName()
     {
    	 return teamName;
     }
     /**
      * 获得首发球员
      * @return  首发球员
      */
	public CurrentPlayer[] getFirsts() {
		return firsts;
	}
	/**
	 * 获得替补球员
	 * @return  替补球员
	 */
	public CurrentPlayer[] getBenches() {
		return benches;
	}
	/**
	 * 获得投篮数
	 * @return  投篮数
	 */
	public String getShot() {
		return shot;
	}
	/**
	 * 获得三分楸树
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
	 * @return 防守篮板数
	 */
	public String getDefenceRebs() {
		return defenceRebs;
	}
	/**
	 * 获得篮板数
	 * @return  篮板数
	 */
	public String getRebs() {
		return rebs;
	}
	/**
	 * 获得助攻数
	 * @return 助攻数
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
	 * 获得抢断数
	 * @return 抢断数
	 */
	public String getSteals() {
		return steals;
	}
	/**
	 * 获得失误数
	 * @return  失误数
	 */
	public String getMistakes() {
		return mistakes;
	}
	/**
	 * 获得盖帽数
	 * @return  盖帽数
	 */
	public String getBlock() {
		return block;
	}
	/**
	 * 获得得分
	 * @return 得分
	 */
	public String getScores() {
		return scores;
	}
	/**
	 * 获得投篮命中率
	 * @return  投篮命中率
	 */
	public String getShotRate() {
		return shotRate;
	}
	/**
	 * 获得三分球命中率
	 * @return  三分球命中率
	 */
	public String getThreeRate() {
		return threeRate;
	}
	/**
	 * 获得罚球命中率
	 * @return  罚球命中率
	 */
	public String getPenaltyRate() {
		return penaltyRate;
	}
	/**
	 * 获得基本数据
	 * @return  基本数据
	 */
	public String[] getPrimaryDatas() {
		return primaryDatas;
	}
	/**
	 * 获得命中率
	 * @return 命中率
	 */
	public String[] getRates() {
		return rates;
	}
       
}
