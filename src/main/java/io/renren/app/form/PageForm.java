package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * auther: zzxka
 * date: 2020/2/25
 * description:
 */
@ApiModel
@Data
public class PageForm {
    @ApiModelProperty(example = "1")
    private Integer page;
    @ApiModelProperty(example = "10")
    private Integer limit;
}
