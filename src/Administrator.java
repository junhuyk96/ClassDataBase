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
   /*0.   초기화   1. 입력   2. 삭제   3. 변경   4.   전체 테이블*/
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
            System.out.println("드라이버 로드 성공");
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         }
         
         try { /* 데이터베이스를 연결하는 과정 */
             System.out.println("데이터베이스 연결 준비...");           
             con = DriverManager.getConnection(url, userid, pwd);
             System.out.println("데이터베이스 연결 성공");             
          } catch (SQLException e1) {
             e1.printStackTrace();
          }
      }

   
   void init() {
      button[0]=new JButton("초기화");
      button[1]=new JButton("입력/삭제/변경");
      button[2]=new JButton("전체 테이블 출력");
      
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
      super("관리자");
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
            txtStatus.setText("초기화 성공");
         }
      }
      catch (Exception e2){
         System.out.println("오류 : " + e2);
      }
      
      try {
         stmt=con.createStatement();
         if(e.getSource()==button[1]) {
            new admin();
         }
      }
      catch(Exception e2) {
         System.out.println("오류 : " + e2);
      }
      
      try {
         stmt=con.createStatement();
         if(e.getSource()==button[2]) {
            new table();
         }
      }
      catch(Exception e2) {
         System.out.println("오류 : " + e2);
      }
   }
}