package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.model.Bet;
import com.example.demo.repository.BetItemRepository;
import com.example.demo.repository.BetRepository;
import com.example.demo.repository.BetSlipRepository;
import com.example.demo.rest.request.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Testcontainers
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class BetControllerTests {

    static {
        System.setProperty("spring.main.lazy-initialization", "true");
    }

    @MockBean
    private BetRepository betRepository;
    @MockBean
    private BetItemRepository betItemRepository;
    @MockBean
    private BetSlipRepository betSlipRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateBet() throws Exception {
        Bet bet = new Bet("Tallinn vs Tartu", 1.6, 2.3, 1.5);

        mockMvc.perform(post("/api/bet").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bet)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

//                response.andDo(print())
//                        .andExpect(jsonPath("$.betData.name",
//                        is(bet.getName())))
//                .andExpect(jsonPath("$.betData.status",
//                        is("pending")))
//                .andExpect(jsonPath("$.betData.drawOdd",
//                        is(bet.getDrawOdd())))
//                .andExpect(jsonPath("$.betData.awayOdd",
//                        is(bet.getAwayOdd())))
//                .andExpect(jsonPath("$.betData.homeOdd",
//                        is(bet.getHomeOdd())));
    }

    @Test
    void allBetSlips() throws Exception {
        BetSlipByStatusRequest request = new BetSlipByStatusRequest();
        request.setStatus("pending");

        mockMvc.perform(post("/api/bet/slip/by/status").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void userBetSlips() throws Exception {
        mockMvc.perform(get("/api/bet/slip/account/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void settleSingleBet() throws Exception {
        SettleSingleBetRequest request = new SettleSingleBetRequest();
        request.setResult("home");
        request.setBetId(1L);
        mockMvc.perform(post("/api/bet/settle-single-bet").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void settlePendingBets() throws Exception {
        SettlePendingBetsRequest request = new SettlePendingBetsRequest();
        request.setResult("home");
        mockMvc.perform(post("/api/bet/settle-pending-bets").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }
    @Test
    void findBetsByStatus() throws Exception {
        BetSlipByStatusRequest request = new BetSlipByStatusRequest();
        request.setStatus("pending");
        mockMvc.perform(post("/api/bet/all").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }


/*
    BetSlip createBetSlip(BetSlipData betSlipData);


    List<BetData> findBetsByStatus(String BetStatus);
 */
}
