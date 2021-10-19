package com.hk.daos;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.OffDto;

public class OffDao extends SqlMapConfig {

	private String namespace="com.hk.off.";
	
	public boolean insertOff(OffDto dto) {
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertOff", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertOff():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
}
