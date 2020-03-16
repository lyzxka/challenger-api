package io.renren.app.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.app.entity.vo.KnowledgeListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识资料 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Mapper
public interface ChKnowledgeDao extends BaseMapper<ChKnowledge> {
    List<KnowledgeListVO> selectKnowledgeForPage(@Param("param") Map map, Page page);
}
