package com.brainstrom.meokjang.common.dto.response;

import lombok.Getter;

@Getter
public class ApiResponse {
    private Integer status;
    private String message;
    private Object data;

    public ApiResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}