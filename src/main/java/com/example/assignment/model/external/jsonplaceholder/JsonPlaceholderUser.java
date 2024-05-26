package com.example.assignment.model.external.jsonplaceholder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonPlaceholderUser {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private JsonPlaceholderAddress address;
    private JsonPlaceholderCompany company;
}
