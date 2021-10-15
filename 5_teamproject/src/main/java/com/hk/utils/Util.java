package com.hk.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.hk.dtos.CalDto;
import com.hk.dtos.JoinUserDto;

public class Util {

	private String toDates;
	
	public void setToDates(String mDate) {
		
		//mDate를 날짜형식으로 편집한다. yyyy-MM-dd hh:mm:ss
		String m=mDate.substring(0, 4)+"-"   //year-
				+mDate.substring(4, 6)+"-"   //year-month-
				+mDate.substring(6, 8)+" "   //"year-month-date "
				+mDate.substring(8,10)+":"   //"year-month-date hh:"
				+mDate.substring(10)+":00";  //"year-month-date hh:mm:00"
		
		//문자열 ---> date타입으로 변환
		Timestamp tm=Timestamp.valueOf(m);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH:mm");
		this.toDates=sdf.format(tm);
	}

	public String getToDates() {
		return toDates;
	}
	
	//한자릿수를 두자릿수로 변환하는 메서드
	public static String[] isTwo(String []s) {
		String []a=new String[s.length];
		for (int i = 0; i < s.length; i++) {
			 a[i]=s[i].length()<2?"0"+s[i]:s[i];	 
		}
		return a;
	}
	

	//요일별 날짜 색깔 적용하기
	public static String fontColor(int dayOfWeek,int i){
		String color="";
		if((i+dayOfWeek-1)%7==0){//토요일
			color="blue";
		}else if((i+dayOfWeek-1)%7==1){//일요일
			color="red";
		}else{//평일
			color="black";
		}
		return color;
	}
	
	public static String dName(JoinUserDto dto) {
		String dname;
		if(dto.getDname().equals("PEDIATRIC")){
			dname="소아과";
		}else if(dto.getDname().equals("NEUROLOGY")){
			dname="신경과";
		}else if(dto.getDname().equals("PLASTIC")){
			dname="성형회과";
		}else if(dto.getDname().equals("EARNOSETHROAT")){
			dname="이비인후과";
		}else if(dto.getDname().equals("ORTHOPEDICS")){
			dname="정형외과";
		}else if(dto.getDname().equals("INTEGRATED")){
			dname="통합진료과";
		}else if(dto.getDname().equals("UROLOGY")){
			dname="비뇨기과";
		}else{
			dname="관리자";
		}
		return dname;
	}
	public static String rName(JoinUserDto dto) {
		String role;
		if(dto.getRole().equals("HEAD")){
			role="간호부장";
		}else if(dto.getRole().equals("CHIEF")){
			role="수간호사";
		}else if(dto.getRole().equals("RESPONSIBLE")){
			role="책임간호사";
		}else{ 
			role="평간호사";
		}
		return role;
	}
}







