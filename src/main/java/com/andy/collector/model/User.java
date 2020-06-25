package com.andy.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator = "User_SequenceStyleGenerator")
	@GenericGenerator(name = "User_SequenceStyleGenerator", 
	strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	parameters = {
		@Parameter(name = "sequence_name", value = "User_SEQ"),
		@Parameter(name = "initial_value", value = "1"),
		@Parameter(name = "increment_size", value = "1") 
	})
	private int id;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "password")
	private String password;	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
