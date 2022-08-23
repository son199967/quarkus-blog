package com.example.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;
@Data
public class PostRequest {

    private Long id;
    @NotBlank(message = "title may not empty")
    private String title;
    @NotBlank(message = "content may not empty")
    private String content;

    private Set<PreTagRequest> tags;

}
