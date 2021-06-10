package kz.accounting.commons.domain;

import kz.accounting.jpa.model.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageDto<T extends BaseDto> {

    private int totalPages;
    private long totalElements;
    private List<T> content;
}
