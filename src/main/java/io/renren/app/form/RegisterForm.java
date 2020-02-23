package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * auther: zzxka
 * date: 2020/2/24
 * description:
 */
@Data
@ApiModel
public class RegisterForm {
    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String code;
}
