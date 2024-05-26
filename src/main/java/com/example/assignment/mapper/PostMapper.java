package com.example.assignment.mapper;

import com.example.assignment.model.entity.PostEntity;
import com.example.assignment.model.external.jsonplaceholder.JsonPlaceholderPost;
import com.example.generated.model.CreatePost;
import com.example.generated.model.PatchPost;
import com.example.generated.model.Post;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    PostEntity createPostToPostEntity(final CreatePost createPost);

    Post postEntityToPost(final PostEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    PostEntity patchPostToPostEntity(final PatchPost patchPost, @MappingTarget final PostEntity entity);

    PostEntity jsonPlaceholderPostToPostEntity(final JsonPlaceholderPost jsonPlaceholderPost);
}
