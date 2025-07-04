package com.demo.demo.structure.repository;

import com.demo.demo.structure.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
}