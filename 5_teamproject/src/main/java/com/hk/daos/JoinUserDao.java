package com.hk.daos;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.JoinUserDto;

public class JoinUserDao extends SqlMapConfig {
	private String namespace="com.hk.joinuser.";
	
	public JoinUserDto getLogin(String id, String password) {
		JoinUserDto dto=null;
		SqlSession sqlSession=null;
		
		try {
			Map<String, String>map=new HashMap<>();
			map.put("id", id);
			map.put("password", password);
			sqlSession=getSqlSessionFactory().openSession(false);
			dto=sqlSession.selectOne(namespace+"getLogin", map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto; 
	}
}
