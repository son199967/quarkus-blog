package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javafx.geometry.Pos;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tag")
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "label", unique = true)
    private String label;
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts;


    public Tag() {
    }

    public Tag(Long id, String label, Set<Post> posts) {
        this.id = id;
        this.label = label;
        this.posts = posts;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonBackReference
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
