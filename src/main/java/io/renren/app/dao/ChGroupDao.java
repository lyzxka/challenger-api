package io.renren.app.dao;

import io.renren.app.entity.ChGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.app.entity.vo.GroupListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 队伍 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Mapper
public interface ChGroupDao extends BaseMapper<ChGroup> {
    List<GroupListVO> selectGroupList(String groupNo);
}
