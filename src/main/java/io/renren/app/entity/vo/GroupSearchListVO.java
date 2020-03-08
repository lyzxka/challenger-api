package io.renren.app.entity.vo;

import io.renren.app.entity.ChGroupSearch;
import lombok.Data;
import lombok.ToString;

/**
 * auther: zzxka
 * date: 2020/3/8
 * description:
 */
@Data
@ToString
public class GroupSearchListVO  extends ChGroupSearch {
    private String userIcon;
    private String userName;
    private String matchName;
}
