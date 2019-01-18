package com.douzone.jdbc.hr.test;

import java.util.List;
import java.util.Scanner;

import com.douzone.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDaoTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.print("이름을 입력하세요 > ");
//		String name = sc.nextLine();
//		getListTest(name);
		String str = sc.nextLine();
		getSalary(str);
	}
	
	public static void getListTest(String name){
		List<EmployeeVo> list = new EmployeeDao().getList(name);
		for(EmployeeVo vo : list) {
			System.out.println(vo.getNo()+":"+vo.getFirstName()+
					" "+vo.getLastName()+":"+vo.getHireDate());
		}
	}
	
	public static void getSalary(String str) {
		String[] tokens = str.split(" ");
		int s1 = Integer.parseInt(tokens[0]);
		int s2 = Integer.parseInt(tokens[1]);
		
		List<EmployeeVo> list = new EmployeeDao().getSalary(s1, s2);
		for(EmployeeVo vo : list) {
			System.out.println(vo.getFirstName()+" "+vo.getLastName()+
					"   "+vo.getSalary());
		}
	}
}
