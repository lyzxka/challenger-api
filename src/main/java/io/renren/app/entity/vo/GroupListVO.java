package io.renren.app.entity.vo;

import io.renren.app.entity.ChGroup;
import lombok.Data;

/**
 * auther: zzxka
 * date: 2020/4/17
 * description:
 */
@Data
public class GroupListVO extends ChGroup {
    private String userName;
    private String userIcon;
}
