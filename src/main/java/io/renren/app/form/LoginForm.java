package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * auther: zzxka
 * date: 2020/2/23
 * description:
 */
@Data
@ToString
@ApiModel
public class LoginForm {
    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
