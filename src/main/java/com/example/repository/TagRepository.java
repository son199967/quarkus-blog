package com.example.repository;

import com.example.model.Post;
import com.example.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    Optional<Tag> findByLabel(String label);

    Page<Tag> findAll(Pageable pageable);
}
