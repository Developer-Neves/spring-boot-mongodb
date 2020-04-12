package com.jdnevesti.mongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdnevesti.mongodb.domain.Post;
import com.jdnevesti.mongodb.repository.PostRepository;
import com.jdnevesti.mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	// busca usuário por id
	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Obejeto não encontrado"));
	}	
	
	/*
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}*/	
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}
	
	// busca por texto no título, corpo e comentários, em um intervalo de data
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); // corrigindo a data máxima
		return repo.fullSearch(text, minDate, maxDate);
	}
}
