package com.jdnevesti.mongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdnevesti.mongodb.domain.User;
import com.jdnevesti.mongodb.dto.UserDTO;
import com.jdnevesti.mongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)  // ou @GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){ // ResponseEntity para resposta HTTP com possiveis cabeçalhos, erros, etc. 
		/* criando usuários
		  User maria = new User("1", "Maria Brown", "maria@gmail.com");
		  User alex = new User("2", "Alex Green", "alex@gmail.com");*/		
		/* criando a lista
		  List<User> list = new ArrayList<>();*/		
		/* adicionando os usuários na lista
		  list.addAll(Arrays.asList(maria, alex));*/
		
		List<User> list = service.findAll(); // buscando usuários no banco
		
		// convertendo a lista para uma listaDto
		List<UserDTO> listDto = list.parallelStream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto); // instanciando o ResponseEntity para extrair a lista formatada
	}
}
