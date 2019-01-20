package com.douzone.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao {

	public List<EmployeeVo> getList(String name){
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select * from employees "
					+ "where first_name like ? or last_name like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			pstmt.setString(2, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(rs.getInt(1));
				vo.setBirthDate(rs.getString(2));
				vo.setFirstName(rs.getString(3));
				vo.setLastName(rs.getString(4));
				vo.setGender(rs.getString(5));
				vo.setHireDate(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<EmployeeVo> getSalary(int salary1, int salary2){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmployeeVo> list = new ArrayList<EmployeeVo>();
		try {
			conn = getConnection();
			String sql = "select a.first_name, a.last_name, b.salary" + 
					" from employees a, salaries b" + 
					" where a.emp_no = b.emp_no" + 
					" and to_date = '9999-01-01'" + 
					" and b.salary > ? and b.salary < ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, salary1);
			pstmt.setInt(2, salary2);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVo vo = new EmployeeVo();
				vo.setFirstName(rs.getString(1));
				vo.setLastName(rs.getString(2));
				vo.setSalary(rs.getInt(3));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return conn;
	}
}
