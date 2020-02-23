package io.renren.app.dao;

import io.renren.app.entity.ChUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@Mapper
public interface ChUserDao extends BaseMapper<ChUser> {

}
