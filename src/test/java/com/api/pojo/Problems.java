package com.api.pojo;

public class Problems {

	private String id;
	private String remark;
	public Problems(String id, String remark) {
		super();
		this.id = id;
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Problems [id=" + id + ", remark=" + remark + "]";
	}
	
	
}
