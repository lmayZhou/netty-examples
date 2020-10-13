package com.lmaye.ms.service.user.controller;

import com.lmaye.ms.core.context.ResponseResult;
import com.lmaye.ms.starter.minio.service.IMinIoFileStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * -- 用户 Controller
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/5 13:22 星期日
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {
    /**
     * MinIo File Store Service
     */
    @Autowired
    private IMinIoFileStoreService minIoFileStoreService;

    /**
     * 测试接口
     *
     * @return Mono<ResponseResult<String>>
     */
    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试示例", response = ResponseResult.class)
    public Mono<ResponseResult<String>> test() {
        return Mono.just(ResponseResult.success("Test"));
    }

    public Mono<ResponseResult<String>> uploadFile() {
        minIoFileStoreService.saveStream(null, "");
        return Mono.just(ResponseResult.success("Test"));
    }
}
