package kz.accounting.service.impl;

import kz.accounting.commons.domain.PageDto;
import kz.accounting.service.ConvertService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvertServiceImpl implements ConvertService {

    @Override
    public PageDto getPageDto(Page pageEntity, List list) {
        return PageDto.builder()
                .totalElements(pageEntity.getTotalElements())
                .totalPages(pageEntity.getTotalPages())
                .content(list)
                .build();
    }
}
