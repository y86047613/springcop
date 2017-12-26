package com.yny.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	private Integer id;
	private String username	;
	private String password;
	private String email;	
	private String hobbit;
	private String sex;
	
	@Id
	// 主鍵注解
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 自动增长
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getHobbit() {
		return hobbit;
	}
	public void setHobbit(String hobbit) {
		this.hobbit = hobbit;
	}
	@Column
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}	
	
}
