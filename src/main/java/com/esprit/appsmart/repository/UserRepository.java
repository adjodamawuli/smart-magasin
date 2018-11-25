package com.esprit.appsmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esprit.appsmart.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("SELECT u FROM User u where u.login = ?1 AND u.password= ?2  ")
	User checkIfExist(String login, String password);
}
