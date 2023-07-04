package com.ikik.ex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestOjbdc {
	
	@Test
	public void calcTest() {
		Calc calc = new Calc();
		int res = calc.add(5, 6);
		
		// 예상결과값, 실제결과값
//		assertEquals(4, res);		// Failure Trace
		assertEquals(11, res);
	}
	
	@Test
	public void ojdbcTest() {
	
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. 커넥션 생성
			Connection conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:orcl", "library", "1234");
			
			ResultSet rs = conn
					.createStatement()
					.executeQuery("select to_char(sysdate,'yyyy/mm/dd')||'입니다' from dual");
			rs.next();
			// 편의를 위해서 값을 출력하고 끝내는 경우가 많은데
			// Test case를 만들어두고 모든 Test를 통과하면.. 모든결과가 일치하면...
			// 메서드를 실행할때마다..
			System.out.println(rs.getString(1));
			System.out.println(conn);
			
			assertNotNull(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("라이브러리 로드중 오류가 발생 하였습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
