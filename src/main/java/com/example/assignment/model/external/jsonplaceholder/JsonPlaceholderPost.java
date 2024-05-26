package com.example.assignment.model.external.jsonplaceholder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonPlaceholderPost {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
