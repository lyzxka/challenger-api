package io.renren.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 门类
 * </p>
 *
 * @author ${author}
 * @since 2020-02-23
 */
@TableName("ch_category")
public class ChCategory extends Model<ChCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 门类名称
     */
	private String categoryName;
    /**
     * 门类编号
     */
	private String categoryNo;
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
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
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
		return "ChCategory{" +
			", id=" + id +
			", categoryName=" + categoryName +
			", categoryNo=" + categoryNo +
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
