package io.renren.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.app.entity.Area;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 14:33
 * @Description:
 */
@Mapper
public interface AreaDao extends BaseMapper<Area> {

    /**
     * 获取所有的省份
     * @return
     */
    List<Area> selectAllProvince();

    /**
     * 获取市
     * @return
     */
    List<Area> selectCityByParentId(String id);

    /**
     * 获取市
     * @return
     */
    Area selectAreaById(String id);

}
