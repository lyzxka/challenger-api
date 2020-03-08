package io.renren.app.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChGroupSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.app.entity.vo.GroupSearchListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组队请求 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Mapper
public interface ChGroupSearchDao extends BaseMapper<ChGroupSearch> {

    List<GroupSearchListVO> selectGroupSearchForPage(Page page, @Param("param") Map map);

}
