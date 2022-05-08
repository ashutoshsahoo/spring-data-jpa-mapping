package com.ashu.one2many.mapper;

import com.ashu.one2many.dto.PostDto;
import com.ashu.one2many.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PostMapper {

    Post postDtoToPost(PostDto postDto);

    /*
     * Adding mapping rule for userId only as it has different property name in source.
     */
    @Mappings(@Mapping(target = "userId", source = "post.user.id"))
    PostDto postToPostDto(Post post);
}
