package io.renren.app.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.app.annotation.Login;
import io.renren.app.entity.ChUser;
import io.renren.app.form.LoginForm;
import io.renren.app.form.PasswordResetForm;
import io.renren.app.form.PasswordUpdateForm;
import io.renren.app.form.RegisterForm;
import io.renren.app.service.ChUserService;
import io.renren.app.utils.Constants;
import io.renren.app.utils.JwtUtils;
import io.renren.app.utils.Md5Utils;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * auther: zzxka
 * date: 2020/2/23
 * description:
 */
@Api(tags = "用户操作")
@RestController
@RequestMapping("/app/auth/")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    /**
     * 验证码Key
     */
    public static final String YZM_KEY = "YZM_CODE_";

    //测试挡板
    @Value("${switch.flag}")
    private String flag;

    @Autowired
    ChUserService userService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisUtils redisUtils;


    @ApiOperation("登录")
    @PostMapping("login")
    private R login(@RequestBody LoginForm form){
        log.info("登录：",form.getPhone());
        ValidatorUtils.validateEntity(form);
        ChUser user=userService.getOne(new QueryWrapper<ChUser>().eq("del_flag",false).eq("phone",form.getPhone()));
        if(null==user){
            return R.error("用户未注册");
        }
        if(!StringUtils.equals(Md5Utils.md5(form.getPassword()+user.getSalt(),"utf-8"),user.getPassword())){
            return R.error("密码错误");
        }
        String token=jwtUtils.generateToken(user.getId()+"");
        return R.ok().put("token",token);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    private R register(@RequestBody RegisterForm form){
        log.info("注册：{}",form.getPhone());
        ValidatorUtils.validateEntity(form);
        ChUser user=userService.getOne(new QueryWrapper<ChUser>().eq("del_flag",false).eq("phone",form.getPhone()));
        if(null!=user){
            return R.error("该手机号已注册，请进行登录");
        }
        String code=redisUtils.get(YZM_KEY+form.getPhone());
        if(null==code){
            return R.error("验证码失效，请重新获取");
        }
        if(!StringUtils.equals(code,form.getCode())){
            return R.error("短信验证码不正确");
        }
        user=new ChUser();
        user.setPhone(form.getPhone());
        String salt= RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz1234567890",5);
        String password=Md5Utils.md5(form.getPassword()+salt,"utf-8");
        user.setPassword(password);
        user.setSalt(salt);
        user.setDelFlag(0);
        user.setCreateDate(new Date());
        userService.save(user);
        return R.ok();
    }

    @Login
    @ApiOperation("用户信息==登录")
    @PostMapping("userInfo")
    private R userInfo(@RequestAttribute("userId")Long userId){
        log.info("获取用户信息：{}",userId);
        ChUser user=userService.getById(userId);
        if(null==user){
            return R.error("用户信息不存在");
        }
        user.setSalt(null);
        user.setPassword(null);
        return R.ok().put("info",user);
    }

    @Login
    @ApiOperation("修改密码")
    @PostMapping("passUpdate")
    public R passUpdate(@RequestBody PasswordUpdateForm form,@RequestAttribute("userId")Long userId){
        log.info("修改密码");
        ValidatorUtils.validateEntity(form);
        ChUser user=userService.getById(userId);
        if(null==user){
            return R.error("用户信息不存在");
        }
        String code=redisUtils.get(YZM_KEY+user.getPhone());
        if(null==code){
            return R.error("验证码失效，请重新获取");
        }
        if(!StringUtils.equals(code,form.getCode())){
            return R.error("短信验证码不正确");
        }
        String newPassword=Md5Utils.md5(form.getNewPass()+user.getSalt(),"utf-8");
        String oldPassword=Md5Utils.md5(form.getOldPass()+user.getSalt(),"utf-8");
        if(StringUtils.equals(oldPassword,user.getPassword())){
            return R.error("密码不正确");
        }
        if(StringUtils.equals(newPassword,user.getPassword())){
            return R.error("新旧密码不能一致");
        }
        String salt= RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz1234567890",5);
        String password=Md5Utils.md5(form.getNewPass()+salt,"utf-8");
        user.setPassword(password);
        user.setSalt(salt);
        userService.updateById(user);
        return R.ok();
    }

    @Login
    @ApiOperation("重置密码")
    @PostMapping("passReset")
    public R passReset(@RequestBody PasswordResetForm form){
        log.info("重置密码");
        ChUser user=userService.getOne(new QueryWrapper<ChUser>().eq("del_flag",false).eq("phone",form.getPhone()));
        if(null==user){
            return R.error("手机号未注册");
        }
        String code=redisUtils.get(YZM_KEY+form.getPhone());
        if(null==code){
            return R.error("验证码失效，请重新获取");
        }
        if(!StringUtils.equals(code,form.getCode())){
            return R.error("短信验证码不正确");
        }
        String salt= RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz1234567890",5);
        String password=Md5Utils.md5(Constants.DEFAULT_PASSWORD +salt,"utf-8");
        user.setPassword(password);
        user.setSalt(salt);
        userService.updateById(user);
        return R.ok();
    }
}
