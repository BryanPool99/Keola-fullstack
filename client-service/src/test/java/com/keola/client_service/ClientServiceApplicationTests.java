package com.keola.client_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClientServiceApplicationTests {

	@Autowired
	private ClientServiceApplication clientServiceApplication;

	@Test
	void contextLoads() {
		assertThat(clientServiceApplication).isNotNull();
	}

}
