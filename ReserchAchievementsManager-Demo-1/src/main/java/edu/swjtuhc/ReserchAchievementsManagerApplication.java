package edu.swjtuhc;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
public class ReserchAchievementsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReserchAchievementsManagerApplication.class, args);
	}
}
