package com.hk.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.JoinUserDto;
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
	
	//자신이 쓴 근무변경 조회
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
	
	//근무변경 전체 조회
	public List<OffDto> getAllOffList(String id) {
		List<OffDto> list=new ArrayList<OffDto>();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getAllOffList",id);
		} catch (Exception e) {
			System.out.println("JDBC실패:getAllOffList():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	//근무변경글 상세조회
	public OffDto geSelectOff(String seq) {
		OffDto dto=new OffDto();
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			dto=sqlSession.selectOne(namespace+"geSelectOff",seq);
		} catch (Exception e) {
			System.out.println("JDBC실패:geSelectOff():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	public boolean delOffList(String[] noseqs) {
		int count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(false);
			for(int i =0; i <noseqs.length; i++) {		
				String seq=noseqs[i];
				sqlSession.delete(namespace+"delOffList", seq);
			}
			count = 1;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("JDBC실패:delOffList():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	//승인 반려 update
	public boolean updateOff(String seq) {
		SqlSession sqlSession=null;
		int count=0;
		try {  
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			count=sqlSession.update(namespace+"updateOff",seq);
		} catch (Exception e) {
			System.out.println("JDBC실패:updateOff():"+getClass());
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
