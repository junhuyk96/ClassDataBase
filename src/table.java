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
	
	public table() {
		super("��ü���̺�");
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
		button[0]=new JButton("�л�");
		button[1]=new JButton("����");
		button[2]=new JButton("�а�");
		button[3]=new JButton("����");
		button[4]=new JButton("��������");
		button[5]=new JButton("��ϱ�");
		button[6]=new JButton("���Ƹ�");
		
		top=new JPanel();
		center=new JPanel();
		for(int i=0; i<7; i++) top.add(button[i]);
		
		add("North", top);
		add("Center", center);
		
		for(int i=0; i<7; i++) button[i].addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)  {
		/*�л� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, a.studentName, a.studentPhone, a.studentEmail, b.departmentName, "
					+ "c.professorName, a.tuitionAccount, d.departmentName from student a, department b, "
					+ "professor c, department d where b.departmentId=a.majorId and c.professorId=a.professorId and d.departmentId=a.minorId";
			String colNames[] = {"�л���ȣ", "�л��̸�", "�л���ȭ��ȣ", "�л��̸���", 
					"�����а���", "��米����", "���¹�ȣ", "�������а���"};
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
			System.out.println("���� : " + e2);
		}
		
		/*���� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.professorId, a.professorName, a.professorAddress, a.professorPhone, a.professorEmail, b.departmentName "
					+ "from professor a, department b where b.departmentId =a.departmentId";
			String colNames[] = {"������ȣ", "�����̸�", "�����繫��", "������ȭ��ȣ","�����̸���", 
					"�Ҽ��а���"};
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
			System.out.println("���� : " + e2);
		}
		
		/*�а� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.departmentId, a.departmentName, a.departmentPhone, a.departmentAddress, b.professorName"
					+ " from department a, professor b where a.departmentId != 0 and b.professorId = a.professorId";
			String colNames[] = {"�а���ȣ", "�а���", "�а���ȭ��ȣ", "�а��繫��", 
					"�а����"};
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
			System.out.println("���� : " + e2);
		}
		
		/*���� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.classId, a.divideNum, a.className, b.professorName, c.departmentName, a.classDay, a.period, a.credits, "
					+ "a.progressingTime, a.classAddress from class a, professor b, department c where "
					+ "a.professorId=b.professorId and a.departmentId=c.departmentId";
			String colNames[] = {"���ǹ�ȣ", "�й�", "���Ǹ�", "��米����", "�а���", "����", "����", "�������", "�ð�", "���ǽ�"};
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
			System.out.println("���� : " + e2);
		}
		
		/*�������� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.studentId, b.className, c.professorName, a.midScore, a.finalScore, a.ectScore, a.scoreSum, a.grade "
					+ "from classHistory a, class b, professor c where a.classId=b.classId and a.professorId=c.professorId";
			String colNames[] = {"�л���ȣ", "���Ǹ�", "��米����", "�߰�����",
					"�⸻����", "��Ÿ����", "����", "����"};
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
			System.out.println("���� : " + e2);
		}
		
		/*��ϱ� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select * from tuition";
			String colNames[] = {"�л���ȣ", "����", "�б�", "��ϱݾ�", "���αݾ�", "��������"};
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
			System.out.println("���� : " + e2);
		}
		
		/*���Ƹ� ���̺� ���*/
		try {
			stmt=con.createStatement();
			String query="select a.clubId, a.clubName, a.numberOfStudents, a.clubAddress, b.professorName, a.presidentStudentId "
					+ "from club a, professor b  where a.professorId=b.professorId";
			String colNames[] = {"���Ƹ���ȣ", "���Ƹ���", "�Ҽ� �л���", 
					"���Ƹ���", "��米����", "���Ƹ���"};
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
			System.out.println("���� : " + e2);
		}
	}
}
