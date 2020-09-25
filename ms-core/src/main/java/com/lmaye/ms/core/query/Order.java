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
 * -- Order
 *
 * @author lmay.Zhou
 * @date 2020/9/25 16:46 星期五
 * @since Email: lmay_zlm@meten.com
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Order", description = "顺序参数")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 排序字段
     */
    @Safe
    @NotBlank
    @ApiModelProperty("排序字段")
    private String name;

    /**
     * 是否正序: 0. 否; 1. 是;
     */
    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty("是否正序: 0. 否; 1. 是;")
    private Integer asc;
}
