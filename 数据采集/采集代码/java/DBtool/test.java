package DBtool;

import java.io.File;

public class test 
{
 public static void main(String[] args)
 {
	 String path = "K:/NBAData/a";
	 File file = new File(path);
	 System.out.println("filename : "+file.getName());
	 System.out.println("path : "+file.getPath());
	 System.out.println("absolute path : "+file.getAbsolutePath());
	 System.out.println(file.getParentFile().getName());
 }
}
