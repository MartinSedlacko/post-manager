package com.example.assignment.controller;

import com.example.assignment.service.PostService;
import com.example.generated.api.PostApi;
import com.example.generated.model.CreatePost;
import com.example.generated.model.PatchPost;
import com.example.generated.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController implements PostApi {

    private final PostService postService;

    public PostController(final PostService postService) {
        this.postService = postService;
    }
    @Override
    public ResponseEntity<Post> createPost(CreatePost createPost) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.postService.createPost(createPost));
    }

    @Override
    public ResponseEntity<Void> deletePost(Long postId) {
        this.postService.deletePost(postId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Post> findPost(Long userId, Long postId) {
        return ResponseEntity.ok(this.postService.findPost(userId, postId));
    }

    @Override
    public ResponseEntity<Post> updatePost(Long postId, PatchPost patchPost) {
        return ResponseEntity.ok(this.postService.updatePost(postId, patchPost));
    }
}
