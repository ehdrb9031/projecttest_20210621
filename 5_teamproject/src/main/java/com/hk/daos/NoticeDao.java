package com.hk.daos;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.hk.config.SqlMapConfig;
import com.hk.dtos.NoticeDto;

public class NoticeDao extends SqlMapConfig {
	private String namespace="com.hk.notice.";
	
	public boolean insertNotice(NoticeDto dto) {
		int count = 0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.insert(namespace+"insertNotice", dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:insertUser():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	public List<NoticeDto> getNoticeList() {
	      List<NoticeDto> list=new ArrayList<NoticeDto>();
	      SqlSession sqlSession=null;
	      
	      try {
	         SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
	         sqlSession=sqlSessionFactory.openSession(true);
	         list=sqlSession.selectList(namespace+"getnoticelist");
	      } catch (Exception e) {
	         System.out.println("JDBC실패:getNoticeList():"+getClass());
	      }finally {
	         sqlSession.close();
	      }
	      return list;
	   }
	
	public boolean delNotice(String[] noseqs) {
		int count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(false);
			for(int i =0; i <noseqs.length; i++) {		
				String seq=noseqs[i];
				sqlSession.delete(namespace+"delnotice", seq);
			}
			count = 1;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("JDBC실패:delBorad():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
	
	public NoticeDto detailNotice(int seq) {
		NoticeDto dto = new NoticeDto();
		SqlSession sqlSession = null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			dto=sqlSession.selectOne(namespace+"detailnotice",seq);
		}catch (Exception e) {
			System.out.println("JDBC실패:detailNotice():"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dto;
	}
	
	public boolean updateNotice(NoticeDto dto) {
		int count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=getSqlSessionFactory().openSession(true);
			count=sqlSession.update(namespace+"updatenotice",dto);
		} catch (Exception e) {
			System.out.println("JDBC실패:updateNotice()"+getClass());
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count>0?true:false;
	}
}
