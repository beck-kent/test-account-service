package kz.accounting.service;

import kz.accounting.commons.domain.PageDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConvertService {

    PageDto getPageDto(Page pageEntity, List list);
}
