package kz.accounting.commons.domain;

import kz.accounting.jpa.model.AbstractDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PageDto<T extends AbstractDto> {

    private int totalPages;
    private long totalElements;
    private List<T> content;
}
