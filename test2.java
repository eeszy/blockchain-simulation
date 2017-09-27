

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class test2 {
		public static String dbDriver = "com.mysql.jdbc.Driver";
		public static String dbUrl = "jdbc:mysql://localhost:3306/test";
		public static String dbUser = "root";
		public static String dbPass = "941216";
		public int j=1;
		public static Object[][] rowData ;
        public static Connection getConn()
        {
        	Connection conn=null;
        	try{
        		Class.forName(dbDriver);}
        	catch(ClassNotFoundException e)
        	{e.printStackTrace();
        	}
        	try{conn=DriverManager.getConnection(dbUrl, dbUser, dbPass);
	}
        	catch(SQLException e)
        	{e.printStackTrace();}
        	return conn;}

	public int insert()
	{int i=0;
	String sql="insert into blockchain4 values(?,?,?,?,?,?,?,?)";
	Connection cnn=getConn();

	try{
		PreparedStatement preStmt=cnn.prepareStatement(sql);
		String x1=visual6.textField_4.getText();
		String x2=visual6.textField.getText();
		String x3=visual6.textField_1.getText();
		int x33=Integer.parseInt(x3);
		String x4=visual6.textField_2.getText();
		String x5=visual6.textField_3.getText();
		int x55=Integer.parseInt(x5);
		preStmt.setString(1,x1);
		preStmt.setDouble(2,Math.random()*1000000);
		preStmt.setString(3,x2);
		preStmt.setInt(4,x33);
		preStmt.setInt(5,x55);
		preStmt.setInt(6,x33*x55); 
		preStmt.setString(7,x4);
		preStmt.setTimestamp(8,new java.sql.Timestamp(new java.util.Date().getTime()));
		i=preStmt.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();}
	return i;
}
	
}