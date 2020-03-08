package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * auther: zzxka
 * date: 2020/3/4
 * description:
 */
@Data
@ToString
@ApiModel
public class UserChangeInfoForm {
    @ApiModelProperty("头像")
    private String photo;
    @ApiModelProperty("昵称")
    private String name;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
}
