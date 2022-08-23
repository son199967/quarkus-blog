package com.example.service;

import com.example.model.Post;
import com.example.model.request.PostRequest;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    Post createNewPost(PostRequest postRequest);
    Post updatePost(PostRequest postRequest);

    void deletePost(Long id);

    Post getPostById(Long id);

    Page<Post> getAllPost(int page, int size);
}
