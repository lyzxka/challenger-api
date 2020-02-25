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
@ToString
public class MatchListForm extends PageForm {
    @ApiModelProperty(example = "0",value = "0为全部，其余传id")
    private Long categoryId;
}
