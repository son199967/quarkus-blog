package com.example.model.request;

import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest {

    @NotBlank(message = "label may not empty")
    private String label;
}
