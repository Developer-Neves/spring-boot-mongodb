package com.jdnevesti.mongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jdnevesti.mongodb.domain.User;
import com.jdnevesti.mongodb.repository.UserRepository;

@Configuration // classe para cadastrar usuários ao iniciar o projeto
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// limpar o banco antes de inserir novos registros
		userRepository.deleteAll();
		
		// instanciando os usuários
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// cadastrando os usuários
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
