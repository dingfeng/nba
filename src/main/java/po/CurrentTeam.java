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
       
   public Image getImg()
   {
	   if (img!=null)
	   return  ImageTool.bytesToImage(img);
	   else return null;
   }
   public String getDisc() 
   {
	return disc;
   }
	public String getWin() {
		return win;
	}
	public String[] getPoints()
       {
    	   return points;
       }
	public void changePoints(int size)
	{
		String[] pts = new String[size];
		for (int i = 0; i < size; ++i)
		{
			pts[i] = points[i];
		}
		points = pts;
	}
      public String getTotalScores()
      {
    	  return totalScores;
      }
     public String getTeamName()
     {
    	 return teamName;
     }
	public CurrentPlayer[] getFirsts() {
		return firsts;
	}
	public CurrentPlayer[] getBenches() {
		return benches;
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
	public String getShotRate() {
		return shotRate;
	}
	public String getThreeRate() {
		return threeRate;
	}
	public String getPenaltyRate() {
		return penaltyRate;
	}
	public String[] getPrimaryDatas() {
		return primaryDatas;
	}
	public String[] getRates() {
		return rates;
	}
       
}
