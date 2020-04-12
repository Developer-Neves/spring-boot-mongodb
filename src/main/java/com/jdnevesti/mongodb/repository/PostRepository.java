package com.jdnevesti.mongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jdnevesti.mongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	// método com nome que eu escolhi, para gerar consulta com código MongoDB na forma de texto JSON
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	// método com nome padão do Spring Data JPA para gerar a consulta
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} } , { $or: [ {'title': { $regex: ?0, $options: 'i'} }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
