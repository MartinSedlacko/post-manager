package com.example.assignment.external;

import com.example.assignment.model.external.jsonplaceholder.JsonPlaceholderPost;
import com.example.assignment.model.external.jsonplaceholder.JsonPlaceholderUser;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsonPlaceholderExternalService {

    private final JsonPlaceholderExternalApiClient apiClient;

    public JsonPlaceholderExternalService(final JsonPlaceholderExternalApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Optional<JsonPlaceholderUser> findUserById(final Long userId) {
        try {
            return this.apiClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }

    public Optional<JsonPlaceholderPost> findPostById(final Long postId) {
        try {
            return this.apiClient.getPostById(postId);
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        }
    }
}
