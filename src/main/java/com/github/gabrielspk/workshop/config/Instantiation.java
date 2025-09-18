package com.github.gabrielspk.workshop.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.gabrielspk.workshop.domain.Post;
import com.github.gabrielspk.workshop.domain.User;
import com.github.gabrielspk.workshop.dto.AuthorDTO;
import com.github.gabrielspk.workshop.dto.CommentDTO;
import com.github.gabrielspk.workshop.repositories.PostRepository;
import com.github.gabrielspk.workshop.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		LocalDate dp1 = LocalDate.parse("21/03/2025", formatter);
		Post post1 = new Post(null, dp1, "Partiu viagem", "Vou viajar pra São Paulo", new AuthorDTO(maria));
		
		LocalDate dp2 = LocalDate.parse("23/03/2025", formatter);
		Post post2 = new Post(null, dp2, "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		LocalDate dc1 = LocalDate.parse("21/03/2025", formatter);
		CommentDTO comment1 = new CommentDTO("Boa viagem mano", dc1, new AuthorDTO(alex));
		
		LocalDate dc2 = LocalDate.parse("23/03/2025", formatter);
		CommentDTO comment2 = new CommentDTO("Boa viagem mano", dc2, new AuthorDTO(bob));
		
		LocalDate dc3 = LocalDate.parse("23/03/2025", formatter);
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia", dc3, new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		postRepository.saveAll(Arrays.asList(post1, post2));
	}
}
