package com.javaex.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
			
		//시작화면
		System.out.println("*************************");
		System.out.println("*    전화번호 관리 프로그램    *");
		System.out.println("*************************");
		System.out.println("");
		
		while (true) {
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("-----------------------------");
			System.out.print(">메뉴번호:");
			
			int select = sc.nextInt();
			
			if (select == 1) {
				//리스트 생성
				List<Person> pList = new ArrayList<Person>(); 
				
				//파일을 읽어 pList를 반환하는 메소드 호출
				pList = readFile(pList);
				
				//pList 전체를 출력하는 메소드 호출
				printList(pList);
				
			} else if (select == 2) {
				//등록
				sc.nextLine();
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = sc.nextLine();
				System.out.print(">휴대전화: ");
				String hp = sc.nextLine();
				System.out.print(">회사전화: ");
				String company = sc.nextLine();
				
				//리스트 생성
				List<Person> pList = new ArrayList<Person>(); 
				
				//파일을 읽어 pList를 반환하는 메소드 호출
				pList = readFile(pList);
				
				Person p = new Person(name, hp, company);
				
				pList.add(p);
				
				//pList를 입력받아 파일을 쓰는 메소드
				writeFile(pList);
			
				System.out.println("[등록되었습니다.]");
				System.out.println("");
				
			} else if (select == 3) {
				//삭제
				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
				int delNum = sc.nextInt();
				
				//리스트 생성
				List<Person> pList = new ArrayList<Person>(); 
				
				//파일을 읽어 pList를 반환하는 메소드 호출
				pList = readFile(pList);
				
				pList.remove(delNum-1);
				
				//pList를 입력받아 파일을 쓰는 메소드
				writeFile(pList);
				
				System.out.println("[삭제되었습니다.]");
				System.out.println("");
				
				
			} else if (select == 4) {
				//검색
				sc.nextLine();
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String keyword = sc.nextLine();
				
				//리스트 생성
				List<Person> pList = new ArrayList<Person>(); 
				
				//파일을 읽어 pList를 반환하는 메소드 호출
				pList = readFile(pList);
				
				//contains(): 문자열에 특정 문자열이 포함되면 true 리턴
				for(int i=0; i<pList.size(); i++) {
					if (pList.get(i).getName().contains(keyword)) {
						System.out.println(i+1 + ".\t" + pList.get(i).getName() + "\t" + pList.get(i).getHp() + "\t" + pList.get(i).getCompany());
					}
				}
				
				System.out.println("");
				
				
			} else if (select == 5) {
				//종료
				System.out.println("");
				System.out.println("*************************");
				System.out.println("*        감사합니다        *");
				System.out.println("*************************");
				
				break;
				
			} else {
				//없는메뉴
				System.out.println("[다시 입력해 주세요.]");
				System.out.println("");
			}
			
		}
	
		sc.close();
		
	}
	
	//파일을 읽어 pList를 반환하는 메소드
	public static List<Person> readFile(List<Person> pList) throws IOException {
		Reader fr = new FileReader("./PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		
		while (true) {
			
			String data = br.readLine();
			
			if (data == null) {
				break;
			}
			
			//Person 객체를 생성하는 메소드 호출
			Person p01 = makePerson(data);
			
			pList.add(p01);
			
		}
		
		br.close();
		
		return pList;
	}
	
	//pList를 입력받아 파일을 쓰는 메소드
	public static void writeFile(List<Person> pList) throws IOException {
		Writer fw = new FileWriter("./PhoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(Person p : pList) {
			bw.write(p.getName() + "," + p.getHp() + "," + p.getCompany());
			bw.newLine();
		}
		
		bw.close();
	}
	
	//Person 객체를 생성하는 메소드
	public static Person makePerson(String data) {
		String[] dArray = data.split(",");
		
		String name = dArray[0];
		String hp = dArray[1];
		String company = dArray[2];
		
		Person p = new Person(name, hp, company);
		
		return p;
	}
	
	//pList 전체를 출력하는 메소드
	public static void printList(List<Person> pList) {
		System.out.println("<1.리스트>");
		for(int i=0; i<pList.size(); i++) {
			System.out.println(i+1 + ".\t" + pList.get(i).getName() + "\t" + pList.get(i).getHp() + "\t" + pList.get(i).getCompany());
		}
		System.out.println("");
	}

}
