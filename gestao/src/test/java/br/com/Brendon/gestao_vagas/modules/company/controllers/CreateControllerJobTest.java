package br.com.Brendon.gestao_vagas.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class CreateControllerJobTest {
    
    private MockMvc mockMvc;

    private WebApplicationContext  webApplicationContext;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(webApplicationContext).build();
    }

    @Test
    public void shouldBeAbleToCreateAnewApplyJob() {
        mockMvc.perform(MockMvcRequestBuilder.post("/company/job"));
      
    }

}
