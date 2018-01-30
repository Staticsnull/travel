package cn.kane.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.kane.ttms.common.web.JsonDateTypeConvert;
/**
 *  用于封装团信息：
 *  业务：一个项目可以有多个团， 关系：one to many
 *  表设计：关联字段是projectId 应该添加在tms_teams 多的一端
 * @author soft01
 *
 */
public class Team implements Serializable {
	private static final long serialVersionUID = 368009064732092303L;
	//团id
	private Integer id;
	//团名称
	private String name;
	//项目id
	private Integer projectId;
	//状态：1 启用，0禁用
	private Integer valid = 1; 
	//备注
	private String note;
	//创建时间
	private Date createdTime;
	//修改时间
	private Date modifiedTime;
	//创建用户
	private String createdUser;
	//修改用户
	private String modifiedUser;
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	//记住这个注释
	@JsonSerialize(using=JsonDateTypeConvert.class)
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
