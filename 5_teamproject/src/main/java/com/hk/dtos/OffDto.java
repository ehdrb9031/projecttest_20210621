package com.hk.dtos;

import java.util.Date;

public class OffDto {
	private int seq;
	private String id;
	private String off_title;
	private String off_content;
	private String wdate;
	private String category;
	private String off;
	private String odate;
	private Date regdate;

	public OffDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OffDto(String id, String off_title, String off_content, String wdate, String odate) {
		super();
		this.id = id;
		this.off_title = off_title;
		this.off_content = off_content;
		this.wdate = wdate;
		this.odate = odate;
	}

	public OffDto(int seq, String id, String off_title, String off_content, String wdate, String category, String off,
			String odate, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.off_title = off_title;
		this.off_content = off_content;
		this.wdate = wdate;
		this.category = category;
		this.off = off;
		this.odate = odate;
		this.regdate = regdate;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOff_title() {
		return off_title;
	}

	public void setOff_title(String off_title) {
		this.off_title = off_title;
	}

	public String getOff_content() {
		return off_content;
	}

	public void setOff_content(String off_content) {
		this.off_content = off_content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOff() {
		return off;
	}

	public void setOff(String off) {
		this.off = off;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "offDto [seq=" + seq + ", id=" + id + ", off_title=" + off_title + ", off_content=" + off_content
				+ ", wdate=" + wdate + ", category=" + category + ", off=" + off + ", odate=" + odate + ", regdate="
				+ regdate + "]";
	}
	
	
}
