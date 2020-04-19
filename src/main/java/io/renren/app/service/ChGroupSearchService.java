package io.renren.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.app.entity.ChGroupSearch;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.app.entity.vo.GroupSearchDetailVO;
import io.renren.app.entity.vo.GroupSearchListVO;

import java.util.Map;

/**
 * <p>
 * 组队请求 服务类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
public interface ChGroupSearchService extends IService<ChGroupSearch> {

    Page<GroupSearchListVO> selectGroupSearchForPage(Page page, Map map);
    GroupSearchDetailVO getGroupSearchDetail(Long groupSearchId);
}
