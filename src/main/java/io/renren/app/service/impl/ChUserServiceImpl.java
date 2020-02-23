package io.renren.app.service.impl;

import io.renren.app.entity.ChUser;
import io.renren.app.dao.ChUserDao;
import io.renren.app.service.ChUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChUserServiceImpl extends ServiceImpl<ChUserDao, ChUser> implements ChUserService {

}
