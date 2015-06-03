package data.matchdata;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
   public static void main(String[] args)
   {
	   String str = "ll dinfeng kkkll";
	   Pattern p = Pattern.compile("\\bdinfeng\\b");
	  Matcher m = p.matcher(str);
	   while(m.find())
	   {
		   System.out.println(m.group());
	   }
   }
}
