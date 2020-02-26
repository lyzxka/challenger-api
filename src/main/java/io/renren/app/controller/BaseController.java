package io.renren.app.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.app.entity.ChCategory;
import io.renren.app.form.PhoneForm;
import io.renren.app.service.ChCategoryService;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * auther: zzxka
 * date: 2020/2/24
 * description:
 */
@Api(tags = "通用接口")
@RestController
@RequestMapping("/app/base/")
public class BaseController {
    private static final Logger log= LoggerFactory.getLogger(BaseController.class);

    /**
     * 验证码Key
     */
    public static final String YZM_KEY = "YZM_CODE_";

    //测试挡板
    @Value("${switch.flag}")
    private String flag;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ChCategoryService categoryService;

    @ApiOperation("获取短信验证码")
    @PostMapping("getYZM")
    public R getYzmCode(@RequestBody PhoneForm form){
        log.info("获取验证码：{}",form.getPhone());
        String code= RandomUtil.randomNumbers(6);
        if(!Boolean.valueOf(flag)){
            //TODO  发短信
        }else{
            code="123456";
        }
        redisUtils.set(YZM_KEY+form.getPhone(),code);
        return R.ok();
    }

    @ApiOperation("门类列表")
    @PostMapping("categoryList")
    public R categoryList(){
        log.info("门类列表");
        List<ChCategory> list=categoryService.list(new QueryWrapper<ChCategory>().eq("del_flag",false));
        return R.ok().put("data",list);
    }
}
