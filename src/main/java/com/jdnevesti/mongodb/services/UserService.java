package com.jdnevesti.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdnevesti.mongodb.domain.User;
import com.jdnevesti.mongodb.dto.UserDTO;
import com.jdnevesti.mongodb.repository.UserRepository;
import com.jdnevesti.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	// busca usuário por id
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Obejeto não encontrado"));
	}
	
	// inserir usuário
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	// exceluir usuário
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	// atualizar usuário
	public User update(User obj) {
		User newObj = findById(obj.getId()); // criei um novo objeto pedando os dados do banco
		updateData(newObj, obj); // método para atualizar os dados do obj para o newObj
		return repo.save(newObj); // salvando os novos dados do usuário no banco
	}
	
	// método para entregar os dados do obj para o newObj
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
}
