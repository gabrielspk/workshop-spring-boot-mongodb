package com.github.gabrielspk.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gabrielspk.workshop.domain.User;
import com.github.gabrielspk.workshop.dto.UserDTO;
import com.github.gabrielspk.workshop.repositories.UserRepository;
import com.github.gabrielspk.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}

	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	} 
	
	public User update(User user) {
		User newUser = repository.findById(user.getId())
				.orElseThrow(() -> new ObjectNotFoundException(user.getId()));
		updateData(newUser, user);
		return repository.save(newUser);
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}
}
