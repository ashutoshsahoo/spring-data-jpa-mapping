package com.ashu.one2one.controller;

import com.ashu.SpringDataJpaMappingApplicationTests;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import jakarta.servlet.ServletException;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LockerControllerIT extends SpringDataJpaMappingApplicationTests {

    @Nested
    class CreateLocker {

        @Test
        void createsLockerUsingEmployeeIdAsLockerId() throws Exception {
            Long employeeId = createEmployee("Ivan");
            createLocker(employeeId, "L-101");
        }

        @Test
        void returnsNotFoundWhenEmployeeDoesNotExist() throws Exception {
            mockMvc.perform(post("/api/v1/lockers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"lockerNo\":\"L-404\",\"employeeId\":999999}"))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsBadRequestWhenBodyIsMalformedJson() throws Exception {
            mockMvc.perform(post("/api/v1/lockers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{invalid"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void returnsBadRequestWhenBodyIsMissing() throws Exception {
            mockMvc.perform(post("/api/v1/lockers")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void throwsWhenSecondLockerIsAssignedToSameEmployee() throws Exception {
            Long employeeId = createEmployee("Judy");
            createLocker(employeeId, "L-201");

            assertThatThrownBy(() -> mockMvc.perform(post("/api/v1/lockers")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"lockerNo\":\"L-202\",\"employeeId\":" + employeeId + "}")))
                    .isInstanceOf(ServletException.class)
                    .hasCauseInstanceOf(DuplicateKeyException.class);
        }
    }

    @Nested
    class ViewLockerById {

        @Test
        void returnsLockerWhenIdExists() throws Exception {
            Long employeeId = createEmployee("Mallory");
            createLocker(employeeId, "L-301");

            mockMvc.perform(get("/api/v1/lockers/{id}", employeeId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(employeeId.intValue()))
                    .andExpect(jsonPath("$.lockerNo").value("L-301"))
                    .andExpect(jsonPath("$.employeeId").value(employeeId.intValue()));
        }

        @Test
        void returnsNotFoundWhenLockerDoesNotExist() throws Exception {
            mockMvc.perform(get("/api/v1/lockers/{id}", 999999L))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsBadRequestWhenIdIsNotNumeric() throws Exception {
            mockMvc.perform(get("/api/v1/lockers/{id}", "abc"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class ViewLockerByEmployee {

        @Test
        void returnsLockerWhenEmployeeHasOne() throws Exception {
            Long employeeId = createEmployee("Niaj");
            createLocker(employeeId, "L-401");

            mockMvc.perform(get("/api/v1/lockers/employee/{id}", employeeId))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(employeeId.intValue()))
                    .andExpect(jsonPath("$.lockerNo").value("L-401"))
                    .andExpect(jsonPath("$.employeeId").value(employeeId.intValue()));
        }

        @Test
        void returnsNotFoundWhenEmployeeExistsButHasNoLocker() throws Exception {
            Long employeeId = createEmployee("Olivia");

            mockMvc.perform(get("/api/v1/lockers/employee/{id}", employeeId))
                    .andExpect(status().isNotFound());
        }

        @Test
        void returnsNotFoundWhenEmployeeDoesNotExist() throws Exception {
            mockMvc.perform(get("/api/v1/lockers/employee/{id}", 999999L))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class ListLockers {

        @Test
        void returnsEmptyListWhenNoLockersExist() throws Exception {
            mockMvc.perform(get("/api/v1/lockers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void returnsAllCreatedLockers() throws Exception {
            Long firstEmployeeId = createEmployee("Peggy");
            Long secondEmployeeId = createEmployee("Rory");
            createLocker(firstEmployeeId, "L-501");
            createLocker(secondEmployeeId, "L-502");

            mockMvc.perform(get("/api/v1/lockers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(2))
                    .andExpect(jsonPath("$[*].lockerNo", hasItem("L-501")))
                    .andExpect(jsonPath("$[*].lockerNo", hasItem("L-502")))
                    .andExpect(jsonPath("$[*].employeeId", hasItem(firstEmployeeId.intValue())))
                    .andExpect(jsonPath("$[*].employeeId", hasItem(secondEmployeeId.intValue())));
        }
    }
}
