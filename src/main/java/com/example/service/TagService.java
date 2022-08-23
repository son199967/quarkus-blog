package com.example.service;

import com.example.model.Post;
import com.example.model.Tag;
import com.example.model.request.PostRequest;
import com.example.model.request.TagRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface TagService {

    Tag createNewTag(TagRequest tagRequest);

    Tag updateTag(TagRequest tagRequest);

    void deleteTag(Long id);

    Tag getTagById(Long id);

    Page<Tag> getAllTag(int page, int size);
}
