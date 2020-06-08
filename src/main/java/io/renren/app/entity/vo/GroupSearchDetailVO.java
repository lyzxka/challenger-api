package io.renren.app.entity.vo;

import io.renren.app.entity.ChGroupSearch;
import lombok.Data;

/**
 * auther: zzxka
 * date: 2020/4/16
 * description:
 */
@Data
public class GroupSearchDetailVO extends ChGroupSearch {
    private String userName;
    private String userIcon;
    private String matchName;
    private String groupNo;
    private String signUpStatus;
    private Long userId;
}
