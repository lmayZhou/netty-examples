package com.lmaye.ms.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * -- 分页查询参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "PageQuery", description = "分页查询参数")
public class PageQuery extends ListQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码(默认: 1)
     */
    @Min(1)
    @ApiModelProperty("当前页码(默认: 1)")
    private Long pageIndex = 1L;

    /**
     * 每页显示页数(默认: 10)
     */
    @Range(min = 1, max = 10000)
    @ApiModelProperty("每页显示页数(默认: 10)")
    private Long pageSize = 10L;
}
