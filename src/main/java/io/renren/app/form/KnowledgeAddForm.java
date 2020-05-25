package io.renren.app.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class KnowledgeAddForm {
    @ApiModelProperty("标题")
    @NotNull(message = "标题信息不能为空")
    private String title;
    @ApiModelProperty("内容")
    @NotNull(message = "内容信息不能为空")
    private String content;
    @ApiModelProperty("门类id")
    @NotNull(message = "门类信息不能为空")
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "KnowledgeAddForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
