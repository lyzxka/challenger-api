package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * auther: zzxka
 * date: 2020/2/25
 * description:
 */
@ApiModel
@Data
public class PageForm {
    private Integer page=1;
    private Integer limit=10;
}
