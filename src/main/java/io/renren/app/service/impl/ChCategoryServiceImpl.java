package io.renren.app.service.impl;

import io.renren.app.entity.ChCategory;
import io.renren.app.dao.ChCategoryDao;
import io.renren.app.service.ChCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 门类 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChCategoryServiceImpl extends ServiceImpl<ChCategoryDao, ChCategory> implements ChCategoryService {

}
