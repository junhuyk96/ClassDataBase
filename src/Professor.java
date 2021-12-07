import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*���� ȭ��*/
class Professor extends JFrame implements ActionListener {
	JButton button[] = new JButton[7];
	/*0. �Է�	1. ���	2. �������� 3. �����л�����	4. �а�����
	 5. ���ǽð�ǥ		6. ����ǥ�Է�*/
	JPanel left, right, center;
	
	JTextField input[] = new JTextField[3];
	int output[] = new int[3];
	/*0. ������ȣ	1. ����	2.�б�*/
	
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
		button[3]=new JButton("�����л�����");
		button[4]=new JButton("�а�����");
		button[5]=new JButton("���ǽð�ǥ");
		button[6]=new JButton("����ǥ�Է�");
		
		left=new JPanel();
		left.setLayout(new GridLayout(1,0));
		for(int i=2; i<7; i++) left.add(button[i]);
		
		right=new JPanel();
		right.setLayout(new GridLayout(0,2,500,0));
		for(int i=0; i<3; i++) input[i]=new NumberField();
		right.add(new JLabel("������ȣ"));
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
		
		for(int i=0; i<7; i++) button[i].addActionListener(this);
	}
	
	public Professor() {
		super("����");
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
		/*������ȣ ���� �б� �Է�*/
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
			System.out.println("���� ��ȣ ���� : " + e2);
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
		
		/*���� ��� ����*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId and a.professorId = " + output[0];
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
			System.out.println("���� ��ȣ ���� : " + e2);
		}
		
		/*���� ��� �л�*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, b.departmentName, "
					+ "c.professorName, a.tuitionAccount, d.departmentName from student a, department b, "
					+ "professor c, department d where b.departmentId=a.majorId and "
					+ "c.professorId=a.professorId and d.departmentId=a.minorId and a.professorId=" + output[0];
			String colNames[] = {"�л���ȣ", "�л��̸�", "��ȭ��ȣ", "�̸����ּ�", "�а���", "��米����", "���¹�ȣ", "�������а���"};
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
			System.out.println("���� ��ȣ ���� : " + e2);
		}
		
		/*���� �Ҽ� �а�����*/
		try {
			stmt=con.createStatement();
			String query="select a.departmentId, a.departmentName, a.departmentPhone, a.departmentAddress, b.professorName "
					+ "from department a, professor b where a.departmentId IN (select departmentId from professor where professor.professorId = "
					+ output[0]
					+ ") and b.professorId = a.professorId ";
			String colNames[] = {"�а���ȣ", "�а���", "�а���ȭ��ȣ", "�а��繫��", "�а����"};
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
			System.out.println("���� ��ȣ ���� : " + e2);
		}
		
		/*���� ���� �ð�ǥ*/
		try {
			stmt=con.createStatement();
			String query="select classDay, period from class where class.professorId = " + output[0];
			String colNames[] = {"����", "����"};
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
			System.out.println("���� ��ȣ ���� : " + e2);
		}
		
		/*���� �Է�*/
		try {
			stmt=con.createStatement();
			if(e.getSource()==button[6]) {
				new grade_input(output[0]);
			}
		}
		catch(Exception e2) {
			System.out.println("���� ��ȣ ���� : " + e2);
		}
	}
	
}
/*������ ���� ������ Ŭ�� �� �� ���Ǹ� ��� �л� ���� ��� Ŭ����*/
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
	
	public class_student(int cid) {
		super("�л�����");
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
			String colNames[] = {"�л���ȣ", "�л��̸�", "��ȭ��ȣ", "�̸����ּ�", "�а���", "��米����", "���¹�ȣ", "�������а���"};
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

/*������ ����ϴ� �л����� ���������� ����ϴ� Ŭ����*/
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
	
	public student_classhistory(int sid) {
		super("�л�����");
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
			String colNames[] = {"�л���ȣ", "���Ǹ�", "������", "�߰�����", "�⸻����", "��Ÿ����", "�հ�", "����"};
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
			System.out.println("���� :" + e2);
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
		
		panel=new JPanel();
	
		
		panel.add(button[0]);
		panel.add(button[1]);
		
		add("South", panel);
		
		panel2=new JPanel();
		panel2.setLayout(new GridLayout(2,7));
		panel2.add(new JLabel("�л���ȣ"));
		panel2.add(new JLabel("���ǹ�ȣ"));
		panel2.add(new JLabel("�߰�����"));
		panel2.add(new JLabel("�⸻����"));
		panel2.add(new JLabel("��Ÿ����"));
		panel2.add(new JLabel("������"));
		panel2.add(new JLabel("����"));
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
		super("�����Է�");
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
			System.out.println("���� : " + e2);
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
			System.out.println("���� : " + e2);
		}
	}
}