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
public class PasswordResetForm {
    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;
    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String code;
}
