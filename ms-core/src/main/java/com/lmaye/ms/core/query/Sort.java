package com.lmaye.ms.core.query;

import com.lmaye.ms.core.validator.constraints.Safe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
     * 排序字段
     */
    @Safe
    @NotBlank
    @ApiModelProperty("排序字段")
    private String column;

    /**
     * 是否正序: 0. 否; 1. 是;
     */
    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty("是否正序: 0. 否; 1. 是;")
    private Integer asc;
}
