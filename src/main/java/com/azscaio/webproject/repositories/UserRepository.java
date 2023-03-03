package com.azscaio.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azscaio.webproject.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
