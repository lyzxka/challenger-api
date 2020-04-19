package io.renren.app.service.impl;

import io.renren.app.entity.ChGroup;
import io.renren.app.dao.ChGroupDao;
import io.renren.app.entity.vo.GroupListVO;
import io.renren.app.service.ChGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 队伍 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChGroupServiceImpl extends ServiceImpl<ChGroupDao, ChGroup> implements ChGroupService {

    @Override
    public List<GroupListVO> selectGroupList(String groupNo) {
        return baseMapper.selectGroupList(groupNo);
    }
}
