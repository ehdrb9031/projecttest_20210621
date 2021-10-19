package com.hk.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.OffDto;

public class OffDao extends SqlMapConfig {

	private String namespace="com.hk.off.";
	
	//근무변경 insert
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
	
	//근무변경 조회
	public List<OffDto> getOffList(String id) {
		List<OffDto> list=new ArrayList<OffDto>();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getOffList",id);
		} catch (Exception e) {
			System.out.println("JDBC실패:getOffList():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
}
