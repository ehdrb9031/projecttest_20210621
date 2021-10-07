package com.hk.daos;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.NoticeDto;

public class NoticeDao extends SqlMapConfig {
	private String namespace="com.hk.notice";
	
	public boolean insertNotice(NoticeDto dto) {
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertNotice", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertUser():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
