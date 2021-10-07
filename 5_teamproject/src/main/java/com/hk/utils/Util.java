package com.hk.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.hk.dtos.CalDto;

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
	
}