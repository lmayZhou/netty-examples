package com.lmaye.ms.core.context;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * -- 通用响应结果实体
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 8:01 星期三
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ResponseResult", description = "响应结果")
public class ResponseResult<T> implements Serializable {
    /**
     * 响应代码
     */
    @ApiModelProperty("响应代码")
    private Integer code;

    /**
     * 响应消息
     */
    @ApiModelProperty("响应消息")
    private String msg;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 处理成功
     *
     * @param data 响应数据
     * @param <T>  泛型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), data);
    }

    /**
     * 处理失败
     *
     * @param <T> 泛型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> failed() {
        return new ResponseResult<>(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getPropKey(), null);
    }

    /**
     * 处理失败
     *
     * @param resultCode 响应编码
     * @param <T>        泛型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> failed(ResultCode resultCode) {
        return new ResponseResult<>(resultCode.getCode(), resultCode.getPropKey(), null);
    }

    /**
     * 响应结果
     *
     * @param resultCode 响应编码
     * @param data       响应数据
     * @param <T>        泛型
     * @return ResponseResult<T>
     */
    public static <T> ResponseResult<T> response(ResultCode resultCode, T data) {
        return new ResponseResult<>(resultCode.getCode(), resultCode.getPropKey(), data);
    }

    /**
     * 响应结果是否成功
     *
     * @return boolean
     */
    public boolean isSuccess() {
        if (Objects.isNull(code)) {
            return false;
        }
        return Objects.equals(ResultCode.SUCCESS.getCode(), code);
    }
}
