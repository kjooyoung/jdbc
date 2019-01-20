package com.douzone.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.vo.AuthorVo;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class BookDao {
	
	public List<BookVo> getList(){
		List<BookVo> list = new ArrayList<BookVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			//3. Statement 객체를 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행 (세미콜론 빼기)
			String sql = "select a.no, a.title, a.status, b.name "
					+ "from book a, author b "
					+ "where a.author_no = b.no "
					+ "order by a.no asc";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while(rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public BookVo getList(long no) {
		BookVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select a.no, a.title, a.status, b.name "
					+ "from book a, author b "
					+ "where a.author_no = b.no "
					+ "and a.no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BookVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setStatus(rs.getString(3));
				vo.setAuthorName(rs.getString(4));
			}
			
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
		return vo;
	}
	public boolean insert(BookVo book) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into book values(null,?,'대여가능',?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book.getTitle());
			pstmt.setLong(2, book.getAuthorNo());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (Exception e) {
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
	
	public boolean update(long no, String status) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update book set status = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, status);
			pstmt.setLong(2, no);
			
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (Exception e) {
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
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}
		return conn;
	}

	

	
}
