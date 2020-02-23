package io.renren.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 队伍
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@TableName("ch_group")
public class ChGroup extends Model<ChGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 队伍编号
     */
	private String groupNo;
    /**
     * 队名
     */
	private String groupName;
    /**
     * 用户
     */
	private Long userId;
    /**
     * 身份
     */
	private String userRole;
    /**
     * 比赛
     */
	private Long matchId;
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
    /**
     * 状态
     */
	private String status;

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
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ChGroup{" +
			", id=" + id +
			", groupNo=" + groupNo +
			", groupName=" + groupName +
			", userId=" + userId +
			", userRole=" + userRole +
			", matchId=" + matchId +
			", categoryId=" + categoryId +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", remarks=" + remarks +
			", delFlag=" + delFlag +
			", status=" + status +
			"}";
	}

	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}
