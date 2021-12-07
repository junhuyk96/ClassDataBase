import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PRJ extends JFrame implements ActionListener{
	JButton button[] = new JButton[3];	/*0. 관리자	1. 교수	2. 학생*/
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
	         //System.out.println("드라이버 로드 성공");
	         txtStatus.append("드라이버 로드 성공...\n");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }
	      
	      try { /* 데이터베이스를 연결하는 과정 */
	          //System.out.println("데이터베이스 연결 준비...");
	    	  txtStatus.append("데이터베이스 연결 준비...\n");
	          con = DriverManager.getConnection(url, userid, pwd);
	          //System.out.println("데이터베이스 연결 성공");
	          txtStatus.append("데이터베이스 연결 성공...\n");
	       } catch (SQLException e1) {
	          e1.printStackTrace();
	       }
	   }
	
	public PRJ() {
		super("프로젝트");
		init();
		txt();
		conDB();
		setVisible(true);
	    setSize(300, 200); //가로위치,세로위치,가로길이,세로길이
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void init() {
		button[0] = new JButton("관리자");
		button[1] = new JButton("교수");
		button[2] = new JButton("학생");
		
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
		/*관리자 사용자*/
		try {
			   stmt=con.createStatement();
			   if(e.getSource()==button[0]) {
				   new Administrator();
			   }
		}
		catch(Exception e2) {
			   System.out.println("오류: " + e2);
		}
		/*교수 사용자*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[1]) {
				new Professor();
			}
		}
		catch(Exception e2) {
			System.out.println("오류: " + e2);
		}
		/*학생 사용자*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[2]) {
				new Student();
			}
		}
		catch(Exception e2) {
			System.out.println("오류: " + e2);
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
	    		System.out.println("프로그램 완전 종료!");
	    		System.exit(0);
	    	  }
	    	});
	}

}


/*숫자만 입력 가능하게 하는 클래스*/
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
