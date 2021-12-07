import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*교수 화면*/
class Professor extends JFrame implements ActionListener {
	JButton button[] = new JButton[7];
	/*0. 입력	1. 취소	2. 과목정보 3. 지도학생정보	4. 학과정보
	 5. 강의시간표		6. 성적표입력*/
	JPanel left, right, center;
	
	JTextField input[] = new JTextField[3];
	int output[] = new int[3];
	/*0. 교수번호	1. 연도	2.학기*/
	
	JTable tb;
	JScrollPane sp;
	
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
		button[0]=new JButton("입력");
		button[1]=new JButton("취소");
		button[2]=new JButton("강의정보");
		button[3]=new JButton("지도학생정보");
		button[4]=new JButton("학과정보");
		button[5]=new JButton("강의시간표");
		button[6]=new JButton("성적표입력");
		
		left=new JPanel();
		left.setLayout(new GridLayout(1,0));
		for(int i=2; i<7; i++) left.add(button[i]);
		
		right=new JPanel();
		right.setLayout(new GridLayout(0,2,500,0));
		for(int i=0; i<3; i++) input[i]=new NumberField();
		right.add(new JLabel("교수번호"));
		right.add(input[0]);
		right.add(new JLabel("연도"));
		right.add(input[1]); 
		right.add(new JLabel("학기"));
		right.add(input[2]); 
		for(int i=0; i<2; i++) right.add(button[i]);
		
		center=new JPanel();
		
		add("North", left);
		add("South", right);
		add("Center", center);
		
		for(int i=0; i<7; i++) button[i].addActionListener(this);
	}
	
	public Professor() {
		super("교수");
		init();
		conDB();
		setVisible(true);
	    setSize(1000, 500); //가로위치,세로위치,가로길이,세로길이
	   
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		/*교수번호 연도 학기 입력*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[0]) {
				String PID=input[0].getText();
				output[0]=Integer.parseInt(PID);
				String YEAR = input[1].getText();
				output[1]=Integer.parseInt(YEAR);
				String TERM = input[2].getText();
				output[2]=Integer.parseInt(TERM);
			}
		}
		catch (Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
		
		/*입력창을 빈 창으로 만듦*/
		try {
			if(e.getSource()==button[1]) {
				for(int i=0; i<3; i++) input[i].setText("");
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*교수 담당 강의*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId and a.professorId = " + output[0];
			String colNames[] = {"강의번호", "분반", "강의명", "담당교수명", "학과명", "요일", "교시", "취득학점", "시간", "강의실"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[2]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)
							,rs.getInt(10)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(1000,320);
	            center.removeAll();
	            center.add(sp);
	            
	            tb.addMouseListener(new MouseAdapter() {
	            	public void mouseClicked(MouseEvent e) {
	            		if(e.getButton()==1) {
	            			 String s=tb.getValueAt(tb.getSelectedRow(), 0).toString();
	            			 
	            			 int cid = Integer.parseInt(s);
	            			 new class_student(cid);
	            		}
	            	}
	            });
			}
		}
		catch(Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
		
		/*교수 담당 학생*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, b.departmentName, "
					+ "c.professorName, a.tuitionAccount, d.departmentName from student a, department b, "
					+ "professor c, department d where b.departmentId=a.majorId and "
					+ "c.professorId=a.professorId and d.departmentId=a.minorId and a.professorId=" + output[0];
			String colNames[] = {"학생번호", "학생이름", "전화번호", "이메일주소", "학과명", "담당교수명", "계좌번호", "부전공학과명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[3]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(1000,320);
	            center.removeAll();
	            center.add(sp);
	            
	            tb.addMouseListener(new MouseAdapter() {
	            	public void mouseClicked(MouseEvent e) {
	            		if(e.getButton()==1) {
	            			 String s=tb.getValueAt(tb.getSelectedRow(), 0).toString();
	            			 
	            			 int sid = Integer.parseInt(s);
	            			 new student_classhistory(sid);
	            		}
	            	}
	            });
			}
		}
		catch(Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
		
		/*교수 소속 학과정보*/
		try {
			stmt=con.createStatement();
			String query="select a.departmentId, a.departmentName, a.departmentPhone, a.departmentAddress, b.professorName "
					+ "from department a, professor b where a.departmentId IN (select departmentId from professor where professor.professorId = "
					+ output[0]
					+ ") and b.professorId = a.professorId ";
			String colNames[] = {"학과번호", "학과명", "학과전화번호", "학과사무실", "학과장명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[4]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
							rs.getString(5)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(1000,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
		
		/*교수 강의 시간표*/
		try {
			stmt=con.createStatement();
			String query="select classDay, period from class where class.professorId = " + output[0];
			String colNames[] = {"요일", "교시"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[5]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getString(1), rs.getInt(2)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(1000,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
		
		/*성적 입력*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[6]) {
				new grade_input(output[0]);
			}
		}
		catch(Exception e2) {
			System.out.println("교수 번호 오류 : " + e2);
		}
	}
	
}
/*교수가 강의 정보를 클릭 시 그 강의를 듣는 학생 정보 출력 클래스*/
class class_student extends JFrame {
	JTable tb;
	JScrollPane sp;
	int id;
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
	
	public class_student(int cid) {
		super("학생정보");
		id=cid;
		conDB();
		setLocation(100,100);
		setVisible(true);
	    setSize(700, 300);
	    view_table();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	void view_table() {
		try {
			stmt=con.createStatement();
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, b.departmentName, "
					+ "c.professorName, a.tuitionAccount, d.departmentName\r\n"
					+ "from student a, department b, professor c, department d\r\n"
					+ "where a.studentId IN  (select studentId from classHistory where classHistory.classId = " +id + ")"
					+ "and b.departmentId=a.majorId and c.professorId = a.professorId and d.departmentId = a.minorId";
			String colNames[] = {"학생번호", "학생이름", "전화번호", "이메일주소", "학과명", "담당교수명", "계좌번호", "부전공학과명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					});
			}
	        tb=new JTable(model);
	        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            sp=new JScrollPane(tb);
            add("Center", sp);
		}
		catch(Exception e2) {
			System.out.println("오류 :" + e2);
		}
	}
}

/*교수가 담당하는 학생들의 수강내역을 출력하는 클래스*/
class student_classhistory extends JFrame {
	JTable tb;
	JScrollPane sp;
	int id;
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
	
	public student_classhistory(int sid) {
		super("학생정보");
		id=sid;
		conDB();
		setLocation(100,100);
		setVisible(true);
	    setSize(700, 300);
	    view_table();
	    
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	void view_table() {
		try {
			stmt=con.createStatement();
			String query="select a.studentId, b.className, c.professorName, a.midScore, a.finalScore, a.ectScore, a.scoreSum, a.grade "
					+ "from classHistory a, class b, professor c where a.classId=b.classId and a.professorId=c.professorId and a.studentId=" + id;
			String colNames[] = {"학생번호", "강의명", "교수명", "중간점수", "기말점수", "기타점수", "합계", "학점"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				model.addRow(new Object[] {
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8)
					});
			}
	        tb=new JTable(model);
	        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            sp=new JScrollPane(tb);
            add("Center", sp);
		}
		catch(Exception e2) {
			System.out.println("오류 :" + e2);
		}
	}
}

class grade_input extends JFrame implements ActionListener{
	int id;
	JButton button[]=new JButton[2];
	JPanel panel, panel2;
	JTextField SID, CID, MID, FIN, ECT, SUM, GRADE; 
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
		button[0]=new JButton("입력");
		button[1]=new JButton("취소");
		
		panel=new JPanel();
	
		
		panel.add(button[0]);
		panel.add(button[1]);
		
		add("South", panel);
		
		panel2=new JPanel();
		panel2.setLayout(new GridLayout(2,7));
		panel2.add(new JLabel("학생번호"));
		panel2.add(new JLabel("강의번호"));
		panel2.add(new JLabel("중간점수"));
		panel2.add(new JLabel("기말점수"));
		panel2.add(new JLabel("기타점수"));
		panel2.add(new JLabel("총점수"));
		panel2.add(new JLabel("학점"));
		SID=new NumberField();
		CID=new NumberField();
		MID=new NumberField();
		FIN=new NumberField();
		ECT=new NumberField();
		SUM=new NumberField();
		GRADE=new JTextField(30);
		
		
		panel2.add(SID);
		panel2.add(CID);
		panel2.add(MID);
		panel2.add(FIN);
		panel2.add(ECT);
		panel2.add(SUM);
		panel2.add(GRADE);
		
		add("North", panel2);
		button[0].addActionListener(this);
		button[1].addActionListener(this);
	}
	
	public grade_input(int pid) {
		super("성적입력");
		id = pid;
		conDB();
		init();
		setLocation(100,100);
		setVisible(true);
	    setSize(700, 300);
	    
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
				String s[] = new String[7];
				
				s[0]=SID.getText();
				s[1]=CID.getText();
				s[2]=MID.getText();
				s[3]=FIN.getText();
				s[4]=ECT.getText();
				s[5]=SUM.getText();
				s[6]=GRADE.getText();
				
				String query = "insert into classHistory values("
						+ s[0] + "," + s[1] +"," + id + "," + s[2] +
						"," + s[3] + "," + s[4] + "," + s[5] +"," + "'"+ s[6] + "'" + ")";
				stmt.executeUpdate(query);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		try {
			if(e.getSource()==button[1]) {
				SID.setText("");
				CID.setText("");
				MID.setText("");
				FIN.setText("");
				ECT.setText("");
				SUM.setText("");
				GRADE.setText("");
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
	}
}