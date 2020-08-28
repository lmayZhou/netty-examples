package com.lmaye.ms.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * -- 列表查询参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ListQuery", description = "列表查询参数")
public class ListQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 条件
     */
    @Valid
    @ApiModelProperty("条件")
    private Query query;

    /**
     * 排序
     */
    @Valid
    @ApiModelProperty("排序")
    private List<Sort> sorts;
}
