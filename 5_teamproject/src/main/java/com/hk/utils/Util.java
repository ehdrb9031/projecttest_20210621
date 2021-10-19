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

	public static String dateForm(String mDate) { 
		
		//mDate를 날짜형식으로 편집한다. yyyy-MM-dd hh:mm:ss
		String m=mDate.substring(0, 4)+"-"   //year-
				+mDate.substring(4, 6)+"-"   //year-month-
				+mDate.substring(6, 8)+"-"   //"year-month-date "
				+mDate.substring(8);   //"year-month-date hh:"
		
		return m;
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
	
	public static String isTwo(String s) {
		return s.length()<2?"0"+s:s;
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
	
	public static String getCalView(List<String>calList,int year, int month, int i){ 
		//i가 int형 +""를 하면서 string 변환  5일 --> 5 --> "05" 정수형의 숫자를 두자리 문자열로 변환	
		String d=Util.isTwo(i+"");
		String cal="";//출력해 줄 p태그 "<p>day</p>"
		String y=year+"";
		String m=month+"";
		String yyyymmdd=y+m+d;//20210928
		
		for (int j = 0; j < calList.size(); j++) {
			String a=calList.get(j);
			//"20210928day" -> 20210928
			if(yyyymmdd.equals(a.substring(0, 8))){ 
				cal+=transWork(a.substring(8));//"20210928day" -> day
			}
		}	
		return cal;
	}
	
	public static String getCalView(List<CalDto>calList,String id,int year, int month, int i){ 
		//i가 int형 +""를 하면서 string 변환  5일 --> 5 --> "05" 정수형의 숫자를 두자리 문자열로 변환	
		String d=Util.isTwo(i+"");
		String cal="";//출력해 줄 p태그 "<p>day</p>"
		String y=year+"";
		String m=month+"";
		String yyyymmdd=y+m+d;//20210928
		
		for (int j = 0; j < calList.size(); j++) {
			CalDto dto=calList.get(j);
			if(dto.getId().equals(id)) {
				//"20210928day" -> 20210928
				if(yyyymmdd.equals(dto.getWdate().substring(0, 8))){ 
					cal+="<p>"
							+transWork(dto.getWdate().substring(8))//"20210928day" -> day
							+"</p>"; 		
				}
			}
		}	
		return cal;
	}
	
	public static String transWork(String wdate) {
		if (wdate.equals("day")) {
			wdate="day";
		}else if(wdate.equals("eve")) {
			wdate="eve";
		}else if(wdate.equals("night")) {
			wdate="night";
		}else if(wdate.equals("off")) {
			wdate="";
		}
		return wdate;
	}
	
	public static String offChange(String off) {
		if(off.equals("N")) {
			off="반려";
		}else if(off.equals("Y")){
			off="승인";
		}else if(off.equals("W")){
			off="대기";
		}
		return off;
	}
	
	//휴가or변경 선택
	public static String categoryChange(String category) {
		if(category.equals("CH")) {
			category="변경";
		}else if(category.equals("VA")){
			category="휴가";
		}
		return category;
	}
}

