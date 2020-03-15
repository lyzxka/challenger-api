package io.renren.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.vo.GroupSearchListVO;
import io.renren.app.form.GroupSearchForm;
import io.renren.app.service.ChGroupSearchService;
import io.renren.app.service.ChGroupService;
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
 * date: 2020/3/8
 * description:
 */
@Api(tags = "志友")
@RestController
@RequestMapping("/app/group")
public class GroupController {
    private static final Logger log= LoggerFactory.getLogger(GroupController.class);

    @Autowired
    ChGroupService groupService;
    @Autowired
    ChGroupSearchService groupSearchService;

    @ApiOperation("志友列表")
    @PostMapping("groupSearchList")
    public R groupSearchList(@RequestBody GroupSearchForm form){
        log.info("志友列表:{}",form.toString());
        Map param=new HashMap();
        if(StringUtils.isNotBlank(form.getMatchName())){
            param.put("matchName",form.getMatchName());
        }
        if(null!=form.getCategoryId()){
            param.put("categoryId",form.getCategoryId());
        }
        Page<GroupSearchListVO> page = groupSearchService.selectGroupSearchForPage(new Page(form.getPage(),form.getLimit()),param);
        return R.ok().put("data", page.getRecords()).put("pages", page.getPages());
    }
}
