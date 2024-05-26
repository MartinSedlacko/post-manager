package com.example.assignment.external;

import com.example.assignment.model.external.jsonplaceholder.JsonPlaceholderPost;
import com.example.assignment.model.external.jsonplaceholder.JsonPlaceholderUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "jsonplaceholder", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderExternalApiClient {

    @GetMapping("/users/{userId}")
    Optional<JsonPlaceholderUser> getUserById(@PathVariable("userId") final Long userId);

    @GetMapping("/posts/{postId}")
    Optional<JsonPlaceholderPost> getPostById(@PathVariable("postId") final Long postId);
}
