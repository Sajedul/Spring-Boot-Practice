package com.infomanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infomanager.services.EmailService;

@SpringBootTest
class SmartInformationManagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest() {
		service.sendEmail(
				"rahat14048@gmail.com",
				"Just managing the emails",
				"this is Information Manager project working on email service");
	}

}
