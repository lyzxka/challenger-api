package io.renren.app.service.impl;

import io.renren.app.entity.ChKnowledge;
import io.renren.app.dao.ChKnowledgeDao;
import io.renren.app.service.ChKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 知识资料 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChKnowledgeServiceImpl extends ServiceImpl<ChKnowledgeDao, ChKnowledge> implements ChKnowledgeService {

}
