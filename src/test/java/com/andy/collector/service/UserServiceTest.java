package com.andy.collector.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {
	
	protected static  User user;
	protected static  User userEdit;
	protected static List<User> list;
	
	@Autowired
	protected UserService us;
	
	//inicialization of variables for test methods and starting SpringApplication
	@BeforeAll
	public static void init() {
		user = new User();
		user.setNickname("mordos");
		user.setPassword("nieco");
		
		userEdit = new User();
		userEdit.setNickname("editedUser");
		userEdit.setPassword("editedPass");
		
		list = new ArrayList<>();
		list.add(user);
		list.add(user);
		
		SpringApplication app = new SpringApplication(Main.class);
     	app.run();
	}
	
	//tests sizes of list from text method and list from DB
	@Test
	@Disabled // when testing addNewUser lists has the same size no longer
	public void testFindAllUsersMethod() {
		List<User> userListFromDb = us.findAllUsers();
		assertEquals(list.size(), userListFromDb.size());
	}
	
	//test findUserById method and compares nickname and password of User from test method with user fetched form DB
	@Test
	public void testFindUserByIdMethod() {
		Optional<User> userFromDb = us.findUser(19);
		
		assertTrue(userFromDb.isPresent());
		
		User userFromOpt = userFromDb.get();
		
		assertEquals(user.getNickname(), userFromOpt.getNickname());
		assertEquals(user.getPassword(), userFromOpt.getPassword());
	}
	
	//test updateUserById method 
	@Test
	public void testUpdateUserByIdMethod() {
		us.updateUserbyId(userEdit, 18);
		
		Optional<User> userFromDb = us.findUser(18);
		
		assertTrue(userFromDb.isPresent());
		
		User userFromOpt = userFromDb.get();
		
		assertEquals(userEdit.getNickname(), userFromOpt.getNickname());
		assertEquals(userEdit.getPassword(), userFromOpt.getPassword());
	}
	
	//test if new user was added to DB by comparing list sizes before and after addNewUser 	
	@Test
	public void testAddNewUserMethod() {
		int sizeBefore = us.findAllUsers().size();
		
		us.addNewUser(user);
		
		int sizeAfter = us.findAllUsers().size();
		
		assertEquals(sizeBefore+1, sizeAfter);
	}
	
	@Test
	public void testComparePassMethod() {
		assertTrue("should be true", us.comparePass(3, "nieco"));
	}
}
