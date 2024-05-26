package com.example.assignment.repository;

import com.example.assignment.model.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>{
    Optional<PostEntity> findByUserId(final Long userId);
}
