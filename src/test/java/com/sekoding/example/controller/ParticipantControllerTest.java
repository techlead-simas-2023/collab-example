package com.sekoding.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParticipantController.class)
public class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGroupPage_ok() throws Exception {
        mockMvc.perform(get("/group/Kelompok 1"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(content().string(containsString("Kelompok 1")));
    }

    @Test
    void getGroupPage_notFound() throws Exception {
        mockMvc.perform(get("/group/Kelompok XYZ"))
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(content().string(containsString("Kelompok XYZ")))
            .andExpect(content().string(containsString("Not Found")));
    }
}
