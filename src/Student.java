import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*학생 화면*/
class Student extends JFrame implements ActionListener {
	JButton button[] = new JButton[6];
	/*0. 입력	1. 취소	2. 강의정보	3. 시간표 4. 동아리정보	5. 성적표*/
	JPanel left, right, center;
	
	JTextField input[] = new JTextField[3];
	int output[] = new int[3];
	/*0. 학생번호	1. 연도	2.학기*/
	
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
		button[3]=new JButton("시간표");
		button[4]=new JButton("동아리정보");
		button[5]=new JButton("성적표");
		
		left=new JPanel();
		left.setLayout(new GridLayout(1,0));
		for(int i=2; i<6; i++) left.add(button[i]);
		
		right=new JPanel();
		right.setLayout(new GridLayout(0,2,100,0));
		for(int i=0; i<3; i++) input[i]=new NumberField();
		right.add(new JLabel("학생번호"));
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
		
		for(int i=0; i<6; i++) button[i].addActionListener(this);
	}
	
	public Student() {
		super("학생");
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
		/*학생번호를 입력*/
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
			System.out.println("학생 번호 오류 : " + e2);
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
		
		/*강의정보*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId and"
					+ " a.classId IN (select classId from classHistory where classHistory.studentId="
					+ output[0] + ")";
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
			}
		}
		catch(Exception e2) {
			System.out.println("학생 번호 오류 : " + e2);
		}
		
		/*학생 시간표*/
		try {
			stmt=con.createStatement();
			String query="select classDay, period from class where classId IN (select classId from classHistory "
					+ "where classHistory.studentId=" + output[0]+")";
			String colNames[] = {"요일", "교시"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[3]) {
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
			System.out.println("학생 번호 오류 : " + e2);
		}
		
		/*동아리정보*/
		try {
			int a=-1;
			stmt=con.createStatement();
			String query="select a.clubId, a.clubName, a.numberOfStudents, a.clubAddress, b.professorName, a.presidentStudentId "
					+ "from club a, professor b  where a.professorId=b.professorId and"
					+" a.clubId IN (select clubId from clubStudentList "
					+ "where clubStudentList.studentId =" + output[0] + ")";
			String colNames[] = {"동아리번호", "동아리명", "학생수", "동아리실", "담당교수명", "동아리장"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[4]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5),rs.getInt(6)
					});
					if(rs.getInt(6)==output[0]) a=1;
				}
				
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(1000,320);;
	            center.removeAll();
	            center.add(sp);
	            if(a==1) {
	            	new club(output[0]);
	            }
			}
		}
		catch(Exception e2) {
			System.out.println("학생 번호 오류 : " + e2);
		}
		
		/*성적표*/
		try {
			int cnt=0;
			double score=0;
			String s;
			stmt=con.createStatement();
			String query="select a.classId, a.className, a.credits, b.grade "
					+ "from class a, classHistory b "
					+ "where a.classId = b.classId and b.studentId = " + output[0];
			String colNames[] = {"강의번호", "강의명", "취득학점", "평점"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[5]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)							
					});
					
					cnt++;
					s=rs.getString(4).toString();
					if(s.equals("A")) score+=4.0;
					else if(s.equals("B"))score+=3.0;
					else if(s.equals("C")) score+=2.0;
					else if(s.equals("D")) score+=1.0;
				}
				String colNames1[] = {"GPA"};
				DefaultTableModel model1 = new DefaultTableModel(colNames1,0);
				model1.addRow(new Object[] {score/cnt});
				JTable tb2 = new JTable(model1);
				JScrollPane sp2 = new JScrollPane(tb2);
				
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(700,80);
	            sp2.setSize(80,80);
	            sp2.setLocation(700, 0);
	            center.removeAll();
	            center.add(sp);
	            center.add(sp2);
			}
		}
		catch(Exception e2) {
			System.out.println("학생 번호 오류 : " + e2);
		}
	}
}

class club extends JFrame {
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
	
	public club(int sid) {
		super("동아리원 정보");
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
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, "
					+ "b.departmentName, c.professorName, a.tuitionAccount, d.departmentName \r\n"
					+ "from student a, department b, professor c, department d "
					+ "where b.departmentId=a.majorId and c.professorId=a.professorId and d.departmentId=a.minorId "
					+ "and a.studentId IN (select studentId from clubStudentList\r\n"
					+ "where clubStudentList.clubId "
					+ "IN (select clubId from club where club.presidentStudentId = " + id + "))";
			String colNames[] = {"학생번호", "학생이름", "학생전화번호", "학생이메일", "전공학과명", "담당교수명", "계좌번호", "부전공학과명"};
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