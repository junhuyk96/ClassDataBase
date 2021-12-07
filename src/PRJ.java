import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PRJ extends JFrame implements ActionListener{
	JButton button[] = new JButton[3];	/*0. ������	1. ����	2. �л�*/
	JPanel panel;
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
	         //System.out.println("����̹� �ε� ����");
	         txtStatus.append("����̹� �ε� ����...\n");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	      
	      try { /* �����ͺ��̽��� �����ϴ� ���� */
	          //System.out.println("�����ͺ��̽� ���� �غ�...");
	    	  txtStatus.append("�����ͺ��̽� ���� �غ�...\n");
	          con = DriverManager.getConnection(url, userid, pwd);
	          //System.out.println("�����ͺ��̽� ���� ����");
	          txtStatus.append("�����ͺ��̽� ���� ����...\n");
	       } catch (SQLException e1) {
	          e1.printStackTrace();
	       }
	   }
	
	public PRJ() {
		super("������Ʈ");
		init();
		txt();
		conDB();
		setVisible(true);
	    setSize(300, 200); //������ġ,������ġ,���α���,���α���
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void init() {
		button[0] = new JButton("������");
		button[1] = new JButton("����");
		button[2] = new JButton("�л�");
		
		panel=new JPanel();
		
		for(int i=0; i<3; i++) panel.add(button[i]);
		
		add("Center", panel);
		
		for(int i=0; i<3; i++) button[i].addActionListener(this);
	}
	
	void txt() {
		   txtStatus = new JTextArea(5,30);
		  
		   txtStatus.setEditable(false);
		   JScrollPane scrollPane1 = new JScrollPane(txtStatus);
		   
		   add("South", scrollPane1);
	}

	public void actionPerformed(ActionEvent e) {
		/*������ �����*/
		try {
			   stmt=con.createStatement();
			   if(e.getSource()==button[0]) {
				   new Administrator();
			   }
		}
		catch(Exception e2) {
			   System.out.println("����: " + e2);
		}
		/*���� �����*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[1]) {
				new Professor();
			}
		}
		catch(Exception e2) {
			System.out.println("����: " + e2);
		}
		/*�л� �����*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[2]) {
				new Student();
			}
		}
		catch(Exception e2) {
			System.out.println("����: " + e2);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PRJ prj= new PRJ();
		
		prj.addWindowListener(new WindowAdapter() {
	    	  public void windowClosing(WindowEvent we) {
	    		try {
	    			con.close();
	    		} catch (Exception e4) { 	}
	    		System.out.println("���α׷� ���� ����!");
	    		System.exit(0);
	    	  }
	    	});
	}

}


/*���ڸ� �Է� �����ϰ� �ϴ� Ŭ����*/
class NumberField extends JTextField implements KeyListener {
	private static final long serialVersionUID=1;
	
	public NumberField() {
		addKeyListener(this);
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		JTextField src = (JTextField)e.getSource();
		if(!Character.isDigit(c) || src.getText().length()>=12) {
			e.consume();
			return;
		}
	}
}
