package kz.accounting.jpa.mapper;

import kz.accounting.jpa.dto.UserBalanceHistoryDto;
import kz.accounting.jpa.entity.UserBalanceHistory;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBalanceHistoryMapper {

    @Mappings({})
    UserBalanceHistoryDto userBalanceHistoryToUserBalanceHistoryDto(UserBalanceHistory userBalanceHistory);

    @IterableMapping(qualifiedByName = "userBalanceHistoryToUserBalanceHistoryDto")
    List<UserBalanceHistoryDto> listUserBalanceHistoryToListUserBalanceHistoryDto(List<UserBalanceHistory> list);
}
