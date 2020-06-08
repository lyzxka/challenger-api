package io.renren.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.annotation.Login;
import io.renren.app.entity.ChGroup;
import io.renren.app.entity.ChGroupSearch;
import io.renren.app.entity.ChMatch;
import io.renren.app.entity.ChUser;
import io.renren.app.entity.vo.GroupListVO;
import io.renren.app.entity.vo.GroupSearchDetailVO;
import io.renren.app.entity.vo.GroupSearchListVO;
import io.renren.app.form.GroupSearchAddForm;
import io.renren.app.form.GroupSearchListForm;
import io.renren.app.form.ObjectIdForm;
import io.renren.app.service.ChGroupSearchService;
import io.renren.app.service.ChGroupService;
import io.renren.app.service.ChMatchService;
import io.renren.app.service.ChUserService;
import io.renren.app.utils.OrderNumberUtil;
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
import java.util.List;
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
    @Autowired
    ChUserService userService;
    @Autowired
    ChMatchService matchService;

    @ApiOperation("志友列表")
    @PostMapping("groupSearchList")
    public R groupSearchList(@RequestBody GroupSearchListForm form){
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
    @Login
    @ApiOperation("我的比赛")
    @PostMapping("groupSearchListOfMy")
    public R groupSearchListOfMy(@RequestAttribute("userId")Long userId,@RequestBody GroupSearchListForm form){
        log.info("我的比赛:{}",form.toString());
        Map param=new HashMap();
        param.put("userId",userId);
        Page<GroupSearchListVO> page = groupSearchService.selectGroupSearchForPage(new Page(form.getPage(),form.getLimit()),param);
        return R.ok().put("data", page.getRecords()).put("pages", page.getPages());
    }

    @Login
    @ApiOperation("志友组队详情")
    @PostMapping("groupSearchDetail")
    public R groupSearchDetail(@RequestAttribute("userId")Long userId,@RequestBody ObjectIdForm form){
        log.info("志友组队详情:{}",form.getObjectId());
        ValidatorUtils.validateEntity(form);
        GroupSearchDetailVO groupSearch=groupSearchService.getGroupSearchDetail(form.getObjectId());
        if(null==groupSearch){
            return R.error("组队详情信息不存在");
        }
        ChGroup myGroupInfo=groupService.getOne(new QueryWrapper<ChGroup>().eq("user_id",userId).eq("group_no",groupSearch.getGroupNo()));
        if(null==myGroupInfo){
            groupSearch.setSignUpStatus("5");
        }else{
            groupSearch.setSignUpStatus(myGroupInfo.getStatus());
        }
        List<GroupListVO> groupList=groupService.selectGroupList(groupSearch.getGroupNo());
        Map data=new HashMap();
        data.put("groupSearch",groupSearch);
        data.put("groupList",groupList);

        return R.ok(data);
    }

    @Login
    @ApiOperation("创建组队招募")
    @PostMapping("groupCreate")
    public R groupCreate(@RequestAttribute("userId")Long userId, @RequestBody GroupSearchAddForm form){
        log.info("创建组队招募:{}",form.toString());
        ChMatch match=matchService.getById(form.getMatchId());
        if(null==match){
            return R.error("比赛信息不存在");
        }
        ChGroup group=groupService.getOne(new LambdaQueryWrapper<ChGroup>().eq(ChGroup::getUserId,userId).eq(ChGroup::getMatchId,form.getMatchId()).notIn(ChGroup::getStatus,"3","4"));
        if(null!=group){
            return R.error("您已参与本比赛");
        }
        group=new ChGroup();
        group.setGroupNo(OrderNumberUtil.priStrID());
        group.setDelFlag(0);
        group.setCreateDate(new Date());
        group.setUserRole("1");
        group.setUserId(userId);
        group.setGroupName(form.getGroupName());
        group.setStatus("2");
        group.setCategoryId(match.getCategoryId());
        group.setMatchId(form.getMatchId());
        groupService.save(group);
        ChGroupSearch groupSearch=new ChGroupSearch();
        groupSearch.setCreateDate(new Date());
        groupSearch.setCategoryId(match.getCategoryId());
        groupSearch.setContent(form.getContent());
        groupSearch.setTitle(form.getTitle());
        groupSearch.setDelFlag(0);
        groupSearch.setUserNum(form.getUserNum());
        groupSearch.setMatchId(match.getId());
        groupSearch.setGroupNo(group.getGroupNo());
        groupSearchService.save(groupSearch);
        return R.ok();
    }

    @Login
    @ApiOperation("报名或者取消报名")
    @PostMapping("signUpOrCancelGroupSearch")
    public R signUpOrCancelGroupSearch(@RequestAttribute("userId")Long userId,@RequestBody ObjectIdForm form){
        log.info("报名或者取消报名:{}",form.getObjectId());
        ChUser user=userService.getById(userId);
        if(null==user){
            return R.error("用户信息不存在");
        }
        ChGroupSearch groupSearch=groupSearchService.getById(form.getObjectId());
        if(null==groupSearch){
            return R.error("志友组队信息不存在");
        }
        ChGroup group=groupService.getOne(new QueryWrapper<ChGroup>().eq("user_role",1).eq("group_no",groupSearch.getGroupNo()));
        if(null==group){
            return R.error("组队信息不存在");
        }
        ChGroup myGroupInfo=groupService.getOne(new QueryWrapper<ChGroup>().eq("user_id",userId).eq("group_no",groupSearch.getGroupNo()));
        if(null==myGroupInfo){
            group.setUserId(userId);
            group.setStatus("0");
            group.setId(null);
            group.setUserRole("2");
            group.setCreateDate(new Date());
            groupService.save(group);
            return R.ok("报名成功，请耐心等待队长审核").put("status",myGroupInfo.getStatus());
        }
        if(myGroupInfo.getStatus().equals("1")){
            myGroupInfo.setStatus("4");
            groupService.updateById(myGroupInfo);
            return R.ok("取消报名成功").put("status",myGroupInfo.getStatus());
        }else if(myGroupInfo.getStatus().equals("2")||myGroupInfo.getStatus().equals("3")){
            return R.ok("报名请求已被队长处理，不可再次进行操作").put("status",myGroupInfo.getStatus());
        }else {
            myGroupInfo.setStatus("1");
            groupService.updateById(myGroupInfo);
            return R.ok("报名成功，请耐心等待队长审核").put("status",myGroupInfo.getStatus());
        }
    }

}
