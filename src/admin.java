import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class admin extends JFrame implements ActionListener {
   
   String table1[]= {"����", "����"};
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
   
   public admin() {
      super("�Է�/����/����");
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
      c.add(new JLabel("�ϰ� ���� table�� �����ϼ���."));
      c.add(cb2);
      
      c.add(new JLabel("�����Ϸ��� ��� ���氪�� �Է��ϼ���.(�����ǰ�� ����)"));
      c.add(tf2);
      
      c.add(new JLabel("����/������ ���� ���ǹ��� �Է��ϰ�, enterŰ�� �Է��ϼ���."));
      c.add(tf1);
      
      c.add(new JLabel("�Է��� ���� INSERT���� �Է��ϰ�, enterŰ�� �Է��ϼ���."));
      c.add(tf3);
      
      tf1.addActionListener(this);
      tf3.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e)  {
      try {
         stmt=con.createStatement();
         if(e.getSource()==tf1) {
         
            if(cb1.getSelectedItem().toString()=="����") {
               
               query= "update " + cb2.getSelectedItem().toString()+" set "+tf2.getText()+" where "+tf1.getText();
               System.out.print(query);
               tf1.setText("");
               stmt.executeUpdate(query);
            }
            else if(cb1.getSelectedItem().toString()=="����") {
            
               query= "delete from " + cb2.getSelectedItem().toString()+" where "+tf1.getText();
               System.out.print(query);
               tf1.setText("");
               stmt.executeUpdate(query);
            }
         }
      }
      catch (Exception e2){
         System.out.println("���� : " + e2);
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
         System.out.println("���� : " + e2);
      }
   }
}