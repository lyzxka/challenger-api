package io.renren.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 组队请求
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@TableName("ch_group_search")
public class ChGroupSearch extends Model<ChGroupSearch> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 队伍
     */
	private String groupNo;
    /**
     * 标题
     */
	private String title;
    /**
     * 比赛
     */
	private Long matchId;
    /**
     * 详情
     */
	private String content;
    /**
     * 成员人数
     */
	private Integer userNum;
    /**
     * 类别
     */
	private Long categoryId;
    /**
     * 创建时间
     */
	private Date createDate;
    /**
     * 创建人
     */
	private Long createBy;
    /**
     * 修改时间
     */
	private Date updateDate;
    /**
     * 修改人
     */
	private Long updateBy;
    /**
     * 备注
     */
	private String remarks;
    /**
     * 删除标记
     */
	private Integer delFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}


	@Override
	public String toString() {
		return "ChGroupSearch{" +
			", id=" + id +
			", groupNo=" + groupNo +
			", title=" + title +
			", matchId=" + matchId +
			", content=" + content +
			", userNum=" + userNum +
			", categoryId=" + categoryId +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", remarks=" + remarks +
			", delFlag=" + delFlag +
			"}";
	}

	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}
