package ui.mainui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class MyComboBox extends JComboBox{
	
	public MyComboBox(){
		setFeature();
	}
	
	public MyComboBox(String [] a){
		for(int i=0;i<a.length;i++){
			this.addItem(a[i]);
		}
		setFeature();
	}
	
	public MyComboBox(String text,ArrayList<String> a){
		this.addItem(text);
		for(int i=0;i<a.size();i++){
			this.addItem(a.get(i));
		}
		setFeature();
	}
	
	public MyComboBox(String text,String[] a){
		this.addItem(text);
		if (a != null)
		for(int i=0;i<a.length;i++){
			this.addItem(a[i]);
		}
		setFeature();
	}
	
	private void setFeature(){
		this.setBackground(new Color(69,69,69));
		this.setForeground(Color.white);
		this.setFont(new Font("微软雅黑",Font.PLAIN, 12));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		this.setSelectedIndex(5);
		}
}
