package org.jannal.aggregation.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.jannal.common.exception.StatusCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
public class ApiResponse<T> {

    @Getter
    private Integer status = StatusCode.OPERATION_SUCCESS.getStatus();
    @Getter
    private String message;
    private T data;
    @Getter
    private String currentTime = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .format(LocalDateTime.now());

    //为了让Swagger识别，必须返回T， @Getter可能返回Object
    public T getData() {
        return data;
    }

    public static ApiResponse<?> ok() {
        return valueOf(StatusCode.OPERATION_SUCCESS.getStatus(), null);
    }

    public static ApiResponse<?> valueOfParamsIllegal(String message) {
        return valueOf(StatusCode.PARAMS_ILLEGAL.getStatus(), message);
    }

    public static ApiResponse<?> fail(String message) {
        return valueOf(StatusCode.OPERATION_FAILED.getStatus(), message);
    }

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        return apiResponse;
    }

    public static ApiResponse<?> valueOf(int status, String message) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public static <T> ApiResponse<T> valueOf(int status, String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(status);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static ApiResponse<?> needLogin() {
        return ApiResponse.valueOf(StatusCode.NEED_LOGIN.getStatus(), StatusCode.NEED_LOGIN.getMessage());
    }

    public static <T> ApiResponse<T> needLogin(T data) {
        return ApiResponse.valueOf(StatusCode.NEED_LOGIN.getStatus(), StatusCode.NEED_LOGIN.getMessage(), data);
    }


    public static ApiResponse<?> noPrivilege() {
        return ApiResponse.valueOf(StatusCode.NO_PRIVILEGE.getStatus(), StatusCode.NO_PRIVILEGE.getMessage());
    }


    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean prettyFormat) {
        return JSONObject.toJSONString(this, true);
    }
}

