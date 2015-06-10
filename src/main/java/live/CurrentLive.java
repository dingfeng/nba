package live;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class CurrentLive {
	public static void main(String[] args)
	{
		CurrentLive currentLive = new CurrentLive();
		while (true)
		{
		ArrayList<CurrentMatch> list = currentLive.getAllMatches();
		for (int i = 0;i < list.size();++i)
		{
			print(list.get(i));
		}	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		}
	} 
	public static void print(Object o)
	{
		System.out.println(o.toString());
	}
	public static void print(Object[] os)
	{
		for (int i= 0;i < os.length; ++i)
		{
			System.out.println(os[i]);
		}
	}
    ArrayList<String> dataUrls = new ArrayList<String>();
    ArrayList<String> liveUrls = new ArrayList<String>();
    String indexUrl = "http://g.hupu.com/nba/";
    public CurrentLive()
    {
      init();	
    }
    public void init()
    {
    	Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(date);
		String[] dateArray = dateStr.split("-");
		dateStr = dateArray[0]+"-"+String.valueOf(Integer.parseInt(dateArray[1]))+"-"+String.valueOf(Integer.parseInt(dateArray[2]));
		indexUrl += dateStr;
		print(indexUrl);
//		indexUrl = "http://g.hupu.com/nba/2015-6-8";
		Iterator<String> itr = WebTool.getWebCon(indexUrl);
		initUrls(itr);
    }
    public void initUrls(Iterator<String> xmlItr)
    {
    	String line;
     while (xmlItr.hasNext())
     { 
    	line = xmlItr.next();
    	if (line.contains("<s></s>文字实录</a>") || line.contains("<s></s>文字直播</a>"))
    	{
    		liveUrls.add(getPref(line));
    	}
    	else if (line.contains("<s></s>数据统计</a>")||line.contains("<s></s>数据直播</a>"))
    	{
    		dataUrls.add(getPref(line));
    	}
     }
    }
    public ArrayList<CurrentMatch> getAllMatches()
    {
    	String dataUrl = null;
    	String messUrl = null;
    	ArrayList<CurrentMatch> result = new ArrayList<CurrentMatch>();
    	for (int i = 0;i < dataUrls.size();++i)
    	{
    		dataUrl = dataUrls.get(i);
    		messUrl = liveUrls.get(i);
    		result.add(toCurrentMatch(messUrl,dataUrl));
    	}
    	return result;
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
    private   String getPref(String line)
	{
		Pattern p = Pattern.compile("/nba.*?\\.html");
		Matcher m = p.matcher(line);
		String result = null;
		if (m.find())
	       result = m.group();
	    return "http://g.hupu.com/"+result;
	}
    
    
    public CurrentMatch toCurrentMatch(String liveUrl,String dataUrl)
    {
//    	String liveContent = null;
//    	if (livexml.hasNext())
//    	{
//    		liveContent = livexml.next();
//    	}
//    	ArrayList<String> messageList = getMessages(liveContent);
    	ArrayList<String> messageList = null;
    	String matchId = getMatchId(liveUrl);
    	Iterator<String> dataXml = WebTool.getWebCon(dataUrl);
    	String s = null;
    	ArrayList<String> points1 = new ArrayList<String>();
    	ArrayList<String> points2 = new ArrayList<String>();
    	CurrentPlayer[] playersf1 = null;
    	CurrentPlayer[] playerst1 = null;
    	CurrentPlayer[] playersf2 = null;
     	CurrentPlayer[] playerst2 = null;
     	String[] imgUrls = new String[2];
     	int imgUrl_len = 0;
     	Image img1 = null;
     	Image img2 = null;
    	String[] teams = new String[2];
    	int team_len = 0;
    	String[] points = new String[2];
    	int points_len = 0;
    	String[] team_info = new String[2];
    	int team_info_len = 0;
    	String time_f = null;
    	String consumTime = null;
    	String arena = null;
    	String peopleNum = null;
    	String[] win = new String[2];
    	int win_len = 0;
    	boolean begin = false;
    	while (dataXml.hasNext())
    	{
    		s = dataXml.next();
    		if (s.contains("gamecenter_content_l"))
    		{
    			begin = true;
    		}
    		
    		if (begin)
    		{
    		if (s.contains("target='_blank'>"))
    		{
    			teams[team_len++] = getTeamName(s);
    		}
    		else if (s.contains("主队") || s.contains("客队") )
    		{
    			team_info[team_info_len++] = getContent(s);
    		}
    		else if (s.contains("time_f"))
    		{
    			time_f = getContent(s);
    		}
    		else if (s.contains("consumTime"))
    		{
    			consumTime = getContent(s);
    		}
    		else if (s.contains("arena"))
    		{
    			arena = getContent(s);
    		}
    		else if (s.contains("peopleNum"))
    		{
    			peopleNum = getContent(s);
    			break;
    		}
    		else if (s.contains("</p>"))
    		{
    			Pattern p = Pattern.compile("\\s+(\\S+)\\s+");
    			Matcher m = p.matcher(s);
    			if (m.find())
    			 win[win_len++]  = m.group(1);
    		}
    		else if (s.contains("<img alt="))
    		{
    			imgUrls[imgUrl_len++] = getImgUrl(s);
    		}
    		}
    	}
    	boolean onTeam1 = true;
    	while (dataXml.hasNext())
    	{
    		s = dataXml.next();
    		if (s.contains(teams[1]))
    		{
    			onTeam1 = false;
    		}
    		else if (s.contains("td"))
    		{
    			if (onTeam1)
    			{
    				points1.add(getContent(s));
    			}
    			else 
    			{
    				points2.add(getContent(s));
    			}
    		}
    		else if (s.contains("yuece_num_b")) 
            {
            	break;
            }
    	}
    	
    	
    	StringBuilder sb = new StringBuilder();
		int tableNum = 0;
		boolean tableList = false;
		boolean readable = false;
		String tableListMet = "table_list_live";
		String tableBegin = "<table>";
		String tableEnd = "</table>";
		String line = null;
		while(dataXml.hasNext())
		{
			line = dataXml.next();
			if (line.contains(tableListMet))
			{
				tableList = true;
			}
			if (tableList)
			{
				
			  if (line.contains(tableBegin))
			  {
				  readable = true;
				  tableNum += 1;
			  }
			  else if (line.contains(tableEnd))
			  {
				  line = line.replace("&nbsp", "");
				  sb.append(line+"\n");
				  readable = false;
				  if (tableNum == 2)
				  {
					  break;
				  }
			  }
			  if (readable)
			  {
				  line = line.replace("&nbsp", "");
				  sb.append(line+"\n");
			  }
			}
			
		}
		String xml = sb.toString();
		ArrayList<String> list = null;
		String[] teamData = new String[4];
		int teamData_len = 0;
		try {
			list = parseDataXml(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int l = 0; l < list.size(); ++l)
		{
			String t = list.get(l);
			if (t.contains("统计") || t.contains("命中率"))
			{
				teamData[teamData_len++] = t;
			}
		}
		if (list != null)
		{
			int i = 1;
			String li = null;
			for (;i<list.size();i++)
			{
				li = list.get(i);
				if (li.contains("首发"))
				{
					break;
				}
			}
			i += 1;
			CurrentPlayer[] temp = toCurrentPlayer(1,list);
			playersf1 = new CurrentPlayer[5];
			playerst1 = new CurrentPlayer[temp.length-5];
			for (int j = 0; j < temp.length; ++j)
			{
				if (j < 5)
				{
					playersf1[j] = temp[j];
				}
				else 
				{
					playerst1[j-5] = temp[j]; 
				}
			}
			temp = toCurrentPlayer(i,list);
			playersf2 = new CurrentPlayer[5];
			playerst2 = new CurrentPlayer[temp.length-5];
			for (int j = 0; j < temp.length; ++j)
			{
				if (j < 5)
				{
					playersf2[j] = temp[j];
				}
				else 
				{
					playerst2[j-5] = temp[j]; 
				}
			}
			
		}
		try {
			String messageUrl = getMessageUrl(teams[0],teams[1],matchId);
			Iterator<String> liveXmlItr = WebTool.getWebCon(messageUrl);
			String liveXml = "";
			while (liveXmlItr.hasNext())
			{
				liveXml = liveXmlItr.next();
			}
		  messageList = getMessages(liveXml);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		try {
			img1 = ImageIO.read(new URL(imgUrls[0]));
			img2 = ImageIO.read(new URL(imgUrls[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] primaryDatas1 = new String[12];
		String[] primaryDatas2 = new String[12];
		String[] rate1 = new String[3];
		String[] rate2 = new String[3];
		String[] temp1 = null;
		String[] temp2 = null;
		temp1 = teamData[0].split(" ");
		temp2 = teamData[2].split(" ");
		for (int i = 0;i < 12; ++i)
		{
			primaryDatas1[i] = temp1[i+2];
			primaryDatas2[i] = temp2[i+2];
		}
		temp1 = teamData[1].split(" ");
		temp2 = teamData[3].split(" ");
		for (int i = 0; i < 3; ++i)
		{
			rate1[i] = temp1[i+3];
			rate2[i] = temp2[i+3];
		}
		String[] points_temp = new String[points1.size()-9];
		String totalScores_temp = null;
		for (int i = 0; i < points_temp.length; i++)
		{
			points_temp[i] = points1.get(i+8);
		}
		totalScores_temp = points1.get(points1.size()-1);
		
		CurrentTeam team1 = new CurrentTeam(playersf1, playerst1,primaryDatas1,rate1
	    		  ,totalScores_temp,points_temp ,teams[0],img1,team_info[0], win[0]);
//		print(points_temp);
	    points_temp = new String[points2.size()-1];
		for (int i = 0; i < points_temp.length; i++)
		{
			points_temp[i] = points2.get(i);
		}
		totalScores_temp = points2.get(points2.size()-1);
		
		CurrentTeam team2 = new CurrentTeam(playersf2,playerst2,primaryDatas2,rate2
				,totalScores_temp,points_temp,teams[1],img2,team_info[1],win[1]);
//		print(points_temp);
//		String time_f = null;
//    	String consumTime = null;
//    	String arena = null;
//    	String peopleNum = null;
		CurrentMatch currentMatch = new CurrentMatch( matchId, team1,  team2,
				time_f, consumTime, arena, peopleNum);
		try {
			String messUrl = this.getMessageUrl(teams[0], teams[1], matchId);
			Iterator<String> xmlItr = WebTool.getWebCon(messUrl);
			String xmlM ="";
			while (xmlItr.hasNext())
			{
				xmlM+=xmlItr.next();
			}
			messageList = this.getMessages(xmlM);
			currentMatch.setMessages(messageList);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		return currentMatch;
    }
    
    private CurrentPlayer[] toCurrentPlayer(int start, ArrayList<String> list)
    {
    	ArrayList<CurrentPlayer> players = new ArrayList<CurrentPlayer>();
    	String str = null;
    	int i = start;
    	String[] playerInfo = null;
    	for (; i < list.size();++i)
    	{
    		str = list.get(i);
    		if (str.contains("统计"))
    		{
    			break;
    		}
    		else if (str.contains("替补"))
    		{
    			continue;
    		}
    		playerInfo = str.split(" ");
    		players.add(new CurrentPlayer(playerInfo));
    	}
    	CurrentPlayer[] currentPlayers = new CurrentPlayer[players.size()];
    	players.toArray(currentPlayers);
    	return currentPlayers;
    }
    
    
    
    private String getMessageUrl(String team1,String team2,String matchId) throws ScriptException
	{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript"); 
		String teamCode1 = (String) engine.eval("encodeURIComponent(\""+team1+"\")");
		String teamCode2 = (String)engine.eval("encodeURIComponent(\""+team2+"\")");
//		"http://g.hupu.com/node/playbyplay/matchLives?sid=-1&s_count=1&match_id=150105&homeTeamName=%E8%80%81%E9%B9%B0&awayTeamName=%E9%AA%91%E5%A3%AB"
		String result = "http://g.hupu.com/node/playbyplay/matchLives?sid=-1&s_count=1&match_id="+matchId+"&homeTeamName="+teamCode1+"&awayTeamName="+teamCode2;
		return result;
	}
    
    private String getTeamName(String line)
    {
    	String regex = "arget='_blank'>(.*?)<";
    	return getMatcher(line,regex);
    }
    private String getContent(String line)
    {
    	String regex = ">(.*?)<";
    	return getMatcher(line,regex);
    }
    private String getMatcher(String line, String regex)
    {
    	Pattern p = Pattern.compile(regex);
    	Matcher m = p.matcher(line);
    	String result = null;
    	if (m.find())
    	{
    		result = m.group(1);
    	}
    	return result;
    }
    
    private  ArrayList<String> getMessages(String xml)
	{
		ArrayList<String> list = new ArrayList<String>();
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
	

	public  ArrayList<String> parseDataXml(String xml) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory   
                .newInstance();   
        DocumentBuilder builder = factory.newDocumentBuilder();   
        org.w3c.dom.Document doc = builder   
                .parse(new InputSource(new StringReader("<a>"+xml+"</a>")));   
        Element root = doc.getDocumentElement();   
        NodeList books = root.getChildNodes(); 
        ArrayList<String> list = getDataArray(books);
        return list;
	}
	
	
    public String getImgUrl(String line)
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
    
    
    private ArrayList<String> getDataArray(NodeList nodeList)
	{
		ArrayList<String> list = new ArrayList<String>();
		itrXml(nodeList,null,list);
		return list;
	}
	
    private void itrXml(NodeList nodeList,StringBuilder sb,ArrayList<String> list)
	{
		
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (node.hasChildNodes())
			{
				String nodeName = node.getNodeName();
				if (nodeName.equals("tr"))
				{
					sb = new StringBuilder();
				}
				itrXml(node.getChildNodes(),sb,list);
				if (nodeName.equals("tr"))
				{
					
					list.add(sb.toString());
				}
			}
			else 
			{
				String s = node.getNodeValue();
				if (s != null)
				{
			    s = s.replaceAll("\\s*", "");
				if (!s.equals(""))
				  sb.append(s+" ");
				}
			}
		}
	}
    
    
    private Iterator<String> getDataHtml() throws Exception
    {
    	String path = "K:/2.txt";
       return readFile(path);
    }
    
    private Iterator<String> getLiveHtml()  throws Exception 
    {
    	String path = "K:/3.txt";
        return readFile(path);
    }
    
    private Iterator<String> readFile(String path) throws Exception
    {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        ArrayList<String> content = new ArrayList<String>();
        String s = null;
        while ((s = reader.readLine())!= null)
        {
     	   content.add(s);
        }
        return content.iterator();
    }
} 
