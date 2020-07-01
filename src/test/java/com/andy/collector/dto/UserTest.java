package com.andy.collector.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.andy.collector.dto.User;

public class UserTest {
	protected User user;
	
	@BeforeEach
	void init() {
		user = new User();
		
		user.setId(1);
		user.setNickname("John Snow");
		user.setPassword("password");
	}
	
	@Test
	public void testGetMethods() {
		assertEquals(1, user.getId(), "User id 1 expected!");
		assertEquals("John Snow", user.getNickname(), "Nickname John Snow expected!");
		assertEquals("password", user.getPassword(), "Password: password expected!");
	}
	
	@Test
	public void testSetMethods() {
		int id = 3;
		String name = "new name";
		String pass = "new password";
		
		user.setId(id);
		user.setNickname(name);
		user.setPassword(pass);
		
		assertEquals(id, user.getId(), "User id 3 expected!");
		assertEquals(name, user.getNickname(), "Nickname new name expected!");
		assertEquals(pass, user.getPassword(), "Password: new password expected!");
	}
}
