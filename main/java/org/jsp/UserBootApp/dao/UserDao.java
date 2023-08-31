package org.jsp.UserBootApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.UserBootApp.dto.User;
import org.jsp.UserBootApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User u)
	{
		return repository.save(u);
	}
	
	public User updateUser(User u)
	{
		return repository.save(u);
	}
	
	public Optional<User> findById(int id)
	{
		return repository.findById(id);
	}
	
	public void deleteUser(int id)
	{
		repository.deleteById(id);
	}
	public List<User> findAll()
	{
		return repository.findAll();
	}
	
	public Optional<User> verifyByphone(long phone ,String password)
	{
		return repository.verifyByphone(phone, password);
	}
	
	public Optional<User> verifyByemail(String email,String password)
	{
		return repository.verifyByemail(email, password);
	}

}
