package com.lwo.akulov.graph.repository;

import com.lwo.akulov.graph.dto.PaginationDto;
import com.lwo.akulov.graph.dto.PaginationUserRequest;

public interface UserCustomRepository {

    PaginationDto getAllByParam(PaginationUserRequest paginationUserRequest);
}
