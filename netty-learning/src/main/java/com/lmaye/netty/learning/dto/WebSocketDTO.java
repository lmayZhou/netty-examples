package com.lmaye.netty.learning.dto;

import com.lmaye.netty.learning.constants.ModuleEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * -- WebSocketDTO
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:28
 * @email lmay@lmaye.com
 */
@Data
public class WebSocketDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private ModuleEnum module;

    public static WebSocketDTO data(ModuleEnum heartCheck, String msg) {
        return null;
    }

    public Object getData() {
        return null;
    }
}
