package com.hk.daos;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.JoinUserDto;



public class JoinUserDao extends SqlMapConfig {
	private String namespace="com.hk.joinuser.";
	
	//로그인
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

	
	// 회원가입 
public boolean insertUser(JoinUserDto dto) {
		
		int count=0;
		SqlSession sqlSession=null;
	
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertUser", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertUser():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
