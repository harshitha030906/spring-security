package com.harshitha.springsecurity.springsecurity;

import com.harshitha.springsecurity.springsecurity.entities.User;
import com.harshitha.springsecurity.springsecurity.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringsecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void testJwtService() {
		User user = new User(4L, "harshu@gmail.com", "1234");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
