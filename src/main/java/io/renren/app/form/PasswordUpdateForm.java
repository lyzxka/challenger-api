package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * auther: zzxka
 * date: 2020/2/24
 * description:
 */
@Data
@ApiModel
public class PasswordUpdateForm {
    @ApiModelProperty("新密码")
    @NotNull(message = "新密码不能为空")
    private String newPass;
    @ApiModelProperty("旧密码")
    @NotNull(message = "旧密码不能为空")
    private String oldPass;
    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String code;
}
