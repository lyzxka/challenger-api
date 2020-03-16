package io.renren.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.vo.KnowledgeListVO;
import io.renren.app.form.KnowledgeListForm;
import io.renren.app.service.ChKnowledgeService;
import io.renren.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
 * date: 2020/3/16
 * description:
 */
@Api(tags = "知识接口")
@RestController
@RequestMapping("/app/knowledge")
public class KnowledgeController {
    private static final Logger log= LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    ChKnowledgeService knowledgeService;

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
}
