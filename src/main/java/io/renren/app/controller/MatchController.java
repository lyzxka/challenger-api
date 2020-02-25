package io.renren.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChMatch;
import io.renren.app.form.MatchListForm;
import io.renren.app.service.ChMatchService;
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

    @ApiOperation("比赛列表")
    @PostMapping("list")
    private R list(@RequestBody MatchListForm form){
        log.info("比赛列表:{}",form.toString());
        QueryWrapper<ChMatch> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        if(!form.getCategoryId().equals(0L)) {
            // 0 为全部
            wrapper.eq("category_id",form.getCategoryId());
        }
        IPage<ChMatch> page=matchService.page(new Page<ChMatch>(form.getPage(),form.getLimit()),wrapper);
        return R.ok().put("data",page.getRecords()).put("pages",page.getPages());
    }
}
