package com.lmaye.ms.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * -- 查询参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 7:56 星期三
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "Query", description = "查询参数")
public class Query implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 等值查询
     */
    @Valid
    @ApiModelProperty("等值查询")
    private List<TermQuery> terms;

    /**
     * 模糊查询
     */
    @Valid
    @ApiModelProperty("模糊查询")
    private List<MatchQuery> matches;

    /**
     * 范围查询
     */
    @Valid
    @ApiModelProperty("范围查询")
    private List<RangeQuery> ranges;

    /**
     * IN查询
     */
    @Valid
    @ApiModelProperty("IN查询")
    private List<InQuery> ins;

    /**
     * 且查询
     */
    @Valid
    @ApiModelProperty("且查询")
    private Query must;

    /**
     * 或查询
     */
    @Valid
    @ApiModelProperty("或查询")
    private Query should;

    /**
     * 是否为空
     *
     * @return boolean
     */
    public boolean isNull() {
        return CollectionUtils.isEmpty(terms) && CollectionUtils.isEmpty(matches)
                && CollectionUtils.isEmpty(ranges) && CollectionUtils.isEmpty(ins);
    }
}
