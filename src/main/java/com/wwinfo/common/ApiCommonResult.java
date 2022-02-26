package com.wwinfo.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: lk
 * DateTime: 2022-02-26 21:57
 */
@Data
public class ApiCommonResult<T> {

    private long code;
    private String message;
    private T data;
    private T rows;

    protected ApiCommonResult() {
    }

    protected ApiCommonResult(long code, String message, T data, T rows) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.rows = rows;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ApiCommonResult<T> success(T data, T rows) {
        return new ApiCommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, rows);
    }


    /**
     * 失败返回结果
     */
    public static <T> ApiCommonResult<T> failed(String message, T data, T rows) {
        return new ApiCommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, data, rows);
    }

}
