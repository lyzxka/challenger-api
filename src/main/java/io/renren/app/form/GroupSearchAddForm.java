package io.renren.app.form;

public class GroupSearchAddForm {
    private String title;
    private Long categoryId;
    private Long matchId;
    private String content;
    private Integer userNum;
    private String groupName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    @Override
    public String toString() {
        return "GroupSearchAddForm{" +
                "title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", matchId=" + matchId +
                ", content='" + content + '\'' +
                ", userNum=" + userNum +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
