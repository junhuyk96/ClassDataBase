import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;

class Administrator extends JFrame implements ActionListener {
   JButton button[]=new JButton[5];
   /*0.   �ʱ�ȭ   1. �Է�   2. ����   3. ����   4.   ��ü ���̺�*/
   JPanel north, south, center;
   JTextArea txtStatus;
   
   static Connection con;
   Statement stmt;
   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";
   
   public void conDB() {
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("����̹� �ε� ����");
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
         
         try { /* �����ͺ��̽��� �����ϴ� ���� */
             System.out.println("�����ͺ��̽� ���� �غ�...");           
             con = DriverManager.getConnection(url, userid, pwd);
             System.out.println("�����ͺ��̽� ���� ����");             
          } catch (SQLException e1) {
             e1.printStackTrace();
          }
      }

   
   void init() {
      button[0]=new JButton("�ʱ�ȭ");
      button[1]=new JButton("�Է�/����/����");
      button[2]=new JButton("��ü ���̺� ���");
      
      north =new JPanel();
      north.add(button[0]);
      north.add(button[1]);
      north.add(button[2]);
      
      txtStatus = new JTextArea();
      
      add("North", north);
      add("Center", txtStatus);
      for(int i=0; i<3; i++) button[i].addActionListener(this);
   }
   
   public Administrator() {
      super("������");
      init();
      conDB();
      setVisible(true);
       setSize(500, 500); 
      
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
         }
      });
   }
   
   public void actionPerformed(ActionEvent e) {
      try {
         stmt=con.createStatement();
         if(e.getSource()==button[0]) {
            
            ScriptRunner runner = new ScriptRunner(con);
            InputStreamReader reader = new InputStreamReader(new FileInputStream("madang.sql"), "UTF-8");
            runner.runScript(reader);
            reader.close();
            txtStatus.setText("�ʱ�ȭ ����");
         }
      }
      catch (Exception e2){
         System.out.println("���� : " + e2);
      }
      
      try {
         stmt=con.createStatement();
         if(e.getSource()==button[1]) {
            new admin();
         }
      }
      catch(Exception e2) {
         System.out.println("���� : " + e2);
      }
      
      try {
         stmt=con.createStatement();
         if(e.getSource()==button[2]) {
            new table();
         }
      }
      catch(Exception e2) {
         System.out.println("���� : " + e2);
      }
   }
}