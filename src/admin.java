import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class admin extends JFrame implements ActionListener {
   
   String table1[]= {"변경", "삭제"};
   String table2[]= {"student","professor","department","class","classHistory","tuition","club" };
   Container c = getContentPane();
   JComboBox cb1 = new JComboBox<String>(table1);
   JComboBox cb2 = new JComboBox<String>(table2);
   JTextField tf1 = new JTextField(50);
   JTextField tf2 = new JTextField(50);
   JTextField tf3 = new JTextField(50);
   String query = new String();
   
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
   
   public admin() {
      super("입력/삭제/변경");
      conDB();
      init();
      setSize(700,400);
      setVisible(true);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
         }
      });
   }
   
   void init() {
      
      c.setLayout(new FlowLayout());
      
      c.add(cb1);
      c.add(new JLabel("하고 싶은 table을 선택하세요."));
      c.add(cb2);
      
      c.add(new JLabel("변경하려는 경우 변경값을 입력하세요.(삭제의경우 생략)"));
      c.add(tf2);
      
      c.add(new JLabel("삭제/변경을 위한 조건문을 입력하고, enter키를 입력하세요."));
      c.add(tf1);
      
      c.add(new JLabel("입력을 위한 INSERT문을 입력하고, enter키를 입력하세요."));
      c.add(tf3);
      
      tf1.addActionListener(this);
      tf3.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e)  {
      try {
         stmt=con.createStatement();
         if(e.getSource()==tf1) {
         
            if(cb1.getSelectedItem().toString()=="변경") {
               
               query= "update " + cb2.getSelectedItem().toString()+" set "+tf2.getText()+" where "+tf1.getText();
               System.out.print(query);
               tf1.setText("");
               stmt.executeUpdate(query);
            }
            else if(cb1.getSelectedItem().toString()=="삭제") {
            
               query= "delete from " + cb2.getSelectedItem().toString()+" where "+tf1.getText();
               System.out.print(query);
               tf1.setText("");
               stmt.executeUpdate(query);
            }
         }
      }
      catch (Exception e2){
         System.out.println("오류 : " + e2);
      }
      
      try {
         stmt=con.createStatement();
         if(e.getSource()==tf3) {
         
            query= tf3.getText();
            System.out.print(query);
            tf3.setText("");
            stmt.executeUpdate(query);
            
         }
      }
      catch (Exception e2){
         System.out.println("오류 : " + e2);
      }
   }
}