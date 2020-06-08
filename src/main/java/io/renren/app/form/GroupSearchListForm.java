package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * auther: zzxka
 * date: 2020/3/9
 * description:
 */
@ApiModel
@Data
@ToString
public class GroupSearchListForm extends PageForm {
    @ApiModelProperty("比赛名称")
    private String matchName;
    @ApiModelProperty("类别id")
    private Long categoryId;
}
