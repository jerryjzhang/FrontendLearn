package com.ms.junz.domain;

import com.ms.junz.enums.ErrorConditionType;

public class ErrorCondition {
	private int id;
	private ErrorConditionType type;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ErrorConditionType getType() {
		return type;
	}
	public void setType(ErrorConditionType type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ErrorCondition [id=" + id + ", type=" + type + ", value="
				+ value + "]";
	}
	
}
