package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	//아이디 중복체크: 가입할 아이디가 기존 DB에 존재하는 여부 체크-select문실행, 파리미터 : 가입할 ID
		public String idChk(String id) {
			String resultId=null;
			SqlSession sqlSession=null;
			try {

				sqlSession=getSqlSessionFactory().openSession(false);
				id=sqlSession.selectOne(namespace+"idChk", id);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return id; 
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

	public boolean updateList(String[] seqs) {
		int count=0;
		SqlSession sqlSession=null;

		try {

			sqlSession=getSqlSessionFactory().openSession(false);

			for(int i=0; i < seqs.length; i++) {
				String seq=seqs[i];
				sqlSession.delete(namespace+"updateList", seq);
			}
			//map.put("seqs", seqs);
			//count=sqlSession.update(namespace+"delList", map);
			count=1;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("JDBC실패:updateList()"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}

	//나의정보 수정
	public boolean updateUser(JoinUserDto dto) {
		SqlSession sqlSession=null;
		int count=0;
		try {  
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			count=sqlSession.update(namespace+"updateUser",dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:updateUser():"+getClass());
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}


	//상세정보 조회
	public JoinUserDto getUser(String id){
		JoinUserDto  dto=new JoinUserDto ();
		SqlSession sqlSession=null;

		try {  
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			dto=sqlSession.selectOne(namespace+"getUser",id);
		} catch (Exception e) {
			System.out.println("JDBC실패:getUser():"+getClass());
		}finally {
			sqlSession.close();
		}
		return dto;
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
	
	//체크된 간호사 리스트 구하기
	public List<JoinUserDto> addNurseList(String[] seqs) {
		List<JoinUserDto> list=new ArrayList<JoinUserDto>();
		SqlSession sqlSession=null;

		try {
			sqlSession=getSqlSessionFactory().openSession(false);

			for(int i=0; i < seqs.length; i++) {
				String seq=seqs[i];
				list=sqlSession.selectList(namespace+"addNurseList", seq);
			}
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("JDBC실패:addNurseList():"+getClass());
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	public String getRole(String id) {
		String role="";
		SqlSession sqlSession=null;

		try {
			SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
			sqlSession=sqlSessionFactory.openSession(true);
			role=sqlSession.selectOne(namespace+"getRole",id);
		} catch (Exception e) {
			System.out.println("JDBC실패:getRole():"+getClass());
		}finally {
			sqlSession.close();
		}
		return role;
	}
	
}
