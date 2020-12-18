package com.lmaye.netty.learning.dto;

import com.lmaye.netty.learning.constants.DeviceEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * -- UserRegisterDataDTO
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:29
 * @email lmay@lmaye.com
 */
@Data
public class UserRegisterDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private DeviceEnum device;
}
