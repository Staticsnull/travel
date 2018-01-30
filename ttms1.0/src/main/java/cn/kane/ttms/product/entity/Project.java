package cn.kane.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.kane.ttms.common.web.JsonDateTypeConvert;

/**
 * 项目信息 实体对象 对应的表是：tms_projects
 * @author soft01
 *
 */
public class Project implements Serializable{
	private static final long serialVersionUID = 6574907259893147431L;
	//项目id，对应表中的主键值
	private Integer id;//Integer默认值是null
	/*项目名称*/
	private String name;
	//项目编码
	private String code;
	//开始日期
	private Date beginDate;
	//结束日期
	private Date endDate;
	//是否有效
	private Integer valid = 1;
	//备注
	private String note;
	//创建用户
	private String createdUser;
	//修改用户
	private String modifiedUser;
	//创建日期
	private Date createdTime;
	//修改日期
	private Date modifiedTime;
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", code=" + code + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", valid=" + valid + ", note=" + note + ", createdUser=" + createdUser + ", modifiedUser="
				+ modifiedUser + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	
	
}
