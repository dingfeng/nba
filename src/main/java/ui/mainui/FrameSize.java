package ui.mainui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.math.BigDecimal;

import javax.swing.ImageIcon;

public class FrameSize {
	static int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int width=3*screenWidth/4;
	public static int height = 8*screenHeight/9;
	public static Color backColor = new Color(0,128,255,200);
	public static Color buttonbackColor=new Color(0,128,255);
	public static Color bluecolor=new Color(0,102,175);
	public static Color darkbluecolor=new Color(6,72,131);
	public static Color lightbluecolor=new Color(183,220,249);
	public static double roundForNumber(double v,int num){
		BigDecimal bg = new BigDecimal(v);
        double f = bg.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }
	public static ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) {
			int width = icon.getIconWidth();
			int height = icon.getIconHeight();

			if (width == iconWidth && height == iconHeight) {
				return icon;
			}
			Image image = icon.getImage();
			image = image.getScaledInstance(iconWidth, iconHeight,
					Image.SCALE_DEFAULT);

			return new ImageIcon(image);
		}

	public static MyComboBox season=new MyComboBox(new String[]{"2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985"});
}
