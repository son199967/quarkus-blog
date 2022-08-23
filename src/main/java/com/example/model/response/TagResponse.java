package com.example.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private String code;
    private String message;
    private Object response;
}
