package com.douzone.jdbc.bookshop.test;

import java.util.List;

import com.douzone.jdbc.bookshop.dao.AuthorDao;
import com.douzone.jdbc.bookshop.vo.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		insertTest("조정래");
		insertTest("원수연");
		getListTest();
		
	}
	
	public static void insertTest(String name) {
		AuthorVo vo = new AuthorVo();
		vo.setName(name);
		vo.setBio("");
		
		new AuthorDao().insert(vo);
	}
	
	public static void getListTest() {
		List<AuthorVo> list = new AuthorDao().getList();
		for(AuthorVo vo : list) {
			System.out.println(vo);
		}
	}
}
