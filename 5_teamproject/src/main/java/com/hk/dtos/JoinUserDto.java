package com.hk.dtos;

import java.util.Date;

public class JoinUserDto {
	private int seq;
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String role;
	private String dname;
	private String enabled;
	private Date regdate;
	
	public JoinUserDto() {
		super();
	}
	
	public JoinUserDto(String id, String address, String phone, String email, String role, String dname) {
		super();
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.dname = dname;
	}

	public JoinUserDto(String id, String name, String password, String address, String phone, String email, String role,
			String dname) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.dname = dname;
	}

	public JoinUserDto(int seq , String id, String name, String password, String address, String phone, String email, String role,
			String dname, String enabled, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.dname = dname;
		this.enabled = enabled;
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

	public String getName() {
		return name;
	}   

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "JoinUserDto [seq=" + seq + ", id=" + id + ", name=" + name + ", password=" + password + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", role=" + role + ", dname=" + dname
				+ ", enabled=" + enabled + ", regdate=" + regdate + "]";
	}
	
	
}
