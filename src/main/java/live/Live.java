package live;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Live
{
	String url = "http://g.hupu.com/nba/";
	String liveUrl;
	String dataUrl;
	String imgUrl1;
	String imgUrl2;
	static Image img1;
	static Image img2;
	String team1;
	String team2;
	String totalPoints1;
	String totalPoints2;
	String p1[];
	String p2[];
	CurrentPlayer first1[];
	CurrentPlayer first2[];
	CurrentPlayer bench1[];
	CurrentPlayer bench2[];
	String disc1;
	String disc2;
	String win1;
	String win2;
	public Live()
	{
		Date date = new Date();
		SimpleDateFormat format  = null;
		int year = date.getYear() + 1900;
		int month = date.getMinutes();
		int day = date.getDay();
		String dateStr = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
		url += dateStr;
	}
	public  CurrentMatch getCurrentMatch(String url1)
	{
		dealWithIndex(url1);
		
		
		return null;
	}
	public void dealWithMesage()
	{
		
	}
	public void dealWithData()
	{
		
	}
	
	public  void dealWithIndex(String url0)
	{
		Iterator<String> itr = WebTool.getWebCon(url0);
		String line = null;
		boolean list_box = false;
		boolean tableData = false;
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		while (itr.hasNext())
		{
			line = itr.next();
			if (line.contains("<s></s>数据统计</a>"))
			{
				dataUrl = getPref(line);
			}
			if (line.contains("<s></s>文字实录</a>"))
			{
				liveUrl = getPref(line);
			}
			if (line.contains("list_box"))
			{
				list_box = true;
			}
			if (line.contains("img src=") && list_box && imgUrl1 != null && imgUrl2 == null)
			{
				imgUrl2 = getImgUrl(line);
			}
			if (line.contains("img src=") && list_box && imgUrl1 == null)
			{
				imgUrl1 = getImgUrl(line);
			}
			
			if (line.contains("table_data"))
			{
				 tableData = tableData ? false : true;
			}
			if (tableData)
			{
				if (line.contains("/a")&&team1 != null)
				{
					team2 = getTeamName(line);
				}
				if (line.contains("/a") && team1 == null)
				{
					team1 = getTeamName(line);
				}
				
				if (line.contains("</td>"))
				{
					String temp = getPoint(line);
					print(temp);
					if (temp != null)
					{
					 if (team2 ==  null)
					 {
						list1.add(temp);
					 }
					 else 
					 {
						list2.add(temp);
				   	 }
					}
				}
			}
		}
		totalPoints1 = list1.get(list1.size()-1);
		totalPoints2 = list2.get(list2.size()-1);
		p1 = new String[list1.size() -1];
		p2 = new String[list2.size()-1];
		for (int i = 0; i < p1.length; i++)
		{
			p1[i] = list1.get(1);
			p2[i] = list2.get(2);
		}
		try {
			img1 = ImageIO.read(new URL(imgUrl1));
			img2 = ImageIO.read(new URL(imgUrl2));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getPoint(String line)
	{
		Pattern p = Pattern.compile("<td>(\\d+)</td>");
		Matcher m = p.matcher(line);
		String result = null;
		if (m.find())
		{
			result = m.group(1);
		}
		return result;
	}
	
	public static String getTeamName(String line)
	{
		Pattern p = Pattern.compile("\\s+(.*?)\\s+</a>");
		Matcher m = p.matcher(line);
		String result = null;
		if (m.find())
		{
			result = m.group(1);
		}
		return result;
	}
	public static  String getPref(String line)
	{
		Pattern p = Pattern.compile("/nba.*?\\.html");
		Matcher m = p.matcher(line);
		String result = null;
		if (m.find())
	       result = m.group();
	    return "http://g.hupu.com/"+result;
	}
	public static String getImgUrl(String line)
	{
		String result = null;
		Pattern p = Pattern.compile("(http.*?)\\\"");
		Matcher m = p.matcher(line);
		if (m.find())
		{
			result = m.group(1);
		}
		return result;
	}
//	public static void main(String[] args)
//	{
//		Date date = new Date();
//		System.out.println(date.getYear()+1900);
//		System.out.println(date.getMonth());
//		System.out.println(date.getDay());
//	}
	public static void main(String[] args) throws MalformedURLException, IOException
	{
//		String s = "                        <td>顶</td>";
//		print(getPoint(s));
		new Live().dealWithIndex("http://g.hupu.com/nba/2015-5-21");
	}
	
	public static Image toImage(String url1) throws Exception
	{
		URL r = new URL(url1);
		return ImageIO.read(r);
	}
        
	public static void print(String line) 
	{
		System.out.println(line);
	}
}
