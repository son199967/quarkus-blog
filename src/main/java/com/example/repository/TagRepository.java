package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
    Optional<Tag> findByLabel(String label);
}
