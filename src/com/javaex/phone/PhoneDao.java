package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// DB연결
	public void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	//추가
	public int personInsert(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	//수정
	public int personUpdate(PersonVo personVo) {

		int count = -1;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += "       hp = ?, ";
			query += "       company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}

		this.close();

		return count;
	}

	//삭제

	public int personDelete(PersonVo personVo) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
		   
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";  
	        query += " delete from person "; 
	        query += " where person_id = ? ";
	         
		    pstmt = conn.prepareStatement(query); 
		    pstmt.setInt(1,personVo.getPersonId());
		         
		    count = pstmt.executeUpdate(); 
		        
	        // 4.결과처리
		    System.out.println(count +"건 삭제");

		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		
		this.close();

		
		return count;
	}
	
	
	//리스트
	public List<PersonVo> getPersonList() {
		
		//리스트 생성
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		this.getConnection();

		try {
		  
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select  person_id, ";
		    query += "         name, ";
	        query += "         hp, ";
	        query += "         company ";
		    query += " from person ";
		    query += " order by person_id asc ";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				
				personList.add(personVo);
			}
		    
		    

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		this.close();
		
		return personList;
		
	}
	
	//검색
	public List<PersonVo> getPersonList(String search) {
		
		//리스트 생성
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		this.getConnection();

		try {
		  
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " select  person_id, ";
		    query += "         name, ";
	        query += "         hp, ";
	        query += "         company ";
		    query += " from person ";
		    query += " where (name || hp || company) like " + "'%" + search + "%' ";
		    query += " order by person_id asc ";
		    
		    pstmt = conn.prepareStatement(query);
		    
		    rs = pstmt.executeQuery();
			
		    // 4.결과처리
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				
				personList.add(personVo);
			}
		    
		    

		
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		this.close();
		
		return personList;
		
	}
	
	

}
