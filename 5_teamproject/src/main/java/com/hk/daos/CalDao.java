package com.hk.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
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
	
	//나의 근무표 가져오기
	public List<String> getCal(String id) {
		List<String> list=new ArrayList<String>();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getCal",id);
		} catch (Exception e) {
			System.out.println("JDBC실패:getCal():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	//근무표가 작성된 (wdate가 있는)id를 가져온다
	public List<String> getGivenId(String yyyymm) {
		List<String> list=new ArrayList<String>();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getGivenId",yyyymm);
		} catch (Exception e) {
			System.out.println("JDBC실패:getGivenId():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	public List<CalDto> getAllList() {
		List<CalDto> list=new ArrayList<CalDto>();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getAllList");
		} catch (Exception e) {
			System.out.println("JDBC실패:getAllList():"+getClass());
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	
}