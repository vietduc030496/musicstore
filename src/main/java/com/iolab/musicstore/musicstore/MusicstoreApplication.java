package com.iolab.musicstore.musicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MusicstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicstoreApplication.class, args);
	}

}
