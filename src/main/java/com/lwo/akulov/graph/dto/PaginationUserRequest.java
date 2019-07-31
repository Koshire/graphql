package com.lwo.akulov.graph.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaginationUserRequest {

    private String fName;
    private String mName;
    private String lName;
    private String eMail;
    private int page;
    private int perPage;
}
