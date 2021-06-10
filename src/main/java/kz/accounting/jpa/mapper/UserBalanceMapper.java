package kz.accounting.jpa.mapper;

import kz.accounting.jpa.dto.UserBalanceDto;
import kz.accounting.jpa.entity.UserBalance;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserBalanceMapper {

    @Mappings({})
    UserBalanceDto userBalanceToUserBalanceDto(UserBalance userBalance);
}
