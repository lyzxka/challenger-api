package io.renren.app.service.impl;

import io.renren.app.entity.ChGroupSearch;
import io.renren.app.dao.ChGroupSearchDao;
import io.renren.app.service.ChGroupSearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 组队请求 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChGroupSearchServiceImpl extends ServiceImpl<ChGroupSearchDao, ChGroupSearch> implements ChGroupSearchService {

}
