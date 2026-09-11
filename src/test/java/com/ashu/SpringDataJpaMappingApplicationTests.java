package com.ashu;

import com.ashu.one2one.controller.EmployeeController;
import com.ashu.one2one.controller.LockerController;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SpringDataJpaMappingApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private LockerController lockerController;

    @Test
    void contextLoads() {
        assertNotNull(employeeController);
        assertNotNull(lockerController);
    }

    protected Long createUser(String name) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + name + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(name))
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }

    protected String createPost(Long userId, String message) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"" + message + "\",\"userId\":" + userId + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.message").value(message))
                .andExpect(jsonPath("$.userId").value(userId.intValue()))
                .andReturn();
        return JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    }

    protected Long createEmployee(String name) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + name + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(name))
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }

    protected Long createLocker(Long employeeId, String lockerNo) throws Exception {
        MvcResult result = mockMvc.perform(post("/api/v1/lockers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lockerNo\":\"" + lockerNo + "\",\"employeeId\":" + employeeId + "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeId.intValue()))
                .andExpect(jsonPath("$.lockerNo").value(lockerNo))
                .andExpect(jsonPath("$.employeeId").value(employeeId.intValue()))
                .andReturn();
        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        return id.longValue();
    }
}
