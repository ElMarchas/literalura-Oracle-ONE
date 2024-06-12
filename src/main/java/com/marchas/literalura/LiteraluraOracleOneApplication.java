package com.marchas.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.marchas.literalura.main.MainApp;
import com.marchas.literalura.repository.BookRepository;

@SpringBootApplication
public class LiteraluraOracleOneApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraOracleOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainApp main = new MainApp(repository);
		main.run();
	}

}
