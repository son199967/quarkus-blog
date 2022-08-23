package com.example.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class TagRequest {
    private Long id;

    @NotBlank(message = "label may not empty")
    private String label;

}
