package com.andy.collector.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.andy.collector.Main;
import com.andy.collector.dto.UserDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceTest {
	
	protected static  UserDTO user;
	protected static  UserDTO userEdit;
	protected static List<UserDTO> list;
	
	@Autowired
	protected UserService us;
	
	//inicialization of variables for test methods and starting SpringApplication
	@BeforeAll
	public void init() {
		user = new UserDTO();
		user.setNickname("mordos");
		user.setPassword("nieco");
		
		userEdit = new UserDTO();
		userEdit.setNickname("editedUser");
		userEdit.setPassword("editedPass");
		
		list = new ArrayList<>();
		list.add(user);
		list.add(user);
		
		//SpringApplication app = new SpringApplication(Main.class);
     	//app.run();
	}
	
	//test if new user was added to DB by comparing list sizes before and after addNewUser 	
		@Test
		public void testAddNewUserMethod() {
			int sizeBefore = us.findAllUsers().size();
			
			us.addNewUser(user);
			
			int sizeAfter = us.findAllUsers().size();
			
			assertTrue(sizeBefore < sizeAfter);
		}
	
	//tests sizes of list from text method and list from DB
	@Test
	public void testFindAllUsersMethod() {
		List<UserDTO> userListFromDb = us.findAllUsers();
		assertTrue(0<userListFromDb.size());
	}
	
	//test findUserById method and compares nickname and password of User from test method with user fetched form DB
	@Test
	public void testFindUserByIdMethod() {
		Optional<UserDTO> userFromDb = us.findUser(1);		
		assertTrue(userFromDb.isPresent());		
		UserDTO userFromOpt = userFromDb.get();		
		assertEquals("Johny", userFromOpt.getNickname());
	}
	
	//test updateUserById method 
	@Test
	public void testUpdateUserByIdMethod() {
		us.updateUserbyId(userEdit, 2);		
		Optional<UserDTO> userFromDb = us.findUser(2);	
		assertTrue(userFromDb.isPresent());		
		UserDTO userFromOpt = userFromDb.get();
		
		assertEquals(userEdit.getNickname(), userFromOpt.getNickname());
		assertEquals(userEdit.getPassword(), userFromOpt.getPassword());
	}
	
	
	
	@Test
	public void testComparePassMethod() {
		assertTrue("should be true", us.comparePass(1, "nieco"));
	}
}
