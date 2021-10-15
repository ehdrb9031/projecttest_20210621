package com.hk.daos;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.CalDto;
import com.hk.dtos.JoinUserDto;

public class CalDao extends SqlMapConfig {
	
	private String namespace="com.hk.cal.";
	
	//wdate넣기
	public boolean insertCal(String id,String []works) {

		int count=0;
		SqlSession sqlSession=null;
		String[] ids= {id};
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			Map<String, String[]>map=new HashMap<String, String[]>();
			map.put("ids", ids);
			map.put("works", works);
			count=sqlSession.insert(namespace+"insertCal", map);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertCal():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
