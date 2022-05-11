package org.jannal.common.exception;

import lombok.Getter;

@Getter
public enum StatusCode {
    /**
     * 操作成功
     */
    OPERATION_SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    OPERATION_FAILED(500, "操作失败"),
    /**
     * 参数非法
     */
    PARAMS_ILLEGAL(400, "参数非法"),
    /**
     * 没有权限
     */
    NO_PRIVILEGE(402, "没有权限"),
    /**
     * 不支持的请求方法
     */
    NO_SUPPORT_METHOD(403, "不支持的请求方法"),
    /**
     * 需要登陆
     */
    NEED_LOGIN(405, "需要登录"),
    /**
     * 远程调用异常
     */
    REMOTE_INVOKE_EXCEPTION(503, "远程调用异常");

    private Integer status;
    private String message;

    StatusCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}