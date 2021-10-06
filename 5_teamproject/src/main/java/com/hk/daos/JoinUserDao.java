package com.hk.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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


	//유저목록 조회
	public List<JoinUserDto> getUserList() {
		List<JoinUserDto> list=new ArrayList<JoinUserDto>();
		SqlSession sqlSession=null;
		
		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			list=sqlSession.selectList(namespace+"getUserList");
		} catch (Exception e) {
			System.out.println("JDBC실패:getUserList():"+getClass());
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	public boolean deleteList(String[] seqs) {
		int count=0;
		SqlSession sqlSession=null;
	
		try {
			sqlSession=getSqlSessionFactory().openSession(false);
			//Map<String, String[]>map=new HashMap<String, String[]>();
			for(int i=0; i < seqs.length; i++) {
				String seq=seqs[i];
				sqlSession.delete(namespace+"deleteList", seq);
			}
			//map.put("seqs", seqs);
			//count=sqlSession.update(namespace+"delList", map);
			count=1;
			sqlSession.commit();

			

		} catch (Exception e) {
			System.out.println("JDBC실패:deleteList()"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	

	//재직중인 유저목록 조회
    public List<JoinUserDto> getPreUserList() {
       List<JoinUserDto> list=new ArrayList<JoinUserDto>();
       SqlSession sqlSession=null;
       
       try {
          SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
          sqlSession=sqlSessionFactory.openSession(true);
          list=sqlSession.selectList(namespace+"getPreUserList");
       } catch (Exception e) {
          System.out.println("JDBC실패:getPreUserList():"+getClass());
       }finally {
          sqlSession.close();
       }
       return list;
    }

}