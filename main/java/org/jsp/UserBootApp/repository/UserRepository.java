package org.jsp.UserBootApp.repository;

import java.util.Optional;

import org.jsp.UserBootApp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	Optional<User> verifyByphone(long phone, String passowrd);

	@Query("select u from User u where u.email=?1 and u.password=?2")
	Optional<User> verifyByemail(String email, String passowrd);

}