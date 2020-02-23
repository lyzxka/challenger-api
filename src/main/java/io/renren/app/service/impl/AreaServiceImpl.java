package io.renren.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.app.dao.AreaDao;
import io.renren.app.entity.Area;
import io.renren.app.service.AreaService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 14:19
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {

    @Cacheable(value = "allProvince",unless = "#result == null or #result.size() == 0")
    @Override
    public List<Area> selectAllProvince() {
        return baseMapper.selectAllProvince();
    }

    @Cacheable(value = "allCityById",unless = "#result == null or #result.size() == 0")
    @Override
    public List<Area> selectCityByParentId(String id) {
        return baseMapper.selectCityByParentId(id);
    }


    @Cacheable(value = "selectAreaById",unless = "#result == null")
    @Override
    public Area selectAreaById(String id) {
        return baseMapper.selectAreaById(id);
    }

}
