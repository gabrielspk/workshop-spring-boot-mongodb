package com.github.gabrielspk.workshop.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabrielspk.workshop.domain.Post;
import com.github.gabrielspk.workshop.resources.utils.URL;
import com.github.gabrielspk.workshop.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findbyId(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findbyTitle(@RequestParam(value = "text", defaultValue ="") String text) {
		text = URL.decodeParam(text);
		List<Post> listPost = service.findByTitle(text);
		return ResponseEntity.ok().body(listPost);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue ="") String text,
			@RequestParam(value = "minDate", defaultValue ="") String minDate,
			@RequestParam(value = "maxDate", defaultValue ="") String maxDate) {
		text = URL.decodeParam(text);
		System.out.println("minDate string: " + minDate);
		System.out.println("maxDate string: " + maxDate);
		LocalDate min = URL.convertDate(minDate, LocalDate.of(1970, 1, 1));
		LocalDate max = URL.convertDate(maxDate, LocalDate.now());
		List<Post> listPost = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(listPost);
	}
}
