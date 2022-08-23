package com.example.service.serviceImpl;


import com.example.model.Post;
import com.example.model.Tag;
import com.example.model.request.PostRequest;
import com.example.model.request.PreTagRequest;
import com.example.repository.PostRepository;
import com.example.repository.TagRepository;
import com.example.service.PostService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Inject
    private PostRepository postRepository;
    @Inject
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Post createNewPost(PostRequest postRequest) {

        Set<Tag> tags = settTags(postRequest);
        Post post = Post.builder().content(postRequest.getContent())
                .title(postRequest.getTitle())
                .tags(tags)
                .build();

       return postRepository.save(post);
    }

    @Override
    public Post updatePost(PostRequest postRequest) {
        if (Objects.isNull(postRequest.getId())) {
            throw new ServiceException("Post does not have a id");
        }
        Post postUpdate = postRepository.findById(postRequest.getId())
                .orElseThrow(()->new ServiceException("No post found for id::"+ postRequest.getId()));

        postUpdate.setContent(postRequest.getContent());
        postUpdate.setTitle(postUpdate.getTitle());

        Set<Tag> tagUpdates = settTags(postRequest);
        postUpdate.setTags(tagUpdates);
        return postRepository.save(postUpdate);
    }

    private Set<Tag> settTags(PostRequest postRequest) {
        Set<Tag> tagUpdates = new HashSet<>();
        for (PreTagRequest tagRequest : postRequest.getTags()){

            Optional<Tag> tagOptional = tagRepository.findByLabel(tagRequest.getLabel());

            if (tagOptional.isPresent()){
                Tag tag = tagOptional.get();
                tagUpdates.add(tag);
            }else {
                Tag tag = Tag.builder()
                        .label(tagRequest.getLabel())
                        .build();
                tagUpdates.add(tag);
            }
        }
        return tagUpdates;
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post postDelete = postRepository.findById(id)
                .orElseThrow(()->new ServiceException("No post found for id::"+ id));
        postRepository.deleteById(postDelete.getId());
    }

    @Override
    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new ServiceException("No post found for id::"+ id));
        return post;
    }

    @Override
    public Page<Post> getAllPost(int page, int size) {
        Page<Post> postPages = postRepository.findAll(PageRequest.of(page, size));
        return postPages;
    }
}
