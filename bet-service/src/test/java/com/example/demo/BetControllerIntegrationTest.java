package com.example.demo;

import com.example.demo.dto.BetData;

import com.example.demo.dto.BetSlipData;
import com.example.demo.repository.BetRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.util.CollectionUtils;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.List;

import static com.example.demo.BetUtils.createBetDatas;
import static com.example.demo.BetUtils.createBetItems;
import static java.lang.String.format;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(initializers = {BetControllerIntegrationTest.PropertiesInitializer.class})
@Testcontainers
class BetControllerIntegrationTest {

    private static final String MYSQL_SERVICE_NAME = "mysql";

    private static final Integer MYSQL_SERVICE_PORT = 3306;


    @Container
    private static final DockerComposeContainer COMPOSE_CONTAINER = new DockerComposeContainer(
            new File("src/test/resources/docker-compose.yml"))
            .withExposedService(MYSQL_SERVICE_NAME, MYSQL_SERVICE_PORT);

    private static final String MYSQL_DATA_SOURCE_PROPERTY_FORMAT = "spring.datasource.url=jdbc:mysql://%s:%s/%s";

    private static final String DATABASE_NAME = "test?createDatabaseIfNotExist=true&serverTimezone=UTC";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BetRepository betRepository;

    @BeforeEach
    void setUp() {
        betRepository.saveAll(createBetDatas());
    }


    @Test
    @SneakyThrows
    void shouldCreateBet() throws Exception {
        BetData bet = new BetData("Tallinn vs Tartu", 1.6, 2.3, 1.5);

         mockMvc.perform(post("/api/bet").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bet)))
                .andExpect(status().isOk())
                 .andDo(print())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(jsonPath("$.message").value("Created Successfully"))
                 .andExpect(jsonPath("$.betData.name").value(bet.getName()))
                 .andExpect( jsonPath("$.betData.status").value("pending"))
                 .andExpect( jsonPath("$.betData.drawOdd").value(bet.getDrawOdd()))
                 .andExpect( jsonPath("$.betData.awayOdd").value(bet.getAwayOdd()))
                 .andExpect( jsonPath("$.betData.homeOdd").value(bet.getHomeOdd()));
    }

    @Test
    @SneakyThrows
    void shouldCreateBetSlip() throws Exception {

        BetSlipData betSlip = new BetSlipData(1L, 100, createBetItems());

         mockMvc.perform(post("/api/bet/slip").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(betSlip)))
                .andExpect(status().isOk())
                 .andDo(print())
                 .andExpect(content().contentType("application/json"))
                 .andExpect(jsonPath("$.message").value("Created Successfully"))
                 .andExpect(jsonPath("$.betSlip.accountId").value(betSlip.accountId()))
                 .andExpect( jsonPath("$.betSlip.status").value("pending"))
                 .andExpect( jsonPath("$.betSlip.result").value("pending"))
                 .andExpect( jsonPath("$.betSlip.totalOdd").value(4.5))
                 .andExpect( jsonPath("$.betSlip.stake").value(betSlip.stake()));
    }




    @AfterEach
    void tearDown() {
        //betRepository.deleteAll();
    }

    static class PropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    format(
                            MYSQL_DATA_SOURCE_PROPERTY_FORMAT,
                            COMPOSE_CONTAINER.getServiceHost(MYSQL_SERVICE_NAME, MYSQL_SERVICE_PORT),
                            COMPOSE_CONTAINER.getServicePort(MYSQL_SERVICE_NAME, MYSQL_SERVICE_PORT),
                            DATABASE_NAME));
            values.applyTo(configurableApplicationContext);
        }
    }
}
