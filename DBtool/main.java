package DBtool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class main {
   public static void main(String[] args) throws Exception
   {
	   String usr = "root";
	   String password = "root";
	   String url = "jdbc:mysql://127.0.0.1:3306/nba";
	   String driver = "com.mysql.jdbc.Driver";
	   Class.forName(driver);
  	   Connection conn = DriverManager.getConnection(url,"root","");
  	   String sql = "select * from match_player where match_id = 1850048";
  	   PreparedStatement statement  = conn.prepareStatement(sql);
  	   ResultSet result = statement.executeQuery();
  	   int i = 0;
  	   while (result.next())
  	   {
  		   i++;
  		   System.out.println(result.getInt(1));
  	   }
   }
}
