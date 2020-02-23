package io.renren.app.service.impl;

import io.renren.app.entity.ChMatch;
import io.renren.app.dao.ChMatchDao;
import io.renren.app.service.ChMatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 比赛 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChMatchServiceImpl extends ServiceImpl<ChMatchDao, ChMatch> implements ChMatchService {

}
