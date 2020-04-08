package com.jdnevesti.mongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdnevesti.mongodb.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET)  // ou @GetMapping
	public ResponseEntity<List<User>> findAll(){ // ResponseEntity para resposta HTTP com possiveis cabeçalhos, erros, etc. 
		// criando usuários
		User maria = new User("1", "Maria Brown", "maria@gmail.com");
		User alex = new User("2", "Alex Green", "alex@gmail.com");
		
		// criando a lista
		List<User> list = new ArrayList<>();
		
		// adicionando os usuários na lista
		list.addAll(Arrays.asList(maria, alex));
		
		return ResponseEntity.ok().body(list); // instanciando o ResponseEntity para extrair a lista formatada
	}
}
