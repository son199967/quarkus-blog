package com.example.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
@Data
public class PostRequest {

    private Long id;
    @NotBlank(message = "title may not empty")
    private String title;
    @NotBlank(message = "content may not empty")
    private String content;

    private Set<TagRequest> tags;

}
