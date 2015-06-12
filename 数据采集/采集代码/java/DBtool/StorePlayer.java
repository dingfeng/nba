package DBtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class StorePlayer {
	 String usr = "root";
	 String password = "root";
	 String url = "jdbc:mysql://dingfeng:3306/nba??useUnicode=true&characterEncoding=utf8";
	 String driver = "com.mysql.jdbc.Driver";
	 Connection conn;
	public StorePlayer() 
	{
		try {
			Class.forName(driver);
		    conn = DriverManager.getConnection(url,"myuser","mypassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}
	public void storePlayer()
	{
		ArrayList<String[]> list = getData();
		Iterator<String[]> itr = list.iterator();
		String sql = "insert into hplayerinfo values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement;
		try{
		while (itr.hasNext())
		{
			statement = conn.prepareStatement(sql);
			String[] temp = itr.next();
			int less = temp.length > 7? 7 : temp.length;
			for (int i = 0; i < less;i++)
			{
				statement.setString(1+i, temp[i]);
			}
			if (less < 7)
			{
				for (int i = less; i <=10;i++)
				{
					statement.setString(i, null);
				}
			}
			if (less == 7)
			{
				if (temp.length == 10)
				{
					statement.setString(8, temp[7]);
					statement.setString(9, temp[8]);
					statement.setString(10, temp[9]);
				}
					
				if ( temp.length == 9&&temp[7].contains("University") )
				{
					statement.setString(8, null);
					statement.setString(9, temp[7]);
					statement.setString(10, temp[8]);
				}
				if ( temp.length == 9&&(!temp[7].contains("University")) )
				{
					statement.setString(8, temp[7]);
					statement.setString(9, null);
					statement.setString(10, temp[8]);
				}
				if (temp.length == 8)
				{
					statement.setString(8, null);
					statement.setString(9, null);
					statement.setString(10, temp[7]);
				}
				if (temp.length == 7)
				{
					statement.setString(8, null);
					statement.setString(9, null);
					statement.setString(10, null);
				}
			}
			
			statement.execute();
		}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<String[]> getData()
	{
		ArrayList<String[]> list = new ArrayList<String[]>(4000);
		File file = new File("F:/playerInfo");
		File[] files = file.listFiles();
		for (File f : files)
		{
			try {
				list.add(dealWithOne(f));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	private String[] dealWithOne(File file) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		ArrayList<String> list = new ArrayList<String>();
		String[] strs = null;
		int i = 0;
		String s = null;
		while ((s = reader.readLine()) != null)
		{
			list.add(s); 
		}
		strs = new String[list.size()];
		list.toArray(strs);
		return strs;
		
	}
	public static void main(String[] args)
	{
		StorePlayer store = new StorePlayer();
		store.storePlayer();
	}
}
