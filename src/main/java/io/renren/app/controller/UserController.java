package io.renren.app.controller;

import io.renren.app.annotation.Login;
import io.renren.app.entity.ChUser;
import io.renren.app.form.UserChangeInfoForm;
import io.renren.app.service.ChUserService;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * auther: zzxka
 * date: 2020/3/4
 * description:
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/app/user")
public class UserController {
    private  static final Logger log= LoggerFactory.getLogger(UserController.class);
    @Autowired
    ChUserService userService;

    @Login
    @ApiOperation("修改信息")
    @PostMapping("changeInfo")
    public R changeInfo(@RequestAttribute("userId") Long userId, @RequestBody UserChangeInfoForm form){
        log.info("修改信息:{}",form.toString());
        ChUser user=userService.getById(userId);
        if(null==user){
            return R.error("用户信息错误，请重新登录");
        }
        user.setName(form.getName());
        user.setPhone(form.getPhone());
        user.setIcon(form.getPhoto());
        user.setEmail(form.getEmail());
        userService.updateById(user);
        return R.ok();
    }
}
