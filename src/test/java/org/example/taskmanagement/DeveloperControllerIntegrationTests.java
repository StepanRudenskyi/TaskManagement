package org.example.taskmanagement;

import org.example.taskmanagement.dto.DeveloperDto;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeveloperControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    DeveloperRepository developerRepository;

    @Test
    void testCreateDeveloper() throws Exception {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setName("Test Developer");
        developerDto.setEmail("test.dev@gmail.com");

        ResultActions result = mockMvc.perform(post("/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Developer\"," +
                                "\"email\":\"test.dev@gmail.com\"}")
                );
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.email").exists());

        developerRepository.deleteAll();
    }

}