package com.lmaye.ms.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * -- 排序参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Sort", description = "排序参数")
public class Sort implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    @NotEmpty
    private List<Order> order;
}
