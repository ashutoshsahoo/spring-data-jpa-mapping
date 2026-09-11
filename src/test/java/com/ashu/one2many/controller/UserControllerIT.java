package com.ashu.one2many.controller;

import com.ashu.SpringDataJpaMappingApplicationTests;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerIT extends SpringDataJpaMappingApplicationTests {

    @Nested
    class CreateUser {

        @Test
        void createsUserAndReturnsGeneratedId() throws Exception {
            createUser("Alice");
        }

        @Test
        void returnsBadRequestWhenBodyIsMalformedJson() throws Exception {
            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{invalid"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void returnsBadRequestWhenBodyIsMissing() throws Exception {
            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void returnsUnsupportedMediaTypeWhenContentTypeIsMissing() throws Exception {
            mockMvc.perform(post("/api/v1/users")
                            .content("{\"name\":\"Alice\"}"))
                    .andExpect(status().isUnsupportedMediaType());
        }
    }

    @Nested
    class ViewUser {

        @Test
        void returnsUserWhenIdExists() throws Exception {
            Long id = createUser("Bob");

            mockMvc.perform(get("/api/v1/users/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id.intValue()))
                    .andExpect(jsonPath("$.name").value("Bob"));
        }

        @Test
        void returnsNotFoundWhenIdDoesNotExist() throws Exception {
            mockMvc.perform(get("/api/v1/users/{id}", 999999L))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsBadRequestWhenIdIsNotNumeric() throws Exception {
            mockMvc.perform(get("/api/v1/users/{id}", "abc"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ListUsers {

        @Test
        void returnsEmptyListWhenNoUsersExist() throws Exception {
            mockMvc.perform(get("/api/v1/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void returnsAllCreatedUsers() throws Exception {
            Long firstId = createUser("Carol");
            Long secondId = createUser("Dave");

            mockMvc.perform(get("/api/v1/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].id", hasItem(firstId.intValue())))
                    .andExpect(jsonPath("$[*].id", hasItem(secondId.intValue())))
                    .andExpect(jsonPath("$[*].name", hasItem("Carol")))
                    .andExpect(jsonPath("$[*].name", hasItem("Dave")));
        }
    }

    @Nested
    class UnsupportedOperations {

        @Test
        void returnsMethodNotAllowedForPut() throws Exception {
            mockMvc.perform(put("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Alice\"}"))
                    .andExpect(status().isMethodNotAllowed());
        }
    }
}
