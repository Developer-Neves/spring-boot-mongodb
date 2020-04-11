package com.jdnevesti.mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto); // instanciando o ResponseEntity para extrair a lista formatada
	}
	
	@GetMapping(value="/{id}") // ou @RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ // O @PathVariable serve para usar id na URL
		User obj = service.findById(id);		
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping // ou @RequestMapping(method=RequestMethod.POST) 
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ // O @RequestBody para usar o UserDto
		User obj = service.fromDTO(objDto);	// convertendo o objDto para o User obj
		obj = service.insert(obj); // chamando o método insert e inserindo os dados no banco
		
		// pegando o endereço do novo objeto que inseri
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		// retonando uma resposta vazia com o códico HTTP 201 e com cabeçalho contendo a localização do recurso criado
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}") // ou @RequestMapping(value="/{id}", method=RequestMethod.DALETE) 
	public ResponseEntity<Void> delete(@PathVariable String id){ // O @PathVariable serve para usar id na URL
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}") // ou @RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
		User obj = service.fromDTO(objDto);	// convertendo o objDto para o User obj
		obj.setId(id); // pegando o id que veio no parametro
		obj = service.update(obj); // chamando o método insert e inserindo os dados no banco
		return ResponseEntity.noContent().build();
	}
}
