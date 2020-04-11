package com.jdnevesti.mongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jdnevesti.mongodb.domain.Post;
import com.jdnevesti.mongodb.domain.User;
import com.jdnevesti.mongodb.dto.AuthorDTO;
import com.jdnevesti.mongodb.repository.PostRepository;
import com.jdnevesti.mongodb.repository.UserRepository;

@Configuration // classe para cadastrar usuários ao iniciar o projeto
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// formatando a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		// limpar o banco antes de inserir novos registros
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		// instanciando os usuários
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		// cadastrando os usuários
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraçoes!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("13/14/2019"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		// salvando os posts
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
