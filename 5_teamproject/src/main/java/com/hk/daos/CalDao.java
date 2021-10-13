package com.hk.daos;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.CalDto;
import com.hk.dtos.JoinUserDto;

public class CalDao extends SqlMapConfig {
	
	private String namespace="com.hk.cal.";
	
	//wdate넣기
	public boolean insertCal(CalDto dto) {

		int count=0;
		SqlSession sqlSession=null;

		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertCal", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertCal():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
