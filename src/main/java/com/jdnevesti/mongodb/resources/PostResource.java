package com.jdnevesti.mongodb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdnevesti.mongodb.domain.Post;
import com.jdnevesti.mongodb.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;
		
	@GetMapping(value="/{id}") // ou @RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<Post> findById(@PathVariable String id){ // O @PathVariable serve para usar id na URL
		Post obj = service.findById(id);		
		return ResponseEntity.ok().body(obj);
	}
		
}
