package io.renren.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.app.entity.vo.KnowledgeListVO;

import java.util.Map;

/**
 * <p>
 * 知识资料 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
public interface ChKnowledgeService extends IService<ChKnowledge> {
    Page<KnowledgeListVO> selectKnowledgeForPage(Map map, Page page);
}
