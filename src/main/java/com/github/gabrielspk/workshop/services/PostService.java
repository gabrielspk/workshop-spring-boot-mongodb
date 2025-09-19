package com.github.gabrielspk.workshop.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gabrielspk.workshop.domain.Post;
import com.github.gabrielspk.workshop.repositories.PostRepository;
import com.github.gabrielspk.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, LocalDate minDate, LocalDate maxDate) {
		LocalDateTime minDateTime = minDate.atStartOfDay();
		LocalDateTime maxDateTime = maxDate.atTime(23, 59, 59);
		
		
		System.out.println("Min: " + minDate + " | Max: " + maxDate);
		
		return repository.fullSearch(text, minDateTime, maxDateTime);
	}
}
