package com.hmrc.bsp.copis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmrc.bsp.copis.domain.reference.TrafficType;
import com.hmrc.bsp.copis.exception.COPISServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class ReferenceDataControllerTrafficTypeTest {

    private TrafficType reference;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        this.reference = this.createTrafficType();
        this.mockMvc.perform(post("/reference-data/traffic-type")
                .contentType(APPLICATION_JSON)
                .content(toJson(this.reference)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @After
    public void tearDown() throws Exception {
        this.mockMvc.perform(delete("/reference-data/traffic-type/" + this.reference.getCode())
                .contentType(APPLICATION_JSON)
                .content(toJson(this.reference)))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testFindAllTrafficTypes() throws Exception {
        this.mockMvc.perform(get("/reference-data/traffic-type")
                .contentType(APPLICATION_JSON)
                .content(toJson(this.reference)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindTrafficTypeByCode() throws Exception {
        this.mockMvc.perform(get("/reference-data/traffic-type/" + this.reference.getCode())
                .contentType(APPLICATION_JSON)
                .content(toJson(this.reference)))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateTrafficType() throws Exception {
        TrafficType updated = new TrafficType(this.reference.getCode(), "New Value To Be Updated With");
        this.mockMvc.perform(put("/reference-data/traffic-type")
                .contentType(APPLICATION_JSON)
                .content(toJson(updated)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private TrafficType createTrafficType() {
        return new TrafficType("key123", "value321");
    }

    private String toJson(TrafficType reference) {
        String json = null;
        try {
            json = this.mapper.writeValueAsString(reference);
        } catch (JsonProcessingException e) {
            throw new COPISServiceException(String.format("Failed to create json string from TrafficType: %s.", reference), e);
        }
        return json;
    }
}
