package DBtool;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

public class StoreOldMatch {
	 String usr = "root";
	 String password = "root";
	 String url = "jdbc:mysql://127.0.0.1:3306/nba";
	 String driver = "com.mysql.jdbc.Driver";
	 Connection conn;
	 public StoreOldMatch()
	 {
		 try {
				Class.forName(driver);
			    conn = DriverManager.getConnection(url,"root","");
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 public void store()
	 {
		 File root = new File("H:/regularGame");
		 File[] seasonFiles = root.listFiles();
		 for (File f : seasonFiles)
		 {
			 new myThread(f).start();;
		 }
		 root = new File("H:/playoffGame");
		 seasonFiles = root.listFiles();
		 for (File f : seasonFiles)
		 {
			 new myThread(f).start();
		 }
	 }
class myThread extends Thread
	 {
	     File file;
	     int matchIdBase;
	     int add = 0;
		 public myThread(File file)
		 {
			 this.file = file;
			 String parent = file.getParentFile().getName();
			 String filename = file.getName();
			 int matchId = Integer.parseInt(filename);
			 matchId = matchId - 1900;
			 if (parent.contains("playoff"))
			 {
				 matchId += 100;
			 }
			 matchId += 100;
			 matchId  *= 10000;
			 matchIdBase = matchId;
		 }
		 public void run()
		 {
			 storeMatch();
		 }
		 private void  storeMatch()
		 {
			 try {
				File[] files = file.listFiles();
				Arrays.sort(files, new Comparator<File>(){

					@Override
					public int compare(File o1, File o2) {
						long lastm1 = o1.lastModified();
						long lastm2 = o2.lastModified();
						if (lastm2 > lastm1)
						{
							return -1;
						}
						else if (lastm2 < lastm1)
						{
							return 1;
						}
						else 
						{
							return 0;
						}
					}
					
				});
//				ArrayList<OldMatch> matches = new ArrayList<OldMatch>();
				OldMatch match = null;
				for (File f: files)
					if (!f.getName().equals("image"))
				        {
						match = readOneMatch(f.getName());
						storeIntoDB(match);
				        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 private void storeIntoDB(OldMatch match)
		 {
			   int matchId = match.getMatchId();
		       String hostTeam = match.getHost_team();
		       String guestTeam = match.getGuestTeam();
		       String date = match.getDate();
		       String pt1[] = match.getPt1();
		       String pt2[] = match.getPt2();
		       String temps[] = new String[pt1.length-1];
		       String totalScores = null;
		       Image img = match.getImg();
		       String sql = "insert into oldmatch(match_id,guestTeam,hostTeam) values(?,?,?)";
		       String sqlimg = "insert into oldmatchimg(matchId,infoimg) values(?,?)";
		       try
		       {
//		    	  PreparedStatement statement = conn.prepareStatement(sql); 
//		    	  statement.setInt(1, matchId);
//		    	  statement.setString(2, guestTeam);
//		    	  statement.setString(3, hostTeam);
//		    	  statement.execute();
//		    	  statement = conn.prepareStatement(sqlimg);
//		    	  statement.setInt(1, matchId);
//		    	  Store.outImageTodb(img, statement, 2);
//		    	  statement.execute();
//		    	  for (int i = 0; i < temps.length; ++i)
//		    	  {
//		    		  temps[i] = pt1[i];
//		    	  }
//		    	  totalScores = pt1[pt1.length-1];
//		    	  storeTeam(matchId,hostTeam,totalScores,temps);
//		    	  for (int i = 0; i < temps.length; ++i)
//		    	  {
//		    		  temps[i] = pt2[i];
//		    	  }
//		    	  totalScores = pt2[pt2.length-1];
//		    	  storeTeam(matchId,guestTeam,totalScores,temps);
		    	  insertDate(matchId,date);
		       }
		       catch (Exception e)
		       {
		    	   e.printStackTrace();
		       }
		       System.gc();
		 }
		 private void insertDate(int matchId, String date)
		 {
			 String sql = "update oldmatch set date = ? where match_id = ?";
			 try
			 {
				 PreparedStatement statement = conn.prepareStatement(sql);
				 statement.setString(1, date);
				 statement.setInt(2, matchId);
				 statement.execute();
			 }
			 catch (Exception e)
			 {
				 
			 }
		 }
		 private void storeTeam(int matchId,String teamName,String totalScores,String[] pts)
		 {
			 String sql = "insert into oldmatchteam values(?,?,?,?,?,?,?,?,?,?,?)";
			 try
			 {
				 PreparedStatement statement = conn.prepareStatement(sql);
				 statement.setInt(1, matchId);
				 statement.setString(2, teamName);
				 statement.setString(3, totalScores);
				 for (int i = 0; i < 8; ++i)
				 {
					 if (i < pts.length)
					 statement.setString(i+4, pts[i]);
					 else statement.setString(i+4, null);
				 }
				 statement.execute();
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 
		 }
		 private OldMatch readOneMatch(String gameName) throws Exception
		 {
			 String matchPath = file.getAbsolutePath()+"/"+gameName;
			 String imgPath = file.getAbsolutePath()+"/image/"+gameName+".jpg";
			 BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(matchPath),"UTF-8"));
			 String team1 =null;
			 String team2 = null;
			 ArrayList<String> pt1 = new ArrayList<String>();
			 ArrayList<String> pt2 = new ArrayList<String>();
			 String date = null;
			 String s = null;
			 while ((s=reader.readLine()) != null)
			 {
				 if (date == null)
				 {
					 String[] dates = s.split("-");
					 int day = Integer.parseInt(dates[1]);
					 String dayStr = dates[1];
					 if (day <= 9)
					 {
						 dayStr = "0"+dayStr;
					 }
					 date = dates[0]+"-"+dayStr;
				 }
				 else if (s.equals("team1"))
				 {
					team1 = reader.readLine();
					while ((s=reader.readLine()) != null)
					{
						if (s.equals("team2"))
						{
							break;
						}
						if (!isNum(s))
							s = " ";
						pt1.add(s);
					}
					while ((s=reader.readLine()) != null)
					{
						if (team2 == null)
						{
							team2 = s;
						}
						else 
						{
							if (!isNum(s))
								s = " ";
							pt2.add(s);
						}
					}
					break;
				 }
			 }
			 String[] ptsa1 = new String[pt1.size()];
			 String[] ptsa2 = new String[pt2.size()];
			 pt1.toArray(ptsa1);
			 pt2.toArray(ptsa2);
			 System.out.println(imgPath);
			 Image img = null;
			 try{
			  img = ImageIO.read(new File(imgPath));
			 }catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 OldMatch match = new OldMatch(matchIdBase+(++add),team1,team2,date,img,ptsa1,ptsa2);
			 return match;
		 }
	 }
public static boolean isNum(String str){
	return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
}
    public static void main(String[] args)
    {
    	StoreOldMatch oldMatchStore = new StoreOldMatch();
    	oldMatchStore.store();
    }
}
