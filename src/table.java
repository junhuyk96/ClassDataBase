import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class table extends JFrame implements ActionListener {
	JButton button[]= new JButton[7];
	JPanel top, center;
	
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
	
	public table() {
		super("전체테이블");
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
		button[0]=new JButton("학생");
		button[1]=new JButton("교수");
		button[2]=new JButton("학과");
		button[3]=new JButton("강의");
		button[4]=new JButton("수강내역");
		button[5]=new JButton("등록금");
		button[6]=new JButton("동아리");
		
		top=new JPanel();
		center=new JPanel();
		for(int i=0; i<7; i++) top.add(button[i]);
		
		add("North", top);
		add("Center", center);
		
		for(int i=0; i<7; i++) button[i].addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)  {
		/*학생 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, b.departmentName, "
					+ "c.professorName, a.tuitionAccount, d.departmentName from student a, department b, "
					+ "professor c, department d where b.departmentId=a.majorId and c.professorId=a.professorId and d.departmentId=a.minorId";
			String colNames[] = {"학생번호", "학생이름", "학생전화번호", "학생이메일", 
					"전공학과명", "담당교수명", "계좌번호", "부전공학과명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[0]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*교수 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.professorId, a.professorName, a.professorAddress, a.professorPhone, a.professorEmail, b.departmentName "
					+ "from professor a, department b where b.departmentId =a.departmentId";
			String colNames[] = {"교수번호", "교수이름", "교수사무실", "교수전화번호","교수이메일", 
					"소속학과명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[1]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
							rs.getString(5), rs.getString(6)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*학과 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.departmentId, a.departmentName, a.departmentPhone, a.departmentAddress, b.professorName"
					+ " from department a, professor b where a.departmentId != 0 and b.professorId = a.professorId";
			String colNames[] = {"학과번호", "학과명", "학과전화번호", "학과사무실", 
					"학과장명"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[2]) {
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
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*강의 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId";
			String colNames[] = {"강의번호", "분반", "강의명", "담당교수명", "학과명", "요일", "교시", "취득학점", "시간", "강의실"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[3]) {
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
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*수강내역 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, b.className, c.professorName, a.midScore, a.finalScore, a.ectScore, a.scoreSum, a.grade "
					+ "from classHistory a, class b, professor c where a.classId=b.classId and a.professorId=c.professorId";
			String colNames[] = {"학생번호", "강의명", "담당교수명", "중간점수",
					"기말점수", "기타점수", "총점", "학점"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[4]) {
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
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*등록금 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select * from tuition";
			String colNames[] = {"학생번호", "연도", "학기", "등록금액", "납부금액", "납부일자"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[5]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getInt(5),rs.getString(6)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
		
		/*동아리 테이블 출력*/
		try {
			stmt=con.createStatement();
			String query="select a.clubId, a.clubName, a.numberOfStudents, a.clubAddress, b.professorName, a.presidentStudentId "
					+ "from club a, professor b  where a.professorId=b.professorId";
			String colNames[] = {"동아리번호", "동아리명", "소속 학생수", 
					"동아리실", "담당교수명", "동아리장"};
			DefaultTableModel model = new DefaultTableModel(colNames,0);
			if(e.getSource()==button[6]) {
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					model.addRow(new Object[] {
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5),rs.getInt(6)
					});
				}
	            tb=new JTable(model);
	            tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            sp=new JScrollPane(tb);
	            sp.setSize(700,320);
	            center.removeAll();
	            center.add(sp);
			}
		}
		catch(Exception e2) {
			System.out.println("오류 : " + e2);
		}
	}
}
