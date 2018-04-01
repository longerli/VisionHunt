package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	static Connection conn=null;
	static String url = "jdbc:mysql://localhost:3306/visionhunt";
	
	
	public static Connection getConnect(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection(url,"root","123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}


	public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) 
			throws SQLException {
		// TODO Auto-generated method stub
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}finally {
				try {
					if(pstm!=null){
						pstm.close();
					}
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		}
		
		
	}

}
