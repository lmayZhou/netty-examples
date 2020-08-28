package com.lmaye.ms.core.query;

import com.lmaye.ms.core.validator.constraints.Safe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * -- 模糊查询参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "MatchQuery", description = "模糊查询参数")
public class MatchQuery extends Negation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 字段
     */
    @Safe
    @NotBlank
    @ApiModelProperty("字段")
    private String field;

    /**
     * 值
     */
    @NotNull
    @ApiModelProperty("值")
    private Object value;
}
