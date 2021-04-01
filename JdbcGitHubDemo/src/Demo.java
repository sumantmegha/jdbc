import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
	public static void main(String[] args) {
		try {
			//register driver
			Class.forName("com.mysql.jdbc.Driver"); 
			//obtain connection
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/test","root","admin");
			//driver for Oracle database
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection for Oracle database
			//cn=DriverManager.getConnection("jdbc:oracle:thin:@system:admin","java","java");
			System.out.println("Connection obtained");
			//driver for type 1 driver
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//cn=DriverManager.getConnection("jdbc:odbc:empdb");
			//get Statement referance
			Statement stmt=con.createStatement();
			//fire SQL query
			stmt.executeUpdate("insert into emp values(110,'Radhika',4000)"); 
			//you can change the query like update or delete 
			ResultSet rs=stmt.executeQuery("select * from emp"); 
			while(rs.next()) 
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			//close the connection
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
