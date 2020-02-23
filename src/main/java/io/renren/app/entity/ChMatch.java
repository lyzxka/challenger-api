package io.renren.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 比赛
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@TableName("ch_match")
public class ChMatch extends Model<ChMatch> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 标题
     */
	private String title;
    /**
     * 比赛编号
     */
	private String matchNo;
    /**
     * 主图
     */
	private String imgUrl;
    /**
     * 内容
     */
	private String content;
    /**
     * 门类
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
     * 浏览量
     */
	private Long views;
    /**
     * 开始时间
     */
	private Date beginDate;
    /**
     * 截止时间
     */
	private Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(String matchNo) {
		this.matchNo = matchNo;
	}
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "ChMatch{" +
			", id=" + id +
			", title=" + title +
			", matchNo=" + matchNo +
			", imgUrl=" + imgUrl +
			", content=" + content +
			", categoryId=" + categoryId +
			", createDate=" + createDate +
			", createBy=" + createBy +
			", updateDate=" + updateDate +
			", updateBy=" + updateBy +
			", remarks=" + remarks +
			", delFlag=" + delFlag +
			", views=" + views +
			", beginDate=" + beginDate +
			", endDate=" + endDate +
			"}";
	}

	@Override
	protected Serializable pkVal() {
        return this.id;
	}
}
