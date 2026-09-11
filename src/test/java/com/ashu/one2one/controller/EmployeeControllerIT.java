package com.ashu.one2one.controller;

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

class EmployeeControllerIT extends SpringDataJpaMappingApplicationTests {

    @Nested
    class CreateEmployee {

        @Test
        void createsEmployeeAndReturnsGeneratedId() throws Exception {
            createEmployee("Eve");
        }

        @Test
        void returnsBadRequestWhenBodyIsMalformedJson() throws Exception {
            mockMvc.perform(post("/api/v1/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{invalid"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void returnsBadRequestWhenBodyIsMissing() throws Exception {
            mockMvc.perform(post("/api/v1/employees")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ViewEmployee {

        @Test
        void returnsEmployeeWhenIdExists() throws Exception {
            Long id = createEmployee("Frank");

            mockMvc.perform(get("/api/v1/employees/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id.intValue()))
                    .andExpect(jsonPath("$.name").value("Frank"));
        }

        @Test
        void returnsNotFoundWhenIdDoesNotExist() throws Exception {
            mockMvc.perform(get("/api/v1/employees/{id}", 999999L))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsBadRequestWhenIdIsNotNumeric() throws Exception {
            mockMvc.perform(get("/api/v1/employees/{id}", "abc"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ListEmployees {

        @Test
        void returnsEmptyListWhenNoEmployeesExist() throws Exception {
            mockMvc.perform(get("/api/v1/employees"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void returnsAllCreatedEmployees() throws Exception {
            Long firstId = createEmployee("Grace");
            Long secondId = createEmployee("Heidi");

            mockMvc.perform(get("/api/v1/employees"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].id", hasItem(firstId.intValue())))
                    .andExpect(jsonPath("$[*].id", hasItem(secondId.intValue())))
                    .andExpect(jsonPath("$[*].name", hasItem("Grace")))
                    .andExpect(jsonPath("$[*].name", hasItem("Heidi")));
        }
    }

    @Nested
    class UnsupportedOperations {

        @Test
        void returnsMethodNotAllowedForPut() throws Exception {
            mockMvc.perform(put("/api/v1/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"name\":\"Eve\"}"))
                    .andExpect(status().isMethodNotAllowed());
        }
    }
}
