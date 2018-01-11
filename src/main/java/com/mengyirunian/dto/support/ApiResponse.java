package com.mengyirunian.dto.support;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Jiaxiayuan on 2018/1/12
 */
@Data
@Accessors(chain = true)
public class ApiResponse<T> {

    private String code;
    private String msg;
    private T result;

    public static <T> ApiResponse returnSuccessResponse(String code, String msg, T result) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code)
                   .setMsg(msg)
                   .setResult(result);
        return apiResponse;
    }

    public static <T> ApiResponse returnFailResponse(String code, String msg) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setCode(code)
                .setMsg(msg);
        return apiResponse;
    }

}
