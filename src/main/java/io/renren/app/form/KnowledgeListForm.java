package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * auther: zzxka
 * date: 2020/3/16
 * description:
 */
@Data
@ApiModel
public class KnowledgeListForm extends PageForm {
    @ApiModelProperty("标题名字/用户名称/主题名称")
    private String context;
}
