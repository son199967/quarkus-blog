package com.example.service.serviceImpl;

import com.example.model.Post;
import com.example.model.Tag;
import com.example.model.request.PostRequest;
import com.example.model.request.TagRequest;
import com.example.repository.TagRepository;
import com.example.service.TagService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Inject
    private TagRepository tagRepository;

    @Override
    public Tag createNewTag(TagRequest tagRequest) {
        Optional<Tag> tagOptional = tagRepository.findByLabel(tagRequest.getLabel());
        if (tagOptional.isPresent()) {
          throw new ServiceException("Already existed LabelName::" + tagRequest.getLabel());
        }
        Tag tag = Tag.builder()
                .label(tagRequest.getLabel())
                .build();
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(TagRequest tagRequest) {

        if (Objects.isNull(tagRequest.getId())) {
            throw new ServiceException("Tag does not have a id");
        }
        Tag tagUpdate = tagRepository.findById(tagRequest.getId())
                .orElseThrow(()->new ServiceException("No Tag found for id::"+ tagRequest.getId()));
        Optional<Tag> tagOptional = tagRepository.findByLabel(tagRequest.getLabel());
        if (tagOptional.isPresent()) {
            throw new ServiceException("Already existed LabelName::" + tagRequest.getLabel());
        }
        tagUpdate.setLabel(tagRequest.getLabel());
        tagRepository.save(tagUpdate);
        return tagUpdate;
    }

    @Override
    public void deleteTag(Long id) {
        Tag tagDelete = tagRepository.findById(id)
                .orElseThrow(()->new ServiceException("No tag found for id::"+ id));
        tagRepository.deleteById(tagDelete.getId());
    }

    @Override
    public Tag getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()->new ServiceException("No tag found for id::"+ id));
        return tag;
    }

    @Override
    public Page<Tag> getAllTag(int page, int size) {
        Page<Tag> tagPage = tagRepository.findAll(PageRequest.of(page, size));
        return tagPage;
    }
}
