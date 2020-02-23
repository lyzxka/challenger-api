package io.renren.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.app.dao.RescourceDao;
import io.renren.app.entity.Rescource;
import io.renren.app.service.RescourceService;
import org.springframework.stereotype.Service;


@Service("rescourceService")
public class RescourceServiceImpl extends ServiceImpl<RescourceDao, Rescource> implements RescourceService {

    @Override
    public Rescource selectRescourceByHash(String hash) {
        return baseMapper.selectOne(new QueryWrapper<Rescource>().eq("hash",hash));
    }

}
