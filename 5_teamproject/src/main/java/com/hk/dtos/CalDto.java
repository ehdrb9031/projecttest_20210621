package com.hk.dtos;

public class CalDto {
	
	private int to_seq;
	private String id;
	private String wdate;
	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalDto(int to_seq, String id, String wdate) {
		super();
		this.to_seq = to_seq;
		this.id = id;
		this.wdate = wdate;
	}
	
	public CalDto(String id, String wdate) {
		super();
		this.id = id;
		this.wdate = wdate;
	}
	public int getTo_seq() {
		return to_seq;
	}
	public void setTo_seq(int to_seq) {
		this.to_seq = to_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;  
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "CalDto [to_seq=" + to_seq + ", id=" + id + ", wdate=" + wdate + "]";
	}

}


