package com.lmaye.ms.core.exception;

import com.lmaye.ms.core.context.ResultCode;

/**
 * -- 通用自定义异常
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
public class CoreException extends RuntimeException {
    /**
     * 响应编码
     */
    private final ResultCode resultCode;

    public CoreException(ResultCode resultCode) {
        super(resultCode.getPropKey());
        this.resultCode = resultCode;
    }

    public CoreException(ResultCode resultCode, Throwable cause) {
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
