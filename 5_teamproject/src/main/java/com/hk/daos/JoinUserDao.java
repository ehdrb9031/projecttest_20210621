package com.hk.daos;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.JoinUserDto;


public class JoinUserDao extends SqlMapConfig {
	private String namespace="com.hk.login.";
	
	public JoinUserDto getLogin(String id, String password) {

		JoinUserDto dto=new JoinUserDto();
		
		SqlSession sqlSession=null;
		
		try {
			Map<String, String>map=new HashMap<String, String>();
			map.put("id", id);//보내는 이름과 Mapper의 이름이 같이야 한다.
			map.put("password", password);
			sqlSession=getSqlSessionFactory().openSession(true);
			dto=sqlSession.selectOne(namespace+"getLogin",map);
		} catch (Exception e) {
			System.out.println("JDBC실패:getLogin"+getClass());//object의 4대 메서드 getClass()
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	} 
	
	public boolean insertUser(JoinUserDto dto) {
		int count=0;
		
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertUser", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertUser()"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
