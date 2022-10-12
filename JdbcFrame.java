/* A java program to add,delete,modify records into Database record. 
 * Make sure that you are having a table named emp in test database in MYSQL
 * emp table is having 3 fields empid int, empname varchar(20), mobile varchar(20) 
 * Also add mysql-connector-java.jar file in build path
 */
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class JdbcFrame extends JFrame implements ActionListener {
	JLabel l1,l2,l3,l4;
	JTextField t1,t2,t3;
	JButton b1,b2,b3,b4,b5;
	GridLayout g1;
	GridLayout c1;
	JPanel p1,p2,p3,p4,p5;
	Connection cn;
	PreparedStatement pstmt;
	ResultSet rs;
	public JdbcFrame()  {
		getContentPane().setBackground(Color.PINK);
		try {
			//register driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//obtain connection
			cn=DriverManager.getConnection( "jdbc:mysql://localhost:3306/test","root","root");
		}
		catch(Exception e) {

		}
		l1=new JLabel("Employee ID ");
		l2=new JLabel("Employee Name ");
		l3=new JLabel("Mobile ");
		l4=new JLabel("                                                                             ");
		t1=new JTextField(20);
		t2=new JTextField(25);
		t3=new JTextField(20);
		//CRUD Operations C create R read U update D delete
		b1=new JButton("Add");
		b1.setBackground(Color.YELLOW);
		b2=new JButton("Delete");
		b2.setBackground(Color.MAGENTA);
		b3=new JButton("Search");
		b3.setBackground(Color.GREEN);
		b4=new JButton("Update");
		b4.setBackground(Color.ORANGE);
		b5=new JButton("Exit");
		b5.setBackground(Color.GREEN);
		p1=new JPanel();
		p1.setBackground(Color.PINK);
		p2=new JPanel();
		p2.setBackground(Color.MAGENTA);
		p3=new JPanel();
		p3.setBackground(Color.CYAN);
		p4=new JPanel();
		p4.setBackground(Color.BLACK);
		p5=new JPanel();
		p1.add(l1);
		p1.add(t1);
		p2.add(l2);
		p2.add(t2);
		p3.add(l3);
		p3.add(t3);
		p4.setLayout(new GridLayout(1,5,2,2));
		p4.add(b1);
		p4.add(b2);
		p4.add(b3);
		p4.add(b4);
		p4.add(b5);
		p5.add(l4);
		getContentPane().setLayout(new GridLayout(5,1,2,5));
		getContentPane().add(p1);
		getContentPane().add(p2);
		getContentPane().add(p3);
		getContentPane().add(p4);
		getContentPane().add(p5);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		setTitle("Java Database CRUD Demo");
		setSize(500,300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==b1){
			getContentPane().add();
		}
		else if(ae.getSource()==b2) {
			dele();
		}
		else if(ae.getSource()==b3) {
			search();
		}
		else if(ae.getSource()==b4) {
			modify();
		}
		else if(ae.getSource()==b5){
			System.exit(0);
		}
	}
	public void add() {
		try {
			String str="insert into emp values(?,?,?)";
			pstmt=cn.prepareStatement(str);
			int r;
			String n,m;
			r=Integer.parseInt(t1.getText());
			n=t2.getText();
			m=t3.getText();
			pstmt.setInt(1, r);
			pstmt.setString(2, n);
			pstmt.setString(3, m);
			pstmt.executeUpdate();
			l4.setText("Record Added Successfully");
		}
		catch(Exception e) {
			l4.setText("Connection Error / Employee ID already exists");
		}
	}
	public void dele() {
		try {
			String str="delete from emp where empid=?";
			pstmt=cn.prepareStatement(str);
			int r;
			r=Integer.parseInt(t1.getText());
			pstmt.setInt(1, r);
			pstmt.executeUpdate();
			l4.setText("Record Deleted Successfully");
		}
		catch(Exception e) {
			l4.setText("Record Not Found ");
		}
	}
	public void modify() {
		try {
			String str="update emp set empname=?,mobile=? where empid=?";
			pstmt=cn.prepareStatement(str);
			int r;
			String n,m;
			r=Integer.parseInt(t1.getText());
			n=t2.getText();
			m=t3.getText();
			pstmt.setString(1, n);
			pstmt.setString(2, m);
			pstmt.setInt(3, r);
			pstmt.executeUpdate();
			l4.setText("Record Updated Successfully");
		}
		catch(Exception e){
			l4.setText("Employee ID not found ");
		}
	}
	public void search(){
		try {
			
			String str="select * from emp where empid = ?";
			pstmt=cn.prepareStatement(str);
			int r;
			r=Integer.parseInt(t1.getText());
			pstmt.setInt(1, r);
			rs=pstmt.executeQuery();
			rs.next();
			t2.setText(rs.getString(2));
			t3.setText(rs.getString(3));
		}
		catch(Exception e) {
			l4.setText("Record Not Found ");
		}
	}
	public static void main(String [] args) throws ClassNotFoundException, SQLException{
		new JdbcFrame();
	}
}