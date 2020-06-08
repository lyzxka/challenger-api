package io.renren.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.annotation.Login;
import io.renren.app.entity.ChCategory;
import io.renren.app.entity.ChKnowledge;
import io.renren.app.entity.ChUser;
import io.renren.app.entity.vo.KnowledgeListVO;
import io.renren.app.form.KnowledgeAddForm;
import io.renren.app.form.KnowledgeListForm;
import io.renren.app.service.ChCategoryService;
import io.renren.app.service.ChKnowledgeService;
import io.renren.app.service.ChUserService;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * auther: zzxka
 * date: 2020/3/16
 * description:
 */
@Api(tags = "知识接口")
@RestController
@RequestMapping("/app/knowledge")
public class KnowledgeController {
    private static final Logger log= LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    private ChKnowledgeService knowledgeService;
    @Autowired
    private ChUserService userService;
    @Autowired
    private ChCategoryService chCategoryService;

    @ApiOperation("知识列表")
    @PostMapping("list")
    public R list(@RequestBody KnowledgeListForm form){
        log.info("知识列表:{}",form.toString());
        Map map=new HashMap();
        if(StringUtils.isNotBlank(form.getContext())) {
            map.put("context", form.getContext());
        }
        IPage<KnowledgeListVO> page=knowledgeService.selectKnowledgeForPage(map,new Page(form.getPage(),form.getLimit()));
        return R.ok().put("data",page.getRecords()).put("pages",page.getPages());
    }
    @Login
    @ApiOperation("知识列表")
    @PostMapping("listOfMy")
    public R listOfMy(@RequestAttribute("userId")Long userId,@RequestBody KnowledgeListForm form){
        log.info("知识列表:{}",form.toString());
        Map map=new HashMap();
        map.put("userId",userId);
        IPage<KnowledgeListVO> page=knowledgeService.selectKnowledgeForPage(map,new Page(form.getPage(),form.getLimit()));
        return R.ok().put("data",page.getRecords()).put("pages",page.getPages());
    }
    @Login
    @ApiOperation("发布知识")
    @PostMapping("add")
    public R add(@RequestAttribute("userId")Long userId, @RequestBody KnowledgeAddForm form){
        log.info("发布知识:{}",form.toString());
        ValidatorUtils.validateEntity(form);
        if(null==userService.getById(userId)){
            return R.error("用户不存在");
        }
        if(null==chCategoryService.getById(form.getCategoryId())){
            return R.error("门类不存在");
        }
        ChKnowledge knowledge=new ChKnowledge();
        knowledge.setCategoryId(form.getCategoryId());
        knowledge.setUserId(userId);
        knowledge.setContent(form.getContent());
        knowledge.setTitle(form.getTitle());
        knowledge.setDelFlag(0);
        knowledge.setCreateDate(new Date());
        knowledgeService.save(knowledge);
        return R.ok();
    }
}
