package kz.accounting.service.impl;

import kz.accounting.commons.domain.PageDto;
import kz.accounting.service.ConvertService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvertServiceImpl implements ConvertService {

    @Override
    public PageDto getPageDto(long totalElements, int totalPages, List list) {
        return PageDto.builder()
                .totalElements(totalElements)
                .totalPages(totalPages)
                .content(list)
                .build();
    }
}
