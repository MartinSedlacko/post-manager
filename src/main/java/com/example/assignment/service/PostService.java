package com.example.assignment.service;

import com.example.assignment.exception.BadRequestException;
import com.example.assignment.exception.NotFoundException;
import com.example.assignment.exception.error.CommonErrorEnum;
import com.example.assignment.external.JsonPlaceholderExternalService;
import com.example.assignment.mapper.PostMapper;
import com.example.assignment.repository.PostRepository;
import com.example.generated.model.CreatePost;
import com.example.generated.model.PatchPost;
import com.example.generated.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final JsonPlaceholderExternalService jsonPlaceholderExternalService;

    public PostService(final PostRepository postRepository, final PostMapper postMapper,
                       final JsonPlaceholderExternalService jsonPlaceholderExternalService) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.jsonPlaceholderExternalService = jsonPlaceholderExternalService;
    }

    public Post createPost(final CreatePost createPost) {
        if (createPost.getUserId() == null) {
            throw new BadRequestException(CommonErrorEnum.BAD_REQUEST, "User id is required");
        }

        this.jsonPlaceholderExternalService.findUserById(createPost.getUserId())
                .orElseThrow(() -> new NotFoundException(CommonErrorEnum.NOT_FOUND,
                        STR. "User with ID: \{ createPost.getUserId() } not found." ));

        final var insertEntity = this.postMapper.createPostToPostEntity(createPost);

        final var result = this.postRepository.save(insertEntity);

        return this.postMapper.postEntityToPost(result);
    }

    public void deletePost(final Long postId) {
        if (!this.postRepository.existsById(postId)) {
            throw new NotFoundException(CommonErrorEnum.NOT_FOUND, STR. "Post with ID: \{ postId } not found." );
        }

        this.postRepository.deleteById(postId);
    }

    @Transactional
    public Post findPost(final Long userId, final Long postId) {
        if (userId == null && postId == null) {
            throw new BadRequestException(CommonErrorEnum.BAD_REQUEST, "At least one of userId or postId is required.");
        }

        if (postId != null) {
            return this.findPostById(postId);
        }

        return this.findPostByUserId(userId);
    }

    private Post findPostById(final Long postId) {
        final var dbPost = this.postRepository.findById(postId);

        if (dbPost.isPresent()) {
            return this.postMapper.postEntityToPost(dbPost.get());
        }

        final var apiPost = this.jsonPlaceholderExternalService.findPostById(postId)
                .orElseThrow(() -> new NotFoundException(CommonErrorEnum.NOT_FOUND,
                        STR. "Post with ID: \{ postId } not found." ));

        final var entity = this.postRepository.save(this.postMapper.jsonPlaceholderPostToPostEntity(apiPost));

        return this.postMapper.postEntityToPost(entity);
    }

    private Post findPostByUserId(final Long userId) {
        return this.postRepository.findByUserId(userId)
                .map(this.postMapper::postEntityToPost)
                .orElseThrow(() -> new NotFoundException(CommonErrorEnum.NOT_FOUND,
                        STR. "Post with userId: \{ userId } not found." ));
    }

    @Transactional
    public Post updatePost(final Long postId, final PatchPost patchPost) {

        final var entity = this.postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(CommonErrorEnum.NOT_FOUND,
                        STR. "Post with ID: \{ postId } not found." ));

        final var patched = this.postMapper.patchPostToPostEntity(patchPost, entity);

        final var result = this.postRepository.save(patched);

        return this.postMapper.postEntityToPost(result);
    }
}
