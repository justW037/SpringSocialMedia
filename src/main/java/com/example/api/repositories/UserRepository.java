package com.example.api.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api.models.User;


public interface UserRepository extends JpaRepository<User, Long> { }
