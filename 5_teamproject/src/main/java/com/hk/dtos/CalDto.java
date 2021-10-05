package com.hk.dtos;

import java.util.Date;

public class CalDto {
	
	private int to_seq;
	private String id;
	private String wdate;


   public CalDto() {
	    super();
     }

   public CalDto(int to_seq, String id, String wdate) {
	super();
	this.to_seq = to_seq;
	this.id = id;
	this.wdate = wdate;
   }
  
   public int getto_seq() {
	return to_seq;
   }  

   public String getid() {
	return id;
   }

   public String getwdate() {
	return wdate;
   }

   public String toString() {
	return "CalDto [to_seq=" + to_seq + ", id=" + id + ", wdate= " + wdate + "]";
   }
}


