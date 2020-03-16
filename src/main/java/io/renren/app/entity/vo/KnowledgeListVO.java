package io.renren.app.entity.vo;

import io.renren.app.entity.ChKnowledge;
import lombok.Data;

/**
 * auther: zzxka
 * date: 2020/3/16
 * description:
 */
@Data
public class KnowledgeListVO extends ChKnowledge {
    private String userName;
    private String categoryName;
}
