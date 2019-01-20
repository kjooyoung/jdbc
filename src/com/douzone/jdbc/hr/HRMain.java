package com.douzone.jdbc.hr;

import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class HRMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. 사원 검색");
		System.out.println("2. 연봉 검색");
		System.out.print("> ");
		int num = sc.nextInt();
		if(num == 1) {
			getList();
		}else if(num == 2) {
			getSalary();
		}
	}
	
	public static void getList() {
		EmployeeDao dao = new EmployeeDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요.");
		System.out.print("> ");
		String name = sc.nextLine();
		List<EmployeeVo> list = dao.getList(name);
		for(EmployeeVo vo : list) {
			System.out.println(vo.getNo()+":"+vo.getFirstName()+
					" "+vo.getLastName()+":"+vo.getHireDate());
		}
	}
	
	public static void getSalary() {
		EmployeeDao dao = new EmployeeDao();
		Scanner sc = new Scanner(System.in);
		System.out.println("연봉 범위를 입력하세요.");
		System.out.print("> ");
		String str = sc.nextLine();
		
		String[] tokens = str.split(" ");
		int s1 = Integer.parseInt(tokens[0]);
		int s2 = Integer.parseInt(tokens[1]);
		List<EmployeeVo> list = dao.getSalary(s1, s2);
		for(EmployeeVo vo : list) {
			System.out.println(vo.getFirstName()+" "+vo.getLastName()+
					"   "+vo.getSalary());
		}
	}
}
