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
}
