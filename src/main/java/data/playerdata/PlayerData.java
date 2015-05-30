package data.playerdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.PlayerHighPO;
import po.PlayerNormalPO;
import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.SeasonType;

public class PlayerData implements PlayerDataService{
    private Connection conn;
	public PlayerData(Connection conn)
	{
		this.conn = conn;
	}
	@Override
	public PlayerPO[] getAllPlayerData() {
		String sql = "select * from player";
		ArrayList<PlayerPO> list = new ArrayList<PlayerPO>(3500);
		PlayerPO[] players = null;
		try
		{
		  PreparedStatement statement = conn.prepareStatement(sql);
		  ResultSet results = statement.executeQuery();
		   Image action = null;// 大头图片
		   Image portrait = null;// 全身图片
		   String name = null;// 姓名
		   int number = -1;// 球衣号码
		   String position = null;// 位置
		   int heightfeet = -1;// 身高的英尺
		   int heightinch = -1;// 身高的英寸
		   int weight = -1;// 体重（磅）
		   String birth = null;// 生日
		   int age = -1;// 年龄
		   int exp = -1;// 球龄
		   String school = null;// 毕业学校
		   Blob blob =null;
		   PlayerPO player = null;
		  while (results.next())
		  {
			blob  = results.getBlob("photo_action");
			action = blobToImage(blob);
			blob = results.getBlob("photo_portrait");
			portrait = blobToImage(blob);
			name = results.getString("player_name");
			number = results.getInt("num");
			position = results.getString("position");
			heightfeet = results.getInt("heightfeet");
			heightinch = results.getInt("heightinch");
			weight = results.getInt("weight");
			birth = results.getString("birth");
			age = results.getInt("age");
			exp = results.getInt("exp");
			school = results.getString("school");
			player = new PlayerPO( action,  portrait,  name,  number,
					 position,  heightfeet,  heightinch,  weight,
					 birth,  age,  exp,  school);
			list.add(player);
		  }
		  list.toArray(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}

	 private Image blobToImage(Blob blob)
	 {
		 if (blob == null)
			 return null;
		 byte[] bytes = null;
		 try {
			bytes = blob.getBytes(0,(int)blob.length());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return Toolkit.getDefaultToolkit().createImage(bytes);
	 }
	@Override
	public PlayerPO findPlayer(String playerName) {
		return null;
	}
	@Override
	public PlayerHighPO getPlayerHigh(int season, String playerName, SeasonType type) {
		return null;
	}
	@Override
	public PlayerHighPO[] sortPlayerHighn(int season, String sort, int n,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO getPlayerNormalTotal(int season, String playerName,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO[] sortPlayerNormalTotal(int season, String sort,
			int n, SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO getPlayerNormalAve(int season, String playerName,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO sortPlayerNormalAven(int season, String sort, int n,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsTotal(String playerName,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerNormalPO[] getPlayerAllSeasonsAve(String playerName,
			SeasonType type) {
		return null;
	}
	@Override
	public PlayerHighPO[] getPlayerAllSeasons(String playerName, SeasonType type) {
		return null;
	}
  
}
