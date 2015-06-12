package ui.statistics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class ImageRenderer implements TableCellRenderer{

	public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, 
			boolean hasFocus, int rowIndex, int columnIndex) {
		try{
			if( value instanceof Image )
			{
				ImageIcon imageI = scaleImage(new ImageIcon((Image)value),60,50);
				JLabel label = new JLabel(imageI);
				label.setOpaque(true);
				if (hasFocus || isSelected)
				{
					label.setBackground(Color.gray);
				}
				return label;
			}
			else if( value instanceof File ) {
			try {
			return new JLabel(scaleImage(new ImageIcon(ImageIO.read((File)value)),50,50));
			} catch(IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
			}
			}

			else {
			String val = String.valueOf(value);
			try {
			return new JLabel(scaleImage(new ImageIcon(ImageIO.read(new File(val))),50,50));
			} catch(IOException ex) {
			throw new RuntimeException(ex.getMessage(), ex);
			}
			}
		}catch (Exception e)
		{
			return new JLabel(value.toString());
		}
			}
	private ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) { 
		int width = icon.getIconWidth(); 
		int height = icon.getIconHeight(); 

		if (width == iconWidth && height == iconHeight) { 
		return icon; 
		} 
		Image image = icon.getImage(); 
		image = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_DEFAULT); 

		return new ImageIcon(image); 
		}

}
