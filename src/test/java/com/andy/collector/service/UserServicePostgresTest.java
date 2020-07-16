package com.andy.collector.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.andy.collector.dto.UserDTO;

@Testcontainers
@Profile("test")
@SpringBootTest
@ContextConfiguration(initializers = UserServicePostgresTest.Initializer.class)
public class UserServicePostgresTest {
	protected static UserDTO user1;
	protected static UserDTO user2;
	
	@Autowired
	protected UserService userService;
	
	@Container
	public static PostgreSQLContainer postgreSQLContainer =
			(PostgreSQLContainer) new PostgreSQLContainer()
          .withDatabaseName("sampleuserbd")
          .withUsername("sampleuser")
          .withPassword("samplepwd");
	
	
	
	
	static class Initializer implements
    ApplicationContextInitializer<ConfigurableApplicationContext> {
	@Override
    public void initialize(ConfigurableApplicationContext context) {
        TestPropertyValues.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl())
                .applyTo(context.getEnvironment());
        TestPropertyValues.of("spring.datasource.username=" + postgreSQLContainer.getUsername())
        .applyTo(context.getEnvironment());
        TestPropertyValues.of("spring.datasource.password=" + postgreSQLContainer.getPassword())
        .applyTo(context.getEnvironment());
    }}
	
	@BeforeAll
	public static void init() {
        user1 = new UserDTO();
        user1.setNickname("John");
        user1.setPassword("Snow");
        
        user2 = new UserDTO();
        user2.setNickname("Hibiki");
        user2.setPassword("Sakura");
	}
	
	@Test
	@Order(1)
	public void addNewUserTest() {	
		userService.addNewUser(user1);
		userService.addNewUser(user2);
		List<UserDTO> list = userService.findAllUsers();
		assertTrue(list.size()==2);
		UserDTO userGOT = list.get(1);
		assertEquals(user2.getNickname(), userGOT.getNickname());
		assertEquals(2, userGOT.getId());
	}
	
	@Test
	@Order(2)
	public void updateUserTest() {
		user1.setId(1);
		user1.setNickname("Ed");
		userService.updateUserbyId(user1, 1);
		
		UserDTO userGot = userService.findUser(1);
		
		System.out.println(userGot.getNickname());
		assertEquals(user1.getId(), userGot.getId(), "ID 1 expected");
		assertEquals(user1.getNickname(), userGot.getNickname(), "Nickname Ed expected.");
	}
	
	@Test
	@Order(3)
	public void deleteUserTest() {
		userService.deleteUser(1);
		int size = userService.findAllUsers().size();
		
		assertTrue(size==1);
	}
	
}

