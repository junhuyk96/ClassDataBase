import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*�л� ȭ��*/
class Student extends JFrame implements ActionListener {
	JButton button[] = new JButton[6];
	/*0. �Է�	1. ���	2. ��������	3. �ð�ǥ 4. ���Ƹ�����	5. ����ǥ*/
	JPanel left, right, center;
	
	JTextField input[] = new JTextField[3];
	int output[] = new int[3];
	/*0. �л���ȣ	1. ����	2.�б�*/
	
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
		button[0]=new JButton("�Է�");
		button[1]=new JButton("���");
		button[2]=new JButton("��������");
		button[3]=new JButton("�ð�ǥ");
		button[4]=new JButton("���Ƹ�����");
		button[5]=new JButton("����ǥ");
		
		left=new JPanel();
		left.setLayout(new GridLayout(1,0));
		for(int i=2; i<6; i++) left.add(button[i]);
		
		right=new JPanel();
		right.setLayout(new GridLayout(0,2,100,0));
		for(int i=0; i<3; i++) input[i]=new NumberField();
		right.add(new JLabel("�л���ȣ"));
		right.add(input[0]);
		right.add(new JLabel("����"));
		right.add(input[1]);
		right.add(new JLabel("�б�"));
		right.add(input[2]);
		for(int i=0; i<2; i++) right.add(button[i]);
		
		center=new JPanel();
		
		add("North", left);
		add("South", right);
		add("Center", center);
		
		for(int i=0; i<6; i++) button[i].addActionListener(this);
	}
	
	public Student() {
		super("�л�");
		init();
		conDB();
		setVisible(true);
	    setSize(1000, 500); //������ġ,������ġ,���α���,���α���
	   
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		/*�л���ȣ�� �Է�*/
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
			System.out.println("�л� ��ȣ ���� : " + e2);
		}
		
		/*�Է�â�� �� â���� ����*/
		try {
			if(e.getSource()==button[1]) {
				for(int i=0; i<3; i++) input[i].setText("");
			}
		}
		catch(Exception e2) {
			System.out.println("���� : " + e2);
		}
		
		/*��������*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId and"
					+ " a.classId IN (select classId from classHistory where classHistory.studentId="
					+ output[0] + ")";
			String colNames[] = {"���ǹ�ȣ", "�й�", "���Ǹ�", "��米����", "�а���", "����", "����", "�������", "�ð�", "���ǽ�"};
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
			System.out.println("�л� ��ȣ ���� : " + e2);
		}
		
		/*�л� �ð�ǥ*/
		try {
			stmt=con.createStatement();
			String query="select classDay, period from class where classId IN (select classId from classHistory "
					+ "where classHistory.studentId=" + output[0]+")";
			String colNames[] = {"����", "����"};
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
			System.out.println("�л� ��ȣ ���� : " + e2);
		}
		
		/*���Ƹ�����*/
		try {
			int a=-1;
			stmt=con.createStatement();
			String query="select a.clubId, a.clubName, a.numberOfStudents, a.clubAddress, b.professorName, a.presidentStudentId "
					+ "from club a, professor b  where a.professorId=b.professorId and"
					+" a.clubId IN (select clubId from clubStudentList "
					+ "where clubStudentList.studentId =" + output[0] + ")";
			String colNames[] = {"���Ƹ���ȣ", "���Ƹ���", "�л���", "���Ƹ���", "��米����", "���Ƹ���"};
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
			System.out.println("�л� ��ȣ ���� : " + e2);
		}
		
		/*����ǥ*/
		try {
			int cnt=0;
			double score=0;
			String s;
			stmt=con.createStatement();
			String query="select a.classId, a.className, a.credits, b.grade "
					+ "from class a, classHistory b "
					+ "where a.classId = b.classId and b.studentId = " + output[0];
			String colNames[] = {"���ǹ�ȣ", "���Ǹ�", "�������", "����"};
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
			System.out.println("�л� ��ȣ ���� : " + e2);
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
	
	public club(int sid) {
		super("���Ƹ��� ����");
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
			String colNames[] = {"�л���ȣ", "�л��̸�", "�л���ȭ��ȣ", "�л��̸���", "�����а���", "��米����", "���¹�ȣ", "�������а���"};
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
			System.out.println("���� :" + e2);
		}
	}
	
	
}