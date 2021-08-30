package kz.accounting.service;

import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.jpa.enums.OperationType;
import kz.accounting.jpa.model.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static kz.accounting.commons.utils.FileUtils.getJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class UserBalanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveBalance_ADD() throws Exception {
        RequestUserBalanceHistoryDto request = new RequestUserBalanceHistoryDto();
        request.setUserId(1L);
        request.setOperationType(OperationType.ADD);
        request.setCurrency(Currency.USD);
        request.setAmount(BigDecimal.valueOf(100));

        mockMvc.perform(put("/api/v1/user-balance/saveBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"success\":true")));
    }
}