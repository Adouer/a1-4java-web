package com.adouer.a14javaweb.entity;

import java.io.Serializable;

public class NewsType implements Serializable {

	private Integer typeid;
	private String typename;
	
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	
	
}
