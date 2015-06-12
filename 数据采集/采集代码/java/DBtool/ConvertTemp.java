package DBtool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertTemp {
	public static void main(String[] args) throws Exception
	{
		BufferedWriter logw = new BufferedWriter(new FileWriter("F:/seasons/log.txt",true));
		ConvertTemp temp = new ConvertTemp();
		File file = new File("k:/temp/");
		File[] files = file.listFiles();
		for (File f : files)
		{
		try{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
		ArrayList<String> content = new ArrayList<String>();
		String s = null;
		while ((s = br.readLine())!= null)
		{
		content.add(s);	
		}
		br.close();
		String[] contentArray = new String[content.size()]; 
		content.toArray(contentArray);
		String result[] = temp.convert(contentArray);
		String season = contentArray[0];
		String dir = "K:/temp/"+season;
		File dirFile = new File(dir);
		if (!dirFile.exists())
			dirFile.mkdirs();
		BufferedWriter bw = new BufferedWriter(new FileWriter(dir+"/"+result[1]));
		bw.write(result[0]);
		bw.close();
		}catch (Exception e)
		{
			System.out.println(f.getName());
			e.printStackTrace();
			logw.append(f.getName());
			logw.newLine();
		}
		}
		logw.close();
	}
      public String[] convert(String[] inStrs)
      {
    	  StringBuilder sb = new StringBuilder();
    	  String[] player = new String[18];
    	  int player_len = 0;
    	  int count = 0;
    	  String season = inStrs[0];
    	  String ptop = inStrs[1];
    	  String mon = inStrs[2];
    	  String day = inStrs[3];
    	  if (mon.length() == 3)
    	  {
    		  mon = mon.substring(1, 3);
    	  }
    	  if (day.length() == 3)
    		  day = day.substring(1, 3);
    	  String[] team1  = new String[2]; 
    	  int len = 0;
    	  ArrayList<String> qs = new ArrayList<String>(10);
    	  int m = 0;
    	  boolean first = true;
          for (int i = 4;i < inStrs.length; i++)
          {
        	  m = i;
        	  String s = inStrs[i];
        	  if (s.equals("team"))
        	  {
        		  team1[len++] = inStrs[++i];
        		  if (len == 2)
        			  break;
        	  }
        	  else if (s.equals("q"))
        	  {
        		  if (first)
        		  {
        		  ++i;
        		  qs.add(inStrs[++i]);
        		  if (isNumeric(inStrs[i+1]))
        		  {
        			  break;
        		  }
        		  first = false;
        		  }
        		  else
        		  {
        			  qs.add(inStrs[i-1]);
        			  i += 2;
        		  }
        	  }
          }
          
    	  sb.append(mon+"-"+day+";");
          sb.append(team1[0]+"-"+team1[1]+";");
          sb.append(ptop+";");
          sb.append("\n");
          int qsLen = qs.size();
          int halflen = qsLen/2;
          for (int i = 0;i  < halflen ;i++)
          {
        	  String q1 = qs.get(i);
        	  String q2 = qs.get(i+halflen);
        	  sb.append(q1+"-"+q2+";");
          }
          sb.append("\n");
                   
    	  for (int i = m+2; i < inStrs.length;i++)
    	  {
    		  String s = inStrs[i];
    		  if (s.equals("team"))
    		  {
    			  sb.append(inStrs[++i]+"\n");
    			  count = 0;
    		  }
    		  else if (s.equals("player"))
    		  {
				 String name=inStrs[i+1];// 球员名称
				 String location = "";//位置
				 if (count < 5)
				 {
					 location = "n";
				 }
				 String time= inStrs[i+3]+":"+"00";// 在场时间
				 String hitNo = inStrs[i+5]; // 投篮命中数
				 String handNo = inStrs[i+6]; // 投篮出手次数
				 String threeHitNo = inStrs[i+8]; // 三分命中数
				 String threeHandNo = inStrs[i+9]; // 三分出手数
				 String penaltyHitNo = inStrs[i+11]; // 罚球命中数
				 String penaltyHandNo = inStrs[i+12]; // 罚球出手数
				 String offenseRebs = inStrs[i+15]; // 进攻篮板数
				 String defenceRebs = inStrs[i+16]; // 防守篮板数
				 String rebs = inStrs[14+i]; // 篮板数
				 String help = inStrs[17+i];//总篮板数
				 String stealsNo = inStrs[18+i];// 抢断数
				 String blockNo = inStrs[19+i];// 盖帽数
				 String mistakesNo = inStrs[20+i];// 失误数
				 String foulsNo = inStrs[21+i];// 犯规数
				 String points1 = inStrs[22+i];// 得分
				 sb.append(name+";");
				 sb.append(location+";");
				 sb.append(time+";");
				 sb.append(hitNo+";");
				 sb.append(handNo+";");
				 sb.append(threeHitNo+";");
				 sb.append(threeHandNo+";");
				 sb.append(penaltyHitNo+";");
				 sb.append(penaltyHandNo+";");
				 sb.append(offenseRebs+";");
				 sb.append(defenceRebs+";");
				 sb.append(rebs+";");
				 sb.append(help+";");
				 sb.append(stealsNo+";");
				 sb.append(blockNo+";");
				 sb.append(mistakesNo+";");
				 sb.append(foulsNo+";");
				 sb.append(points1+";");
				 sb.append("\n");
				 ++count;
				 i += 22;
		     }
    		  
    	  }
    	  String[] result = new String[2];
    	  result[0] = sb.toString();
    	  result[1] = season+"_"+mon+"-"+day+"_"+team1[0]+"-"+team1[1];
    	  return result;
      }
      public static void print(String s)
      {
    	  System.out.println(s);
      }
      public boolean isNumeric(String str)
      {
    	  Pattern pattern = Pattern.compile("[0-9]*"); 
    	   Matcher isNum = pattern.matcher(str);
    	   if( !isNum.matches() ){
    	       return false; 
    	   } 
    	   return true; 
      }
      
}
