package com.example.laba;

import com.example.laba.Service.DataBaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabaApplication.class, args);
	}

//	public static void main(String[] args) {
//		DataBaseService dataBaseService =  new DataBaseService();
//		dataBaseService.select();
//	}
}
