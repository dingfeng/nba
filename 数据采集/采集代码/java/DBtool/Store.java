package DBtool;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Store {
    NBADataFactory factory = DataFactoryImp.instance();
	 String usr = "root";
	 String password = "root";
	 String url = "jdbc:mysql://127.0.0.1:3306/nba";
	 String driver = "com.mysql.jdbc.Driver";
	 BufferedImageBuilder builder = new BufferedImageBuilder();
     public static void  main(String[] args)
      {
    	   Store store = new Store();
    	   try {
			store.storeMatches();
		} catch (Exception e) {
			e.printStackTrace();
		}
      }
     public void storePlayer() throws Exception
     {
    	 System.out.println("player store");
    	 Class.forName(driver);
    	 Connection conn = DriverManager.getConnection(url,"root","");
    	 PlayerDataService playerData = factory.getPlayerData();
    	 PlayerPO[] players = playerData.getAllPlayerData();
    	 String sql = "insert into mplayer(photo_action,photo_portrait,player_name,"
    	 		+ "num,position,heightfeet,heightinch,weight,birth,age,exp,school) "
    	 		+ "values(?,?,?,"
    	 		+ "?,?,?,?,?,?,?,?,?)";
    	 for (PlayerPO p : players)
    	 {
    		  Image action = p.getAction();// 大头图片
			  Image portrait = p.getPortrait();// 全身图片
			  String name = p.getName();// 姓名
			  int number = p.getNumber();// 球衣号码
			  String position = p.getPosition();// 位置
			  int heightfeet = p.getHeightfeet();// 身高的英尺
			  int heightinch = p.getHeightinch();// 身高的英寸
			  int weight = p.getWeight();// 体重（磅）
			 String birth = p.getBirth();// 生日
			 int age = p.getAge();// 年龄
			  int exp = p.getExp();// 球龄
			  String school = p.getSchool();// 毕业学校
    		 PreparedStatement statement = conn.prepareStatement(sql);
    		 outImageTodb(action,statement,1);
    		 outImageTodb(portrait,statement,2);
    		 statement.setString(3, name);
    		 statement.setInt(4, number);
    		 statement.setString(5, position);
    		 statement.setInt(6, heightfeet);
    		 statement.setInt(7, heightinch);
    		 statement.setInt(8, weight);
    		 statement.setString(9, birth);
    		 statement.setInt(10, age);
    		 statement.setInt(11, exp);
    		 statement.setString(12, school);
    		 statement.execute();
    		 System.out.println(name);
    	 }
     }
     
     public void storeTeam() throws Exception
     {
    	 Class.forName(driver);
    	 Connection conn = DriverManager.getConnection(url,"root","");
    	 PlayerDataService playerData = factory.getPlayerData();
    	 PlayerPO[] players = playerData.getAllPlayerData();
    	 String sql = "insert into team(photo,name_total,name_abr,addr,match_area,player_area,manage,found_year)"
    	 		+ "values(?,?,?,?,?,?,?,?)";
    	 	
    	 TeamDataService teamData = factory.getTeamData();
    	 TeamPO[] teams = teamData.getAllTeamData();
    	 for (TeamPO p : teams)
    	 {
    		     Image image = p.getImage(); // 队伍图标
    			 String name = p.getName(); // 队伍名称
    			 String nameAbridge = p.getNameAbridge(); // 名称缩写
    			 String address = p.getAddress(); // 所在地
    			 String matchArea = p.getMatchArea(); // 赛区
    			 String playerArea = p.getPlayerArea();// 分区
    			 String manage = p.getManage(); // 主场
    			 int foundYear = p.getFoundYear(); // 建立时间
    			 PreparedStatement statement = conn.prepareStatement(sql);
    			 outImageTodb(image,statement,1);
    			 statement.setString(2, name);
    			 statement.setString(3, nameAbridge);
    			 statement.setString(4, address);
    			 statement.setString(5, matchArea);
    			 statement.setString(6, playerArea);
    			 statement.setString(7, manage);
    			 statement.setInt(8, foundYear);
    			 statement.execute();
    	 }
    	 conn.close();
     }
     
     
     public void storeMatches() throws Exception
     {
    	 Connection conn = DriverManager.getConnection(url,"root","");
    	 Connection conn1 = DriverManager.getConnection(url,"root","");
    	 Connection conn2 = DriverManager.getConnection(url,"root","");
    	 String playOffRoot = "F:/seasons/";
    	 String regularRoot = "F:/seasons1/seasons/";
    	String[] playOffSeasons = {"00-01","01-02","02-03",
    			"03-04","04-05","05-06","06-07","07-08","08-09","09-10","10-11","11-12","12-13","13-14","14-15",
    			"85-86","86-87","87-88","88-89","89-90","90-91",
    			"91-92","92-93","93-94","94-95","95-96",
    			"96-97","97-98","98-99","99-00"
    			};
    	String[] regularSeasons = {"00-01","01-02","02-03",
    			"03-04","04-05","05-06","06-07","07-08","08-09","09-10","10-11","11-12","12-13","13-14","14-15",
    			"85-86","86-87","87-88","88-89","89-90","90-91",
    			"91-92","92-93","93-94","94-95","95-96",
    			"96-97","97-98","98-99","99-00"
    			};
    	int[] playerOffids = {12000000,12010000,12020000,12030000,12040000,12050000,12060000,12070000,12080000,12090000,12100000,12110000,12120000,12130000,12140000
    			,11850000,11860000,11870000,11880000,11890000,
    			11900000,11910000,11920000,11930000,11940000,11950000,11960000,
    			11970000,11980000,11990000};
    	int[] regularIds = {2000000,2010000,2020000,2030000,2040000,2050000,2060000,2070000,2080000,2090000,2100000,2110000,2120000,2130000,2140000
    			,1850000,1860000,1870000,1880000,1890000,
    			1900000,1910000,1920000,1930000,1940000,1950000,1960000,
    		   1970000,1980000,1990000};
//    	for (int i = 0;i < regularIds.length; ++i)
//    	{
//    		String season = regularRoot+regularSeasons[i];
//    		int id = regularIds[i];
//    		StoreOneSeason(season,id);
//    	}
//    	for (int i = 0;i < playerOffids.length; ++i)
//    	{
//    		String season = playOffRoot+playOffSeasons[i];
//    		int id = playerOffids[i];
//    		StoreOneSeason(season,id);
//    	}
    	StoreOneSeason(regularRoot+"88-89",1880000);

     }
     
     public void StoreOneSeason(String path, int id)
     {
    	 new MyThread(path,id).start();
     }
     
     
     
     private void dealWithPlayers(MatchPlayerPO[] players,String sql,Connection conn, int matchid) throws SQLException
     {
    	 for (int i= 0 ; i < players.length; i++)
    	 {
    		 try{
    		 MatchPlayerPO player = players[i];
    		 insertMatchPlayer(conn.prepareStatement(sql),player,matchid);
    		 }catch (Exception e)
    		 {
    			 
    		 }
    	 }
     }
     
     private void insertMatches(PreparedStatement statement, MatchesPO match,int matchid) throws SQLException
     {
      String date = match.getDate();
      MatchTeamPO team1 = match.getTeam1();
      MatchTeamPO team2 = match.getTeam2();
      String team_guest = team1.getName();
      String team_host = team2.getName();
      statement.setInt(1, matchid);
      statement.setString(2, team_host);
      statement.setString(3, team_guest);
      statement.setString(4, date);
      statement.execute();
     }
     
     private void insertMatchPlayer(PreparedStatement statement, MatchPlayerPO player, int matchid) throws SQLException
     {
    	  String name = player.getName();// 球员名称
    	  String location = player.getLocation();//位置
          int time = (int)player.getTime();// 在场时间
    	  int hitNo = player.getHitNo(); // 投篮命中数
    	  int handNo = player.getHandNo(); // 投篮出手次数
    	  int threeHitNo = player.getThreeHitNo(); // 三分命中数
    		int threeHandNo = player.getThreeHandNo(); // 三分出手数
    		 int penaltyHitNo = player.getPenaltyHitNo(); // 罚球命中数
    		 int penaltyHandNo = player.getPenaltyHandNo(); // 罚球出手数
    		 int offenseRebs = player.getOffenseRebs(); // 进攻篮板数
    		 int defenceRebs = player.getDefenceRebs(); // 防守篮板数
    		 int rebs = player.getRebs(); // 篮板数
    		 int help = player.getHelp();//总篮板数
    		 int stealsNo = player.getStealsNo();// 抢断数
    		 int blockNo = player.getBlockNo();// 盖帽数
    		 int mistakesNo  = player.getMistakesNo();// 失误数
    		 int foulsNo = player.getFoulsNo();// 犯规数
    		 int points = player.getPoints();// 得分
    		 String teamnameAbridge  = player.getTeamnameAbridge(); //球队缩写名
    		 
    		 statement.setInt(1, matchid);
    		 statement.setString(2, name);
    		 statement.setString(3, location);
    		 statement.setInt(4, time);
    		 statement.setInt(5, hitNo);
    		 statement.setInt(6, handNo);
    		 statement.setInt(7, threeHitNo);
    		 statement.setInt(8, threeHandNo);
    		 statement.setInt(9, penaltyHitNo);
    		 statement.setInt(10, penaltyHandNo);
    		 statement.setInt(11, offenseRebs);
    		 statement.setInt(12, defenceRebs);
    		 statement.setInt(13, rebs);
    		 statement.setInt(14, help);
    		 statement.setInt(15, stealsNo);
    		 statement.setInt(16, blockNo);
    		 statement.setInt(17, mistakesNo);
    		 statement.setInt(18, foulsNo);
    		 statement.setInt(19, points);
    		 statement.setString(20, teamnameAbridge);
    		 if (player.isFirst())
    		 {
    			 statement.setInt(21, 1);
    		 }
    		 else 
    		 {
    			 statement.setInt(21, 0);
    		 }
    		 statement.execute();
     }
     
     private void insertMatchTeam(PreparedStatement statement, MatchTeamPO team, int matchid) throws Exception
     {
    	  int[] scores = team.getScores();
          int totalScores = team.getTotalScores();
          String name = team.getName();
          int time = team.getTime();
          statement.setInt(1, matchid);
          statement.setString(2, name);
          statement.setInt(3, totalScores);
          for (int i =0 ; i < 14; i++)
          {
        	  if (i < scores.length)
        	  statement.setInt(i+4, scores[i]);
        	  else 
        	  {
        		  statement.setInt(i+4, 0);
        	  }
          }
          statement.execute();
     }
    public static void outImageTodb(Image image, PreparedStatement statement,int i)
    {
    	  image = new ImageIcon(image).getImage();
    	  BufferedImage buf = null;
    	  try{
    	  buf = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);
    	  }catch (Exception e)
    	  { 
    		  System.out.println("sdf");
    		  try {
				statement.setNull(i, java.sql.Types.BLOB);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		  return;
    				 
    	  }
    	  Graphics2D g = (Graphics2D)buf.createGraphics();  
//    	  g.setColor(new Color(255,255,255));
    	  g.setStroke(new BasicStroke(1));
          g.drawImage(image,0,0,null);  
          
          g.dispose();  
          //然后将BufferedImage写到ByteArrayOutputStream输出流中  
          ByteArrayOutputStream stream = new ByteArrayOutputStream();  
          try {  
              ImageIO.write(buf,"png",stream);  
              //-----------------------  
              //ImageIO.write(buf, "jpg", new FileOutputStream("f:\\12.jpg"));//此句可以将image写到另一个文件里  
              //-----------------------  
          }catch (Exception e) {  
              System.out.println("imageio error!");  
          }  
          //将ByteArrayOutputStream的内容写到ByteArrayInputStream，供sql执行语句写入  
          byte[] bytes = stream.toByteArray();  
          ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
          try {  
              //此处以更改ISBN为1的图书条目的image为例，image为blob类型  
//              String sql = "update book set image = ? where ISBN = '1'";  
              //此处将ByteArrayInputStream内容写入  
              statement.setBinaryStream(i, in, bytes.length);  
          }
          catch (Exception e) {  
              System.out.println("sql error!");  
          }  
    }
    
    class MyThread extends Thread
    {
    	String path;
    	int id;
    	public MyThread(String path, int id)
    	{
    		super();
    		this.path = path;
    		this.id = id;
    	}
    	public void run()
    	{
    		 String[] sqls =
        		 {
        			 "insert into matches(match_id,team_host,team_guest,match_date) "
        			 + "values(?,?,?,?)", 
        			 "insert into match_team(match_id,teama,scores,q1,q2,q3,q4,o1,o2,o3,o4,o5,o6,o7,o8,o9,o10) "
        			 + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ,
        			 "insert into match_player(match_id,player_name,position,courtTime,"
        			 + "hitNo,handNo,threeHitNo,threeHandNo,penaltyHitNo,penaltyHandNo,offenseRebs,defenceRebs,rebs,assist,steal,blockno,mistakeno,fouls,score,teama,first) "
        			 + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        	     }; 
    		try{
    	    Connection conn = DriverManager.getConnection(url,"root","");
    		int i = 0;
        	String path = this.path;
        	int id = this.id;
        	MatchData matchData = new MatchData(path);
        	MatchesPO[] matches = matchData.getAllMatches();
        	for (int j = 0; j < matches.length; j++)
        	{
        	 MatchesPO matchpo = matches[j];
        	 int matchid = id+j+1;
        	 for (int k =0; k < 3; k++)
        	 {
        	 String sql = sqls[k];
        	 
        	 if (k == 0)
        	 {
        	   PreparedStatement statement =  conn.prepareStatement(sql);
              insertMatches(statement,matchpo,matchid);
        	 }
        	 else  if (k == 1)
        	 {
        		 MatchTeamPO team1 = matchpo.getTeam1();
        		 MatchTeamPO team2 = matchpo.getTeam2();
        		 insertMatchTeam(conn.prepareStatement(sql),team1,matchid);
        		 insertMatchTeam(conn.prepareStatement(sql),team2,matchid);
        	 }
        	 else 
        	 {
        		 MatchTeamPO team1 = matchpo.getTeam1();
        		 MatchTeamPO team2 = matchpo.getTeam2();
        		 MatchPlayerPO[] players1 = team1.getPlayers();
        		 MatchPlayerPO[] players2 = team2.getPlayers();
        		 dealWithPlayers(players1,sql,conn,matchid);
        		 dealWithPlayers(players2,sql,conn,matchid);
        	 }
        	 }
        	}
        	conn.close();
        	}catch (Exception e){e.printStackTrace();}
    	}
    }
}
