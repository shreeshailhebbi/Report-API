package com.flowable.reportapi.model;

import lombok.Data;

@Data
public class SearchRequest {
    private String planName;
    private String planStatus;
}
