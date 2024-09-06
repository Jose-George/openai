package com.openai;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenAIApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenAIApiApplication.class, args);
	}

	@Bean
	public DB mapDB() {
		return DBMaker.fileDB("multi-user-chat-memory.db").transactionEnable().make();
	}

}
