package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * auther: zzxka
 * date: 2020/4/16
 * description:
 */
@ApiModel
@Data
public class ObjectIdForm {
    @NotNull(message = "对象信息不能为空")
    @ApiModelProperty("对象id")
    private Long objectId;
}
