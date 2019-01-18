package com.douzone.jdbc.bookshop.test;

import java.util.ArrayList;
import java.util.List;

import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		insertTest("아리랑",6);
		insertTest("젊은그들",4);
		insertTest("아프니까 청춘이다",5);
		insertTest("귀천",3);
		insertTest("태백산맥",6);
		insertTest("풀하우스",7);
		
		getListTest();

	}
	
	public static void getListTest() {
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void insertTest(String title, long no) {
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setAuthorNo(no);
		new BookDao().insert(vo);
	}
}
