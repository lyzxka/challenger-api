package io.renren.app.service;

import io.renren.app.entity.ChGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.app.entity.vo.GroupListVO;

import java.util.List;

/**
 * <p>
 * 队伍 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
public interface ChGroupService extends IService<ChGroup> {
    List<GroupListVO> selectGroupList(String groupNo);
}
