import java.sql.*;
import java.util.Scanner;

public class JDBCAllDemo {
	static Connection con=null;
	static void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver is registered"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");  
			System.out.println("Connection is established");
		}
		catch(Exception e) {
		}
	}
	static void addrecord() {
		try {
			getConnection();
			Statement stmt=con.createStatement();
			stmt.executeQuery("insert into student values(13,'Tom',82)");
			System.out.println("record is inserted");
			con.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		} 
	}
	static void deleterecord() {
		try {
			Statement stmt=con.createStatement();
			stmt.executeQuery("delete from student where roll = 11");
			System.out.println("record is deleted");
			con.close();
		} catch ( SQLException e) {
			e.printStackTrace();
		} 

	}
	public static void main(String[] args) {
		//CRUD operation demo
		// Create Read Update Delete
		int opt=0;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("1. Insert a new Record ");
			System.out.println("2. Delete any record ");
			System.out.println("3. Update any record ");
			System.out.println("4. Show all records ");
			System.out.println("5. Search any record ");
			System.out.println("6. Exit ");
			System.out.println("Enter your option ");
			opt=sc.nextInt();
			switch(opt) {
			case 1:addrecord();
					break;
			case 2: deleterecord();
					break;
			
			}
			
		}while(opt!=6);
	}
}
