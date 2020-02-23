package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @program: loan-api
 * @description: 测试Form
 * @author: ZhangLei
 * @create: 2018-10-30 20:09
 **/
@ApiModel("测试表单")
public class TestForm {

    @ApiModelProperty(value = "手机号",example = "15611112233")
    @NotBlank(message = "手机号不能为空")
    private String phone;

}
