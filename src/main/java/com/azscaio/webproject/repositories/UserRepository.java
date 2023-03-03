package com.azscaio.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azscaio.webproject.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}