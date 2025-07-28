package br.com.Brendon.gestao_vagas.modules.company.controllers;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.Brendon.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.Brendon.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.Brendon.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.Brendon.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.Brendon.gestao_vagas.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateControllerJobTest {

        private MockMvc mockMvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        private CompanyRepository companyRepository;

        @Before
        public void setup() {
                mockMvc = MockMvcBuilders.webAppContextSetup(context)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        public void shouldBeAbleToCreateAnewApplyJob() throws Exception {

                var company = CompanyEntity.builder()
                                .description("Empresa de teste discription")
                                .email("company@gmail.com")
                                .password("1234567890")
                                .username("company_teste")
                                .name("Empresa Teste").build();

                company = companyRepository.saveAndFlush(company);

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("Beneficios teste")
                                .description("Descricao teste")
                                .level("PLENO")
                                .build();

                var result = mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJson(createJobDTO))
                                .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#")))
                                .andExpect(MockMvcResultMatchers.status().isOk());

                System.out.println(result);
        }

        @Test
        public void shouldNotBeAbletoCreateAnewJobIfCompanyNotFound() throws Exception {

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("Beneficios teste")
                                .description("Descricao teste")
                                .level("PLENO")
                                .build();
               
                        mockMvc.perform(MockMvcRequestBuilders.post("/company/job")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(TestUtils.objectToJson(createJobDTO))
                                        .header("Authorization",
                                                        "Bearer " + TestUtils.generateToken(UUID.randomUUID(),
                                                                        "JAVAGAS_@123#")))
                                                                        .andExpect(MockMvcResultMatchers.status().isBadRequest());
            

        }
}