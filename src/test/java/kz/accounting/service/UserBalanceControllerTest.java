package kz.accounting.service;

import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.jpa.enums.OperationType;
import kz.accounting.jpa.model.Currency;
import kz.accounting.model.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static kz.accounting.commons.utils.FileUtils.getJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class UserBalanceControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveBalance_ADD() throws Exception {
        RequestUserBalanceHistoryDto request = getTestRequestUserBalanceHistoryDto();

        mockMvc.perform(put("/api/v1/user-balance/save/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"success\":true")));
    }

    @Test
    public void testSaveBalanceKafka_ADD() throws Exception {
        RequestUserBalanceHistoryDto request = getTestRequestUserBalanceHistoryDto();

        mockMvc.perform(put("/api/v1/user-balance/save/balance/kafka")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"success\":true")));
    }

    private RequestUserBalanceHistoryDto getTestRequestUserBalanceHistoryDto() {
        RequestUserBalanceHistoryDto request = new RequestUserBalanceHistoryDto();
        request.setUserId(1L);
        request.setOperationType(OperationType.ADD);
        request.setCurrency(Currency.USD);
        request.setAmount(BigDecimal.valueOf(100));

        return request;
    }
}
