package io.renren.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.app.entity.Rescource;

/**
 * @Author: zhanglei
 * @Date: 2018/8/27 10:28
 * @Description:
 */
public interface RescourceService extends IService<Rescource> {

    public Rescource selectRescourceByHash(String hash);

}
