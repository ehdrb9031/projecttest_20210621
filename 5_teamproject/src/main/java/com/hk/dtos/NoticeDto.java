package com.hk.dtos;
import java.util.Date;

public class NoticeDto {
	

	private int no_seq;
	private String id;
	private String title;
	private String content;
	private int readcount;
	private Date regdate;
	
	
	public NoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public NoticeDto(int no_seq, String title, String content) {
		super();
		this.no_seq = no_seq;
		this.title = title;
		this.content = content;
	}


	public NoticeDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}


	public NoticeDto(int no_seq, String id, String title, String content, int readcount, Date regdate) {
		super();
		this.no_seq = no_seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.regdate = regdate;
	}


	public int getNo_seq() {
		return no_seq;
	}


	public void setNo_seq(int no_seq) {
		this.no_seq = no_seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	@Override
	public String toString() {
		return "NoticeDto [no_seq=" + no_seq + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", readcount=" + readcount + ", regdate=" + regdate + "]";
	}
	
	

}
