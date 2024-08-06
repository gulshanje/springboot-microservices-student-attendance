package com.studentservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import com.studentservice.services.StudentService;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class StudentServiceApplicationTests {

	// @Test
	// void contextLoads() {
	// }

	@Autowired
	private StudentService studentservice;

	@MockBean
	private JavaMailSender emailSender;

	@Test
	public void testSendEmail() {
		Mono<Void> result = studentservice.sendEmail("test@example.com", "Test Subject", "Test Text");

		StepVerifier.create(result)
				.expectComplete()
				.verify();
	}

	@Test
	public void testSendEmailWithNulls() {
		Mono<Void> result = studentservice.sendEmail(null, null, null);

		StepVerifier.create(result)
				.expectError(IllegalArgumentException.class)
				.verify();
	}
}
