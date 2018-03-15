/*
 * Company：      Asiainfo Technology Co., Ltd.
 * 
 * @author:   Liulh   
 * 
 * @Createdate:2017年6月14日 上午11:20:34  
 *
 * Copyright: Copyright(C) 2016,2999  All rights Reserved, Designed By Asiainfo 
 * License   
 * The original version of this source code and documentation is copyrighted
 * and owned by Asiainfo Technology Co., Ltd., a wholly-owned subsidiary of Asiainfo. 
 * These materials are provided under terms of a License Agreement Asiainfo. 
 * This notice and attribution to Asiainfo  may not be removed.
 * Asiainfo is a registered trademark of Asiainfo Technology Co., Ltd. 
 */
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description
 *
 * @Liulh 创建时间：2017年6月14日
 */
public class Computer extends JFrame implements ActionListener {

	JButton a1,a2,a3,a4,a5,a6,a7,a8,a9,a0;
	JButton b1,b2,b3,b4;
	JButton c1,c2,c3,c4;
	JTextField t1,t2;
	JPanel p1,p2;
	JLabel bq1,bq2;
	String fuhao;
	Double count,count2;
	boolean chose=false,cliks;
	public static void main(String[] args){
	Computer l = new Computer();
	}
	public Computer(){
	Font font = new Font("宋体", Font.BOLD, 36);
	Font font2 = new Font("宋体", Font.BOLD, 20);
	a1 = new JButton("1");
	a1.setFont(font);
	a1.addActionListener(this);
	a2 = new JButton("2");
	a2.setFont(font);
	a2.addActionListener(this);
	a3 = new JButton("3");
	a3.setFont(font);
	a3.addActionListener(this);
	a4 = new JButton("4");
	a4.setFont(font);
	a4.addActionListener(this);
	a5 = new JButton("5");
	a5.setFont(font);
	a5.addActionListener(this);
	a6 = new JButton("6");
	a6.setFont(font);
	a6.addActionListener(this);
	a7 = new JButton("7");
	a7.setFont(font);
	a7.addActionListener(this);
	a8 = new JButton("8");
	a8.setFont(font);
	a8.addActionListener(this);
	a9 = new JButton("9");
	a9.setFont(font);
	a9.addActionListener(this);
	a0 = new JButton("0");
	a0.setFont(font);
	a0.addActionListener(this);
	b1 = new JButton("清空");
	b1.addActionListener(this);
	b2 = new JButton("返回");
	b2.addActionListener(this);
	b3 = new JButton(".");
	b3.addActionListener(this);
	b4 = new JButton("=");
	b4.addActionListener(this);
	c1 = new JButton("+");
	c1.addActionListener(this);
	c2 = new JButton("-");
	c2.addActionListener(this);
	c3 = new JButton("x");
	c3.addActionListener(this);
	c4 = new JButton("÷");
	c4.addActionListener(this);
	t1 = new JTextField(25);
	t2 = new JTextField(35);
	t1.setFont(font2);
	t2.setFont(font2);
	p1 = new JPanel();
	p2 = new JPanel();
	bq1 = new JLabel("结");
	bq2 = new JLabel("果");
	p1.setLayout(new GridLayout(2,3));
	p2.setLayout(new GridLayout(4,4));
	p1.add(t1);p1.add(b1);p1.add(b2);
	p1.add(t2);p1.add(bq1);p1.add(bq2 );
	p2.add(a1);p2.add(a2);p2.add(a3);p2.add(c1);
	p2.add(a4);p2.add(a5);p2.add(a6);p2.add(c2);
	p2.add(a7);p2.add(a8);p2.add(a9);p2.add(c3);
	p2.add(b3);p2.add(a0);p2.add(b4);p2.add(c4);
	this.add(p1, BorderLayout.NORTH);
	this.add(p2, BorderLayout.CENTER);
	this.setSize(460,380);
	this.setTitle("简易计算器");
	this.setLocation(200,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
	Object temp = e.getSource();
	if(temp == a1){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"1");
	chose=false;
	}
	if(temp == a2){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"2");
	chose=false;
	}
	if(temp == a3){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"3");
	chose=false;
	}
	if(temp == a4){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"4");
	chose=false;
	}
	if(temp == a5){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"5");
	chose=false;
	}
	if(temp == a6){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"6");
	chose=false;
	}
	if(temp == a7){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"7");
	chose=false;
	}
	if(temp == a8){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"8");
	chose=false;
	}
	if(temp == a9){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"9");
	chose=false;
	}
	if(temp == a0){
	if(chose==true){t1.setText("");t2.setText("");}
	t1.setText(t1.getText()+""+"0");
	chose=false;
	}
	if(temp==b3){
	cliks=true;
	for(int i=0;i<t1.getText().length();i++){
	if('.'==t1.getText().charAt(i)){
	cliks=false;
	break;
	}
	if(cliks==true){
	t1.setText(t1.getText()+".");
	}
	}
	}
	if(temp== c1){
	count= Double.parseDouble(t1.getText());
	t1.setText("");
	fuhao = "+";
	}
	if(temp== c2){
	count= Double.parseDouble(t1.getText());
	t1.setText("");
	fuhao = "-";
	}
	if(temp== c3){
	count= Double.parseDouble(t1.getText());
	t1.setText("");
	fuhao = "*";
	}
	if(temp== c4){
	   count= Double.parseDouble(t1.getText());
	   t1.setText("");
	fuhao = "÷";
	}
	if(temp==b1){
	t1.setText("");
	t2.setText("");
	}
	if(temp==b2){
	String s=t1.getText();
	t1.setText("");
	for(int i=0;i<s.length()-1;i++){
	char a = s.charAt(i);
	t1.setText(t1.getText()+a);
	}
	}
	if(temp== b4){
	count2= Double.parseDouble(t1.getText());
	t1.setText("");
	if(fuhao=="+"){
	//int sum=count+count2;
	t1.setText(count+""+fuhao+""+count2+""+"=");
	t2.setText(count+count2+"");
	chose=true;
	}
	if(fuhao=="-"){
	//int sum=count+count2;
	t1.setText(count+""+fuhao+""+count2+""+"=");
	t2.setText(count-count2+"");
	chose=true;
	}
	if(fuhao=="*"){
	//int sum=count+count2;
	t1.setText(count+""+fuhao+""+count2+""+"=");
	t2.setText(count*count2+"");
	chose=true;
	}
	if(fuhao=="÷"){
	//int sum=count+count2;
	if(count2==0){
	t1.setText(count+""+fuhao+""+count2+"");
	t2.setText("除数不能为0");
	return;
	}
	t1.setText(count+""+fuhao+""+count2+""+"=");
	t2.setText(count/count2+"");
	chose=true;
	}
	}
	}
}


