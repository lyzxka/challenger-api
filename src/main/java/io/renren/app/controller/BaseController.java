package io.renren.app.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import io.renren.app.entity.ChCategory;
import io.renren.app.entity.ChMatch;
import io.renren.app.entity.vo.GroupSearchListVO;
import io.renren.app.form.PhoneForm;
import io.renren.app.service.ChCategoryService;
import io.renren.app.service.ChGroupSearchService;
import io.renren.app.service.ChMatchService;
import io.renren.app.utils.QiniuFileUtil;
import io.renren.common.utils.R;
import io.renren.common.utils.RedisUtils;
import io.renren.config.QiniuConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    QiniuConfig qiniuConfig;

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ChCategoryService categoryService;
    @Autowired
    ChMatchService matchService;
    @Autowired
    ChGroupSearchService groupSearchService;

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


    @PostMapping("upload")
    @ApiOperation("上传图片")
    public R uploadImage(@RequestParam("file") MultipartFile file){
        log.info("图片上传");
        if(file == null){
            return R.error("上传文件为空 ");
        }
        Map m = Maps.newHashMap();
        try {
            String url =new  QiniuFileUtil().upload(file,qiniuConfig.getQiniu());
            m.put("url", url);
            m.put("name", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage());
        }
        return R.ok(m);
    }

    @ApiOperation("门类列表")
    @PostMapping("categoryList")
    public R categoryList(){
        log.info("门类列表");
        List<ChCategory> list=categoryService.list(new QueryWrapper<ChCategory>().eq("del_flag",false));
        return R.ok().put("data",list);
    }

    @PostMapping("index")
    @ApiOperation("首页")
    public R index(){
        log.info("首页");
        Map data=new HashMap();
        QueryWrapper<ChMatch> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        wrapper.orderByDesc("create_date");
        IPage page=matchService.page(new Page< ChMatch >(1,5),wrapper);
        data.put("matchList",page.getRecords());
        Page<GroupSearchListVO> groupSearchList=groupSearchService.selectGroupSearchForPage(new Page(1,5),new HashMap());
        data.put("groupSearchList",groupSearchList.getRecords());
        return R.ok(data);
    }
}
