package com.example.repository;

import com.example.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {


    Optional<Post> findById(Long id);

    Page<Post> findAll(Pageable pageable);
    Page<Post> findByTitleContains(String title, Pageable pageable);

}
