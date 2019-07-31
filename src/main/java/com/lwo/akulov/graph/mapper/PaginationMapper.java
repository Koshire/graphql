package com.lwo.akulov.graph.mapper;

import com.lwo.akulov.graph.dto.PaginationDto;
import com.lwo.akulov.graph.entity.User;
import org.springframework.data.domain.Page;

public class PaginationMapper {


    private static final PaginationMapper INSTANCE = new PaginationMapper();

    public PaginationDto entityToDto (Page<User> page) {
        return PaginationDto.builder()
                .data(page.getContent())
                .haveData(page.hasContent())
                .page(page.getNumber() + 1)
                .perPage(page.getSize())
                .build();
    }

    public static PaginationMapper getInstance() {
        return INSTANCE;
    }
}
