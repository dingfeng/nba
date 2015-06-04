package live;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


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
	String matchId;
	static ArrayList<String> list = new ArrayList<String>();
	public Live()
	{
		Date date = new Date();
		SimpleDateFormat format  = null;
		int year = date.getYear() + 1900;
		int month = date.getMinutes();
		int day = date.getDay();
		String dateStr = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
		url += dateStr;
		url = "http://g.hupu.com/nba/2015-5-20";
		dealWithIndex(url);
		matchId = getMatchId(dataUrl);
	}
	private   String getMatchId(String u)
	{
		Pattern p = Pattern.compile("_(\\d+)\\.html");
		Matcher m = p.matcher(u);
		if (m.find())
		{
			return m.group(1);
		}
		return null;
	}
	public  CurrentMatch getCurrentMatch(String url1)
	{
		dealWithIndex(url1);
		return null;
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
			if (img1 == null)
			img1 = ImageIO.read(new URL(imgUrl1));
			if (img2 == null)
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
	private ArrayList<String> getMessagesArray()
	{
		String messageUrl = null;
		try {
			messageUrl = getMessageUrl();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		if (messageUrl != null)
		{
			ArrayList<String> list = getMessages(messageUrl);
			return list;
		}
		else 
		{
			return null;
		}
	}
	
	public ArrayList<String> getNewMessages()
	{
		ArrayList<String> newList =  getMessagesArray();
		ArrayList<String> result = new ArrayList<String>();
		int margin = newList.size() - list.size();
		for (int i =0; i < margin; ++i )
		{
			result.add(newList.get(i));
		}
		list = newList;
		return result;
	} 
	
	public ArrayList<String> getAllMessages()
	{
		return list;
	}
	private String getMessageUrl() throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript"); 
		String teamCode1 = (String) engine.eval("encodeURIComponent(\""+team1+"\")");
		String teamCode2 = (String)engine.eval("encodeURIComponent(\""+team2+"\")");
//		"http://g.hupu.com/node/playbyplay/matchLives?sid=-1&s_count=1&match_id=150105&homeTeamName=%E8%80%81%E9%B9%B0&awayTeamName=%E9%AA%91%E5%A3%AB"
		String result = "http://g.hupu.com/node/playbyplay/matchLives?sid=-1&s_count=1&match_id="+matchId+"&homeTeamName="+teamCode1+"&awayTeamName="+teamCode2;
		return result;
	}
	private  ArrayList<String> getMessages(String url)
	{
		Iterator<String> result = WebTool.getWebCon(url);
		String xml = null;
		ArrayList<String> list = new ArrayList<String>();
		while (result.hasNext())
			
		{
			xml = result.next();
		}
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory   
                    .newInstance();   
            DocumentBuilder builder = factory.newDocumentBuilder();   
            org.w3c.dom.Document doc = builder   
                    .parse(new InputSource(new StringReader("<a>"+xml+"</a>")));   
            Element root = doc.getDocumentElement();   
            NodeList books = root.getChildNodes(); 
            showNode(books,null,list);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public static  void showNode(NodeList list,StringBuilder sb,ArrayList<String> messages1)
	{
		for (int i = 0; i < list.getLength();i++)
		{
			Node book = list.item(i);
			if (book.hasChildNodes())
			{
				if (book.getNodeName().equals("tr"))
					{
					sb = new StringBuilder();
					}
				showNode(book.getChildNodes(),sb,messages1);
				if (book.getNodeName().equals("tr"))
				{
				messages1.add(sb.toString());
				}
			}
			else
			{
				sb.append(book.getNodeValue()+" ");
			}
		}
		
	}
	
	private Image toImage(String url1) throws Exception
	{
		URL r = new URL(url1);
		return ImageIO.read(r);
	}
        
	public static void print(String line) 
	{
		System.out.println(line);
	}
	
	
	public static void parseDataXml(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory   
                .newInstance();   
        DocumentBuilder builder = factory.newDocumentBuilder();   
        org.w3c.dom.Document doc = builder   
                .parse(new InputSource(new StringReader("<a>"+xml+"</a>")));   
        Element root = doc.getDocumentElement();   
        NodeList books = root.getChildNodes(); 
        itrXml(books);
	}
	
	public static void itrXml(NodeList nodeList)
	{
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (node.hasChildNodes())
			{
				itrXml(node.getChildNodes());
			}
			else 
			{
				print(node.getNodeName());
			}
		}
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException, ScriptException
	{
//		String s = "                        <td>顶</td>";
//		print(getPoint(s));
//		new Live().dealWithIndex("http://g.hupu.com/nba/2015-5-21");
//		ScriptEngineManager manager = new ScriptEngineManager();   
//		ScriptEngine engine = manager.getEngineByName("javascript"); 
//		String encodedStr = (String) engine.eval("encodeURIComponent(\"骑士\")");
//		System.out.println(encodedStr);
//		Iterator<String> result = WebTool.getWebCon("http://g.hupu.com/node/playbyplay/matchLives?sid=-1&s_count=1&match_id=150105&homeTeamName=%E8%80%81%E9%B9%B0&awayTeamName=%E9%AA%91%E5%A3%AB");
//		String xml = null;
//		while (result.hasNext())
//		{
//			xml = result.next();
//		}
//		try
//		{
//			DocumentBuilderFactory factory = DocumentBuilderFactory   
//                    .newInstance();   
//            DocumentBuilder builder = factory.newDocumentBuilder();   
//            org.w3c.dom.Document doc = builder   
//                    .parse(new InputSource(new StringReader("<a>"+xml+"</a>")));   
//            System.out.println(xml);
//            Element root = doc.getDocumentElement();   
//            NodeList books = root.getChildNodes(); 
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		Live live = new Live();
//		ArrayList<String> ms = live.getNewMessages();
//		for (int i = 0; i < ms.size(); i++)
//		{
//			System.out.println(ms.get(i));
//		}
		Iterator<String> result = WebTool.getWebCon("http://g.hupu.com/nba/daily/boxscore_150079.html");
		StringBuilder sb = new StringBuilder();
		while(result.hasNext())
		{
			sb.append(result.next());
		}
		String xml = sb.toString();
		try {
			parseDataXml(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
