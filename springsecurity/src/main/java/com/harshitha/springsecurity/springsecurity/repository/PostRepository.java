package com.harshitha.springsecurity.springsecurity.repository;

import com.harshitha.springsecurity.springsecurity.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
