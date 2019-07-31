package com.lwo.akulov.graph.dto;

import com.lwo.akulov.graph.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaginationDto {

    private int page;
    private int perPage;
    private long totalElements;
    private List<User> data;
    private boolean haveData;
}
