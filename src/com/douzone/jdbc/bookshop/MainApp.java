package com.douzone.jdbc.bookshop;

import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.bookshop.dao.BookDao;
import com.douzone.jdbc.bookshop.vo.BookVo;

public class MainApp {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		displayBookInfo();
		
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		long num = scanner.nextLong();
		scanner.close();
		
		rent(num);
	}
	
	private static void rent(long no) {
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo = dao.getList(no);
		if(vo.getStatus().equals("대여중")) {
			System.out.println("이미 대여중입니다.");
		}else {
			new BookDao().update(no, "대여중");
			System.out.println(vo.getTitle()+"이(가) 대여 됐습니다.");
		}
		
		
	}
	private static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		List<BookVo> list = new BookDao().getList();
		for(BookVo vo : list) {
			System.out.println("("+vo.getNo()+
					") 책 제목:"+vo.getTitle()+
					", 작가:"+vo.getAuthorName()+
					", 대여 유무:"+vo.getStatus());
		}
	}
}
