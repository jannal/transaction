package org.jannal.common.exception;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ValidateParamsException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 消息
     */
    private String msg;

    protected ValidateParamsException(Integer status, String msg) {
        super(msg);
        this.status = status;
        this.msg = msg;
    }

    public static ValidateParamsException valueOfParamsIllegal(String message) {
        return new ValidateParamsException(StatusCode.PARAMS_ILLEGAL.getStatus(), message);
    }

    public static ValidateParamsException valueOfMessage(Integer status, String message) {
        return new ValidateParamsException(status, message);
    }

    public static ValidateParamsException valueOfRemoteException(String msg) {
        return new ValidateParamsException(StatusCode.REMOTE_INVOKE_EXCEPTION.getStatus(), StringUtils.isBlank(msg) ? "远程调用异常" : msg);
    }


    /**
     * 防止栈拷贝,防止性能问题
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        //return this;
        // return new Throwable(msg);
        return null;
    }
}