package com.example.model.request;

import lombok.*;


import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreTagRequest {
    @NotBlank(message = "label may not empty")
    private String label;
}
