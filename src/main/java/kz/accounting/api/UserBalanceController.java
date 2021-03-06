package kz.accounting.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.accounting.commons.domain.PageDto;
import kz.accounting.commons.domain.ResponseEntityDto;
import kz.accounting.jpa.dto.RequestUserBalanceHistoryDto;
import kz.accounting.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(tags = "Контроллер для работы с user-balance")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-balance")
public class UserBalanceController {

    private final UserBalanceService userBalanceService;

    @GetMapping("/history")
    @ApiOperation(value = "Синхронный запрос")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "При успешном запросе"),
            @ApiResponse(code = 400, message = "При некорректном запросе"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")
    })
    public ResponseEntityDto<PageDto> deals(@NotNull @RequestParam("page") int page, @NotNull @RequestParam("length") int length) {
        PageDto pageDto = userBalanceService.history(page, length);
        return ResponseEntityDto.ok(pageDto);
    }

    @PutMapping("/save/balance")
    @ApiOperation(value = "Синхронный запрос для сохранения баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "При успешном запросе"),
            @ApiResponse(code = 400, message = "При некорректном запросе"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")
    })
    public ResponseEntityDto<?> saveBalance(@RequestBody @Valid RequestUserBalanceHistoryDto request) {
        userBalanceService.saveBalance(request);
        return ResponseEntityDto.ok();
    }

    @PutMapping("/save/balance/kafka")
    @ApiOperation(value = "Синхронный запрос для сохранения баланса")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "При успешном запросе"),
            @ApiResponse(code = 400, message = "При некорректном запросе"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка")
    })
    public ResponseEntityDto<?> saveBalanceKafka(@RequestBody @Valid RequestUserBalanceHistoryDto request) {
        userBalanceService.saveBalanceKafka(request);
        return ResponseEntityDto.ok();
    }
}
