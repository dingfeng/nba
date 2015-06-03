package live;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Live
{
	String url = "http://g.hupu.com/nba/";
	String liveUrl;
	String dataUrl;
	String imgUrl1;
	String imgUrl2;
	String team1;
	String team2;
	String totalPoints1;
	String totalPoints2;
	String p1[];
	String p2[];
	boolean list_box = false;
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
		Iterator<String> itr = WebTool.getWebCon(url1);
		while (itr.hasNext())
		{
			System.out.println(itr.next());
		}
		return null;
	}
	
	private void dealWithIndex(String url0)
	{
		Iterator<String> itr = WebTool.getWebCon(url0);
		String line = null;
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
			if (line.contains("img") && list_box && imgUrl1 == null)
			{
				
			}
			if (line.contains("img") && list_box && imgUrl2 == null)
			{
				
			}
		}
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
		Pattern p = Pattern.compile("\\\"\\(http.*?)\\\"");
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
	public static void main(String[] args)
	{
//		Live live = new Live();
//		String s = getPref("                            <a  target='_self' href=\"/nba/daily/boxscore_150105.html\" class=\"d \"><s></s>数据统计</a>");
//		print(s);
		print()
	}
	public static void print(String line)
	{
		System.out.println(line);
	}
}
