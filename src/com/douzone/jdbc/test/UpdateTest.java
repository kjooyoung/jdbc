package com.douzone.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
	public static void main(String[] args) {
		boolean result = update("Fluffy","김주영","m");
		System.out.println(result);
	}
	
	public static boolean update(String name, String owner, String gender) {
		//PreparedStatment
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			//1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			//2. 연결하기 (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("연결 성공");
			
			//3. SQL문 준비
			String sql = "update pet set owner = ?, gender = ? where name = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, owner);
			pstmt.setString(2, gender);
			pstmt.setString(3, name);
			
			//5. SQL문 실행 (세미콜론 빼기)
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
