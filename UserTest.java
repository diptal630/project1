package com.cg.bookmydoctor.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.bookmydoctor.entities.Admin;
import com.cg.bookmydoctor.entities.User;
import com.cg.bookmydoctor.repository.IAdminRepository;
import com.cg.bookmydoctor.repository.IUserRepository;
import com.cg.bookmydoctor.service.IAdminService;
import com.cg.bookmydoctor.service.IUserService;

@SpringBootTest
public class UserTest {

	@Autowired
	private IUserService userService;
	
	@MockBean
	IUserRepository userRepository;
	
	@Test
	public void testAddUser() {

		User user=getUser();
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(userService.addUser(user), user);

	}
	
	@Test
	public void testUpdateUser() {

		User user=getUser();
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(userService.updateUser(user,35), user);

	}
	
	@Test
	public void testRemoveUser() {
		User user=getUser();
		userService.removeUser(user.getUserId());
		verify(userRepository, times(1)).existsById(user.getUserId());
	}




private User getUser() {
	User user = new User();

	user.setUserId(1);
	user.setRole("Admin");
	user.setPassword("Pas@123");
	user.setUserName("John");

	return user;
}
}