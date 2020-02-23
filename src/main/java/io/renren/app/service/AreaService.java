package io.renren.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.app.entity.Area;

import java.util.List;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 14:13
 * @Description: 省市service
 */
public interface AreaService extends IService<Area> {

    public List<Area> selectAllProvince();

    public List<Area> selectCityByParentId(String id);

    public Area selectAreaById(String id);
}
