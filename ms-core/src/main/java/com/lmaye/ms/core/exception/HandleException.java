package com.lmaye.ms.core.exception;

import com.lmaye.ms.core.context.ResultCode;

/**
 * -- 处理自定义异常
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 8:03 星期三
 */
public class HandleException extends RuntimeException {
    /**
     * 响应编码
     */
    private final ResultCode resultCode;

    public HandleException(ResultCode resultCode) {
        super(resultCode.getPropKey());
        this.resultCode = resultCode;
    }

    public HandleException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getPropKey(), cause);
        this.resultCode = resultCode;
    }

    /**
     * 获取错误信息
     *
     * @return ResultCode
     */
    public ResultCode getResultCode() {
        return resultCode;
    }
}
