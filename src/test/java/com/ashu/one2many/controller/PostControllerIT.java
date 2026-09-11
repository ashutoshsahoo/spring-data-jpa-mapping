package com.ashu.one2many.controller;

import com.ashu.SpringDataJpaMappingApplicationTests;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostControllerIT extends SpringDataJpaMappingApplicationTests {

    @Nested
    class CreatePost {

        @Test
        void createsPostForExistingUser() throws Exception {
            Long userId = createUser("Author");
            createPost(userId, "Hello world");
        }

        @Test
        void returnsNotFoundWhenUserDoesNotExist() throws Exception {
            mockMvc.perform(post("/api/v1/posts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"message\":\"orphan post\",\"userId\":999999}"))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsBadRequestWhenBodyIsMalformedJson() throws Exception {
            mockMvc.perform(post("/api/v1/posts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{not-json"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void returnsBadRequestWhenBodyIsMissing() throws Exception {
            mockMvc.perform(post("/api/v1/posts")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ViewPostById {

        @Test
        void returnsPostWhenIdExists() throws Exception {
            Long userId = createUser("Author");
            String postId = createPost(userId, "Persisted post");

            mockMvc.perform(get("/api/v1/posts/{id}", postId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(postId))
                    .andExpect(jsonPath("$.message").value("Persisted post"))
                    .andExpect(jsonPath("$.userId").value(userId.intValue()))
                    .andExpect(jsonPath("$.postCreateTime").exists())
                    .andExpect(jsonPath("$.postLastUpdateTime").exists());
        }

        @Test
        void returnsNotFoundWhenPostDoesNotExist() throws Exception {
            String missingId = UUID.randomUUID().toString();

            mockMvc.perform(get("/api/v1/posts/{id}", missingId))
                    .andExpect(status().isNotFound());
        }

        @Test
        void throwsWhenIdIsNotAUuid() {
            assertThatThrownBy(() -> mockMvc.perform(get("/api/v1/posts/{id}", "not-a-uuid")))
                    .isInstanceOf(ServletException.class)
                    .cause()
                    .isInstanceOf(IllegalArgumentException.class)
                    .satisfies(cause -> assertThat(cause.getMessage()).contains("Invalid UUID string"));
        }
    }

    @Nested
    class ViewPostsByUser {

        @Test
        void returnsPostsForUser() throws Exception {
            Long userId = createUser("Author");
            String firstPostId = createPost(userId, "First");
            String secondPostId = createPost(userId, "Second");

            mockMvc.perform(get("/api/v1/posts/user/{id}", userId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].id", hasItem(firstPostId)))
                    .andExpect(jsonPath("$[*].id", hasItem(secondPostId)))
                    .andExpect(jsonPath("$[*].message", hasItem("First")))
                    .andExpect(jsonPath("$[*].message", hasItem("Second")));
        }

        @Test
        void returnsEmptyListWhenUserHasNoPosts() throws Exception {
            Long userId = createUser("Silent");

            mockMvc.perform(get("/api/v1/posts/user/{id}", userId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void doesNotReturnPostsBelongingToAnotherUser() throws Exception {
            Long firstUserId = createUser("Alice");
            Long secondUserId = createUser("Bob");
            createPost(firstUserId, "Alice post");
            String bobPostId = createPost(secondUserId, "Bob post");

            mockMvc.perform(get("/api/v1/posts/user/{id}", secondUserId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].id").value(bobPostId))
                    .andExpect(jsonPath("$[0].userId").value(secondUserId.intValue()));
        }

        @Test
        void returnsNotFoundWhenUserDoesNotExist() throws Exception {
            mockMvc.perform(get("/api/v1/posts/user/{id}", 999999L))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class ListPosts {

        @Test
        void returnsEmptyListWhenNoPostsExist() throws Exception {
            mockMvc.perform(get("/api/v1/posts"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void returnsAllPostsAcrossUsers() throws Exception {
            Long firstUserId = createUser("Alice");
            Long secondUserId = createUser("Bob");
            String firstPostId = createPost(firstUserId, "Alice post");
            String secondPostId = createPost(secondUserId, "Bob post");

            mockMvc.perform(get("/api/v1/posts"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].id", hasItem(firstPostId)))
                    .andExpect(jsonPath("$[*].id", hasItem(secondPostId)));
        }
    }
}
