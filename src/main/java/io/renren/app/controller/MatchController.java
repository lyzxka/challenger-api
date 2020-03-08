package io.renren.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChCategory;
import io.renren.app.entity.ChMatch;
import io.renren.app.entity.ChUser;
import io.renren.app.form.IdForm;
import io.renren.app.form.MatchListForm;
import io.renren.app.service.ChCategoryService;
import io.renren.app.service.ChMatchService;
import io.renren.app.service.ChUserService;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * auther: zzxka
 * date: 2020/2/23
 * description:
 */
@Api(tags = "比赛")
@RestController
@RequestMapping("/app/match")
public class MatchController {
    private static final Logger log= LoggerFactory.getLogger(MatchController.class);
    @Autowired
    ChMatchService matchService;
    @Autowired
    ChCategoryService categoryService;
    @Autowired
    ChUserService userService;

    @ApiOperation("比赛列表")
    @PostMapping("list")
    public R list(@RequestBody MatchListForm form){
        log.info("比赛列表:{},{},{}",form.toString(),form.getPage(),form.getLimit());
        QueryWrapper<ChMatch> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        if(!form.getCategoryId().equals(0L)) {
            // 0 为全部
            wrapper.eq("category_id",form.getCategoryId());
        }
        IPage<ChMatch> page=matchService.page(new Page<ChMatch>(form.getPage(),form.getLimit()),wrapper);
        return R.ok().put("data",page.getRecords()).put("pages",page.getPages());
    }

    @ApiOperation("比赛详情")
    @PostMapping("detail")
    public R detail(@RequestBody IdForm form){
        log.info("比赛详情:{}",form.getObjectId());
        ChMatch match=matchService.getById(form.getObjectId());
        if(null==match){
            return R.error("比赛详情不存在");
    }
        ChCategory chCategory=categoryService.getById(match.getCategoryId());
        if(null==chCategory){
            return R.error("门类信息不存在");
        }
        match.setViews(match.getViews()+1);
        matchService.updateById(match);
        return R.ok().put("match",match).put("category",chCategory.getCategoryName());
    }

}
