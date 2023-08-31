package org.jsp.UserBootApp.service;

import java.util.List;
import java.util.Optional;
import org.jsp.UserBootApp.dao.UserDao;
import org.jsp.UserBootApp.dto.ResponseStructure;
import org.jsp.UserBootApp.dto.User;
import org.jsp.UserBootApp.exception.IdNotFoundException;
import org.jsp.UserBootApp.exception.InvalidCredentialsExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(u));
		structure.setMessage("user saved with id :" + u.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.updateUser(u));
		structure.setMessage("user updated ");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> reUser = dao.findById(id);
		if (reUser.isPresent()) {
			structure.setMessage("user found");
			structure.setData(reUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> reUser = dao.findById(id);
		if (reUser.isPresent()) {
			dao.deleteUser(id);
			structure.setMessage("user deleted");
			structure.setData("user found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(dao.findAll());
		structure.setMessage("users found ");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);

	}

	public ResponseEntity<ResponseStructure<User>> verifyByphone(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> reUser = dao.verifyByphone(phone, password);
		if (reUser.isPresent()) {
			structure.setData(reUser.get());
			structure.setMessage("user verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsExecption();
	}

	public ResponseEntity<ResponseStructure<User>> verifyByemail(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> reUser = dao.verifyByemail(email, password);
		if (reUser.isPresent()) {
			structure.setData(reUser.get());
			structure.setMessage("user verified successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsExecption();
	}

}
