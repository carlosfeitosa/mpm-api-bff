package com.skull.bff;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.bff.client.eureka.ProjectClient;

@SpringBootTest
class MpmBffApplicationTests {

	@Autowired
	private ProjectClient client;

	@Test
	void contextLoads() {

		MpmBffApplication.main(new String[] {});

		assertThat(client).isNotNull();

	}

}
