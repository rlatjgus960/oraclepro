package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList;
		
		personList = phoneDao.getPersonList();
		
		System.out.println("******************************");
		System.out.println("*      전화번호 관리 프로그램      *");
		System.out.println("******************************");
		
		Scanner sc = new Scanner(System.in);
		
		boolean action = true;
		while(action) {
			
			System.out.println("");
			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("------------------------------------");
			System.out.print(">메뉴번호: ");
			
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
				case 1 :
					personList = phoneDao.getPersonList();
					printList(personList);
					
					break;
					
				case 2 :
					
					System.out.println("<2.등록>");
					
					
					System.out.print("이름 > ");
					String nameIn = sc.nextLine();
					
					System.out.print("휴대전화 > ");
					String hpIn = sc.nextLine();
					
					System.out.print("회사번호 > ");
					String companyIn = sc.nextLine();
					
					
					
					System.out.println("[1건 등록되었습니다.]");
					
					PersonVo personVoIn = new PersonVo(nameIn, hpIn, companyIn);
					phoneDao.personInsert(personVoIn);
					
					break;
					
				case 3 :
					
					System.out.println("<3.수정>");
					
					System.out.print("번호 > ");
					int idUp = sc.nextInt();
					sc.nextLine();
					
					System.out.print("이름 > ");
					String nameUp = sc.nextLine();
					
					System.out.print("휴대전화 > ");
					String hpUp = sc.nextLine();
					
					System.out.print("회사번호 > ");
					String companyUp = sc.nextLine();
					
					System.out.println("[1건 수정되었습니다.]");
					
					PersonVo personVoUp = new PersonVo(nameUp, hpUp, companyUp, idUp);
					phoneDao.personUpdate(personVoUp);
					
					break;
					
				case 4 :
					//삭제
					System.out.println("<4.삭제>");
					
					System.out.print("번호 > ");
					int idDe = sc.nextInt();
					sc.nextLine();
										
					System.out.println("[1건 삭제되었습니다.]");
					
					PersonVo personVoDe = new PersonVo(idDe);
					phoneDao.personDelete(personVoDe);
					
					
					break;
					
				case 5 :
					//검색
					
					System.out.println("검색어를 입력해주세요.");
					System.out.print("검색어 : ");
					String search = sc.nextLine();
					

					List<PersonVo> searchList = phoneDao.getPersonList(search);
					printList(searchList);
					
					break;
			
				case 6:
					System.out.println("*******************************");
					System.out.println("*           감사합니다           *");
					System.out.println("*******************************");
					action = false;
					break;
					
				default:
					System.out.println("[다시 입력해 주세요.]");
					break;
					
			}
			
		}
		
		sc.close();

	}
	
	
	
	public static void printList (List<PersonVo> personList) {

		for (int i = 0; i < personList.size(); i++) {

			PersonVo personVo = personList.get(i);
			System.out.println(personVo.getPersonId() + "\t" + personVo.getName() + "\t" + personVo.getHp() + "\t"+ personVo.getCompany());
			
		}
		
		
		
	}
	
 
}
